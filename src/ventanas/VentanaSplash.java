package ventanas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VentanaSplash extends Application {

    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../layouts/layout_splash.fxml"));
        Scene scene = new Scene(root,600,400);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    public void mostrar(){
        launch();
    }
}