package android.example.climbwithme.ui.cerca;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.example.climbwithme.MainActivity;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;


public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener dataSetListener = null;


    public DatePickerFragment(DatePickerDialog.OnDateSetListener dataSetListener) {
        this.dataSetListener = dataSetListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Locale.setDefault(Locale.ITALIAN);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getContext(), dataSetListener, year, month, day);
    }
}