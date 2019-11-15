package com.company;

public class Car {
    private String model;
    private Engine engine;
    private String weight;
    private String color;

    public Car(String model, Engine engine, String weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }

    public Car(String model, Engine engine) {
        this(model, engine, "n/a", "n/a");
        this.model = model;
        this.engine = engine;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        Engine engine = this.getEngine();
        return String.format("%s:%n     Engine: %s:%n     Power: %s%n     Displacement: %s%n     Efficiency: %s%n     Weight: %s%n     Color: %s%n",
                this.model, engine.getModel(), engine.getPower(), engine.getDisplacement(),
                engine.getEfficiency(), this.weight, this.color);
    }
}
