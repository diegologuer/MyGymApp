package org.spring;

import org.spring.config.ProjectConfig;
import org.spring.facade.FacadeImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyGymApp {
    public static void main(String[] Args){

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    }
}
