package info.lostred.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("applicationContext.xml");

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> clazz) {
        return (T) CONTEXT.getBean(clazz);
    }
}
