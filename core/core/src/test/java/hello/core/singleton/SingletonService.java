package hello.core.singleton;

public class SingletonService {
    //자기 자신을 static 으로 하나만 가지고 있음(공용으로 하나만 사용하는 변수)
    private static final SingletonService instance = new SingletonService();

    //오직 이 메서드로만 싱글톤 객체를 호출할 수 있음
    //메서드 또한 static 으로 해놓음으로써 객체를 생성하지 않고 이 메서드를 호출할 수 있음
    public static SingletonService getInstance() {
        return instance;
    }

    //private 으로 해놨기 때문에 밖에서 생성할 수 있는 곳은 아무데도 없음
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
