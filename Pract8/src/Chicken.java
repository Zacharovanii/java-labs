class Chicken extends Animal {
    public Chicken(String name, int age) {
        super(name, age);
    }

    @Override
    public void feed() {
        isFed = true;
        System.out.println(name + " покормлена зерном.");
    }

    @Override
    public double collectProduct() {
        if (!isFed) {
            System.out.println(name + " голодна, яиц нет!");
            return 0;
        }
        int eggs = (int) (1 + Math.random() * 3);
        System.out.println(name + " снесла " + eggs + " яиц.");
        isFed = false;
        return eggs * 0.7;
    }

    @Override
    public String getProductName() {
        return "Яйца";
    }
}
