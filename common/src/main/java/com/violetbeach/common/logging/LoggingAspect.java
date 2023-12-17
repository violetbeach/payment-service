package com.violetbeach.common.logging;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final LoggingProducer loggingProducer;

    @Before("execution(* com.violetbeach.*.adapter.in.web.*.*(..))")
    public void beforeMethodExecution(@NotNull JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        loggingProducer.sendMessage("logging", "Before executing method: " + methodName);
    }
}
