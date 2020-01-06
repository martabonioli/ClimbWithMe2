package android.example.climbwithme.ui.bacheca;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.Uscita;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.climbwithme.R;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class BachecaFragment extends Fragment {

    RecyclerView list;
    SharedPreferences settings;
    AdapterUscita adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bacheca, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(MyModel.getI()==0) {
            MyModel.getInstance().addFakeUscite();
            MyModel.updateI();


        }
        RecyclerView recyclerView = getActivity().findViewById(R.id.text_bacheca);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterUscita(getActivity());
        recyclerView.setAdapter(adapter);



    }

}