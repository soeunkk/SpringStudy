package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {  //form 안의 name=name 인 value 값이 저장됨
        this.name = name;
    }
}
