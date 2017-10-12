package my.toru.advancedmvvm.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import my.toru.advancedmvvm.R;


/*
 * Activity is equivalent to View.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
