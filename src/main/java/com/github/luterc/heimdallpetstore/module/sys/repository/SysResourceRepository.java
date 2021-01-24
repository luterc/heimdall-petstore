package com.github.luterc.heimdallpetstore.module.sys.repository;

import com.github.luterc.heimdallpetstore.module.sys.entity.SysResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Sys resource repository.
 *
 * @author Luter
 */
public interface SysResourceRepository extends JpaRepository<SysResourceEntity, String>, JpaSpecificationExecutor<SysResourceEntity> {
}
