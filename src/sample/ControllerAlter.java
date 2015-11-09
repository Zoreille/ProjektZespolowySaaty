package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;

/**
 * Created by dominik on 19.10.15.
 */
public class ControllerAlter {

    public javafx.scene.control.TextField nazwa;
    public javafx.scene.control.TextField cena;
    public javafx.scene.control.TextField rozdzielczosc;
    public javafx.scene.control.TextField iso;
    public javafx.scene.control.TextField video;
    public javafx.scene.control.TextField bateria;
    public BorderPane alter;

    public void DodajAparat(ActionEvent actionEvent) {
        Main.listaAparatow.add(new Aparaty(nazwa.getText(), cena.getText(), rozdzielczosc.getText(), iso.getText(), video.getText(), bateria.getText()));

        nazwa.clear();
        cena.clear();
        rozdzielczosc.clear();
        iso.clear();
        video.clear();
        bateria.clear();
    }

    public void Dalej(ActionEvent actionEvent) throws Exception{
        if(Main.listaAparatow.isEmpty() || Main.listaAparatow.size()==1) {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Button buttonDialog = new Button("Ok");
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                    children(new Label("Dodaj conajmnije dwa aparaty!"), buttonDialog).
                    alignment(Pos.CENTER).padding(new Insets(5)).build()));
            dialogStage.show();
            buttonDialog.setOnAction((event) -> {
                // Button was clicked, do something...
                dialogStage.close();
            });
        } else {
            Stage sc = (Stage) alter.getScene().getWindow();
            sc.close();


            Parent root = FXMLLoader.load(getClass().getResource("porownanie.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, Main.szerokosc, Main.wysokosc));
            stage.show();
        }
    }




}

