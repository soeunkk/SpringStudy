package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); //getBean 을 할 때마다 새로운 프로토타입 빈이 생성됨
        System.out.println("find prototypeBean1");  //PrototypeBean.init 이 출력되고 find prototypeBean1 이 출력됨
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");  //PrototypeBean.init 이 출력되고 find prototypeBean1 이 출력됨

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close(); //close() 해도 ProtypeBean.destroy 가 출력되지 않음
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
