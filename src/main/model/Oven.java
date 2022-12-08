package main.model;

public class Oven extends Appliance {
    private int powerConsumption;
    private int weight;
    private int capacity;
    private int depth;
    private int height;
    private int width;

    public Oven(int price, int powerConsumption, int weight, int capacity, int depth, int height, int width) {
        super(price);
        this.powerConsumption = powerConsumption;
        this.weight = weight;
        this.capacity = capacity;
        this.depth = depth;
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString() {
        return String.format("Oven{powerConsumption=%d, weight=%d, capacity=%d, depth=%d, height=%d, width=%d, price=%d}",
                powerConsumption, weight, capacity, depth, height, width, price);
    }
}
