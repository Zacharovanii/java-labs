class Sheep extends Animal {
    public Sheep(String name, int age) {
        super(name, age);
    }

    @Override
    public void feed() {
        isFed = true;
        System.out.println(name + " покормлена травой.");
    }

    @Override
    public double collectProduct() {
        if (!isFed) {
            System.out.println(name + " голодна, шерсти нет!");
            return 0;
        }
        double wool = 1 + Math.random() * 2; // кг шерсти
        System.out.println(name + " дала " + String.format("%.1f", wool) + " кг шерсти.");
        isFed = false;
        return wool * 3.0; // цена за кг
    }

    @Override
    public String getProductName() {
        return "Шерсть";
    }
}
