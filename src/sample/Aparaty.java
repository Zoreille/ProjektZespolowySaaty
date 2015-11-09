package sample;

/**
 * Created by dominik on 12.10.15.
 */
public class Aparaty {
    String nazwa;
    String video;
    String rozdzielczosc;
    String cena;        //dolary
    String iso;
    String bateria;

    public Aparaty(String naz, String cen, String roz, String vid, String iso_, String bat) {
        nazwa=naz;
        video=vid;
        rozdzielczosc=roz;
        cena=cen;
        iso=iso_;
        bateria=bat;
    }
    public String getNazwa(){
        return nazwa;
    }
    public String getVideo(){
        return video;
    }
    public String getRozdzielczosc(){
        return rozdzielczosc;
    }
    public String getCena(){
        return cena;
    }
    public String getIso(){
        return iso;
    }
    public String getBateria(){
        return bateria;
    }

    
}
