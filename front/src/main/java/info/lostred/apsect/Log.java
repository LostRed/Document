package info.lostred.apsect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@Aspect
public class Log {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Pointcut("execution(* info.lostred.service.impl.*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println(sdf.format(System.currentTimeMillis()) + ":开始执行");
    }

    @After("pointCut()")
    public void after() {
        System.out.println(sdf.format(System.currentTimeMillis()) + ":执行成功");
    }

    @AfterReturning("pointCut()")
    public void expect() {
        System.out.println(sdf.format(System.currentTimeMillis()) + ":执行失败");
    }

    @AfterReturning("pointCut()")
    public void last() {
        System.out.println(sdf.format(System.currentTimeMillis()) + ":执行完毕");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println(sdf.format(System.currentTimeMillis()) + ":开始执行");
        Object rs = null;
        Object[] args = joinPoint.getArgs();
        try {
            rs = joinPoint.proceed(args);
            System.out.println(sdf.format(System.currentTimeMillis()) + ":执行成功");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println(sdf.format(System.currentTimeMillis()) + ":执行失败");
        } finally {
            System.out.println(sdf.format(System.currentTimeMillis()) + ":执行完毕");
        }
        return rs;
    }
}
