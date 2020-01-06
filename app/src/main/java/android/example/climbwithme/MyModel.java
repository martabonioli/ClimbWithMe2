package android.example.climbwithme;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyModel {
    private static final MyModel ourInstance = new MyModel();
    private static MyModel instance = null;
    private static Utente utente;
    private static int i = 0;


    //Creo arrayList per le uscite
    private static ArrayList<Uscita> uscite = null;

    private MyModel() {
        //Istanza delle uscite
        uscite = new ArrayList<Uscita>();
    }


    public static void popola(List<Uscita> uscit){
        uscite = new ArrayList<>(uscit);
    }

    public ArrayList<Uscita> getUscite(){return uscite; }


   


    public void addFakeUscite() {

        uscite.add(new Uscita("19/12/2019" ,23.4, 23.9,45.2,32.3,"falesia","macchina","tutto", "nvjvfhdj","foto","Marta",8));
        uscite.add(new Uscita("20/12/2019" ,23.4, 23.9,45.2,32.3,"falesia","macchina","tutto", "nvjvfhdj","foto","Luca",9));
    }

    public Uscita get(int index) {
        return uscite.get(index);
    }
    public static int getSize() {
        return uscite.size();
    }


    public static synchronized MyModel getInstance() {
        if (instance == null) {
            instance = new MyModel();
        }
        return instance;

    }

    public  static void setSessionId(String sessionId) {
        utente = new Utente(sessionId,"","","","", 0,0,0);

    }



    public static String getSessionId() {
        return utente.getCodiceSessione();
    }

    public static int getI(){
        return i;
    }

    public static void updateI(){
        i = i+1;
    }




    public static List<Uscita> deserialize(JSONObject serverResponse) {
        Log.d("Android6","Deserializzando");
        List<Uscita> list = new ArrayList<>();
        try {
            JSONArray usciteJSON = serverResponse.getJSONArray("uscite");
            for (int i = 0; i < usciteJSON.length(); i++) {
                JSONObject uscitaJSON = usciteJSON.getJSONObject(i);
                Uscita uscita = new Uscita (uscitaJSON);
                list.add(uscita);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }



}
