package main.model;

public class Kettle extends Appliance{
    private int powerConsumption;
    private double volume;

    public Kettle(int price, int powerConsumption, double volume) {
        super(price);
        this.powerConsumption = powerConsumption;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return String.format("Kettle{powerConsumption=%d, volume=%s, price=%d}", powerConsumption, volume, price);
    }
}
