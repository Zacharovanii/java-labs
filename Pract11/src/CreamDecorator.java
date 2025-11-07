class CreamDecorator extends BeverageDecorator {
    public CreamDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", со сливками";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 25.0;
    }
}
