package com.example.maju.mazdor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class Job_post extends AppCompatActivity {

    //public final static String Storage_Path = "Employee_profile/"; // Folder path for Firebase Storage.
    public final static String Database_Path = "JOBS"; // Root Database Name for Firebase Database.
    static String UID;

    ProgressDialog progressDialog;
    private Button PostJOB;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private StorageReference mStorage;

    EditText Title, Rate, Loc, Det;
    String title, rate, location, detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);


        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();

        UID = firebaseAuth.getCurrentUser().getUid();


//
//        if (firebaseAuth.getCurrentUser() != null) {
//            //profile ativity
//            finish();
//            startActivity(new Intent(getApplicationContext(), Home2.class));
//        }

        //progressDialog = new ProgressDialog(this);
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);


        Title = (EditText) findViewById(R.id.title);
        Rate = (EditText) findViewById(R.id.rate);
        Loc = (EditText) findViewById(R.id.Location);
        Det = (EditText) findViewById(R.id.desic);
        PostJOB = (Button) findViewById(R.id.postMis);
        PostJOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadJOB();
            }
        });


    }

    public void uploadJOB() {

        title = Title.getText().toString().trim();
        rate = Rate.getText().toString().trim();
        location = Loc.getText().toString().trim();
        detail = Det.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            Title.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(rate)) {
            Rate.setError("Required");
            return;

        }
        if (TextUtils.isEmpty(location)) {
            Loc.setError("Required");
            return;

        }
        if (TextUtils.isEmpty(detail)) {
            Det.setError("Required");
            return;
        }

        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        try{

            Job_class  job_class  = new Job_class(title , rate , location , detail, UID);
            mDatabase.push().setValue(job_class, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    Intent i;
                    if (databaseReference.equals(databaseError)) {
                        progressDialog.dismiss();
                        Toast.makeText(Job_post.this, "Error in Posting", Toast.LENGTH_SHORT).show();
                        i = new Intent(Job_post.this, Home2.class);
                        startActivity(i);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Job_post.this, "Post Upload", Toast.LENGTH_SHORT).show();
                        i = new Intent(Job_post.this, Success.class);
                        startActivity(i);
                    }

                }
            });
        }

           catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception applied", Toast.LENGTH_LONG).show();
            Log.e(e.getClass().getName(), e.getMessage(), e);
        }
    }







}
