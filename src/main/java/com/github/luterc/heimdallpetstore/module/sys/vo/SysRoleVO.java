package com.github.luterc.heimdallpetstore.module.sys.vo;

import com.github.luterc.heimdallpetstore.module.sys.dto.SysResourceDTO;
import com.github.luterc.heimdallpetstore.module.sys.entity.SysResourceEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * The type Sys role vo.
 *
 * @author Luter
 */
@Data
@Accessors(chain = true)
public class SysRoleVO implements Serializable {
    /**
     * The Id.
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

    private List<SysResourceVO> resources;
}
