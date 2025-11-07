class SyrupDecorator extends BeverageDecorator {
    public SyrupDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", с сиропом";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 15.0;
    }
}
