package android.example.climbwithme;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.example.climbwithme.ui.bacheca.AdapterUscita;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private final String PREFS_NAME = "registrazioneImplicita";
    private final String SESSION_ID_PREF_NAME = "sessionId";
    private FusedLocationProviderClient fusedLocationClient;
    private double latultimapos;
    private double lonultimapos;
    RecyclerView list;
    private AdapterUscita adapterUscita;

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
                            latultimapos = location.getLatitude();
                            lonultimapos = location.getLongitude();
                            if (getSharedPreferences(PREFS_NAME, 0).getString(SESSION_ID_PREF_NAME, null) == null) {
                                richiediSessionId();
                            } else {
                                MyModel.setSessionId(getSharedPreferences(PREFS_NAME, 0).getString(SESSION_ID_PREF_NAME, null));
                                Log.d("qwerty", MyModel.getSessionId());
                                downloadBacheca();
                            }
                            Log.d("Posizioo", String.valueOf(location.getLatitude()));
                            Log.d("Posizioo", "AAA2" + String.valueOf(latultimapos));
                        } else {

                            Log.d("Location", "Last Known location NOT available");
                        }
                    }
                });





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
                            downloadBacheca();
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


    private void downloadBacheca() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = '"' + sdf.format(new Date()) + '"';

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://climbwithme.herokuapp.com/ricercaUscita.php";
        String sessId = '"' + MyModel.getSessionId() + '"';

        JSONObject datiDaPassare = new JSONObject();
        try {
            datiDaPassare.put("datauscita", currentDateandTime );
            datiDaPassare.put("codiceSessione",MyModel.getSessionId());
            datiDaPassare.put("lauultimapos", latultimapos);
            datiDaPassare.put("lonultimapos", lonultimapos);
            Log.d("Posizioo", "BBB" + String.valueOf(latultimapos));
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
                        final List<Uscita> uscite = MyModel.deserialize(response);
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                MyModel.popola(uscite);
                                Log.d("usciteNelModel", String.valueOf((MyModel.getSize())));
                                // Devo aggiornare l'adapter, non posso farlo in questo Thread secondario, uso Handler per eseguire nel Main thread da uno secondario
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        notifyAdapter();
                                    }
                                });
                            }
                        }.start();

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




   public void notifyAdapter() {
        Log.d("Android6","Aggiorno adapter");
        // Aggiorno l'adapter
        list = findViewById(R.id.text_bacheca);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapterUscita = new AdapterUscita(this, MyModel.getInstance().getUscite());
        list.setAdapter(adapterUscita);
    }

}


//TODO LIST -->     RISOLVERE ROBE DATA- SISTEMARE DB (AGGIUNGERE FOTO) E SISTEMARE CHIAMATE PHP - FARE PROTOTIPO DI UI - COME PRENDERE I DATI