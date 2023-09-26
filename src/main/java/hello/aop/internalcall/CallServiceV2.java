package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    private final ApplicationContext ac;
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider, ApplicationContext ac) {
        this.ac = ac;
        this.callServiceProvider = callServiceProvider;
    }

    public void external() {
        log.info("external 호출");
        //ApplicationContext 사용
        CallServiceV2 callServiceV2 = ac.getBean(CallServiceV2.class);
        callServiceV2.internal();

        //ObjectProvider 사용
        CallServiceV2 callServiceV2_2 = callServiceProvider.getObject();
        callServiceV2_2.internal();

    }

    public void internal() {
        log.info("internal 호출");
    }
}
