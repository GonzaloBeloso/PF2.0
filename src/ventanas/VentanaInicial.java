package ventanas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaInicial extends Stage {

    public VentanaInicial() {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("layouts/ventana_parte_uno.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,600,400);
        this.setScene(scene);
        this.show();
    }

}