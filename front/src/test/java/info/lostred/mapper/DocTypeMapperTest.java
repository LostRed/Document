package info.lostred.mapper;

import info.lostred.pojo.DocType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DocTypeMapperTest {
    private DocTypeMapper mapper;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        mapper = context.getBean(DocTypeMapper.class);
    }

    @Test
    public void testSelectAll() {
        List<DocType> docTypes = mapper.selectAll();
        for (DocType docType : docTypes) {
            System.out.println(docType);
        }
    }
}
