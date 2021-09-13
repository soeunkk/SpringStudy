package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient3 client = ac.getBean(NetworkClient3.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        /*
        //@Bean()을 안에 정보를 통해 콜백 함수 설정하는 방법
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient2 networkClient() {
            NetworkClient2 networkClient = new NetworkClient2();    //생성자 호출
            networkClient.setUrl("http://hello-spring.dev");        //url 이 null 인 상태에서 이제서야 url 이 주입됨 (따라서 이전까지는 NetworkClient 를 사용하면 오류)->콜백 함수를 사용해야 하는 이유
            return networkClient;
        }
         */

        @Bean
        public NetworkClient3 networkClient() {
            NetworkClient3 networkClient = new NetworkClient3();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
