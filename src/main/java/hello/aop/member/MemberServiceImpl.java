package hello.aop.member;

import hello.aop.member.MemberService;
import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@ClassAop
@Component
@Slf4j
public class MemberServiceImpl implements MemberService {

    @MethodAop("test value")
    @Override
    public String hello(String name) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
