package android.example.climbwithme.ui.bacheca;

import android.os.Bundle;
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

public class BachecaFragment extends Fragment {

    private BachecaViewModel bachecaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bachecaViewModel =
                ViewModelProviders.of(this).get(SingolaUscita.class);
        View root = inflater.inflate(R.layout.activity_uscita_lista, container, false);
       /* final TextView textView = root.findViewById(R.id.text_bacheca);
        bachecaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);


            }
        });*/
        return root;


    }


}