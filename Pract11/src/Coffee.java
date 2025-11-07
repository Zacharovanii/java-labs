class Coffee implements Beverage {
    @Override
    public String getDescription() {
        return "Кофе";
    }

    @Override
    public double getCost() {
        return 100.0;
    }
}
