package com.github.luterc.heimdallpetstore.module.security;

import com.github.luterc.heimdallpetstore.module.sys.dto.SysResourceDTO;
import com.github.luterc.heimdallpetstore.module.sys.dto.SysRoleDTO;
import com.github.luterc.heimdallpetstore.module.sys.dto.SysUserDTO;
import com.github.luterc.heimdallpetstore.module.sys.service.SysResourceService;
import com.github.luterc.heimdallpetstore.module.sys.service.SysUserService;
import com.luter.heimdall.boot.starter.util.JacksonUtils;
import com.luter.heimdall.core.authorization.authority.GrantedAuthority;
import com.luter.heimdall.core.authorization.authority.MethodAndUrlGrantedAuthority;
import com.luter.heimdall.core.authorization.service.AuthorizationMetaDataService;
import com.luter.heimdall.core.session.SimpleSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * 权限数据提供服务
 *
 * @author Luter
 */
@Service
@Slf4j
public class AuthorizationMetaDataServiceImpl implements AuthorizationMetaDataService {
    /**
     * The Sys resource service.
     */
    @Autowired
    private SysResourceService sysResourceService;
    /**
     * The Sys user service.
     */
    @Autowired
    private SysUserService sysUserService;

    @Override
    public Map<String, Collection<String>> loadSysAuthorities() {
        final List<SysResourceDTO> objects = sysResourceService.listAll();
        if (null != objects && !objects.isEmpty()) {
            Map<String, Collection<String>> perms = new LinkedHashMap<>();
            for (SysResourceDTO resource : objects) {
                perms.put(resource.getUrl(), null);
            }
            log.warn("加载到的系统权限: \n{}", JacksonUtils.toPrettyJson(perms));
            return perms;
        }
        return new LinkedHashMap<>();
    }

    @Override
    public List<? extends GrantedAuthority> loadUserAuthorities(SimpleSession session) {
        final AppUserDetails details = (AppUserDetails) session.getDetails();
        final SysUserDTO user = details.getUser();
        final SysUserDTO theUser = sysUserService.getById(user.getId());
        final List<SysRoleDTO> roles = theUser.getRoles();
        //通过用户角色获取用户权限，去重
        if (null != roles && !roles.isEmpty()) {
            List<SysResourceDTO> all = new ArrayList<>();
            for (SysRoleDTO role : roles) {
                if (null != role.getResources() && !role.getResources().isEmpty()) {
                    all.addAll(role.getResources());
                }
            }
            final ArrayList<SysResourceDTO> userResources = all.stream().collect(collectingAndThen(toCollection(()
                    -> new TreeSet<>(comparing(SysResourceDTO::getId))), ArrayList::new));
            //构造权限缓存信息
            return userResources.stream().map(d -> new MethodAndUrlGrantedAuthority(d.getMethod(), d.getUrl())).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
