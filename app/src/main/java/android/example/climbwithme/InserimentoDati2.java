package android.example.climbwithme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InserimentoDati2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_dati2);

        Button addConfirm = findViewById(R.id.entra);
        addConfirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        //prendi i dati inseriti e salvali sul model


        //chiamata volley per inserire l'utente nel DB

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
