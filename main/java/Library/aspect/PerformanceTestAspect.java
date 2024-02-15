package Library.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceTestAspect {
    @Pointcut("within(@Library.aspect.Timer *)")
    public void annotatedBeans() {

    }

    @Pointcut("@annotation(Library.aspect.Timer)")
    public void annotatedMethods() {}

    @Around("annotatedBeans() || annotatedMethods()")
    public Object performanceTest(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("{} - {} #({})", joinPoint.getClass(), joinPoint.getSignature().getName(), elapsedTime);
            return result;
        } catch (Throwable e) {
            log.info("exception: {}, {}", e.getClass(), e.getMessage() );
            throw e;
        }
    }
}