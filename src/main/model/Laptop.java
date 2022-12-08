package main.model;

public class Laptop extends Appliance{
    private double batteryCapacity;
    private OS os;
    private int memoryRom;
    private int systemMemory;
    private double CPU;
    private int displayInchs;

    public Laptop(int price, double batteryCapacity, OS os, int memoryRom, int systemMemory, double CPU, int displayInchs) {
        super(price);
        this.batteryCapacity = batteryCapacity;
        this.os = os;
        this.memoryRom = memoryRom;
        this.systemMemory = systemMemory;
        this.CPU = CPU;
        this.displayInchs = displayInchs;
    }
}
