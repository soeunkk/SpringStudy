package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString //객체를 필드 값과 함께 스트링으로 나타내줌
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("soeun");

        System.out.println("helloLombok = " + helloLombok);
        String name = helloLombok.getName();
        System.out.println("name = " + name);

    }
}
