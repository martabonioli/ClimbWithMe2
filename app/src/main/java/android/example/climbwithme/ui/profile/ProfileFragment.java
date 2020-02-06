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
        TextView textView4 = view.findViewById(R.id.addminliv);
        textView4.setText(""+ matchLivello(MyModel.utente.getMinLiv())+"   ----   "+ matchLivello(MyModel.utente.getMaxLiv()));




    }


    public String matchLivello(int s){
        String livello="";
        if (s==1){ livello="4a"; }
        if (s==2){ livello="4a+"; }
        if (s==3){ livello="4b"; }
        if (s==4){ livello="4b+"; }
        if (s==5){ livello="4c"; }
        if (s==6){ livello="4c+"; }
        if (s==7){ livello="5a"; }
        if (s==8){ livello="5a+"; }
        if (s==9){ livello="5b"; }
        if (s==10){ livello="5b+"; }
        if (s==11){ livello="5c"; }
        if (s==12){ livello="5c+"; }
        if (s==13){ livello="6a"; }
        if (s==14){ livello="6a+"; }
        if (s==15){ livello="6b"; }
        if (s==16){ livello="6b+"; }
        if (s==17){ livello="6c"; }
        if (s==18){ livello="6c+"; }
        if (s==19){ livello="7a"; }
        if (s==20){ livello="7a+"; }
        if (s==21){ livello="7b"; }
        if (s==22){ livello="7b+"; }
        if (s==23){ livello="7c"; }
        if (s==24){ livello="7c+"; }
        if (s==25){ livello="8a"; }
        if (s==26){ livello="8a+"; }
        if (s==27){ livello="8b"; }
        if (s==28){ livello="8b+"; }
        if (s==29){ livello="8c"; }
        if (s==30){ livello="8c+"; }
        if (s==31){ livello="9a"; }
        if (s==32){ livello="9a+"; }
        if (s==33){ livello="9b"; }
        if (s==34){ livello="9b+"; }
        if (s==35){ livello="9c"; }
        if (s==36){ livello="9c+"; }
        return livello;
    }
}
