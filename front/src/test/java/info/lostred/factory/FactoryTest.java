package info.lostred.factory;

import info.lostred.controller.DocController;
import org.junit.Test;

public class FactoryTest {
    @Test
    public void testGetInstance() {
        Object instance = BeanFactory.getBean(DocController.class);
        System.out.println(instance);
    }
}
