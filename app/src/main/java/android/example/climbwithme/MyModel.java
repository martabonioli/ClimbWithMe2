package android.example.climbwithme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyModel {
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
        uscite.add(new Uscita(format ,23.4, 23.9,45.2,32.3,"falesia","macchina","tutto", "nvjvfhdj","foto","marta",8));
        uscite.add(new Uscita(format ,23.4, 23.9,45.2,32.3,"falesia","macchina","tutto", "nvjvfhdj","foto","luca",9));
    }

    public Uscita get(int index) {
        return uscite.get(index);
    }

    public int getSize() {
        return uscite.size();
    }


    public static synchronized MyModel getInstance() {
        if (instance == null) {
            instance = new MyModel();
        }
        return instance;
    }


}
