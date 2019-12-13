package android.example.climbwithme;

import java.util.ArrayList;

class MyModel {
    private static final MyModel ourInstance = new MyModel();

    //Creo arrayList per le uscite
    private ArrayList<Uscita> uscite = null;

    private MyModel() {
        //Istanza delle uscite
        uscite = new ArrayList<Uscita>();
    }



    static MyModel getInstance() {
        return ourInstance;
    }


}
