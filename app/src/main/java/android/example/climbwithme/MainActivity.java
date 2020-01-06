package android.example.climbwithme;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private final String PREFS_NAME = "registrazioneImplicita";
    private final String SESSION_ID_PREF_NAME = "sessionId";
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_bacheca, R.id.navigation_proponi, R.id.navigation_cerca, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //I permessi non sono stati (ancora) concessi

        } else {
            //I permessi sono stati concessi

        }
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.d("Location", "Last Known location AVAILABLE");
                        } else {
                            Log.d("Location", "Last Known location NOT available");
                        }
                    }
                });
    checkPrimoAvvio();
    downloadData();





    }

    private void checkPrimoAvvio() {
        if (getSharedPreferences(PREFS_NAME, 0).getString(SESSION_ID_PREF_NAME, null) == null) {
            richiediSessionId();
        } else {
            MyModel.setSessionId(getSharedPreferences(PREFS_NAME, 0).getString(SESSION_ID_PREF_NAME, null));
            Log.d("qwerty", MyModel.getSessionId());
        }
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//LNcwkVSFV3X88V1o
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


    private void downloadData() {
        //dati per la bacheca
        Log.d("entra", "Si");
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String urlRicercaUscita = "https://climbwithme.herokuapp.com/ricercaUscita.php";
        final JSONObject dataDiOggi = new JSONObject();
        try {
            //aggiungere anche ultima posizione
            dataDiOggi.put("datauscita", "2020-01-03");
            dataDiOggi.put("codiceSessione","LpYRKlbnBhJ60xQp" );
            dataDiOggi.put("lauultimapos", 15.6);
            dataDiOggi.put("lonultimapos", 16.7);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request2 = new JsonObjectRequest(
                urlRicercaUscita,
                dataDiOggi,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("VolleyBacheca", "Correct: " + response.toString());
                        List<Uscita> list = new ArrayList<>();
                        try {
                            JSONArray usciteJSON = response.getJSONArray("uscite");
                            for (int i = 0; i < usciteJSON.length(); i++) {
                                JSONObject uscitaJSON = usciteJSON.getJSONObject(i);
                                Uscita uscita = new Uscita(uscitaJSON);
                                list.add(uscita);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MyModel.popola(list);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley", "Error: " + error.toString());
            }
        });
        Log.d("Volley", "Sending request");
        mRequestQueue.add(request2);
    }


}


//TODO LIST -->     RISOLVERE ROBE DATA-
// ESISTEMARE CHIAMATE PHP, set e get foto
// FARE PROTOTIPO DI UI daFareSubito
// COME PRENDERE I DATI 