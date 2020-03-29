package android.example.climbwithme.ui.proponi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.example.climbwithme.MainActivity;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.R;
import android.example.climbwithme.Uscita;
import android.example.climbwithme.ui.cerca.LuogoPartenza;
import android.example.climbwithme.ui.profile.VisualizzaUscite;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;

public class ProponiFinal extends AppCompatActivity implements View.OnClickListener {

    Double latPartenza;
    Double lonPartenza;
    String partenza;
    String arrivo;
    String attrezzatura, mezzo, tipoArr;
    Double latArrivo;
    Double lonArrivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proponi_final);
        latPartenza = getIntent().getExtras().getDouble("latitudinepartenza");
        lonPartenza = getIntent().getExtras().getDouble("longitudinepartenza");
        latArrivo = getIntent().getExtras().getDouble("latitudinearrivo");
        lonArrivo = getIntent().getExtras().getDouble("longitudinearrivo");
        partenza = getIntent().getExtras().getString("luogopartenza");
        arrivo = getIntent().getExtras().getString("luogoarrivo");
        attrezzatura= getIntent().getExtras().getString("attrezz");
        Log.d("attrezzaturaFinal",attrezzatura);
        tipoArr= getIntent().getExtras().getString("tipoarr");
        mezzo= getIntent().getExtras().getString("mezzo");


        TextView data = findViewById(R.id.data);
        TextView part = findViewById(R.id.part);
        TextView arr = findViewById(R.id.arr);
        data.setText(MyModel.getInstance().cercaUscita.getDataUscita());
        part.setText(partenza);
        arr.setText(arrivo);
        TextView addmezzo = findViewById(R.id.mezzo);
        addmezzo.setText(mezzo);
        TextView addattrezzatura = findViewById(R.id.textattrezzatura);
        addattrezzatura.setText(attrezzatura);
        TextView addtipoArr = findViewById(R.id.tipoarrampicata);
        addtipoArr.setText(tipoArr);

        Button conferma = findViewById(R.id.button3);
        conferma.setOnClickListener(this);
        ImageButton indietro = findViewById(R.id.returnc);
        indietro.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button3:
                pubblica();
                Toast.makeText(this, "Uscita Pubblicata", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            break;
            case R.id.returnc:
                Intent i = new Intent(getApplicationContext(), LuogoPartenzaProponi.class);
                startActivity(i);
                break;
        }

    }
    private void pubblica() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String url = "https://climbwithme.herokuapp.com/iserisciuscita.php";
        JSONObject datiDaPassare = new JSONObject();
        try {
            datiDaPassare.put("datauscita",MyModel.getInstance().cercaUscita.getDataUscita());
            Log.d("Pubblica data", MyModel.getInstance().cercaUscita.getDataUscita());
            datiDaPassare.put("codiceSessione",MyModel.getInstance().getSessionId());
            Log.d("Pubblica codice sess ", MyModel.getInstance().getSessionId());
            datiDaPassare.put("latluogopartenza", latPartenza);
            Log.d("Pubblica latPart", latPartenza.toString());
            datiDaPassare.put("lonluogopartenza", lonPartenza);
            Log.d("Pubblica lonPart", lonPartenza.toString());
            datiDaPassare.put("latluogoarrivo", latArrivo);
            Log.d("Pubblica latArr", latArrivo.toString());
            datiDaPassare.put("lonluogoarrivo", lonArrivo);
            Log.d("Pubblica lonArr", lonArrivo.toString());
            datiDaPassare.put("tipoarrampicata", tipoArr);
            Log.d("Pubblica tipo arra", tipoArr);
            datiDaPassare.put("mezzotrasporto", mezzo);
            Log.d("Pubblica mezzo", mezzo);
            datiDaPassare.put("attrezzatura", attrezzatura);
            Log.d("Pubblica attrezzaTURA", attrezzatura);
            Log.d("Pubblica json", datiDaPassare.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                datiDaPassare,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Pubblica Volley", "Correct: " + response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Pubblica Volley", "Error: " + error.toString());

            }
        });
        Log.d("Volley", "Sending request");
        mRequestQueue.add(request);
    }

}
