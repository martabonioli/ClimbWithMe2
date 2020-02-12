package android.example.climbwithme.ui.profile;


import android.content.SharedPreferences;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.Uscita;
import android.example.climbwithme.ui.bacheca.AdapterUscita;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.climbwithme.R;
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


public class VisualizzaUscite extends Fragment {
    SharedPreferences settings;
    RecyclerView list;
    private AdapterUscita adapter;



    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            RecyclerView recyclerView = getActivity().findViewById(R.id.text_bacheca);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new AdapterUscita(getActivity(), MyModel.getInstance().getUscite());
            recyclerView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_visualizza_uscite, container, false);
        ImageButton chiudi= (ImageButton) view.findViewById(R.id.imageButton4);
        chiudi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                Fragment oldFragment = fm.findFragmentByTag("visualizzaUscite");
                fm.beginTransaction().remove(oldFragment).commit();
            }
        });
        return view;
    }



   /*public void notifyAdapter() {
        Log.d("Android6","Aggiorno adapter");
        // Aggiorno l'adapter
        list = (RecyclerView)list.findViewById(R.id.text_bacheca);
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        adapter = new AdapterUscita(getActivity().getApplicationContext(), MyModel.getInstance().getUscite());
        list.setAdapter(adapter);
    }*/
}
