package android.example.climbwithme.ui.cerca;


import android.content.Intent;
import android.content.SharedPreferences;
import android.example.climbwithme.MainActivity;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.R;
import android.example.climbwithme.ui.bacheca.AdapterUscita;
import android.example.climbwithme.ui.bacheca.BachecaFragment;
import android.example.climbwithme.ui.profile.VisualizzaUscite;
import android.example.climbwithme.ui.proponi.LuogoPartenzaProponi;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class VisualizzaUsciteCerca extends Fragment {
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

        View view = inflater.inflate(R.layout.fragment_visualizza_uscite_cerca, container, false);
        ImageButton chiudi= (ImageButton) view.findViewById(R.id.imageButton4);
        chiudi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                Fragment oldFragment = fm.findFragmentByTag("visualizzaUsciteCerca");
                fm.beginTransaction().remove(oldFragment).commit();
                Fragment newFragment = new BachecaFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // transaction.remove(getSupportFragmentManager().findFragmentById(R.id.))
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



}
