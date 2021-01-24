package com.github.luterc.heimdallpetstore.module.sys.service;

import com.github.luterc.heimdallpetstore.module.sys.dto.SysRoleDTO;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysRoleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Sys role service.
 *
 * @author Luter
 */
public interface SysRoleService {

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    SysRoleDTO getById(String id);

    /**
     * Save sys role dto.
     *
     * @param param the param
     * @return the sys role dto
     */
    SysRoleDTO save(SysRoleVO param);

    /**
     * Update sys role dto.
     *
     * @param param the param
     * @return the sys role dto
     */
    SysRoleDTO update(SysRoleVO param);

    /**
     * List page.
     *
     * @param page  the page
     * @param param the param
     * @return the page
     */
    Page<SysRoleDTO> list(Pageable page, SysRoleVO param);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(String id);
}
