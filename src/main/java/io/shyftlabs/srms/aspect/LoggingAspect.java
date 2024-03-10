package io.shyftlabs.srms.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(io.shyftlabs.srms.controller..*)")
    public void controllerPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("within(io.shyftlabs.srms.service..*)")
    public void servicePackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("within(io.shyftlabs.srms.repository..*)")
    public void repositoryPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Before("controllerPackagePointcut()")
    public void logBeforeController(JoinPoint joinPoint) {
        logger.info("Controller - Entering in Method :  " + joinPoint.getSignature().getName());
    }

    @After("controllerPackagePointcut()")
    public void logAfterController(JoinPoint joinPoint) {
        logger.info("Controller - Method Exit : " + joinPoint.getSignature().getName());
    }

    @Before("servicePackagePointcut()")
    public void logBeforeService(JoinPoint joinPoint) {
        logger.info("Service - Entering in Method :  " + joinPoint.getSignature().getName());
    }

    @After("servicePackagePointcut()")
    public void logAfterService(JoinPoint joinPoint) {
        logger.info("Service - Method Exit : " + joinPoint.getSignature().getName());
    }

    @Before("repositoryPackagePointcut()")
    public void logBeforeRepository(JoinPoint joinPoint) {
        logger.info("Repository - Entering in Method :  " + joinPoint.getSignature().getName());
    }

    @After("repositoryPackagePointcut()")
    public void logAfterRepository(JoinPoint joinPoint) {
        logger.info("Repository - Method Exit : " + joinPoint.getSignature().getName());
    }
}