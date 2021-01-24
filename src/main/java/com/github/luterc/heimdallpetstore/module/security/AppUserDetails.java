
package com.github.luterc.heimdallpetstore.module.security;

import com.github.luterc.heimdallpetstore.module.sys.dto.SysUserDTO;
import com.luter.heimdall.core.details.DefaultSimpleUserDetails;
import com.luter.heimdall.core.details.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 自定义用户详情实体
 *
 * @author Luter
 * @see UserDetails
 * @see DefaultSimpleUserDetails
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
public class AppUserDetails implements UserDetails {
    /**
     * The User.
     */
    private SysUserDTO user;
    /**
     * The Principal.
     */
    private String principal;

    /**
     * Instantiates a new Default user detail.
     *
     * @param user the user
     */
    public AppUserDetails(SysUserDTO user) {
        this.user = user;
    }

    @Override
    public String getPrincipal() {
        return "APP:" + user.getId();
    }

    @Override
    public boolean enabled() {
        return user.getEnabled();
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public SysUserDTO getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(SysUserDTO user) {
        this.user = user;
    }

    /**
     * Sets principal.
     *
     * @param principal the principal
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }


}
