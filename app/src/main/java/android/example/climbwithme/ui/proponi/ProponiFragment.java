package android.example.climbwithme.ui.proponi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.example.climbwithme.InserimentoDati;
import android.example.climbwithme.InserimentoDati2;
import android.example.climbwithme.MainActivity;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.ui.cerca.DatePickerFragment;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.example.climbwithme.R;

import java.text.DateFormat;
import java.util.Calendar;

public class ProponiFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    public String data="";
    public View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_proponi, container, false);
        ImageButton button = (ImageButton) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment(android.example.climbwithme.ui.proponi.ProponiFragment.this);
                datePicker.show(getFragmentManager(), "date picker");

                //inserisci data nel modell
                if (!data.equals("")){
                    MyModel.getInstance().cercaUscita.setDataUscita(data);
                    Log.d("dataCercaUscita", MyModel.getInstance().cercaUscita.getDataUscita());
                }


            }
        });

        return root;
    }




    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        view.setMinDate(System.currentTimeMillis() - 1000);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Log.d("calendar",c.getTime().toString());
        TextView textView = (TextView) root.findViewById(R.id.textView);
        textView.setTextColor(Color.parseColor("#FF7514"));
        textView.setText(currentDateString);

        data= ""+ year +"-"+correctdata((month+1))+"-"+correctdata(dayOfMonth);
        MyModel.getInstance().cercaUscita.setDataUscita(data);

    }

    public String correctdata(int num){
        String d ="";
        if (num<10){
            return d="0"+num;
        }
        return ""+num;
    }




}