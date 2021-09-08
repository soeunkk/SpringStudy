package hello.core.singleton;

public class StatefulService {
    /*
    * 필드를 사용함으로 인한 문제

    private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!
    }

    public int getPrice() {
        return price;
    }
    */

    //지역변수에서 멈춤으로써 해결
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
