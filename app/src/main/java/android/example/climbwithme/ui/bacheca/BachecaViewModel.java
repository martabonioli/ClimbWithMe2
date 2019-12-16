package android.example.climbwithme.ui.bacheca;

import android.example.climbwithme.R;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class BachecaViewModel extends RecyclerView.ViewHolder  {

    private MutableLiveData<String> mText;


    public BachecaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bacheca fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}