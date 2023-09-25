package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
@Slf4j
public class AspectV6Advice {

    /*
    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            //@Before
            log.info("[트랜잭션 시작] : {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();

            //AfterReturning
            log.info("[트랜잭션 커밋] : {}", joinPoint.getSignature());

            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜잭션 롤백]:{}",joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[트랜잭션 종료]:{}",joinPoint.getSignature());
        }
    }
    */

    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] : {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[after returning] : {} return={}", joinPoint.getSignature(),result);
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.allOrder()", returning = "result")
    public void doAfterReturning2(JoinPoint joinPoint, String result) {
        log.info("[after returning2] : {} return={}", joinPoint.getSignature(),result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[after throwing]:{}",joinPoint.getSignature());
    }

    @After("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after]:{}",joinPoint.getSignature());
    }
}
