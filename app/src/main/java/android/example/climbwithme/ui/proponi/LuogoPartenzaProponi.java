package android.example.climbwithme.ui.proponi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.example.climbwithme.MainActivity;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.R;
import android.example.climbwithme.ui.cerca.CercaFragment;
import android.example.climbwithme.ui.cerca.DatePickerFragment;
import android.example.climbwithme.ui.profile.VisualizzaUscite;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LuogoPartenzaProponi extends AppCompatActivity {

    public NavController navController;
    public AppBarConfiguration appBarConfiguration;
    public BottomNavigationView navView;
    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luogo_partenza_proponi);
    }



   public void onClickButton2(View v){
        Fragment newFragment = new MapBoxFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.lp, newFragment);
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}
