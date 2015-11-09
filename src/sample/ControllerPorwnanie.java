package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Created by dominik on 19.10.15.
 */
public class ControllerPorwnanie implements Initializable{

    public BorderPane porownanie;
    public Label label;
    VBox vboxAll = new VBox();
    int nrMacierzy = 0;
    int n = Main.listaAparatow.size();


    public void ustawListe() {
        ListView<String> listViewModele = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList(
                "Single", "Double", "Suite", "Family App");
        listViewModele.setItems(items);
        listViewModele.setPrefWidth(100);
        listViewModele.setPrefHeight(70);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {



        try {
            dodajHboxy();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        label.setFont(new Font("Arial", 20));
        porownanie.setCenter(vboxAll);
    }

    void dodajHboxy() throws Exception{
    // zmienna liczba boxów
        label.setText(Main.listaKryteriow[nrMacierzy]);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j) {
                    break;
                }
                HBox hboxTmp = new HBox();
                Label labelTmp = new Label(Main.listaAparatow.get(i).getNazwa());
                Slider sliderTmp = new Slider();
                sliderTmp.setBlockIncrement(1.0);
                sliderTmp.setMajorTickUnit(1.0);
                sliderTmp.setSnapToTicks(true);
                sliderTmp.setShowTickMarks(true);
                sliderTmp.setMin(-8);
                sliderTmp.setMax(8);
                sliderTmp.setMinWidth(273);
                sliderTmp.setMinorTickCount(0);


                sliderTmp.setId("slider" + i + j);
                Label labelTmp2 = new Label(Main.listaAparatow.get(j).getNazwa());
                hboxTmp.getChildren().addAll(labelTmp, sliderTmp, labelTmp2);

                vboxAll.getChildren().addAll(hboxTmp);
            }
        }



    }


    public static long factorial(int N)
    {
        long multi = 1;
        for (int i = 1; i <= N; i++) {
            multi = multi * i;
        }
        return multi;
    }

    public void ZapiszDoMacierzy(ActionEvent actionEvent) {        //ActionEvent actionEvent
        if(nrMacierzy > 4) {
            Dalej();
            return;
        }
        Double [][] macierzTmp = new Double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j) {
                    macierzTmp[i][j] = 1.00;
                    break;
                } else {
                    Slider tmpslider = (Slider) porownanie.lookup("#slider"+i+j);
                    Double tmpDouble = tmpslider.getValue();
                    if(tmpDouble<0) {
                        macierzTmp[i][j] = 1.00/Math.abs(tmpDouble-1);
                        macierzTmp[j][i] = Math.abs(tmpDouble)+1;
                    } else {
                        macierzTmp[j][i] = 1.00/(tmpDouble+1);
                        macierzTmp[i][j] = tmpDouble+1;
                    }
                    tmpslider.setValue(0);

                }
            }
        }
        double suma = 0;
        Double []tabPierw = new Double[n];
        Double []tabWspolczynnikow = new Double[n];

        for (int i = 0; i < n; i++) {
            tabPierw[i] = Main.pierwiastek(macierzTmp[i]);
            suma+=tabPierw[i];
        }
        for (int i = 0; i < n; i++) {
            tabWspolczynnikow[i] = tabPierw[i]/suma * n;
        }
        Main.listaTablicWspol.add(tabWspolczynnikow);

        Main.listaMacierzyAlter.add(macierzTmp);
        System.out.print("\n\n"+Main.listaKryteriow[nrMacierzy]);

        System.out.print("\n Kolejne: \n");
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(macierzTmp[i][j] + " ");
            }
            System.out.print("\n\n\n");
        }
        nrMacierzy++;
        label.setText(Main.listaKryteriow[nrMacierzy]);

    }

    void Dalej() {
        double maxWart = 0.0;
        for (int j = 0; j < 5; j++) {
            double sumaKol = 0;
            for (int i = 0; i < 5; i++) {
                sumaKol += Main.macierzKryteriow[i][j];
            }
            maxWart += sumaKol * Main.tablicaWspolKryt[j];
        }
        maxWart /= 5;
        double ci = (maxWart-5)/4;
        double cr = Math.abs(ci/Main.tabRi[4]);
        if(ci > 0.1 || cr > 0.1) {
            Main.koomunikaty.add("Niespojnooscw macierzy parzystych porownan kryteriow!");
        }

        for (int nr = 0; nr < 5; nr++) {
            double maxWartAlt = 0.0;
            for (int j = 0; j < n; j++) {
                double sumaKol = 0;
                for (int i = 0; i < n; i++) {
                    sumaKol += Main.listaMacierzyAlter.get(nr)[i][j];
                }
                maxWartAlt += sumaKol * Main.listaTablicWspol.get(nr)[j];
            }
            maxWartAlt /= n;
            double ciAlt = (maxWartAlt-n)/(n-1);
            double crAlt = Math.abs(ciAlt/Main.tabRi[4]);
            if(ciAlt > 0.1 || crAlt > 0.1) {
                Main.koomunikaty.add("Niespojnoosc w macierzy parzystych poorownan alternatyw " + nr + " !");
            }
        }



        for (int i = 0; i < n; i++) {
            double warOceny = 0;
            for (int j = 0; j < 5; j++) {
                warOceny += Main.tablicaWspolKryt[j] * Main.listaTablicWspol.get(j)[i];
            }
            Main.listaOcenAlternatyw.add(warOceny);
        }
        boolean flag = true;
        Aparaty tmp;
        while (flag) {
            flag = false;
            for (int i = 0; i < n-1; i++) {
                if (Main.listaOcenAlternatyw.get(i) < Main.listaOcenAlternatyw.get(i + 1)) {
                    tmp = Main.listaAparatow.get(i);
                    Main.listaAparatow.set(i, Main.listaAparatow.get(i + 1));
                    Main.listaAparatow.set(i + 1, tmp);
                    flag = true;
                }
            }
        }
        Main.listaOcenAlternatyw.sort(Comparator.<Double>reverseOrder());

        for (int i = 0; i < n; i++) {
            System.out.println(Main.listaAparatow.get(i).getNazwa() + "  " + Main.listaOcenAlternatyw.get(i));
        }
/*
        Stage sc = (Stage) porownanie.getScene().getWindow();
        sc.close();

        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("wyniki.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, Main.szerokosc, Main.wysokosc));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    //spojnoosc macierzy





}
