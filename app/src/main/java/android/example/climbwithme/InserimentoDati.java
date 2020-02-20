package android.example.climbwithme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class InserimentoDati extends AppCompatActivity implements View.OnClickListener {
    //varInserimentoDataNascita
    private static final String TAG = "InserimentoDati";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String dataDB="";
    private Boolean verifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_dati);

        ImageButton addConfirm = findViewById(R.id.confermap);
        addConfirm.setOnClickListener(this);

        if (MyModel.getInstance().utente.getMinLiv() != 0){
            TextView newTitle = (TextView) findViewById(R.id.textView2);
            newTitle.setText("MODIFICA I TUOI DATI PERSONALI ");
            EditText addNome = findViewById(R.id.addnome);
            addNome.setText(MyModel.utente.getNome());
            EditText addCognome = findViewById(R.id.addcognome);
            addCognome.setText(MyModel.utente.getCognome());
            EditText addTelefono = findViewById(R.id.addtelefono);
            addTelefono.setText(MyModel.utente.getNumeroTelefono());


        }

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
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);
                if (month<10) {
                    String date = day + "-0" + month + "-" + year;
                    dataDB = year + "-0" + month + "-" + day;
                    mDisplayDate.setText(date);
                } else {
                    String date = day + "-" + month + "-" + year;
                    dataDB = year + "-" + month + "-" + day;
                    mDisplayDate.setText(date);
                }
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
            MyModel.getInstance().utente.setNome(nome);
            MyModel.getInstance().utente.setCognome(cognome);
            MyModel.getInstance().utente.setDataDiNascita(dataDB);
            MyModel.getInstance().utente.setNumeroTelefono(telefono);

            Intent intent = new Intent(getApplicationContext(), InserimentoDati2.class);
            /*if (verifica == true){
                intent.putExtra("modifica",true);
            }else{
                intent.putExtra("modifica",false);
            }*/
            startActivity(intent);



        }


}

