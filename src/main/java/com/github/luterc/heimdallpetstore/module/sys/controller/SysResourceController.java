package com.github.luterc.heimdallpetstore.module.sys.controller;

import com.github.luterc.heimdallpetstore.module.sys.dto.SysResourceDTO;
import com.github.luterc.heimdallpetstore.module.sys.service.SysResourceService;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysResourceVO;
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
 * The type Sys resource controller.
 *
 * @author Luter
 */
@RestController
@Slf4j
@RequestMapping("/sys/resources")
@Api(tags = "权限资源管理")
public class SysResourceController {
    /**
     * The Sys resource service.
     */
    @Autowired
    private SysResourceService sysResourceService;

    /**
     * Save sys resource dto.
     *
     * @param param the param
     * @return the sys resource dto
     */
    @PostMapping
    @ApiOperation("新增")
    public SysResourceDTO save(@RequestBody @Validated SysResourceVO param) {
        return sysResourceService.save(param);
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
        sysResourceService.deleteById(id);
        return ResponseEntity.ok("Success");
    }

    /**
     * Update sys resource dto.
     *
     * @param param the param
     * @return the sys resource dto
     */
    @PutMapping
    @ApiOperation("修改")
    public SysResourceDTO update(@RequestBody @Validated SysResourceVO param) {
        return sysResourceService.update(param);
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
    public Page<SysResourceDTO> list(SysResourceVO param, Pageable page) {
        return sysResourceService.list(page, param);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    @ApiOperation("获取数据详情")
    public SysResourceDTO getById(@PathVariable String id) {
        return sysResourceService.getById(id);
    }


}
