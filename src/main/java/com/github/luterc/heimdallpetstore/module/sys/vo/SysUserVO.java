package com.github.luterc.heimdallpetstore.module.sys.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * The type Sys user vo.
 *
 * @author Luter
 */
@Data
@Accessors(chain = true)
public class SysUserVO implements Serializable {
    /**
     * The Id.
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
    private List<SysRoleVO> roles;
}
