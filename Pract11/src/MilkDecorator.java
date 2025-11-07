class MilkDecorator extends BeverageDecorator {
    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", с молоком";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 20.0;
    }
}
