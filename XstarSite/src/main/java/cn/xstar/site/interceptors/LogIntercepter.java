package cn.xstar.site.interceptors;

import cn.xstar.site.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class LogIntercepter implements WebRequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void preHandle(WebRequest request) throws Exception {
        logger.info(StrUtil.map(request.getParameterMap()));
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

    }
}

