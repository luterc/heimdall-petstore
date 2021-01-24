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
 * The type Sys role entity.
 *
 * @author Luter
 */
@Entity
@Table(name = "sys_role")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(comment = "角色表", appliesTo = "sys_role")
@Data
@Accessors(chain = true)
public class SysRoleEntity implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    /**
     * The Name.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    /**
     * The Description.
     */
    private String description;

    /**
     * 角色资源关系表
     */
    @ManyToMany
    @JoinTable(
            name = "sys_role_resource",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
    private List<SysResourceEntity> resources;
}
