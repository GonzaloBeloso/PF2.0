package controladoras;

import SQL.Conexion;
import ventanas.VentanaLogin;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControladoraVentanaRegistro implements Initializable {
    // Elementos
    @FXML
    Button idBRegistrado, idBCrear;
    @FXML
    TextField editUser;
    @FXML
    PasswordField editPasswordUno, editPasswordDos;
    @FXML
    BorderPane borderPane;

    private Conexion conexion;
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new Conexion();
        conexion.realizarConexion();
        connection = conexion.getConnection();
        acciones();
    }
    private void acciones() {
        idBRegistrado.setOnAction(new ManejoPulsaciones());
        idBCrear.setOnAction(new ManejoPulsaciones());
    }

    class ManejoPulsaciones implements EventHandler {
        @Override
        public void handle(Event event) {
            if (event.getSource() == idBRegistrado) {
                try {
                    VentanaLogin ventanaLogin = new VentanaLogin();
                    ventanaLogin.show();
                    Stage stage = (Stage) idBRegistrado.getScene().getWindow();
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (event.getSource() == idBCrear) {
                String nombreUser = editUser.getText().toString();
                String passUserUno = editPasswordUno.getText().toString();
                String passUserDos = editPasswordDos.getText().toString();
                if(nombreUser.equals("") || passUserDos.equals("") || passUserDos.equals("")){
                    Alert dialogoWarning = new Alert(Alert.AlertType.WARNING);
                    dialogoWarning.setHeaderText(" Todos los campos deben de estar completos");
                    dialogoWarning.showAndWait();
                }else{
                    if(passUserDos.equals(passUserUno)){
                        try {
                            conexion.realizarConexion();
                            PreparedStatement preparedStatement = conexion.getConnection().prepareStatement("SELECT * " + "FROM usuariosregistro WHERE nombre = ?");
                            preparedStatement.setString(1,nombreUser);
                            ResultSet rs  = preparedStatement.executeQuery();
                            conexion.closeConnection();
                            if (rs.next()) {
                                Alert dialogoError = new Alert(Alert.AlertType.ERROR);
                                dialogoError.setHeaderText("Ya hay un usuario igual");
                                dialogoError.showAndWait();
                            }else{
                                conexion.realizarConexion();
                                PreparedStatement preparedStatementInsert = conexion.getConnection().prepareStatement("INSERT INTO usuariosregistro () " + "VALUES (?,?)");
                                preparedStatementInsert.setString(1,editUser.getText().toString());
                                preparedStatementInsert.setString(2,editPasswordUno.getText().toString());
                                preparedStatementInsert.executeUpdate();
                                conexion.closeConnection();
                                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                                dialogoInfo.setHeaderText("Usuario registrado");
                                dialogoInfo.showAndWait();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Alert dialogoError = new Alert(Alert.AlertType.ERROR);
                        dialogoError.setHeaderText("Las contraseñas no coinciden");
                        dialogoError.showAndWait();
                    }
                }
            }
        }
    }
    // Conexion
    public void setConection(Conexion conexion){
        this.conexion = conexion;
    }
}