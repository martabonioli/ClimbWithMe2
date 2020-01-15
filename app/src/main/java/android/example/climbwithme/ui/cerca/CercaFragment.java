package android.example.climbwithme.ui.cerca;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;



import android.example.climbwithme.R;

import java.text.DateFormat;
import java.util.Calendar;

public class CercaFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    public View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_cerca, container, false);
        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment(CercaFragment.this);
                datePicker.show(getFragmentManager(), "date picker");
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
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) root.findViewById(R.id.textView);
        textView.setText(currentDateString);
    }


}


