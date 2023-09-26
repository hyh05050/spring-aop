package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1; //자기자신을 생성자 주입시 순환사이클 문제생김

//    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
        //setter로 나중에 의존성을 주입할 수 있지만 스프링이 해당 부분을 2.6 버전부터 금지한다.
        //spring.main.allow-circular-references=true yml을 통해 허용할 수 있긴하다.
    }

    public void external() {
        log.info("external 호출");
        callServiceV1.internal();
    }

    public void internal() {
        log.info("internal 호출");
    }
}
