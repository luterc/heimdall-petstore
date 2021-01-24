package com.github.luterc.heimdallpetstore.module.sys.repository;

import com.github.luterc.heimdallpetstore.module.sys.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Sys role repository.
 *
 * @author Luter
 */
public interface SysRoleRepository extends JpaRepository<SysRoleEntity, String>, JpaSpecificationExecutor<SysRoleEntity> {
}
