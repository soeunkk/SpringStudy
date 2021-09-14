package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();  //count++되어 0에서 1이 됨
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();  //count++되어 0에서 1이 됨

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean1.class, PrototypeBean.class);

        ClientBean1 clientBean1 = ac.getBean(ClientBean1.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1); //count++되어 0에서 1이 됨

        ClientBean1 clientBean2 = ac.getBean(ClientBean1.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(2); //count++되어 1에서 2가 됨
    }

    @Test
    void singletonClientUsePrototypeWithObjectProvider() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);

        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1); //count++되어 0에서 1이 됨

        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1); //count++되어 0에서 1이 됨 (ObjectProvider 에 의해 다른 인스턴스를 사용했기 때문)
    }

    @Test
    void singletonClientUsePrototypeWithProvider() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean3.class, PrototypeBean.class);

        ClientBean3 clientBean1 = ac.getBean(ClientBean3.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1); //count++되어 0에서 1이 됨

        ClientBean3 clientBean2 = ac.getBean(ClientBean3.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1); //count++되어 0에서 1이 됨 (Provider 에 의해 다른 인스턴스를 사용했기 때문)
    }

    @Scope("singleton")
    //필드에 프로토타입 빈을 가지고 있음 -> 싱글톤의 의존주입에 의해 한 번만 생성됨
    static class ClientBean1 {

        private final PrototypeBean prototypeBean; //생성시점에 이미 주입이 되어있기 때문에 다른 클라이언트가 호출해도 이 인스턴스를 사용함함

        public ClientBean1(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    //ObjectPrototype 을 사용하여 클라이언트가 사용할 때마다 다른 인스턴스의 프로토타입 빈을 갖게 됨
    static class ClientBean2 {

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            // getObject() 메서드는 DL 기능을 편리하게 이용하도록 만들어진 것으로, getObject() 메서드를 사용하면 빈 조회가 일어난다.
            //이때 프로토타입 빈은 빈을 조회할 때마다 생성되므로 다른 인스턴스를 가지게 되는 것이다.
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    //ObjectProvider 대신 javax.inject 의 Provider 를 사용함
    static class ClientBean3 {

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;  //get() 메서드 하나만 있기 때문에 기능이 단순하고, 자바 표준이므로 다른 컨테이너에서 사용할 수 있음

        public int logic() {
            // getObject() 메서드는 DL 기능을 편리하게 이용하도록 만들어진 것으로, getObject() 메서드를 사용하면 빈 조회가 일어난다.
            //이때 프로토타입 빈은 빈을 조회할 때마다 생성되므로 다른 인스턴스를 가지게 되는 것이다.
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() { //프로토타입 빈에서는 호출되지 않음
            System.out.println("PrototypeBean.destroy");
        }
    }
}
