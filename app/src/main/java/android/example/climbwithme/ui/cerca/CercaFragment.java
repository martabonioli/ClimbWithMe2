package android.example.climbwithme.ui.cerca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.example.climbwithme.R;

public class CercaFragment extends Fragment {

    private CercaViewModel cercaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cercaViewModel =
                ViewModelProviders.of(this).get(CercaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cerca, container, false);
        final TextView textView = root.findViewById(R.id.text_cerca);
        cercaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}