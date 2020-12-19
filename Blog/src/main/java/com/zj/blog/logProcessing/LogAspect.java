package com.zj.blog.logProcessing;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志信息拦截器
 * 采用 SpringAop 对请求以切面的方式进行拦截
 * 拦截过后进行具体处理（记录日志信息）
 */
@Aspect
@Component
public class LogAspect extends HttpServlet {
    /** 获取日志记录器 */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.zj.blog.controller.*.*(..))")
    public void log(){}
    /** 方法调用之前，拦截并记录用户访问url,ip地址,调用方法和请求参数 */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        // 获取请求request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString(); // 获取请求url
        String ip = request.getRemoteAddr();    // 获取请求IP地址
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();    // 获取请求调用方法名
        Object[] args = joinPoint.getArgs();    // 获取请求参数

        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);

        logger.info("Request : {}",requestLog);
    }
    @After("log()")
    public void doAfter(){
        logger.info("---------doAfter-----------");
    }
    /** pointcut 定义切面，returning收集返回结果 */
    @AfterReturning(pointcut = "log()",returning = "results")
    public void doAfterReturn(Object results){
        logger.info("Result:{}",results);
    }

    /** 定义一个内部类，封装返回信息 */
    private static class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

        public RequestLog() {
        }

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }
    }
}