package controladoras;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladoraInicial implements Initializable {
    @FXML
    MenuItem menu_parte_uno,menu_parte_dos,menu_parte_tres,menu_parte_cuatro;
    @FXML
    BorderPane border_principal;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/ventana_parte_uno.fxml"));
        try {
            Parent root = loader.load();
            border_principal.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu_parte_uno.setOnAction(new ManejadorPulsaciones());
        menu_parte_dos.setOnAction(new ManejadorPulsaciones());
        menu_parte_tres.setOnAction(new ManejadorPulsaciones());
        menu_parte_cuatro.setOnAction(new ManejadorPulsaciones());
    }
     private class ManejadorPulsaciones implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == menu_parte_uno) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/layout_ventana_login.fxml"));
                try {
                    Parent root = loader.load();
                    border_principal.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (event.getSource() == menu_parte_dos) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/layout_ventana_registro.fxml"));
                try {
                    Parent root = loader.load();
                    border_principal.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (event.getSource() == menu_parte_tres) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/ventana_inicial_json.fxml"));
                try {
                    Parent root = loader.load();
                    border_principal.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (event.getSource() == menu_parte_cuatro) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/ventana_parte_uno.fxml"));
                try {
                    Parent root = loader.load();
                    border_principal.setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
