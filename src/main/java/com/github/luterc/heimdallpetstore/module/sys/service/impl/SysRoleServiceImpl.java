package com.github.luterc.heimdallpetstore.module.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.luterc.heimdallpetstore.module.sys.dto.SysRoleDTO;
import com.github.luterc.heimdallpetstore.module.sys.entity.SysRoleEntity;
import com.github.luterc.heimdallpetstore.module.sys.mapper.SysResourceMapper;
import com.github.luterc.heimdallpetstore.module.sys.mapper.SysRoleMapper;
import com.github.luterc.heimdallpetstore.module.sys.repository.SysRoleRepository;
import com.github.luterc.heimdallpetstore.module.sys.service.SysRoleService;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysResourceVO;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysRoleVO;
import com.luter.heimdall.core.session.dao.SessionDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Sys role service.
 *
 * @author Luter
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService {
    /**
     * The Sys role repository.
     */
    @Autowired
    private SysRoleRepository sysRoleRepository;

    /**
     * The Sys role mapper.
     */
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public SysRoleDTO getById(String id) {
        final Optional<SysRoleEntity> data = sysRoleRepository.findById(id);
        if (data.isPresent()) {
            return sysRoleMapper.toDto(data.get());
        } else {
            throw new IllegalArgumentException("未找到");
        }
    }

    @Override
    public SysRoleDTO save(SysRoleVO param) {
        final SysRoleEntity toSave = sysRoleMapper.voToEntity(param);
        final List<SysResourceVO> resources = param.getResources();
        toSave.setResources(sysResourceMapper.voListToEntityList(resources));
        final SysRoleEntity saved = sysRoleRepository.saveAndFlush(toSave);
        return sysRoleMapper.toDto(saved);
    }

    @Override
    public SysRoleDTO update(SysRoleVO param) {
        final Optional<SysRoleEntity> toUpdate = sysRoleRepository.findById(param.getId());
        if (toUpdate.isPresent()) {
            final SysRoleEntity toUpdated = toUpdate.get();
            toUpdated.setName(param.getName());
            toUpdated.setDescription(param.getDescription());
            if(null!=param.getResources()&&!param.getResources().isEmpty()){
                toUpdated.setResources(sysResourceMapper.voListToEntityList(param.getResources()));
            }
            final SysRoleEntity updated = sysRoleRepository.saveAndFlush(toUpdated);
            //角色权限资源发生变动，清理所有用户权限缓存
            //这里严谨一些，应该判断一下，是不是有资源发生变动，如果角色资源没变动，则不需要清理
            sessionDAO.clearAllUserAuthorities();
            return sysRoleMapper.toDto(updated);
        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }

    @Override
    public Page<SysRoleDTO> list(Pageable page, SysRoleVO param) {
        Page<SysRoleEntity> pageData = sysRoleRepository.findAll((Specification<SysRoleEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StrUtil.isNotEmpty(param.getName())) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + param.getName() + "%")
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
        return pageData.map(entity -> sysRoleMapper.toDto(entity));
    }

    @Override
    public void deleteById(String id) {
        final Optional<SysRoleEntity> toDelete = sysRoleRepository.findById(id);
        if (toDelete.isPresent()) {
            sysRoleRepository.delete(toDelete.get());
            sessionDAO.clearAllUserAuthorities();
        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }
}
