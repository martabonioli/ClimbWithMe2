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


    public void addFakeUscite() {
        uscite.add(new Uscita(() ,"cdjskcd",23.4,54.5,34.0,2.89,"falesia","auto","tutto"));
        uscite.add(new Uscita(2019-10-23 ,"aaaaaaa",23.4,54.5,34.0,2.89,"falesia","auto","tutto")));
    }



    static MyModel getInstance() {
        return ourInstance;
    }


}
