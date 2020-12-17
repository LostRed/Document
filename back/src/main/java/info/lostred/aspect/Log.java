package info.lostred.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.text.SimpleDateFormat;

public class Log {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void before() {
        System.out.println(sdf.format(System.currentTimeMillis()) + ":开始执行");
    }

    public void after() {
        System.out.println(sdf.format(System.currentTimeMillis()) + ":执行成功");
    }

    public void expect() {
        System.out.println(sdf.format(System.currentTimeMillis()) + ":执行失败");
    }

    public void last() {
        System.out.println(sdf.format(System.currentTimeMillis()) + ":执行完毕");
    }

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
