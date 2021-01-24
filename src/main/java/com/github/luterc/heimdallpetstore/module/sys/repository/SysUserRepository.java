package com.github.luterc.heimdallpetstore.module.sys.repository;

import com.github.luterc.heimdallpetstore.module.sys.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Sys user repository.
 *
 * @author Luter
 */
public interface SysUserRepository extends JpaRepository<SysUserEntity, String>, JpaSpecificationExecutor<SysUserEntity> {

    /**
     * 通过用户名查找用户
     *
     * @param username the username
     * @return the sys user entity
     */
    SysUserEntity findSysUserEntityByUsername(String username);
}
