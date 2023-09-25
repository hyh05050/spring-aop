package hello.aop.pointcut;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@Import(ThisTargetTest.ThisTargetAspect.class)
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Aspect
    @Slf4j
    static class ThisTargetAspect {
        @Around("this(hello.aop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface]:{}",joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(hello.aop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface]:{}",joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("this(hello.aop.member.MemberServiceImpl)")
        public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
            //jdk동적프록시 사용시 this 안에 명시한 MemberServiceImpl 객체와 위에 실행한 memberService 객체의 프록시를 비교한다.
            //해당 프록시는 MemberService를 기반으로 만들어진 프록시임으로 MemberServiceImpl를 모르기에 적용되지 않는다.
            //cglib는 MemberService를 구현한 MemberServiceImpl을 가지고 프록시를 만듬으로
            //프록시 객체의 부모타입을 허용하므로 MemberServiceImpl으로 명시할 시 AOP를 적용한다.
            //한마디로 프록시 객체가 명시한 객체의 자식인 경우 aop 허용
            log.info("[this-impl]:{}",joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(hello.aop.member.MemberServiceImpl)")
        public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl]:{}",joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
