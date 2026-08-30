package com;

import com.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {


    private static final ApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    public static ApplicationContext getContext() {
        return context;
    }

}
