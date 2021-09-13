package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//인터페이스를 통해 빈 생명주기 콜백을 받는 경우
public class NetworkClient1 implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient1() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close" + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception { //의존관계 주입이 끝나면 호출되는 콜백 함수
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {    //빈이 종료될 때 호출되는 콜백 함수
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
