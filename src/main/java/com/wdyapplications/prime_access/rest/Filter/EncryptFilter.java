///*
// * Created on 2019-03-05 ( Time 12:31:18 )
// * Generator tool : Telosys Tools Generator ( version 3.0.0 )
// * Copyright 2018 Savoir Faire Linux. All Rights Reserved.
// */
//package com.wdyapplications.prime_access.rest.Filter;
//
//import com.wdyapplications.prime_access.dao.repository.SettingRepository;
//import com.wdyapplications.prime_access.utils.FunctionalError;
//import com.wdyapplications.prime_access.utils.Utilities;
//import com.wdyapplications.prime_access.utils.redis.CacheUtils;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Arrays;
//
///**
// * Servlet Filter implementation class CrossFilter
// */
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class EncryptFilter implements Filter {
//    private Logger            slf4jLogger = LoggerFactory.getLogger(getClass());
//    @Autowired
//    private Environment       environment;
//    @Autowired
//    private SettingRepository settingRepository;
//    @Autowired
//    private FunctionalError functionalError;
//    @Autowired
//    private CacheUtils redisRepository;
//
////    @Autowired
////    private BrowserDetectionService browserDetectionService;
////    @Autowired
////    private SettingRepository settingRepository;
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        String              resp                = "";
//        String              requestValue        = "";
//        boolean             ignore              = false;
//        HttpServletRequest  httpServletRequest  = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        String              type                = httpServletRequest.getHeader("Content-Type");
//        String ipAddress        = Utilities.getClientIp(httpServletRequest);
//        // reccupération de l'URL
//        String uri = httpServletRequest.getRequestURI().toString();
//        httpServletResponse.reset();
//        String origin = httpServletRequest.getHeader("Origin");
//        if (origin != null && !origin.isEmpty()) {
//            httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
//        } else {
//            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        }
//        httpServletResponse.setHeader("IP", ipAddress);
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers",
//                "Origin, access-control-allow-methods,Access-Control-Allow-Methods,Accept, X-Requested-With, Content-Type,Access-Control-Allow-Origin, Access-Control-Request-Method, Access-Control-Request-Headers, Show-Success-Message, Show-Loader, Show-Error-Message,sessionUser,lang,user,token,sessionuser,content-type,Origin-Prime,timeout,userId,isEncrypte");
//        requestValue = HttpRequestUtilis.ExtractRequest(httpServletRequest);
//
//        HttpRequestUtilis.noEncryptRequestThenChainDoFilter(response, chain, httpServletRequest, requestValue,redisRepository ,httpServletResponse, uri);
//    }
//}
