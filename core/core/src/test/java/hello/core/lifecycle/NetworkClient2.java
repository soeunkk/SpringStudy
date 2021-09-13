package hello.core.lifecycle;

//설정 정보에 초기화 메서드, 종료 메서드 지정하는 경우
public class NetworkClient2 {
    private String url;

    public NetworkClient2() {
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

    public void init() { //의존관계 주입이 끝나면 호출되는 콜백 함수
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {    //빈이 종료될 때 호출되는 콜백 함수
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
