package info.lostred.service;

import info.lostred.pojo.DocType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DocTypeServiceTest {
    private DocTypeService docTypeService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        docTypeService = context.getBean(DocTypeService.class);
    }

    @Test
    public void findAllDocType() {
        List<DocType> allDocType = docTypeService.findAllDocType();
        for (DocType docType : allDocType) {
            System.out.println(docType);
        }
    }
}