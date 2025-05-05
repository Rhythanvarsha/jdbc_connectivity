import java.awt.Color;

import javax.swing.GroupLayout.Group;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        Stage stage1 = new Stage();
        Group root = new Group();

        Circle c1 = new Circle(50.0, Color.BLUE);
        root.getChildren().add(c1);
        c1.setCenterX(100);
        c1.setCenterY(100);

        Scene s1 = new Scene(root,400,400);
        stage1.setScene(s1);
        stage1.setTitle("JAVAFX_HEEHEHEHE");
        stage1.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}