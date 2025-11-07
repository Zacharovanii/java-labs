class Cow extends Animal {
    public Cow(String name, int age) {
        super(name, age);
    }

    @Override
    public void feed() {
        isFed = true;
        System.out.println(name + " покормлена.");
    }

    @Override
    public double collectProduct() {
        if (!isFed) {
            System.out.println(name + " голодна, молока нет!");
            return 0;
        }
        double milk = 5 + Math.random() * 5; // литры молока
        System.out.println(name + " дала " + String.format("%.1f", milk) + " л молока.");
        isFed = false;
        return milk * 1.5; // цена за литр
    }

    @Override
    public String getProductName() {
        return "Молоко";
    }
}