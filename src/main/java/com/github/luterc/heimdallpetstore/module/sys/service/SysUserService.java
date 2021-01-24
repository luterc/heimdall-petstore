package com.github.luterc.heimdallpetstore.module.sys.service;

import com.github.luterc.heimdallpetstore.module.sys.dto.SysUserDTO;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysUserVO;
import com.luter.heimdall.core.session.SimpleSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

/**
 * The interface Sys user service.
 *
 * @author Luter
 */
public interface SysUserService {

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    SysUserDTO getById(String id);


    /**
     * Save sys user dto.
     *
     * @param param the param
     * @return the sys user dto
     */
    SysUserDTO save(SysUserVO param);

    /**
     * Update sys user dto.
     *
     * @param param the param
     * @return the sys user dto
     */
    SysUserDTO update(SysUserVO param);

    /**
     * List page.
     *
     * @param page  the page
     * @param param the param
     * @return the page
     */
    Page<SysUserDTO> list(Pageable page, SysUserVO param);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(String id);
////////登录注销


    /**
     * 登录
     *
     * @param param the param
     * @return the simple session
     */
    SimpleSession login(SysUserVO param);

    /**
     * 注销
     *
     * @return the simple session
     */
    SimpleSession logout();

    /**
     * 获取登录用户信息
     *
     * @param fromDb 是否从数据库获取
     * @return the sys user dto
     */
    SysUserDTO currentUser(boolean fromDb);


    /**
     * 获取所有在线用户
     *
     * @return the collection
     */
    Collection<SimpleSession> onlineUsers();


    /**
     * 根据 SessionId 踢出用户
     *
     * @param sessionId the session id
     * @return the boolean
     */
    boolean kickOutSession(String sessionId);

    /**
     * 根据 principal 踢出用户
     *
     * @param principal the principal
     * @return the boolean
     */
    boolean kickOutPrincipal(String principal);
}
