package android.example.climbwithme.ui.proponi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.climbwithme.R;
import android.example.climbwithme.ui.cerca.CercaFinal;
import android.os.Bundle;
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

        EditText addtipoArrampicata=  findViewById(R.id.tipoArrampicata);
        EditText addmezzoTrasporto =findViewById(R.id.mezzoTrasporto);
        tipoArr = addtipoArrampicata.getText().toString();
        mezzoTrasp = addmezzoTrasporto.getText().toString();
        CheckBox corda = findViewById(R.id.corda);

        ImageButton conferma = findViewById(R.id.conferma2);
        conferma.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ProponiFinal.class);
            intent.putExtra("latitudinepartenza", latPartenza);
            intent.putExtra("longitudinepartenza", lonPartenza);
            intent.putExtra("luogopartenza", partenza);
            intent.putExtra("latitudinearrivo", latArrivo);
            intent.putExtra("longitudinearrivo",lonArrivo);
            intent.putExtra("luogoarrivo", arrivo);
            intent.putExtra("tipoarr", tipoArr);
            intent.putExtra("mezzo",mezzoTrasp);
            intent.putExtra("attrezzaura", attrezzatura);
            startActivity(intent);

    }
}
