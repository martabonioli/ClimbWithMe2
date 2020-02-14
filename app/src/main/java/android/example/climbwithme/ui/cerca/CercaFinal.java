package android.example.climbwithme.ui.cerca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.R;
import android.example.climbwithme.Uscita;
import android.example.climbwithme.ui.profile.VisualizzaUscite;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

public class CercaFinal extends AppCompatActivity implements View.OnClickListener{
    Double latPartenza;
    Double lonPartenza;
    String partenza;
    String arrivo;
    Double latArrivo;
    Double lonArrivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_final);
        latPartenza = getIntent().getExtras().getDouble("latitudinepartenza");
        lonPartenza = getIntent().getExtras().getDouble("longitudinepartenza");
        latArrivo = getIntent().getExtras().getDouble("latitudinearrivo");
        lonArrivo = getIntent().getExtras().getDouble("longitudinearrivo");
        partenza = getIntent().getExtras().getString("luogopartenza");
        arrivo = getIntent().getExtras().getString("luogoarrivo");

        TextView data = findViewById(R.id.data);
        TextView part = findViewById(R.id.part);
        TextView arr = findViewById(R.id.arr);
        data.setText(MyModel.getInstance().cercaUscita.getDataUscita());
        part.setText(partenza);
        arr.setText(arrivo);
        downloadBacheca();
        Button conferma = findViewById(R.id.button3);
        conferma.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if ( MyModel.getInstance().getUscite().isEmpty()) {
            Toast.makeText(this, "Non ci sono uscite nella data inserita", Toast.LENGTH_SHORT).show();
        }else{
            Fragment newFragment = new VisualizzaUscite();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // transaction.remove(getSupportFragmentManager().findFragmentById(R.id.))
            transaction.replace(R.id.cerca_final, newFragment, "visualizzaUscite");
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
            Log.d("clicvisualizza", "hai cliccato");
        }

    }
    private void downloadBacheca() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String url = "https://climbwithme.herokuapp.com/ricercaUscita.php";
        JSONObject datiDaPassare = new JSONObject();
        try {
            datiDaPassare.put("datauscita",MyModel.getInstance().cercaUscita.getDataUscita());
            Log.d("datauscita", MyModel.getInstance().cercaUscita.getDataUscita());
            datiDaPassare.put("codiceSessione",MyModel.getInstance().getSessionId());
            datiDaPassare.put("latluogopartenza", latPartenza);
            datiDaPassare.put("lonluogopartenza", lonPartenza);
            datiDaPassare.put("latluogoarrivo", latArrivo);
            datiDaPassare.put("lonluogoarrivo", lonArrivo);
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
                                MyModel.getInstance().popola(uscite);
                                Log.d("usciteNelModel", String.valueOf((MyModel.getSize())));
                                /* Devo aggiornare l'adapter, non posso farlo in questo Thread secondario, uso Handler per eseguire nel Main thread da uno secondario
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        notifyAdapter();
                                    }
                                });*/
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


}

