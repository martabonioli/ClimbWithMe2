package android.example.climbwithme.ui.bacheca;

import android.app.Activity;
import android.example.climbwithme.R;
import android.example.climbwithme.Uscita;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;

public class SingolaUscita extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView fotoUtente;
    private TextView nomeUtente;
    private TextView maxLead;
    private SimpleDateFormat dataUscita;
    private TextView partenza;
    private TextView arrivo;

    private String uscita;


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
        fotoUtente.setText(uscita.getFoto());
        nomeUtente.setText (uscita.getNome());
        maxLead.setText (uscita.getLivelloMaxLead());
        dataUscita.setText(uscita.getDataUscita().toString());
        partenza.setText(uscita.getLatLuogoPartenza().toString());
        arrivo.setText(uscita.getLatLuogoArrivo().toString());
    }



    public void onClick(View v) {

    }
}
