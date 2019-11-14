package sample;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TrafficLightFX extends Application {
    //2b
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/
        //
        Group group = new Group();

        Circle red = new Circle(100, 100, 50, Color.RED);
        Circle yellow = new Circle(100, 210, 50, Color.YELLOW);
        Circle green = new Circle(100, 320, 50, Color.GREEN);
        group.getChildren().addAll(red, yellow, green);

        Scene scene = new Scene(group,200, 400, Color.LIGHTBLUE);

        primaryStage.setTitle("Traffic Light");
        primaryStage.setScene(scene);
        Task<Void> trafficLightTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                long currentTime;
                long tm = System.currentTimeMillis();
                long endTime = tm + 90000;
                do {
                    currentTime = System.currentTimeMillis();

                    for (TrafficLightClass.TrafficLight light
                        : TrafficLightClass.TrafficLight.values()) {
                        switch (light) {
                            case RED:
                                red.setFill(Color.RED);
                                green.setFill(Color.GREY);
                                yellow.setFill(Color.GREY);
                                break;
                            case YELLOW:
                                red.setFill(Color.GREY);
                                green.setFill(Color.GREY);
                                yellow.setFill(Color.YELLOW);
                                break;
                            case GREEN:
                                red.setFill(Color.GREY);
                                green.setFill(Color.GREEN);
                                yellow.setFill(Color.GREY);
                                break;
                        }

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
        };
        primaryStage.show();
        Thread thread = new Thread(trafficLightTask);
        thread.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
