package com.github.luterc.heimdallpetstore.module.sys.controller;

import com.github.luterc.heimdallpetstore.module.sys.dto.SysUserDTO;
import com.github.luterc.heimdallpetstore.module.sys.service.SysUserService;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysUserVO;
import com.luter.heimdall.core.session.SimpleSession;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * The type Sys user controller.
 *
 * @author Luter
 */
@RestController
@Slf4j
@RequestMapping("/sys/users")
@Api(tags = "用户管理")
public class SysUserController {
    /**
     * The Sys user service.
     */
    @Autowired
    private SysUserService sysUserService;

    /**
     * Save sys user dto.
     *
     * @param param the param
     * @return the sys user dto
     */
    @PostMapping
    @ApiOperation("新增")
    public SysUserDTO save(@RequestBody @Validated SysUserVO param) {
        return sysUserService.save(param);
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
        sysUserService.deleteById(id);
        return ResponseEntity.ok("Success");
    }

    /**
     * Update sys user dto.
     *
     * @param param the param
     * @return the sys user dto
     */
    @PutMapping
    @ApiOperation("修改")
    public SysUserDTO update(@RequestBody @Validated SysUserVO param) {
        return sysUserService.update(param);
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
    public Page<SysUserDTO> list(SysUserVO param, Pageable page) {
        return sysUserService.list(page, param);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    @ApiOperation("获取数据详情")
    public SysUserDTO getById(@PathVariable String id) {
        return sysUserService.getById(id);
    }

    //////登录注销
    @PostMapping("/login")
    @ApiOperation("登录")
    public SimpleSession login(@RequestBody @Validated SysUserVO param) {
        return sysUserService.login(param);
    }

    @DeleteMapping("/logout")
    @ApiOperation("注销")
    public SimpleSession logout() {
        return sysUserService.logout();
    }

    @PostMapping("/current")
    @ApiOperation("获取当前登录用户信息")
    public SysUserDTO getCurrentUser() {
        return sysUserService.currentUser(true);
    }

    @GetMapping("/online")
    @ApiOperation("在线用户列表查询")
    public Collection<SimpleSession> onlineUsers() {
        return sysUserService.onlineUsers();
    }

}
