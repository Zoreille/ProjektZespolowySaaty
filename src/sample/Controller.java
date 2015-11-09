package sample;

import com.sun.javafx.scene.control.behavior.SliderBehavior;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.plaf.SliderUI;
import javax.swing.text.html.*;
import java.awt.*;
import java.awt.TextField;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {


    public BorderPane borderpane;
    public BorderPane alter;
    public BorderPane porownanie;

    public javafx.scene.control.TextField nazwa;
    public javafx.scene.control.TextField cena;
    public javafx.scene.control.TextField rozdzielczosc;
    public javafx.scene.control.TextField iso;
    public javafx.scene.control.TextField video;
    public javafx.scene.control.TextField bateria;

    public ListView listaParametrow;
    Label labelx = new Label("Test");



    public void pobierzDane(ActionEvent actionEvent) throws IOException {

        int li = 1;

//        Double [][] tab = new Double[5][5];
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5; j++) {
                if(i==j) {
                    Main.macierzKryteriow[i][j] = 1.00;
                    break;
                } else {
                    Slider tmpslider = (Slider) borderpane.lookup("#slider"+li);
                    Double tmpDouble = tmpslider.getValue();
                    if(tmpDouble<0) {
                        Main.macierzKryteriow[i][j] = 1.00/Math.abs(tmpDouble-1);
                        Main.macierzKryteriow[j][i] = Math.abs(tmpDouble)+1;
                    } else {
                        Main.macierzKryteriow[j][i] = 1.00/(tmpDouble+1);
                        Main.macierzKryteriow[i][j] = tmpDouble+1;
                    }
                    li++;
                }
            }
        }

        double suma = 0;
        Double []tabPierw = new Double[5];

        for (int i = 0; i < 5; i++) {
            tabPierw[i] = Main.pierwiastek(Main.macierzKryteriow[i]);
            suma+=tabPierw[i];
        }
        for (int i = 0; i < 5; i++) {
            Main.tablicaWspolKryt[i] = tabPierw[i]/suma * 5;
        }




        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(Main.macierzKryteriow[i][j] + " ");
            }
            System.out.print("\n\n\n");
        }

        Stage sc = (Stage) borderpane.getScene().getWindow();
        sc.close();

        Parent root = FXMLLoader.load(getClass().getResource("alter.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, Main.szerokosc, Main.wysokosc));
        stage.show();


    }



}




