package android.example.climbwithme;

import android.Manifest;
import android.content.Intent;
import android.example.climbwithme.ui.cerca.LuogoPartenza;
import android.example.climbwithme.ui.profile.NumberPickerDialog;
import android.example.climbwithme.ui.profile.ProfileFragment;
import android.view.MenuItem;
import android.content.pm.PackageManager;
import android.example.climbwithme.ui.bacheca.AdapterUscita;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.ui.NavigationUI.navigateUp;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;

    private FusedLocationProviderClient fusedLocationClient;
    private double latultimapos;
    private double lonultimapos;
    RecyclerView list;
    private AdapterUscita adapterUscita;
    public NavController navController;
    public AppBarConfiguration appBarConfiguration;
    public BottomNavigationView navView;

    public static final String BUNDLE_KEY_TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_bacheca, R.id.navigation_proponi, R.id.navigation_cerca, R.id.navigation_profile)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //I permessi non sono stati (ancora) concessi

        } else {
            //I permessi sono stati concessi
            Log.d("sess1", MyModel.getSessionId().toString() );
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

                            Log.d("Posizioo", String.valueOf(location.getLatitude()));
                            Log.d("Posizioo", "AAA2" + String.valueOf(latultimapos));
                        } else {

                            Log.d("Location", "Last Known location NOT available");
                        }
                    }
                });


    }






   /* private void downloadBacheca() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = '"' + sdf.format(new Date()) + '"';

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://climbwithme.herokuapp.com/ricercaUscita.php";
        String sessId = '"' + MyModel.getSessionId() + '"';

        JSONObject datiDaPassare = new JSONObject();
        try {
            datiDaPassare.put("datauscita", "2020-01-07" );
            datiDaPassare.put("codiceSessione",MyModel.getSessionId());
            datiDaPassare.put("lauultimapos", latultimapos);
            datiDaPassare.put("lonultimapos", lonultimapos);
            Log.d("Posizioo", "BBB" + String.valueOf(lonultimapos));
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
    }*/




   /*public void notifyAdapter() {
        Log.d("Android6","Aggiorno adapter");
        // Aggiorno l'adapter
        list = findViewById(R.id.text_bacheca);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapterUscita = new AdapterUscita(this, MyModel.getInstance().getUscite());
        list.setAdapter(adapterUscita);
    }*/

   public void onClickSearch(View v){
       MenuItem cerca = navView.getMenu().findItem(R.id.navigation_cerca);
       NavigationUI.onNavDestinationSelected(cerca, navController);

   }

    public void onClickProponi(View v){
        MenuItem proponi = navView.getMenu().findItem(R.id.navigation_proponi);
        NavigationUI.onNavDestinationSelected(proponi, navController);

    }

    public void onClickAvanti (View v){
        Intent intent = new Intent(getApplicationContext(), LuogoPartenza.class);
        startActivity(intent);
    }


    /*public void onClickDatePick(View v ){
        View view = inflater.inflate(R.layout.fragment_cerca, null);
        FragmentManager fm = getSupportFragmentManager();

//if you added fragment via layout xml
        CercaFragment fragment = (CercaFragment) fm.findFragmentById(R.id.navigation_cerca);
        fragment.onClickDP();
    }*/

}

//TODO LIST -->     RISOLVERE ROBE DATA- SISTEMARE DB (AGGIUNGERE FOTO) E SISTEMARE CHIAMATE PHP - FARE PROTOTIPO DI UI - COME PRENDERE I DATI