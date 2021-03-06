package android.example.climbwithme.ui.bacheca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.example.climbwithme.MainActivity;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.R;
import android.example.climbwithme.Uscita;
import android.example.climbwithme.Utente;
import android.example.climbwithme.ui.cerca.LuogoPartenza;
import android.example.climbwithme.ui.cerca.VisualizzaUsciteCerca;
import android.example.climbwithme.ui.profile.VisualizzaUscite;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class DettagliUscita extends AppCompatActivity implements View.OnClickListener {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    public static final String USCITA_EXTRA = "uscita";
    String data, codicesessione;
    private String numero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_uscita);

        ImageButton indietro = findViewById(R.id.returnc);
        indietro.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        final String uscita = getIntent().getStringExtra(USCITA_EXTRA);
        Log.d("idUscita", uscita);
        String[] split = uscita.split(" ");
        data = split[0];
        Log.d("dataDettagli", data);
        codicesessione = split[1];
        Log.d("codicesessDettagli", codicesessione);
        TextView adddata = findViewById(R.id.data);
        adddata.setText(data);
        downloadUscita();


    }

    private void downloadUscita() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String url = "https://climbwithme.herokuapp.com/getuscita.php";
        JSONObject datiDaPassare = new JSONObject();
        try {
            datiDaPassare.put("datauscita", data);
            datiDaPassare.put("codiceSessione", MyModel.getInstance().getSessionId());
            datiDaPassare.put("codsessione", codicesessione);
            Log.d("codicesessDettagli", codicesessione);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest request = new JsonObjectRequest(
                url,
                datiDaPassare,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("VolleyDettagli", "Correct: " + response.toString());

                        //scaricadati utente
                        downloadUtenteUscita();
                        getResponse1(response);
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

    private void downloadUtenteUscita() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String url = "https://climbwithme.herokuapp.com/getutente.php";
        JSONObject datiDaPassare = new JSONObject();
        try {
            datiDaPassare.put("codiceSessione", codicesessione);

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
                        try {
                            JSONArray utentiJSON = response.getJSONArray("utente");
                            Log.d("jsonObj", String.valueOf(utentiJSON));
                            JSONObject utenteJSON = utentiJSON.getJSONObject(0);
                            Log.d("jsonObj", String.valueOf(utenteJSON));
                            Utente u = new Utente(utenteJSON);
                            TextView addcontatta = findViewById(R.id.contatta);
                            addcontatta.setText("CONTATTA IL NUMERO  +39 " + u.getNumeroTelefono());
                            numero = u.getNumeroTelefono();
                            TextView livello = findViewById(R.id.addminliv);
                            livello.setText("" + matchLivello(u.getMinLiv()) + "   /   " + matchLivello(u.getMaxLiv()));
                            TextView max = findViewById(R.id.addpb2);
                            max.setText(matchLivello(u.getLivelloMaxLead()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley", "Error: " + error.toString());
            }
        });

        mRequestQueue.add(request);
    }


    private void getResponse1(JSONObject serverResponse) {
        try {
            JSONArray usciteJSON = serverResponse.getJSONArray("uscite");
            Log.d("usciteJSON", String.valueOf(usciteJSON));
            JSONObject uscitaJSON = usciteJSON.getJSONObject(0);
            Uscita thisUscita = new Uscita(uscitaJSON);

            TextView partenza = findViewById(R.id.part);
            partenza.setText(getCompleteAddressString(thisUscita.getLatLuogoPartenza(), thisUscita.getLonLuogoPartenza()));
            TextView arrivo = findViewById(R.id.arr);
            arrivo.setText(getCompleteAddressString(thisUscita.getLatLuogoArrivo(), thisUscita.getLonLuogoArrivo()));
            TextView attrezzatura = findViewById(R.id.textattrezzatura);
            attrezzatura.setText(thisUscita.getAttrezzatura() + " ");
            TextView mezzo = findViewById(R.id.mezzo);
            mezzo.setText(thisUscita.getMezzoTrasporto());
            TextView nome = findViewById(R.id.nome);
            nome.setText(thisUscita.getNome());
            ImageView foto = findViewById(R.id.foto);
            foto.setImageBitmap(convert(thisUscita.getFoto()));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private Bitmap convert(String foto) {
        String b64img = foto; //prendo l'immagine sotto forma di stringa
        byte[] decodedString = Base64.decode(b64img, Base64.DEFAULT); //la converto in bytestream
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); //converto il bytestream in Bitmap
        return decodedByte;
    }

    public String matchLivello(int s) {
        String livello = "";
        if (s == 1) {
            livello = "4a";
        }
        if (s == 2) {
            livello = "4a+";
        }
        if (s == 3) {
            livello = "4b";
        }
        if (s == 4) {
            livello = "4b+";
        }
        if (s == 5) {
            livello = "4c";
        }
        if (s == 6) {
            livello = "4c+";
        }
        if (s == 7) {
            livello = "5a";
        }
        if (s == 8) {
            livello = "5a+";
        }
        if (s == 9) {
            livello = "5b";
        }
        if (s == 10) {
            livello = "5b+";
        }
        if (s == 11) {
            livello = "5c";
        }
        if (s == 12) {
            livello = "5c+";
        }
        if (s == 13) {
            livello = "6a";
        }
        if (s == 14) {
            livello = "6a+";
        }
        if (s == 15) {
            livello = "6b";
        }
        if (s == 16) {
            livello = "6b+";
        }
        if (s == 17) {
            livello = "6c";
        }
        if (s == 18) {
            livello = "6c+";
        }
        if (s == 19) {
            livello = "7a";
        }
        if (s == 20) {
            livello = "7a+";
        }
        if (s == 21) {
            livello = "7b";
        }
        if (s == 22) {
            livello = "7b+";
        }
        if (s == 23) {
            livello = "7c";
        }
        if (s == 24) {
            livello = "7c+";
        }
        if (s == 25) {
            livello = "8a";
        }
        if (s == 26) {
            livello = "8a+";
        }
        if (s == 27) {
            livello = "8b";
        }
        if (s == 28) {
            livello = "8b+";
        }
        if (s == 29) {
            livello = "8c";
        }
        if (s == 30) {
            livello = "8c+";
        }
        if (s == 31) {
            livello = "9a";
        }
        if (s == 32) {
            livello = "9a+";
        }
        if (s == 33) {
            livello = "9b";
        }
        if (s == 34) {
            livello = "9b+";
        }
        if (s == 35) {
            livello = "9c";
        }
        if (s == 36) {
            livello = "9c+";
        }
        return livello;
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }


    @Override
    public void onClick(View v) {
        Fragment newFragment = new VisualizzaUsciteCerca();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.dettagliuscita, newFragment, "visualizzaUsciteCerca");
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    public void chiama(View v){
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall();
        }else {

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                phoneCall();
            }else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                //Asking request Permissions
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 9);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch(requestCode){
            case 9:
                permissionGranted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(permissionGranted){
            phoneCall();
        }else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void phoneCall(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+numero));
            this.startActivity(callIntent);
        }else{
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }
}
