package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.common.MyLoggerProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLoggerProxy myLoggerProxy;

    public void logic(String id) {
        myLoggerProxy.log("service id = " + id);    //가짜 프록시 객체는 진짜 myLogger 를 찾는 방법을 알기 때문에 myLogger 내부에 있는 메서드를 호출할 수 있음
    }
}
