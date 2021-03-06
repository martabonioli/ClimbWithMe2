package android.example.climbwithme.ui.cerca;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.example.climbwithme.InserimentoDati;
import android.example.climbwithme.InserimentoDati2;
import android.example.climbwithme.MainActivity;
import android.example.climbwithme.MyModel;
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
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CercaFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    public String data="";
    public View root;
    public Calendar oggi = Calendar.getInstance();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_cerca, container, false);
        ImageButton button = (ImageButton) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale.setDefault(Locale.ITALIAN);
                DialogFragment datePicker = new DatePickerFragment(CercaFragment.this);

                datePicker.show(getFragmentManager(), "date picker");

                Log.d("data",data);
                //inserisci data nel modell
                if (!data.equals("")){
                    MyModel.getInstance().cercaUscita.setDataUscita(data);
                    Log.d("dataCercaUscita", MyModel.getInstance().cercaUscita.getDataUscita());
                }else{
                    MyModel.getInstance().cercaUscita.setDataUscita("");
                }


            }
        });

        return root;
    }


    @Override
    public void onClick(View v) {
        Log.d("frag1", "Sono nell onclick");
        switch (v.getId()) {
            case R.id.button :
                DialogFragment datePicker = new DatePickerFragment(CercaFragment.this);
                datePicker.show(getFragmentManager() , "date picker");

                Log.d("frag1", "Fatta la transaction");
                if (!data.equals("")){
                    MyModel.getInstance().cercaUscita.setDataUscita(data);
                    Log.d("dataCercaUscita", MyModel.getInstance().cercaUscita.getDataUscita());
                }
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(year<oggi.get(Calendar.YEAR) || (year==oggi.get(Calendar.YEAR) && month<oggi.get(Calendar.MONTH)) || ((year==oggi.get(Calendar.YEAR)) && (month==oggi.get(Calendar.MONTH)) && (dayOfMonth<oggi.get(Calendar.DAY_OF_MONTH)))) {
            Toast.makeText(getContext(), "Non si possono cercare uscite passate", Toast.LENGTH_LONG).show();


        }
        else {
            view.setMinDate(System.currentTimeMillis() - 1000);
            Calendar c = Calendar.getInstance();
            view.setMinDate(System.currentTimeMillis() - 1000);

            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            Log.d("mese", String.valueOf(Calendar.MONTH));
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
            TextView textView = (TextView) root.findViewById(R.id.textView);
            textView.setTextColor(Color.parseColor("#FF7514"));
            textView.setText(currentDateString);
            data = "" + year + "-" + correctdata((month + 1)) + "-" + correctdata(dayOfMonth);
            MyModel.getInstance().cercaUscita.setDataUscita(data);
        }

    }

    public String correctdata(int num){
        String d ="";
        if (num<10){
            return d="0"+num;
        }
        return ""+num;
    }


}


