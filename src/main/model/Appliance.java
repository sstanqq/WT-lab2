package main.model;

public class Appliance {
    protected int price;
    protected ApplianceType type;

    public Appliance(int price) {
        this.price = price;
        this.type = ApplianceType.valueOf(this.getClass().getSimpleName().toUpperCase());
    }

    public Appliance() {
        this.type = ApplianceType.valueOf(this.getClass().getSimpleName().toUpperCase());
    }
}
