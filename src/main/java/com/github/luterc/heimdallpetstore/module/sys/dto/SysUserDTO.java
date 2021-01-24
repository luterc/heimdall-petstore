package com.github.luterc.heimdallpetstore.module.sys.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * The type Sys user dto.
 *
 * @author Luter
 */
@Data
@Accessors(chain = true)
public class SysUserDTO implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * The Username.
     */
    private String username;
    /**
     * The Password.
     */
    private String password;
    /**
     * The Enabled.
     */
    private Boolean enabled;
    /**
     * The Roles.
     */
    private List<SysRoleDTO> roles;
}
