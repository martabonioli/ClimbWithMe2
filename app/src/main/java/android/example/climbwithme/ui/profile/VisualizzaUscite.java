package android.example.climbwithme.ui.profile;


import android.content.SharedPreferences;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.Uscita;
import android.example.climbwithme.ui.bacheca.AdapterUscita;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.climbwithme.R;
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


public class VisualizzaUscite extends Fragment {
    SharedPreferences settings;
    RecyclerView list;
    private AdapterUscita adapter;



    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (MyModel.getInstance().getUscite().isEmpty()){
            Toast.makeText(getActivity(), "Non hai pubblicato uscite", Toast.LENGTH_SHORT).show();
        }else {
            downloadUscite();
            RecyclerView recyclerView = getActivity().findViewById(R.id.text_bacheca);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new AdapterUscita(getActivity(), MyModel.getInstance().getUscite());
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visualizza_uscite, container, false);
    }

    private void downloadUscite() {

        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        String url = "https://climbwithme.herokuapp.com/ricercaUscita.php";
        String sessId = '"' + MyModel.getSessionId() + '"';

        JSONObject datiDaPassare = new JSONObject();
        try {
            datiDaPassare.put("codiceSessione",MyModel.getSessionId());

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
        list = (RecyclerView)list.findViewById(R.id.text_bacheca);
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        adapter = new AdapterUscita(getActivity().getApplicationContext(), MyModel.getInstance().getUscite());
        list.setAdapter(adapter);
    }
}
