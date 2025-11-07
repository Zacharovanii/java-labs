interface Workable {
    default void work() {
        System.out.println("Кто-то работает...");
    }
}

interface Restable {
    default void rest() {
        System.out.println("Кто-то отдыхает...");
    }
}

class Person implements Workable, Restable {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void rest() {
        Restable.super.rest();
        System.out.println(name + " отдыхает...");
    }

    @Override
    public void work() {
        Workable.super.work();
        System.out.println(name + " работает...");
    }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Алексей");

        person.work();
        person.rest();
    }
}
