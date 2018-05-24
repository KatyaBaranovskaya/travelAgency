package by.agency.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* by.agency.service.impl.*.update(..))")
    public void update() {
        LOGGER.info("IEntity is updating");
    }

    @After("execution(* by.agency.service.impl.*.getAll())")
    public void getAll(JoinPoint joinPoint) {
        LOGGER.info("Entities were found - " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "execution(* by.agency.service.impl.*.add(..))",
            returning = "result")
    public void add(boolean result) {
        if (result) {
            LOGGER.info("IEntity was added");
        } else {
            LOGGER.info("IEntity wasn't added");
        }
    }
}