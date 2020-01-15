package android.example.climbwithme.ui.profile;

import android.example.climbwithme.MyModel;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.example.climbwithme.R;

public class ProfileFragment extends Fragment {

    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TextView textView = view.findViewById(R.id.text_profile);
        //textView.setText("Siamo nel profile fragment");

        String nomecognome = MyModel.utente.getNome() +" "+ MyModel.utente.getCognome();
        String dataDiNascita = MyModel.utente.getDataDiNascita();
            Log.d("nomecognoeModel",nomecognome);



        TextView textView1 = view.findViewById(R.id.addnome);
        textView1.setText(nomecognome);
        TextView textView2 = view.findViewById(R.id.adddataNascita);
        textView2.setText(dataDiNascita);
        TextView textView3 = view.findViewById(R.id.addtelefono);
        textView3.setText(MyModel.utente.getNumeroTelefono());


    }
}