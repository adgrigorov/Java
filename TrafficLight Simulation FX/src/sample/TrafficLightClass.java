package sample;

import javafx.scene.paint.Color;

public class TrafficLightClass {

    public enum TrafficLight {
        RED(15000),
        GREEN(25000),
        YELLOW(5000);

        private final int duration;

        TrafficLight(int duration) {
            this.duration = duration;
        }

        public int getDuration() {
            return this.duration;
        }
    }
}
