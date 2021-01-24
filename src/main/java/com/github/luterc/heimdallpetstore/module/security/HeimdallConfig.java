package com.github.luterc.heimdallpetstore.module.security;

import com.luter.heimdall.cache.caffeine.CaffeineAuthorizationMetaDataDao;
import com.luter.heimdall.cache.caffeine.CaffeineSessionDaoImpl;
import com.luter.heimdall.core.authorization.dao.AuthorizationMetaDataCacheDao;
import com.luter.heimdall.core.authorization.service.AuthorizationMetaDataService;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.manager.AuthorizationManager;
import com.luter.heimdall.core.servlet.ServletHolder;
import com.luter.heimdall.core.session.dao.SessionDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Heimdall 安全配置
 *
 * @author Luter
 */
@Configuration
@Slf4j
public class HeimdallConfig {
    /**
     * SessionDao 配置
     *
     * @param servletHolder the servlet holder
     * @return the session dao
     */
    @Bean
    public SessionDAO sessionDAO(ServletHolder servletHolder) {
        log.warn("初始化 SessionDAO");
        return new CaffeineSessionDaoImpl(servletHolder);
    }

    /**
     * 认证管理器
     *
     * @param sessionDAO the session dao
     * @return the authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(SessionDAO sessionDAO) {
        log.warn("初始化 认证管理器");
        return new AuthenticationManager(sessionDAO);
    }

    /**
     * 权限缓存Dao,注册此 Bean 便于进行动态授权
     *
     * @return the authorization meta data cache dao
     */
    @Bean
    public AuthorizationMetaDataCacheDao authorizationMetaDataCacheDao() {
        log.warn("初始化 系统权限数据 MetaDataDao");
        return new CaffeineAuthorizationMetaDataDao();
    }

    /**
     * 授权管理器
     *
     * @param authenticationManager        the authentication manager
     * @param authorizationMetaDataService the authorization meta data service
     * @return the authorization manager
     */
    @Bean
    public AuthorizationManager authorizationManager(AuthenticationManager authenticationManager
            , AuthorizationMetaDataService authorizationMetaDataService, AuthorizationMetaDataCacheDao authorizationMetaDataCacheDao) {
        log.warn("初始化 授权管理器");
        return new AuthorizationManager(authorizationMetaDataService,
                authorizationMetaDataCacheDao, authenticationManager);
    }
}
