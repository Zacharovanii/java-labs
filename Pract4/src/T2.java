interface Character {
    void act();

    interface Inventory {
        void listItems();
    }
}

class Warrior implements Character, Character.Inventory {
    @Override
    public void act() {
        System.out.println("Воин атакует мечом!");
    }

    @Override
    public void listItems() {
        System.out.println("Инвентарь: Меч, Щит");
    }
}

public class T2 {
    public static void main(String[] args) {
        Warrior warrior = new Warrior();
        warrior.act();
        warrior.listItems();
    }
}
