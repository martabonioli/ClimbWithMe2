package android.example.climbwithme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InserimentoDati extends AppCompatActivity implements View.OnClickListener {
    //varInserimentoDataNascita
    private static final String TAG = "InserimentoDati";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_dati);

        ImageButton addConfirm = findViewById(R.id.conferma);
        addConfirm.setOnClickListener(this);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        InserimentoDati.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = year + "-" + month + "-" + day;
                mDisplayDate.setText(date);
            }
        };

    }
        @Override
        public void onClick(View v){

            EditText addNome = findViewById(R.id.addnome);
            EditText addCognome = findViewById(R.id.addcognome);
            TextView addDataNascita = (TextView) findViewById(R.id.tvDate);
            EditText addTelefono = findViewById(R.id.addtelefono);
            String nome = addNome.getText().toString();
            String cognome = addCognome.getText().toString();
            String dataNascita = addDataNascita.getText().toString();
            String telefono = addTelefono.getText().toString();

            //inserisco nel Model dati utente
            MyModel.utente.setNome(nome);
            MyModel.utente.setCognome(cognome);
            MyModel.utente.setDataDiNascita(dataNascita);
            MyModel.utente.setNumeroTelefono(telefono);

            Intent intent = new Intent(getApplicationContext(), InserimentoDati2.class);
            startActivity(intent);



        }


}

