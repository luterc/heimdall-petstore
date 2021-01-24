package com.github.luterc.heimdallpetstore.module.sys.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type Sys role vo.
 *
 * @author Luter
 */
@Data
@Accessors(chain = true)
public class SysResourceDTO implements Serializable {
    /**
     * The Id.
     */
    private String id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Method.
     */
    private String method;
    /**
     * The Url.
     */
    private String url;
}
