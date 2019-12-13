package android.example.climbwithme.ui.bacheca;

import android.example.climbwithme.R;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UscitaLista extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uscita_lista);
    }

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        UscitaAdapter studentAdapter = new UscitaAdapter(this,this);
        list.setAdapter(studentAdapter);
    }
}
