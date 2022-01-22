package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j //lombok 기능으로 이 애노테이션을 사용하면 log 변수를 만들지 않아도 사용할 수 있음
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "String";

        System.out.println("name = " + name);

        //로그를 찍을 때 레벨을 볼 수 있음
        log.trace("trace log={}", name);    //로컬 PC에서 사용
        //log.trace("trace log="+name);     //출력하기 전에 문자와 문자가 더하는 연산이 일어남 (출력을 하지 않아도 메모리, CPU 사용됨)
        log.debug("debug log={}", name);    //디버그 할 때 사용되는 정보
        log.info(" info log={}", name);     //중요한 비지니스 정보
        log.warn(" warn log={}", name);     //위험
        log.error("error log={}", name);    //에러 (별도의 파일에 남길 수도 있음)

        return "ok";
    }
}
