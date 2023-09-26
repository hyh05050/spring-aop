package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //jdk동적프록시
@Import(ProxyDIAspect.class)
public class ProxyDITest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;
    //Aspect를 적용하면 해당 의존성에 프록시객체가 주입되는데 해당 프록시객체는 구체타입을 알지 못함으로 에러가 난다.

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
