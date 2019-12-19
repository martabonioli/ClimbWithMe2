package android.example.climbwithme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyModel {
    private static final MyModel ourInstance = new MyModel();
    private static MyModel instance = null;
    private static Utente utente;

    //Creo arrayList per le uscite
    private ArrayList<Uscita> uscite = null;

    private MyModel() {
        //Istanza delle uscite
        uscite = new ArrayList<Uscita>();
    }

    /*popolo
    public void populate (List<Uscita> uscite){
        uscite = new ArrayList<>(uscite);
    }*/

    public ArrayList<Uscita> getUscite(){return uscite; }

    /*metodo utile al Main
    public static List<Uscita> deserialize(JSONObject serverResponse) {
        Log.d("Android6","Deserializzando");
        List<Uscita> list = new ArrayList<>();
        try {
            JSONArray usciteJSON = serverResponse.getJSONArray("uscite");
            for (int i = 0; i < usciteJSON.length(); i++) {
                JSONObject uscitaJSON = usciteJSON.getJSONObject(i);
                Uscita uscita = new Uscita(uscitaJSON);
                list.add(uscita);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }*/


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

    public  static void setSessionId(String sessionId) {

        utente = new Utente(sessionId,"","","","", 0,0,0);

    }

    public static String getSessionId() {
        return utente.getCodiceSessione();
    }




}
