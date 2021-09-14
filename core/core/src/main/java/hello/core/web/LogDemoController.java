package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.common.MyLoggerProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;

    //진짜 객체 조회를 꼭 필요한 시점까지 지연처리 하는 방법
    //private final ObjectProvider<MyLogger> myLoggerProvider;  //Provider 이용
    private final MyLoggerProxy myLoggerProxy;                  //Proxy 이용 (Scope 의 속성으로 간단하게 이용 가능, 하지만 오류를 찾기 어려우므로 정말 필요할 때만 이용하자)

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLoggerProxy.getClass());    //지금 부르는 것은 진짜 myLogger 가 아님. (가짜 프록시 객체이기 때문에 언제든지 호출할 수 있음)
        myLoggerProxy.setRequestURL(requestURL);

        myLoggerProxy.log("controller test");   //가짜 프록시 객체는 진짜 myLogger 를 찾는 방법을 알기 때문에 myLogger 내부에 있는 메서드를 호출할 수 있음
        logDemoService.logic("testId");
        return "OK";
    }

}
