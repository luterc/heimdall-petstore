package com.github.luterc.heimdallpetstore.module.sys.controller;

import com.github.luterc.heimdallpetstore.module.sys.dto.SysRoleDTO;
import com.github.luterc.heimdallpetstore.module.sys.service.SysRoleService;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The type Sys role controller.
 *
 * @author Luter
 */
@RestController
@Slf4j
@RequestMapping("/sys/roles")
@Api(tags = "角色管理")
public class SysRoleController {
    /**
     * The Sys role service.
     */
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * Save sys role dto.
     *
     * @param param the param
     * @return the sys role dto
     */
    @PostMapping
    @ApiOperation("新增")
    public SysRoleDTO save(@RequestBody @Validated SysRoleVO param) {
        return sysRoleService.save(param);
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseEntity<String> delete(@PathVariable String id) {
        sysRoleService.deleteById(id);
        return ResponseEntity.ok("Success");
    }

    /**
     * Update sys role dto.
     *
     * @param param the param
     * @return the sys role dto
     */
    @PutMapping
    @ApiOperation("修改")
    public SysRoleDTO update(@RequestBody @Validated SysRoleVO param) {
        return sysRoleService.update(param);
    }

    /**
     * List page.
     *
     * @param param the param
     * @param page  the page
     * @return the page
     */
    @GetMapping
    @ApiOperation("列表查询")
    public Page<SysRoleDTO> list(SysRoleVO param, Pageable page) {
        return sysRoleService.list(page, param);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    @ApiOperation("获取数据详情")
    public SysRoleDTO getById(@PathVariable String id) {
        return sysRoleService.getById(id);
    }

}
