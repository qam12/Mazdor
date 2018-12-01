package com.example.maju.mazdor.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maju.mazdor.Model.ChatMessage;
import com.example.maju.mazdor.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

import static com.example.maju.mazdor.Adapter.UserAdapter.KEY_NAME;
import static com.example.maju.mazdor.Adapter.UserAdapter.KEY_UID;

public class ChatActivity extends AppCompatActivity {


    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_main;

    String UserPRoID = "";
    String UserProName = "";

    public final static String Database_Path = "Messages"; // Root Database Name for Firebase Database.
    static String UID;
    ProgressDialog progressDialog ;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private StorageReference mStorage;

    ImageView bac_btn;
    TextView AcitveUserName;


    //Add Emojicon
    EmojiconEditText emojiconEditText;
    ImageView emojiButton,submitButton;
    EmojIconActions emojIconActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();


        //        if (firebaseAuth.getCurrentUser() != null) {
//            //profile ativity
//            finish();
//            startActivity(new Intent(getApplicationContext(), Home.class));
//        }
        UID = firebaseAuth.getCurrentUser().getUid();
        Log.v("Loc", UID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.chattingToolbar);
        setSupportActionBar(toolbar);

        activity_main = (RelativeLayout)findViewById(R.id.activity_main);
        bac_btn = (ImageView) findViewById(R.id.Back);
        AcitveUserName = (TextView) findViewById(R.id.userNameAct);

        //backbtn
        bac_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Add Emoji
        emojiButton = (ImageView)findViewById(R.id.emoji_button);
        submitButton = (ImageView)findViewById(R.id.submit_button);
        emojiconEditText = (EmojiconEditText)findViewById(R.id.emojicon_edit_text);
        emojIconActions = new EmojIconActions(getApplicationContext(),activity_main,emojiButton,emojiconEditText);
        emojIconActions.ShowEmojicon();


        try {

            Intent intent = getIntent();
            if (null != intent) {
                UserPRoID = intent.getStringExtra(KEY_UID);
                UserProName = intent.getStringExtra(KEY_NAME);

                //set username in toolbar
                AcitveUserName.setText(UserProName);
            }
            else {
                Toast.makeText(this, "Null ID", Toast.LENGTH_SHORT).show();
            }

            System.out.println(UserPRoID + "UserID");
            System.out.println(UserProName + "UsernName_");



        }
        catch (Exception e){
            Toast.makeText(this, "Exception Applied", Toast.LENGTH_SHORT).show();
        }

//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                        try {
//                            if(emojiconEditText.length() == 0){
//                                Toast.makeText(ChatActivity.this, "Type Something", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                mDatabase.child(UID).child(UserPRoID).push().setValue(
//                                        new ChatMessage(emojiconEditText.getText().toString(),
//                                        firebaseAuth.getCurrentUser().getEmail()));
//                                emojiconEditText.setText("");
//                                emojiconEditText.requestFocus();
//                            }
//                        }
//                        catch (Exception e){
//                            Toast.makeText(ChatActivity.this, "Excpetion Handle", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//
//                //Check if not sign-in then navigate Signin page
//                if(firebaseAuth.getCurrentUser() == null)
//                {
//                    startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
//                }
//                else
//                {
//                  Snackbar.make(activity_main,"Welcome "+firebaseAuth.getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
//                    displayChatMessage();
//                }

    }

//    private void displayChatMessage() {
//
//        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);
//        adapter = new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.chat_list,mDatabase.child(UID).child(UserPRoID))
//        {
//            @Override
//            protected void populateView(View v, ChatMessage model, int position) {
//                //Get references to the views of list_item.xml
//                TextView messageText, messageUser, messageTime;
//                messageText = (BubbleTextView) v.findViewById(R.id.message_text);
//                messageUser = (TextView) v.findViewById(R.id.message_user);
//                messageTime = (TextView) v.findViewById(R.id.message_time);
//
//                messageText.setText(model.getMessageText());
//                messageUser.setText(model.getMessageUser());
//                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
//            }
//        };
//        listOfMessage.setAdapter(adapter);
//    }
}
