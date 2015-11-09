package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    static String[] listaKryteriow = {"Cena","Rozdzielczosc","ISO","Video","Bateria"};
    static Double macierzKryteriow[][] = new Double[5][5];
    static Double tablicaWspolKryt[] = new Double[5];
    static ArrayList<Double[]> listaTablicWspol = new ArrayList<>();
    static Double[] tabRi= {0.0,0.0,0.52,0.89,1.1,1.25,1.35,1.4,1.45,1.49};
    static ArrayList<String> koomunikaty = new ArrayList<>();
    static ArrayList<Double> listaOcenAlternatyw = new ArrayList<>();

    static ArrayList<Aparaty> listaAparatow = new ArrayList<Aparaty>();

    static ArrayList<Double[][]> listaMacierzyAlter = new ArrayList<>();

    public static int wysokosc = 500;
    public static int szerokosc = 500;

    @Override
    public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setScene(new Scene(root, szerokosc, wysokosc));
            primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

/*        Aparaty aparat1 = new Aparaty("Nikon D3300", 496.95, 24.2, 1080, 12800, 700);
        Aparaty aparat2 = new Aparaty("SONY ALPHA A5100", 548.00, 24.3, 1080, 12800, 400);
        Aparaty aparat3 = new Aparaty("PANASONIC LUMIX DMC-ZS45", 229.99, 16.00, 1080, 6400, 350);
        Aparaty aparat4 = new Aparaty("SONY CYBER-SHOT DSC-W800", 88.00, 20.00, 720, 3200, 200);*/


    }



    public static Double pierwiastek(Double []tab) {
        double tmpX = 0;
        for (int i = 0; i < tab.length; i++) {
            tmpX *= tab[i];
        }
        return Math.pow(tmpX, 1/tab.length);
    }




}
