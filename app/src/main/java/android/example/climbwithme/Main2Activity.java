package android.example.climbwithme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    private int SPLASH_TIME = 3000; //This is 3 seconds
    private final String PREFS_NAME = "registrazioneImplicita";
    private final String SESSION_ID_PREF_NAME = "sessionId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getSharedPreferences(PREFS_NAME, 0).getString(SESSION_ID_PREF_NAME, null) == null) {
                    richiediSessionId();
                    Intent intent = new Intent(getApplicationContext(), InserimentoDati.class);
                    startActivity(intent);


                } else {
                    MyModel.setSessionId(getSharedPreferences(PREFS_NAME, 0).getString(SESSION_ID_PREF_NAME, null));
                    Log.d("qwerty", MyModel.getSessionId());
                    //prendoTuttiIdatidell'utente e li inserisco nel model
                    downloadDatiUtente();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


                }
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(mySuperIntent);

                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                finish();

            }
        }, SPLASH_TIME);
    }

    private void richiediSessionId() {
        //richiesta sessionId
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://climbwithme.herokuapp.com/sessionID.php";
        JsonObjectRequest request = new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley", "Correct: " + response.toString());
                        try {
                            MyModel.setSessionId(response.get("codiceSessione").toString());
                            Log.d("daje","TOP" );

                            //VADO ALL'INSERIMENTO DATI
                            Intent intent = new Intent(getApplicationContext(), InserimentoDati.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        getSharedPreferences(PREFS_NAME, 0).edit().putString(SESSION_ID_PREF_NAME, MyModel.getSessionId()).apply();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley", "Error: " + error.toString());
            }
        });
        Log.d("Volley", "Sending request");
        mRequestQueue.add(request);

    }
    private void downloadDatiUtente() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String url = "https://climbwithme.herokuapp.com/getutente.php";
        JSONObject datiDaPassare = new JSONObject();
        try {
            datiDaPassare.put("codiceSessione",MyModel.getSessionId());
            Log.d("codicesessioneVolley",MyModel.getSessionId());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(
                url,
                datiDaPassare,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("VolleyBacheca", "Correct: " + response.toString());

                       //prendi i dati e mettili nel model


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley", "Error: " + error.toString());
            }
        });
        Log.d("Volley", "Sending request");
        mRequestQueue.add(request);
    }


}
