package android.example.climbwithme.ui.cerca;

import androidx.appcompat.app.AppCompatActivity;

import android.example.climbwithme.R;
import android.os.Bundle;
import android.widget.TextView;

public class CercaFinal extends AppCompatActivity {
    Double latPartenza;
    Double lonPartenza;
    Double latArrivo;
    Double lonArrivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_final);
        latPartenza = getIntent().getExtras().getDouble("latitudinepartenza");
        lonPartenza = getIntent().getExtras().getDouble("longitudinepartenza");
        latArrivo = getIntent().getExtras().getDouble("latitudinearrivo");
        lonArrivo = getIntent().getExtras().getDouble("longitudinearrivo");
        TextView lat = findViewById(R.id.latp);
        lat.setText(""+latPartenza);

    }
}
