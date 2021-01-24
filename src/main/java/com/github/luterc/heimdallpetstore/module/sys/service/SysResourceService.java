package com.github.luterc.heimdallpetstore.module.sys.service;

import com.github.luterc.heimdallpetstore.module.sys.dto.SysResourceDTO;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysResourceVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The interface Sys resource service.
 *
 * @author Luter
 */
public interface SysResourceService {

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    SysResourceDTO getById(String id);

    /**
     * Save sys resource dto.
     *
     * @param param the param
     * @return the sys resource dto
     */
    SysResourceDTO save(SysResourceVO param);

    /**
     * Update sys resource dto.
     *
     * @param param the param
     * @return the sys resource dto
     */
    SysResourceDTO update(SysResourceVO param);

    /**
     * List page.
     *
     * @param page  the page
     * @param param the param
     * @return the page
     */
    Page<SysResourceDTO> list(Pageable page, SysResourceVO param);


    /**
     * List all list.
     *
     * @return the list
     */
    List<SysResourceDTO> listAll();

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(String id);
}
