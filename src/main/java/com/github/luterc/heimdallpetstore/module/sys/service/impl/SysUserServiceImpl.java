package com.github.luterc.heimdallpetstore.module.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.github.luterc.heimdallpetstore.module.security.AppUserDetails;
import com.github.luterc.heimdallpetstore.module.sys.dto.SysUserDTO;
import com.github.luterc.heimdallpetstore.module.sys.entity.SysUserEntity;
import com.github.luterc.heimdallpetstore.module.sys.mapper.SysRoleMapper;
import com.github.luterc.heimdallpetstore.module.sys.mapper.SysUserMapper;
import com.github.luterc.heimdallpetstore.module.sys.repository.SysUserRepository;
import com.github.luterc.heimdallpetstore.module.sys.service.SysUserService;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysUserVO;
import com.luter.heimdall.core.exception.HeimdallException;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.core.session.dao.SessionDAO;
import com.luter.heimdall.core.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * The type Sys user service.
 *
 * @author Luter
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

    /**
     * The Sys user repository.
     */
    @Autowired
    private SysUserRepository sysUserRepository;
    /**
     * The Sys user mapper.
     */
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * The Sys role mapper.
     */
    @Autowired
    private SysRoleMapper sysRoleMapper;

    ///////登录注销

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * SessionDao 注入
     */
    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public SysUserDTO getById(String id) {
        final Optional<SysUserEntity> data = sysUserRepository.findById(id);
        if (data.isPresent()) {
            return sysUserMapper.toDto(data.get());
        } else {
            throw new IllegalArgumentException("未找到");
        }
    }

    @Override
    public SysUserDTO save(SysUserVO param) {
        final SysUserEntity toSave = sysUserMapper.voToEntity(param);
        //密码加密
        toSave.setPassword(BCrypt.hashpw(param.getPassword()));
        //设置状态
        toSave.setEnabled(true);
        //角色转换
        toSave.setRoles(sysRoleMapper.voListToEntityList(param.getRoles()));
        final SysUserEntity saved = sysUserRepository.saveAndFlush(toSave);
        return sysUserMapper.toDto(saved);
    }

    @Override
    public SysUserDTO update(SysUserVO param) {
        final Optional<SysUserEntity> toUpdate = sysUserRepository.findById(param.getId());
        if (toUpdate.isPresent()) {
            final SysUserEntity sysUserEntity = toUpdate.get();
            if (StrUtils.isNotBlank(param.getPassword())) {
                sysUserEntity.setPassword(BCrypt.hashpw(param.getPassword()));
            }
            if (null != param.getRoles() && !param.getRoles().isEmpty()) {
                sysUserEntity.setRoles(sysRoleMapper.voListToEntityList(param.getRoles()));
            }
            final SysUserEntity sysUserEntity1 = sysUserRepository.saveAndFlush(sysUserEntity);
            ////用户角色变化了，清理缓存的用户权限，这样在用户下次访问系统的时候会重新加载权限
            //严谨一些，应该判断一下是不是有角色变动，没变动就不清理。
            sessionDAO.clearAllUserAuthorities();
            return sysUserMapper.toDto(sysUserEntity1);
        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }

    @Override
    public Page<SysUserDTO> list(Pageable page, SysUserVO param) {
        Page<SysUserEntity> pageData = sysUserRepository.findAll((Specification<SysUserEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StrUtil.isNotEmpty(param.getUsername())) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("username"), "%" + param.getUsername() + "%")
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
        return pageData.map(entity -> sysUserMapper.toDto(entity));
    }

    @Override
    public void deleteById(String id) {
        final Optional<SysUserEntity> toDelete = sysUserRepository.findById(id);
        if (toDelete.isPresent()) {
            sysUserRepository.delete(toDelete.get());
        } else {
            throw new IllegalArgumentException("参数错误");
        }

    }

    @Override
    public SimpleSession login(SysUserVO param) {
        if (StrUtils.isBlank(param.getUsername()) || StrUtils.isBlank(param.getPassword())) {
            throw new HeimdallException("用户名或者密码不能为空");
        }
        //看看数据库里有没有这个用户
        final SysUserEntity sysUserEntityByUsername = sysUserRepository.findSysUserEntityByUsername(param.getUsername());
        if (null == sysUserEntityByUsername) {
            throw new HeimdallException("用户名或者密码错误:None");
        }

        //看看密码对不对
        if (!BCrypt.checkpw(param.getPassword(), sysUserEntityByUsername.getPassword())) {
            throw new HeimdallException("密码错误");
        }
        //转成 DTO
        final SysUserDTO sysUserDTO = sysUserMapper.toDto(sysUserEntityByUsername);
        //把角色信息置空，免得数据太大，反正缓存也用不上。
        sysUserDTO.setRoles(null);
        //构造登录信息
        AppUserDetails userDetails = new AppUserDetails(sysUserDTO);
        //登录
        return authenticationManager.login(userDetails);
    }

    @Override
    public SimpleSession logout() {
        return authenticationManager.logout();
    }

    @Override
    public SysUserDTO currentUser(boolean fromDb) {
        final SimpleSession currentUser = authenticationManager.getCurrentUser();
        final AppUserDetails details = (AppUserDetails) currentUser.getDetails();
        final SysUserDTO user = details.getUser();
        if (fromDb) {
            return getById(user.getId());
        }
        return user;
    }

    @Override
    public Collection<SimpleSession> onlineUsers() {
        return sessionDAO.getActiveSessions();
    }

    @Override
    public boolean kickOutSession(String sessionId) {
        return authenticationManager.kickOutSession(sessionId);
    }

    @Override
    public boolean kickOutPrincipal(String principal) {
        return authenticationManager.kickOutPrincipal(principal);
    }
}
