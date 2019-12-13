package android.example.climbwithme.ui.bacheca;

import android.app.Activity;
import android.content.Intent;
import android.example.climbwithme.R;
import android.example.climbwithme.Uscita;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SingolaUscita extends RecyclerView.ViewHolder  {

    private TextView fotoutente;
    private TextView nomeUtente;
    private TextView datauscita;
    private TextView luogoarrivo;
    private String uscita;
    private Activity parentActivity;

    public SingolaUscita (View itemView, Activity parentActivity) {
        super(itemView);
        this.parentActivity = parentActivity;
        fotoutente = itemView.findViewById(R.id.fotoutente);
        nomeUtente = itemView.findViewById(R.id.nomeUtente);
        datauscita = itemView.findViewById(R.id.datauscita);
        luogoarrivo = itemView.findViewById(R.id.luogoarrivo);
        //itemView.setOnClickListener(this);
    }

    public void setUscita(Uscita uscita) {
        this.uscita = uscita.getCodiceSessione();
        nomeUtente.setText(uscita.getCodiceSessione());

    }

    /*@Override
    public void onClick(View v) {
        Intent openStudentDetail = new Intent(parentActivity,StudentDetail.class);
        openStudentDetail.putExtra(StudentDetail.MATRICOLA_EXTRA,student);
        parentActivity.startActivity(openStudentDetail);
    }*/
}
