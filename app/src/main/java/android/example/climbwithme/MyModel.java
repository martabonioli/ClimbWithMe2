package android.example.climbwithme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

class MyModel {
    private static final MyModel ourInstance = new MyModel();
    private static MyModel instance = null;

    //Creo arrayList per le uscite
    private ArrayList<Uscita> uscite = null;

    private MyModel() {
        //Istanza delle uscite
        uscite = new ArrayList<Uscita>();
    }


    public void addFakeUscite() {
        SimpleDateFormat format = new SimpleDateFormat("11/23/1998");
        uscite.add(new Uscita(format ,"cdjskcd",23.4,54.5,34.0,2.89,"falesia","auto","tutto"));
        uscite.add(new Uscita(format,"aaaaaaa",23.4,54.5,34.0,2.89,"falesia","auto","tutto"));
    }



    public static synchronized MyModel getInstance() {
        if (instance == null) {
            instance = new MyModel();
        }
        return instance;
    }


}
