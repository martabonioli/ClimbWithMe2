package android.example.climbwithme.ui.bacheca;

import android.app.Activity;
import android.example.climbwithme.R;
import android.example.climbwithme.Uscita;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SingolaUscita extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView fotoUtente;
    private TextView nomeUtente;
    private TextView maxLead;
    private TextView dataUscita;
    private TextView partenza;
    private TextView arrivo;

    private String uscita;
    private Activity parentActivity;


    public SingolaUscita(@NonNull View itemView) {
        super(itemView);
        fotoUtente = itemView.findViewById(R.id.fotoUtente);
        nomeUtente = itemView.findViewById(R.id.nomeUtente);
        maxLead = itemView.findViewById(R.id.maxLead);
        dataUscita = itemView.findViewById(R.id.dataUscita);
        partenza = itemView.findViewById(R.id.partenza);
        arrivo = itemView.findViewById(R.id.arrivo);
        itemView.setOnClickListener(this);
    }

    public void setUscita(Uscita uscita) {
        this.uscita = uscita.getId();
        fotoUtente = 
    }


   /* @Override
    public void onClick(View v) {

    }*/
}
