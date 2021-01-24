package com.github.luterc.heimdallpetstore.module.sys.dto;

import com.github.luterc.heimdallpetstore.module.sys.entity.SysResourceEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * The type Sys role dto.
 *
 * @author Luter
 */
@Data
@Accessors(chain = true)
public class SysRoleDTO implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Description.
     */
    private String description;

    /**
     * 角色资源关系表
     */
    private List<SysResourceDTO> resources;
}
