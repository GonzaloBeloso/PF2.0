package controladoras;

import com.google.gson.Gson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Pelicula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladoraParteUno implements Initializable {

    @FXML
    ListView<Pelicula> listView;

    @FXML
    ImageView imageView;

    @FXML
    BorderPane border_parte_uno;

    ObservableList<Pelicula> listaPeliculas;

    private String urlImagen;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        urlImagen = "https://image.tmdb.org/t/p/w500";
        listaPeliculas = FXCollections.observableArrayList();
        acciones();

        String urlJson ="https://api.themoviedb.org/3/movie/now_playing?api_key=4ef66e12cddbb8fe9d4fd03ac9632f6e&language=en-US&page=1";
        try {
            URL url = new URL(urlJson);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String lecturaJson = in.readLine();
            in.close();
            JSONObject jsonOriginal = new JSONObject(lecturaJson);
            JSONArray jsonArrayGeneral = jsonOriginal.getJSONArray("results");
            for (int i=0;i<jsonArrayGeneral.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArrayGeneral.get(i);
                Gson gson = new Gson();
                Pelicula pelicula = gson.fromJson(jsonObject.toString(),Pelicula.class);
                listaPeliculas.add(pelicula);
            }

            listView.setItems(listaPeliculas);
            Image image = new Image(urlImagen+listaPeliculas.get(0).getPoster_path());
            System.out.println(image.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void acciones() {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pelicula>() {
            @Override
            public void changed(ObservableValue<?extends Pelicula> observable, Pelicula oldValue, Pelicula newValue) {
                Image imagen = new Image(urlImagen+newValue.getPoster_path());
                System.out.println(urlImagen+newValue.getPoster_path());
                imageView.setFitHeight(400);
                imageView.setFitWidth(400);
                imageView.setImage(imagen);
            }
        });
    }

}
