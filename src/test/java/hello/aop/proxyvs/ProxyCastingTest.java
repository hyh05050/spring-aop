package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); //jdk동적프록시 설정

        MemberService proxy = (MemberService) proxyFactory.getProxy(); //프록시를 인터페이스로 캐스팅시 성공
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl memberService = (MemberServiceImpl) proxy;
            //jdk 프록시는 인터페이스를 기반으로 생성하므로 구체타입으로 캐스팅이 불가능하다.
        });

    }
}
