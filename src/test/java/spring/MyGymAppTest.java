package spring;

import org.junit.Test;
import org.spring.config.ProjectConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyGymAppTest {

    @Test
    public void testApplicationContextInitialization() {

        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    }
}