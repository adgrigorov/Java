package sample;

public class TrafficLightTest {

    //2a
    public static void main(String[] args) {
        System.out.println("Traffic lights INFO:");
        for (TrafficLightClass.TrafficLight light : TrafficLightClass.TrafficLight.values()) {
            System.out.printf("Traffic light: %s%n", light);
            System.out.printf("Duratiion: %d%n", light.getDuration());
        }
        System.out.println();

        long currentTime;
        long tm = System.currentTimeMillis();
        long endTime = tm + 90000;
        do {
            currentTime = System.currentTimeMillis();

            for (TrafficLightClass.TrafficLight light : TrafficLightClass.TrafficLight.values()) {
                System.out.println(light);
                tm += light.getDuration();
                while (tm >= currentTime) {
                    currentTime = System.currentTimeMillis();
                    if (currentTime > endTime) {
                        System.exit(0);
                    }
                }
            }
        } while (true);
    }
}
