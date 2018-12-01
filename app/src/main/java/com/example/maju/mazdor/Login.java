package com.example.maju.mazdor;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    static String LoggedIn_User_Email;

    private EditText Email_emp;
    private EditText Pass_emp;
    private Button login;
    TextView signup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    static String UID;

    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //firebase Auth

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile ativity
            UID =firebaseAuth.getCurrentUser().getUid();
            finish();
            startActivity(new Intent(getApplicationContext(),Home2.class));
            //Toast.makeText(this, "Profile Activity", Toast.LENGTH_SHORT).show();
        }



        Email_emp = (EditText) findViewById(R.id.emaiId);
        Pass_emp = (EditText) findViewById(R.id.passwordId);
        login = (Button) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.sigRoot);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(Login.this,Signup.class);
            startActivity(intent);
            }
        });

        login.setOnClickListener(this);


    }


    private void Employee_Login(){
        String email = Email_emp.getText().toString().trim();
        String password = Pass_emp.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            //Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            Email_emp.setError("Required");
            return;
        }

        if(TextUtils.isEmpty(password)){
            //Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            Pass_emp.setError("Required");
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog
        //progressDialog.setCancelable(false);
        progressDialog.setMessage("Login Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //progressDialog.dismiss();
                if (task.isSuccessful()){
                    //profile ativity
                    finish();
                    try {

                        UID =firebaseAuth.getCurrentUser().getUid();
                        startActivity(new Intent(getApplicationContext(),Home2.class));

                        //Toast.makeText(Login.this, "Profile Activity", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Exception applied", Toast.LENGTH_LONG).show();
                        Log.e(e.getClass().getName(), e.getMessage(), e);

                    }

                }
                else{
                    //display some message here
                    // Toast.makeText(Signup.this,"Registration Error",Toast.LENGTH_LONG).show();
                    Toast.makeText(Login.this, "Invalid Email and Password", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view == login){
            Employee_Login();
        }
    }
}
