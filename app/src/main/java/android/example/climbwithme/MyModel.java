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
    public static Utente utente;
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

    public ArrayList<Uscita> getUscite(){
        return uscite;
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
        utente = new Utente(sessionId,"","","","", 0,0,0,"");

    }

    public static String getSessionId() {
        return utente.getCodiceSessione();
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
