package ru.goryachev.warblerchatandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_CODE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE){
            if (resultCode == RESULT_OK){
                toastLoggedIn ();
                displayAllMessages();
            } else {
                toastNotLoggedIn();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void displayAllMessages() {
    }

    private void toastLoggedIn () {
        Toast.makeText(this, "You are logged in", Toast.LENGTH_SHORT).show();
    }

    private void toastNotLoggedIn () {
        Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show();
    }
}
