package com.github.luterc.heimdallpetstore.module.sys.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限资源表
 *
 * @author Luter
 */
@Entity
@Table(name = "sys_resource")
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Table(comment = "权限资源表", appliesTo = "sys_resource")
@Data
@Accessors(chain = true)
public class SysResourceEntity implements Serializable {
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
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * The Method.
     */
    @Column(name = "method", nullable = false)
    private String method;
    /**
     * The Url.
     */
    @Column(name = "url", nullable = false)
    private String url;
}
