abstract class Animal {
    protected String name;
    protected int age;
    protected boolean isFed;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.isFed = false;
    }

    public abstract void feed();
    public abstract double collectProduct(); // возвращает прибыль
    public abstract String getProductName();

    @Override
    public String toString() {
        return name + " (" + getClass().getSimpleName() + ", " + age + " лет)";
    }
}