package com.example.dcc.bestlaptop;

public class Laptop {

    private String cpu;
    private String display;
    private String model;
    private int price;
    private String ram;
    private String storage;
    private int rank = -1;
    private String graphics;

    public Laptop(String cpu, String display, String model, int price, String ram, String storage, String graphics){
        this.cpu = cpu;
        this.display = display;
        this.model = model;
        this.price = price;
        this.ram = ram;
        this.storage = storage;
        this.graphics = graphics;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getGraphics() {
        if(graphics.toLowerCase().trim().compareTo("n/a")==0)
            return "0";
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
