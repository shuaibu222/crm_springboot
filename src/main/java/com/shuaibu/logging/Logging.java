package com.shuaibu.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class Logging {
    
    @Before("execution(* com.shuaibu.controllers.CustomerController.*(..))")
    public void Start() {
        System.out.println("\n\nEndpoint are busy working " + "\n");
    }
}
