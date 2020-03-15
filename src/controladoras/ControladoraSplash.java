package controladoras;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import ventanas.VentanaInicial;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladoraSplash implements Initializable {

    @FXML
    ImageView imagenFondo;
    @FXML
    ProgressBar progreso;
    Task tareaSecundaria;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instancias();
        progreso.progressProperty().bind(tareaSecundaria.progressProperty());
        FadeTransition transition = new FadeTransition(Duration.seconds(4),imagenFondo);
        transition.setToValue(1.0);
        transition.setFromValue(0.0);
        transition.play();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Thread(tareaSecundaria).start();
            }
        });
        tareaSecundaria.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                VentanaInicial ventanaInicial = new VentanaInicial();
                Stage stage = (Stage) imagenFondo.getScene().getWindow();
                stage.hide();
            }
        });
    }
    private void instancias() {
        tareaSecundaria = new Task() {
            @Override
            protected Object call() throws Exception {

                for (int i=0;i<100;i++){
                    updateProgress(i,100);
                    updateMessage("change");
                    Thread.sleep(100);
                }
                return null;
            }
        };
    }
}