package cn.ypjalt.interview.study.thread;
public class TestTransfervalue {
    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setName("xxx");
    }

    public void changeValue3(String str) {
        str = "xxxxxx";
    }

    public static void main(String[] args) {
        TestTransfervalue testTransfervalue = new TestTransfervalue();
        int age = 20;
        testTransfervalue.changeValue1(age);
        System.out.println("age--" + age);

        Person person = new Person("abs");
        testTransfervalue.changeValue2(person);
        System.out.println("person name " + person.getName());

        String str = "ssss";
        testTransfervalue.changeValue3(str);
        System.out.println("str  " + str);
    }
}
