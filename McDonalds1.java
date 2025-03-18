package Amaliyot3;

interface Ingredient {
    String getName();

    double getPrice();
}

class Bread implements Ingredient {
    private final String name;
    private final double price;

    public Bread(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

class Meat implements Ingredient {
    private final String name;
    private final double price;

    public Meat(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

class AdditionalIngredient implements Ingredient {
    private final String name;
    private final double price;

    public AdditionalIngredient(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

class Hamburger {
    private final Ingredient bread;
    private final Ingredient meat;
    private final double basePrice;
    private final Ingredient[] additions;
    private int additionCount;

    public Hamburger(Ingredient bread, Ingredient meat, double basePrice) {
        this.bread = bread;
        this.meat = meat;
        this.basePrice = basePrice;
        this.additions = new Ingredient[4];
        this.additionCount = 0;
    }

    public boolean addAddition(Ingredient addition) {
        if (additionCount < 4) {
            additions[additionCount++] = addition;
            return true;
        }
        return false;
    }

    public double getPrice() {
        double total = basePrice;
        for (int i = 0; i < additionCount; i++) {
            total += additions[i].getPrice();
        }
        return total;
    }

    public String getAdditionNames() {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < additionCount; i++) {
            names.append(additions[i].getName()).append(", ");
        }
        return names.toString();
    }

    public String getInfo() {
        return "Hamburger with " + bread.getName() + ", " + meat.getName() + ". Base price: $" + basePrice;
    }
}

class HealthyBurger extends Hamburger {
    private final Ingredient[] healthyAdditions;
    private int healthyAdditionCount;

    public HealthyBurger(Ingredient meat, double basePrice) {
        super(new Bread("Whole Wheat Bread", 1.0), meat, basePrice);
        this.healthyAdditions = new Ingredient[2];
        this.healthyAdditionCount = 0;
    }

    public boolean addHealthyAddition(Ingredient addition) {
        if (healthyAdditionCount < 2) {
            healthyAdditions[healthyAdditionCount++] = addition;
            return true;
        }
        return false;
    }

    @Override
    public double getPrice() {
        double total = super.getPrice();
        for (int i = 0; i < healthyAdditionCount; i++) {
            total += healthyAdditions[i].getPrice();
        }
        return total;
    }

    @Override
    public String getInfo() {
        return "Healthy " + super.getInfo();
    }
}

class DeluxeBurger extends Hamburger {
    private static double deluxePrice;
    private static boolean priceSet = false;

    public DeluxeBurger(double defaultPrice) {
        super(new Bread("Deluxe Bread", 2.0), new Meat("Deluxe Meat", 3.0), defaultPrice);
        if (!priceSet) {
            deluxePrice = defaultPrice;
            priceSet = true;
        }
    }

    @Override
    public double getPrice() {
        return deluxePrice;
    }

    @Override
    public String getInfo() {
        return "Deluxe " + super.getInfo() + ". Includes chips and drink.";
    }
}

class Main {
    public static void main(String[] args) {

        Hamburger baseBurger = new Hamburger(new Bread("White Bread", 1.5), new Meat("Beef", 2.0), 3.5);
        baseBurger.addAddition(new AdditionalIngredient("Lettuce", 0.5));
        baseBurger.addAddition(new AdditionalIngredient("Tomato", 0.75));
        System.out.println(baseBurger.getInfo());
        System.out.println("Additions: " + baseBurger.getAdditionNames());
        System.out.println("Total price: $" + baseBurger.getPrice());

        HealthyBurger healthyBurger = new HealthyBurger(new Meat("Chicken", 2.5), 4.0);
        healthyBurger.addAddition(new AdditionalIngredient("Lettuce", 0.5));
        healthyBurger.addHealthyAddition(new AdditionalIngredient("Avocado", 1.0));
        System.out.println(healthyBurger.getInfo());
        System.out.println("Total price: $" + healthyBurger.getPrice());

        DeluxeBurger deluxeBurger = new DeluxeBurger(baseBurger.getPrice());
        System.out.println(deluxeBurger.getInfo());
        System.out.println("Total price: $" + deluxeBurger.getPrice());
    }
}