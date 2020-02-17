package android.example.climbwithme.ui.proponi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.climbwithme.R;
import android.example.climbwithme.ui.cerca.CercaFinal;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Attributi extends AppCompatActivity implements View.OnClickListener{
    Double latPartenza;
    Double lonPartenza;
    String partenza;
    String arrivo;
    Double latArrivo;
    Double lonArrivo;
    String tipoArr;
    String mezzoTrasp;
    String attrezzatura="";
    CheckBox corda, rinvii, imbrago, assicuratore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attributi);

        latPartenza = getIntent().getExtras().getDouble("latitudinepartenza");
        lonPartenza = getIntent().getExtras().getDouble("longitudinepartenza");
        latArrivo = getIntent().getExtras().getDouble("latitudinearrivo");
        lonArrivo = getIntent().getExtras().getDouble("longitudinearrivo");
        partenza = getIntent().getExtras().getString("luogopartenza");
        arrivo = getIntent().getExtras().getString("luogoarrivo");

        corda = findViewById(R.id.corda);
        rinvii= findViewById(R.id.rinvii);
        imbrago = findViewById(R.id.imbrago);
        assicuratore = findViewById(R.id.assicuratore);
        ImageButton conferma = findViewById(R.id.conferma2);
        conferma.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        EditText addtipoArrampicata=  findViewById(R.id.tipoArrampicata);
        EditText addmezzoTrasporto =findViewById(R.id.mezzoTrasporto);
        tipoArr = addtipoArrampicata.getText().toString();
        mezzoTrasp = addmezzoTrasporto.getText().toString();

        if (tipoArr.isEmpty()){
            Toast.makeText(this, "Inserisci il tipo di arrampicata", Toast.LENGTH_SHORT).show();
        }else {
            if (mezzoTrasp.isEmpty()) {
                Toast.makeText(this, "Inserisci il mezzo di Trasporto", Toast.LENGTH_SHORT).show();
            }else {

                if (corda.isChecked())
                    attrezzatura = attrezzatura + "corda ";
                if (rinvii.isChecked())
                    attrezzatura = attrezzatura + "rinvii ";
                if (imbrago.isChecked())
                    attrezzatura = attrezzatura + "imbrago ";
                if (assicuratore.isChecked())
                    attrezzatura = attrezzatura + "assicuratore ";

                Intent intent = new Intent(getApplicationContext(), ProponiFinal.class);
                intent.putExtra("latitudinepartenza", latPartenza);
                intent.putExtra("longitudinepartenza", lonPartenza);
                intent.putExtra("luogopartenza", partenza);
                intent.putExtra("latitudinearrivo", latArrivo);
                intent.putExtra("longitudinearrivo", lonArrivo);
                intent.putExtra("luogoarrivo", arrivo);
                intent.putExtra("tipoarr", tipoArr);
                intent.putExtra("mezzo", mezzoTrasp);
                intent.putExtra("attrezz", attrezzatura);
                Log.d("attrezzatura",attrezzatura);
                startActivity(intent);
            }

        }

    }
}
