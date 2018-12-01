package com.example.maju.mazdor.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maju.mazdor.Adapter.MessagesAdapter;
import com.example.maju.mazdor.Model.ChatMessage;
import com.example.maju.mazdor.Model.UserList;
import com.example.maju.mazdor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import static com.example.maju.mazdor.Adapter.UserAdapter.KEY_NAME;
import static com.example.maju.mazdor.Adapter.UserAdapter.KEY_UID;

public class ChatMessagesActivity extends AppCompatActivity {


    public final static String Database_Path = "Messages"; // Root Database Name for Firebase Database.
    static String UID;
    ProgressDialog progressDialog ;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private StorageReference mStorage;

    private RecyclerView mChatsRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private EditText mMessageEditText;
    private ImageView mSendImageButton;
    private DatabaseReference mUsersRef;
    private List<ChatMessage> mMessagesList = new ArrayList<>();
    private MessagesAdapter adapter = null;
//    EditText emojiconEditText;

    private String mReceiverId;
    private String mReceiverName;
    private String  mMessageDate;
    private String mMessageTime;
    private String mSenderName;
    ImageView bac_btn;
    TextView AcitveUserName;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);


        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        mUsersRef = FirebaseDatabase.getInstance().getReference().child("USERS");
        firebaseAuth = FirebaseAuth.getInstance();
        AcitveUserName = (TextView) findViewById(R.id.userNameAct);


        //        if (firebaseAuth.getCurrentUser() != null) {
//            //profile ativity
//            finish();
//            startActivity(new Intent(getApplicationContext(), Home.class));
//        }
        UID = firebaseAuth.getCurrentUser().getUid();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mSenderName = user.getEmail();
        Log.v("Loc", UID);




//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //initialize the views
        mChatsRecyclerView = (RecyclerView)findViewById(R.id.messagesRecyclerView);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendImageButton = (ImageView)findViewById(R.id.sendMessageImagebutton);
        bac_btn = (ImageView) findViewById(R.id.Back);
        mChatsRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setStackFromEnd(true);
        mChatsRecyclerView.setLayoutManager(mLayoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //init Firebase
//        mMessagesDBRef = FirebaseDatabase.getInstance().getReference().child("Messages");
//        mUsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        //get receiverId from intent
//        mReceiverId = getIntent().getStringExtra("USER_ID");

        bac_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        try {

            Intent intent = getIntent();
            if (null != intent) {
                mReceiverId = intent.getStringExtra(KEY_UID);
                mReceiverName = intent.getStringExtra(KEY_NAME);

                //Time
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat  format2 = new SimpleDateFormat("dd-MMM");
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");

                mMessageTime = format.format(calendar.getTime());
                mMessageDate = format2.format(calendar.getTime());


//                set username in toolbar
                AcitveUserName.setText(mReceiverName);
            }
            else {
                Toast.makeText(this, "Null ID", Toast.LENGTH_SHORT).show();
            }

            System.out.println(mReceiverId + "UserID");
            System.out.println(mReceiverName + "UsernName_");



        }
        catch (Exception e){
            Toast.makeText(this, "Exception Applied", Toast.LENGTH_SHORT).show();
        }




        /**listen to send message imagebutton click**/
        mSendImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mMessageEditText.getText().toString();
                String senderId = UID;


                if(message.isEmpty()){
                    Toast.makeText(ChatMessagesActivity.this, "You must enter a message", Toast.LENGTH_SHORT).show();
                }else {
                    //message is entered, send
                    sendMessageToFirebase(message, senderId,mReceiverId,mMessageTime,mMessageDate, mSenderName, mReceiverName);
                }
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        /**Query and populate chat messages**/
        querymessagesBetweenThisUserAndClickedUser();


        /**sets title bar with recepient name**/
        queryRecipientName(mReceiverId);
    }



    private void sendMessageToFirebase(String message, String senderId, String receiverId, String messageTime, String messageDate, String messageSenderName, String messageReceiverName){
        mMessagesList.clear();
        ChatMessage newMsg = new ChatMessage(message, senderId,receiverId,messageTime, messageDate, messageSenderName, messageReceiverName);
        mDatabase.push().setValue(newMsg).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()){
                    //error
                    Toast.makeText(ChatMessagesActivity.this, "Error " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ChatMessagesActivity.this, "Message sent successfully!", Toast.LENGTH_SHORT).show();
                    mMessageEditText.setText(null);
                    hideSoftKeyboard();
                }
            }
        });


    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void querymessagesBetweenThisUserAndClickedUser(){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mMessagesList.clear();

                for(DataSnapshot snap: dataSnapshot.getChildren()){
                    ChatMessage chatMessage = snap.getValue(ChatMessage.class);
                    if(chatMessage.getSenderId().equals(UID) && chatMessage.getReceiverId().equals(mReceiverId) ||
                        chatMessage.getSenderId().equals(mReceiverId) && chatMessage.getReceiverId().equals(UID))
                    {
                        mMessagesList.add(chatMessage);
                    }

                }
                /**populate messages**/
                populateMessagesRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void populateMessagesRecyclerView(){
        adapter = new MessagesAdapter(mMessagesList, this);
        mChatsRecyclerView.setAdapter(adapter);
    }
    private void queryRecipientName(final String receiverId){

        mUsersRef.child(receiverId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserList userList = dataSnapshot.getValue(UserList.class);
                mReceiverName = userList.getPrFirstName();

                System.out.println(mReceiverName + "UserID");
//                try {
//                    getSupportActionBar().setTitle(mReceiverName);
//                    getActionBar().setTitle(mReceiverName);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
