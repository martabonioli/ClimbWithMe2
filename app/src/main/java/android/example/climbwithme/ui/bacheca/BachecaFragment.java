package android.example.climbwithme.ui.bacheca;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.example.climbwithme.MainActivity;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.Uscita;
import android.example.climbwithme.ui.cerca.CercaFragment;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.util.List;

import static androidx.navigation.ui.NavigationUI.onNavDestinationSelected;

public class BachecaFragment extends Fragment /*implements View.OnClickListener*/ {
    public ImageButton res1, res2;
    public NavController navController;
    public AppBarConfiguration appBarConfiguration;
    public BottomNavigationView navView;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bacheca, container, false);

        return root;
    }

   /* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }*/


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // getActivity().findViewById(R.id.searchButton).setOnClickListener(this);
        //getActivity().findViewById(R.id.publiButton).setOnClickListener(this);

    }
   /* @Override
    public void onClick(View v) {
        Log.d("frag1", "Sono nell onclick");
        switch (v.getId()) {
            case R.id.searchButton :
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                Fragment secondFragment = new CercaFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment, secondFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();




                Log.d("frag1", "Fatta la transaction");
                break;

        }
    }*/
}