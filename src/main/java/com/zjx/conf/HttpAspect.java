package com.zjx.conf;

import com.zjx.entity.Film;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * @Description
 * 配置切面，在每次修改数据时自动更新下缓存
 * @auther 断弯刀
 * @create 2019-05-16 14:57
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private StartupRunner startupRunner;

    //定义一个公用方法
    @Pointcut("execution(public * com.zjx.controller.admin.*.save(..)) || execution(public * com.zjx.controller.admin.*.delete(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("切点执行before");
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
            if (args[i] instanceof Film){
                Film film= (Film) args[i];
                film.setName(film.getName()+"(被我aop修改喽)");
            }
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());

    }

    @After("pointcut()")
    public void doAfter(){
        logger.info("切点执行doAfter");
        startupRunner.loadData();
    }
}
