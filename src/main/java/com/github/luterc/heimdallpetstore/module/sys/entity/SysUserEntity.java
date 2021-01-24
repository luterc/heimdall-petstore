package com.github.luterc.heimdallpetstore.module.sys.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 *
 * @author Luter
 */
@Entity
@Table(name = "sys_user")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(comment = "用户表", appliesTo = "sys_user")
@Data
@Accessors(chain = true)
public class SysUserEntity implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    /**
     * The Username.
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    /**
     * The Password.
     */
    @Column(name = "password", nullable = false)
    private String password;
    /**
     * The Enabled.
     */
    @Column(name = "is_enabled", nullable = false, columnDefinition = "boolean default true")
    private Boolean enabled;
    /**
     * 用户角色关联关系中间表
     */
    @ManyToMany
    @JoinTable(
            name = "sys_role_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<SysRoleEntity> roles;
}
