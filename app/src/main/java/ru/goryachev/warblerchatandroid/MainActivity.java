package ru.goryachev.warblerchatandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

import android.text.format.DateFormat;

public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_CODE = 1;
    private FirebaseListAdapter<Message> adapter;

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
        ListView MessagesList = findViewById(R.id.messagesList);
        adapter = new FirebaseListAdapter<Message>(this, ChatMessage.class, R.layout.list_item, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View view, ChatMessage mModel, int position) {
                TextView messageUser;
                TextView messageTime;
                TextView messageText;
                messageUser = view.findViewById(R.id.message_user);
                messageTime = view.findViewById(R.id.message_time);
                messageText = view.findViewById(R.id.message_text);

                messageUser.setText(mModel.getUserName());
                messageTime.setText(DateFormat.format("dd-mm-yyyy HH:mm:ss", mModel.getMessageTime()));
                messageText.setText(mModel.getMessageText());

            }
        };
    }

    private void toastLoggedIn () {
        Toast.makeText(this, "You are logged in", Toast.LENGTH_SHORT).show();
    }

    private void toastNotLoggedIn () {
        Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show();
    }
}
