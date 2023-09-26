package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InternalService {

    //내부 메서드를 새로 클래스로 분리
    public void internal() {
        log.info("internal 호출");
    }
}
