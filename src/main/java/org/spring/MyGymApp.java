package org.spring;

import org.spring.config.ProjectConfig;
import org.spring.facade.Facade;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Date;


public class MyGymApp {
    public static void main(String[] Args){

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var a = context.getBean(Facade.class);
        a.createTrainer("Howg", "Long", 3);
    }
}
