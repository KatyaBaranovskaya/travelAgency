package by.agency.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Before("execution(* by.agency.service.impl.*.update(..))")
    public void update() {
        log.info("IEntity is updating");
    }

    @After("execution(* by.agency.service.impl.*.getAll())")
    public void getAll(JoinPoint joinPoint) {
        log.info("Entities were found - " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "execution(* by.agency.service.impl.*.add(..))",
            returning = "result")
    public void add(boolean result) {
        if (result) {
            log.info("IEntity was added");
        } else {
            log.info("IEntity wasn't added");
        }
    }
}