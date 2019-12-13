package android.example.climbwithme.ui.bacheca;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BachecaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BachecaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bacheca fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}