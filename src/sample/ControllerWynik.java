package sample;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by zor3i on 05.11.2015.
 */
public class ControllerWynik implements Initializable {
    public ListView listView1;
    public ListView listView2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView1.setItems((ObservableList) Main.listaAparatow);
        listView2.setItems((ObservableList) Main.koomunikaty);
    }





}
