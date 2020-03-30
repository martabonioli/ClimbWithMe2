package android.example.climbwithme;

import android.Manifest;
import android.content.Intent;
import android.example.climbwithme.ui.cerca.LuogoPartenza;
import android.example.climbwithme.ui.profile.NumberPickerDialog;
import android.example.climbwithme.ui.profile.ProfileFragment;
import android.example.climbwithme.ui.profile.VisualizzaUscite;
import android.example.climbwithme.ui.proponi.LuogoPartenzaProponi;
import android.view.MenuItem;
import android.content.pm.PackageManager;
import android.example.climbwithme.ui.bacheca.AdapterUscita;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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

import java.util.List;

import static androidx.navigation.ui.NavigationUI.navigateUp;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private FusedLocationProviderClient fusedLocationClient;
    public double latultimapos;
    public double lonultimapos;
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

    public void onClickAvantiProponi1 (View v){
       if (!MyModel.getInstance().cercaUscita.getDataUscita().equals("")) {
           Intent intent = new Intent(getApplicationContext(), LuogoPartenzaProponi.class);
           startActivity(intent);
       }else{
           Toast.makeText(getApplicationContext(),"Inserisci la data",Toast.LENGTH_SHORT).show();
       }
    }

    public void onClickAvantiCerca (View v){
        if (!MyModel.getInstance().cercaUscita.getDataUscita().equals("")) {
            Intent intent = new Intent(getApplicationContext(), LuogoPartenza.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Inserisci la data",Toast.LENGTH_SHORT).show();

        }
    }
    public void onClickVisualizzaUscite(View v){
        if ( MyModel.getInstance().getUscite().isEmpty()) {
            Toast.makeText(this, "Non hai pubblicato uscite", Toast.LENGTH_SHORT).show();
        }else{
            Fragment newFragment = new VisualizzaUscite();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // transaction.remove(getSupportFragmentManager().findFragmentById(R.id.))
            transaction.replace(R.id.nav_host_fragment, newFragment, "visualizzaUscite");
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
            Log.d("clicvisualizza", "hai cliccato");
        }
    }



}

//TODO LIST -->     RISOLVERE ROBE DATA- SISTEMARE DB (AGGIUNGERE FOTO) E SISTEMARE CHIAMATE PHP - FARE PROTOTIPO DI UI - COME PRENDERE I DATI