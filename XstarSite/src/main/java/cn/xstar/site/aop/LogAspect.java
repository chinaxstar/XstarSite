package cn.xstar.site.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }


    @Pointcut("execution(* cn.xstar.site.controllers..*.*(..))")
    private void log() {
    }

    @Around(value = "log()")
    public Object logController(ProceedingJoinPoint pjp) {
        try {
            HttpServletRequest request = getHttpServletRequest();
            logger.info("request==> "+ new ObjectMapper().writeValueAsString(request.getParameterMap()))
            ;
            Object object = pjp.proceed();
            logger.info("response==> "+new ObjectMapper().writeValueAsString(object));
            return object;
        } catch (JsonProcessingException e) {
            logger.error("request error:", e);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


}
