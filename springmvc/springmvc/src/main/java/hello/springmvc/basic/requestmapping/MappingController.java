package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping(value="/mapping-get-v1")
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    //url 에 값이 들어 있는 경우를 path variable 이라고 부름
    @RequestMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info(" info userID={}", data);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable long orderId) {
        log.info(" info userID={}, orderId={}", userId, orderId);
        return "ok";
    }

    //파라미터 추가 매핑
    @GetMapping(value="/mapping-param", params="mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    //헤더 기반 추가 매핑
    @GetMapping(value="/mapping-header", headers="mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    //미디어 타입 매핑
    @PostMapping(value="/mappin-consume", consumes="application/json")
    public String mappingConsumes() {
        log.info("mappingConsume");
        return "ok";
    }

    //HTTP 요청의 Accept 헤더를 기반으로 미디어 타입으로 매핑
    //만약 맞지 않으면 HTTP 406 상태코드(Not Acceptable)을 반환
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
