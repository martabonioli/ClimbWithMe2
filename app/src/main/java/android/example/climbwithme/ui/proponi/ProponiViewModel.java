package android.example.climbwithme.ui.proponi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProponiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProponiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is proponi uscita fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}