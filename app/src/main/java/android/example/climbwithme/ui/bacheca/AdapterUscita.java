package android.example.climbwithme.ui.bacheca;

import android.app.Activity;
import android.content.Context;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.R;
import android.example.climbwithme.Uscita;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUscita  extends RecyclerView.Adapter<SingolaUscita> {

    private LayoutInflater inflater;
    private ArrayList<Uscita> uscite;


    public AdapterUscita (Context context, ArrayList<Uscita> uscite) {
        this.inflater = LayoutInflater.from(context);
        this.uscite = uscite;


    }

    @Override
    public SingolaUscita onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.singola_uscita, parent, false);
            return new SingolaUscita(view);

    }

    @Override
    public void onBindViewHolder(SingolaUscita holder, int position) {
        Uscita uscita = uscite.get(position);
        holder.setUscita(uscita);
    }

    @Override
    public int getItemCount() {
        return uscite.size();
    }
}

