package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        log.info("external 호출");
        internal(); //내부 메서드 호출시 프록시를 타지 않음으로 aspect가 적용되지 않는다.
    }

    public void internal() {
        log.info("internal 호출");
    }
}
