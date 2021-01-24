package com.github.luterc.heimdallpetstore.module.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.luterc.heimdallpetstore.module.sys.dto.SysResourceDTO;
import com.github.luterc.heimdallpetstore.module.sys.entity.SysResourceEntity;
import com.github.luterc.heimdallpetstore.module.sys.mapper.SysResourceMapper;
import com.github.luterc.heimdallpetstore.module.sys.repository.SysResourceRepository;
import com.github.luterc.heimdallpetstore.module.sys.service.SysResourceService;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysResourceVO;
import com.luter.heimdall.core.authorization.dao.AuthorizationMetaDataCacheDao;
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
 * The type Sys resource service.
 *
 * @author Luter
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysResourceServiceImpl implements SysResourceService {
    /**
     * The Sys resource repository.
     */
    @Autowired
    private SysResourceRepository sysResourceRepository;
    /**
     * The Sys resource mapper.
     */
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private AuthorizationMetaDataCacheDao authorizationMetaDataCacheDao;

    @Override
    public SysResourceDTO getById(String id) {
        final Optional<SysResourceEntity> data = sysResourceRepository.findById(id);
        if (data.isPresent()) {
            return sysResourceMapper.toDto(data.get());
        } else {
            throw new IllegalArgumentException("未找到");
        }
    }

    @Override
    public SysResourceDTO save(SysResourceVO param) {
        final SysResourceEntity toSave = sysResourceMapper.voToEntity(param);
        final SysResourceEntity saved = sysResourceRepository.saveAndFlush(toSave);
        //新增加了系统权限，将缓存的系统权限清空，便于下次访问重新加载生效
        authorizationMetaDataCacheDao.clearSysAuthorities();
        return sysResourceMapper.toDto(saved);
    }

    @Override
    public SysResourceDTO update(SysResourceVO param) {
        final Optional<SysResourceEntity> toUpdate = sysResourceRepository.findById(param.getId());
        if (toUpdate.isPresent()) {
            final SysResourceEntity toUpdated = toUpdate.get();
            toUpdated.setName(param.getName());
            toUpdated.setUrl(param.getUrl());
            toUpdated.setMethod(param.getMethod());
            final SysResourceEntity updated = sysResourceRepository.saveAndFlush(toUpdated);
            //修改了系统权限，将缓存的系统权限清空，便于下次访问重新加载生效
            //严谨一些，此处应该判断一下修改的信息是否对拦截规则有影响
            //没有影响则可以不用清理缓存
            authorizationMetaDataCacheDao.clearSysAuthorities();
            return sysResourceMapper.toDto(updated);
        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }

    @Override
    public Page<SysResourceDTO> list(Pageable page, SysResourceVO param) {
        Page<SysResourceEntity> pageData = sysResourceRepository.findAll((Specification<SysResourceEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StrUtil.isNotEmpty(param.getName())) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + param.getName() + "%")
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
        return pageData.map(entity -> sysResourceMapper.toDto(entity));
    }

    @Override
    public List<SysResourceDTO> listAll() {
        final List<SysResourceEntity> all = sysResourceRepository.findAll();
        return sysResourceMapper.entityListToDTOList(all);
    }

    @Override
    public void deleteById(String id) {
        final Optional<SysResourceEntity> toDelete = sysResourceRepository.findById(id);
        if (toDelete.isPresent()) {
            sysResourceRepository.delete(toDelete.get());
            //删除了系统权限，将缓存的系统权限清空，便于下次访问重新加载生效
            authorizationMetaDataCacheDao.clearSysAuthorities();
        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }
}
