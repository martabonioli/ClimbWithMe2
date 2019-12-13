package android.example.climbwithme;

class MyModel {
    private static final MyModel ourInstance = new MyModel();

    static MyModel getInstance() {
        return ourInstance;
    }

    private MyModel() {
    }
}
