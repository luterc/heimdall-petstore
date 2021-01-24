package com.github.luterc.heimdallpetstore.module.pet.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 宠物商店-猫咪管理
 *
 * @author Luter
 */
@RestController
@Slf4j
@RequestMapping("/pet/cats")
@Api(tags = "猫咪管理")
public class PetCatController {


    /**
     * Save response entity.
     *
     * @return the response entity
     */
    @PostMapping
    @ApiOperation("新增")
    public ResponseEntity<Object> save() {
        return ResponseEntity.ok("新增数据");
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
        return ResponseEntity.ok("删除" + id);
    }

    /**
     * Udpate response entity.
     *
     * @return the response entity
     */
    @PutMapping
    @ApiOperation("修改数据")
    public ResponseEntity<Object> udpate() {
        return ResponseEntity.ok("修改数据");
    }

    /**
     * Details response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    @ApiOperation("查看详情")
    public ResponseEntity<String> details(@PathVariable String id) {
        return ResponseEntity.ok("查看详情" + id);
    }

    /**
     * List response entity.
     *
     * @return the response entity
     */
    @GetMapping
    @ApiOperation("列表查询")
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok("查看列表数据");
    }
}
