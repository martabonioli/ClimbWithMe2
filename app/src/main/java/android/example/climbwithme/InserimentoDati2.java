package android.example.climbwithme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class InserimentoDati2 extends AppCompatActivity implements View.OnClickListener{

    private static final String[] paths = { "4a","4a+","4b","4b+","4c","4c+","5a","5a+","5b","5b+","5c","5c+","6a","6a+","6b","6b+","6c","6c+","7a","7a+","7b","7b+","7c","7c+","8a","8a+","8b","8b+","8c","8c+","9a","9a+","9b","9b+","9c","9c+"};
    private int livello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_dati2);

        Button addConfirm = findViewById(R.id.entra);
        addConfirm.setOnClickListener(this);

        ImageButton modify = findViewById(R.id.imageButton);
        modify.setOnClickListener(this);

        ImageButton modify2= findViewById(R.id.imageButton2);
        modify2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        switch(v.getId()) {
            case R.id.imageButton:
                final TextView tv = (TextView) findViewById(R.id.tv);
                NumberPicker np = (NumberPicker) findViewById(R.id.np);
                np.setVisibility(View.VISIBLE);

                //Set TextView text color
                tv.setTextColor(Color.parseColor("#3F51B5"));

                //Initializing a new string array with elements
                final String[] values= {"4a","4a+","4b","4b+","4c","4c+","5a","5a+","5b","5b+","5c","5c+","6a","6a+","6b","6b+","6c","6c+","7a","7a+","7b","7b+","7c","7c+","8a","8a+","8b","8b+","8c","8c+","9a","9a+","9b","9b+","9c","9c+"};

                //Populate NumberPicker values from String array values
                //Set the minimum value of NumberPicker
                np.setMinValue(0);
                //Specify the maximum value/number of NumberPicker
                np.setMaxValue(values.length-1);

                //Specify the NumberPicker data source as array elements
                np.setDisplayedValues(values);

                //Gets whether the selector wheel wraps when reaching the min/max value.
                np.setWrapSelectorWheel(true);

                //Set a value change listener for NumberPicker
                np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                        NumberPicker np = (NumberPicker) findViewById(R.id.np);
                        //Display the newly selected value from picker
                        tv.setText("" + values[newVal]);
                    }

                });

            break;
            case R.id.imageButton2:
                final TextView tv2 = (TextView) findViewById(R.id.tv2);
                NumberPicker np2 = (NumberPicker) findViewById(R.id.np2);
                np2.setVisibility(View.VISIBLE);

                tv2.setTextColor(Color.parseColor("#3F51B5"));
                //Initializing a new string array with elements
                final String[] values2= {"4a","4a+","4b","4b+","4c","4c+","5a","5a+","5b","5b+","5c","5c+","6a","6a+","6b","6b+","6c","6c+","7a","7a+","7b","7b+","7c","7c+","8a","8a+","8b","8b+","8c","8c+","9a","9a+","9b","9b+","9c","9c+"};

                //Populate NumberPicker values from String array values
                //Set the minimum value of NumberPicker
                 np2.setMinValue(0);
                //Specify the maximum value/number of NumberPicker
                  np2.setMaxValue(values2.length-1);//to array last value

                //Specify the NumberPicker data source as array elements
               np2.setDisplayedValues(values2);

                //Gets whether the selector wheel wraps when reaching the min/max value.
                np2.setWrapSelectorWheel(true);
                //Populate NumberPicker values from String array values
                //Set the minimum value of NumberPicker
                np2.setMinValue(0);
                //Specify the maximum value/number of NumberPicker
               np2.setMaxValue(values2.length-1);//to array last value

                //Specify the NumberPicker data source as array elements
                np2.setDisplayedValues(values2);

                //Gets whether the selector wheel wraps when reaching the min/max value.
                np2.setWrapSelectorWheel(true);

                np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                        //Display the newly selected value from picker
                        tv2.setText("" + values2[newVal]);

                    }
                });
            break;
            case R.id.entra:
                //prendi i dati inseriti e salvali sul model
                TextView addLivmin = (TextView) findViewById(R.id.tv);
                TextView addLivmax = (TextView) findViewById(R.id.tv2);
                int livmin = matchLivello(addLivmin.getText().toString());
                int livmax = matchLivello(addLivmax.getText().toString());
                Log.d("livmin", String.valueOf(livmin));
                //inserisco nel Model dati utente
                MyModel.utente.setMinLiv(livmin);
                MyModel.utente.setMaxLiv(livmax);
                MyModel.utente.setLivelloMaxLead(livmax);
                Log.d("modelLivMin", String.valueOf(MyModel.utente.getMinLiv()));


                //chiamata volley per inserire l'utente nel DB
                RequestQueue mRequestQueue = Volley.newRequestQueue(this);
                String url = "https://climbwithme.herokuapp.com/iserisciutente.php";
                JSONObject datiDaPassare = new JSONObject();
                try {
                    datiDaPassare.put("datadinascita", MyModel.utente.getDataDiNascita());
                    datiDaPassare.put("codiceSessione", MyModel.utente.getCodiceSessione());
                    Log.d("codicesess", MyModel.utente.getCodiceSessione());
                    datiDaPassare.put("nome", MyModel.utente.getNome());
                    Log.d("nome", MyModel.utente.getNome());
                    datiDaPassare.put("cognome", MyModel.utente.getCognome());
                    datiDaPassare.put("numerotelefono", MyModel.utente.getNumeroTelefono());
                    datiDaPassare.put("minliv", MyModel.utente.getMinLiv());
                    datiDaPassare.put("maxliv", MyModel.utente.getMaxLiv());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        url,
                        datiDaPassare,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("VolleyBacheca", "Correct: " + response.toString());

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley", "Error: " + error.toString());
                    }
                });
                Log.d("Volley", "Sending request");
                mRequestQueue.add(request);


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            break;
        }
    }






    public int matchLivello(String s){
        int livello=0;
        if (s.equals("4a")){ livello=1; }
        if (s.equals("4a+")){ livello=2; }
        if (s.equals("4b")){ livello=3; }
        if (s.equals("4b+")){ livello=4; }
        if (s.equals("4c")){ livello=5; }
        if (s.equals("4c+")){ livello=6; }
        if (s.equals("5a")){ livello=7; }
        if (s.equals("5a+")){ livello=8; }
        if (s.equals("5b")){ livello=9; }
        if (s.equals("5b+")){ livello=10; }
        if (s.equals("5c")){ livello=11; }
        if (s.equals("5c+")){ livello=12; }
        if (s.equals("6a")){ livello=13; }
        if (s.equals("6a+")){ livello=14; }
        if (s.equals("6b")){ livello=15; }
        if (s.equals("6b+")){ livello=16; }
        if (s.equals("6c")){ livello=17; }
        if (s.equals("6c+")){ livello=18; }
        if (s.equals("7a")){ livello=19; }
        if (s.equals("7a+")){ livello=20; }
        if (s.equals("7b")){ livello=21; }
        if (s.equals("7b+")){ livello=22; }
        if (s.equals("7c")){ livello=23; }
        if (s.equals("7c+")){ livello=24; }
        if (s.equals("8a")){ livello=25; }
        if (s.equals("8a+")){ livello=26; }
        if (s.equals("8b")){ livello=27; }
        if (s.equals("8b+")){ livello=28; }
        if (s.equals("8c")){ livello=29; }
        if (s.equals("8c+")){ livello=30; }
        if (s.equals("9a")){ livello=31; }
        if (s.equals("9a+")){ livello=32; }
        if (s.equals("9b")){ livello=33; }
        if (s.equals("9b+")){ livello=34; }
        if (s.equals("9c")){ livello=35; }
        if (s.equals("9c+")){ livello=36; }
        return livello;
    }



}
