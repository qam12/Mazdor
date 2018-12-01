package com.example.maju.mazdor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
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
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    static String UID;

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    private ProgressDialog progressDialog;
    TextView UserName;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private FirebaseAuth firebaseAuth;
    public final static String Database_Path = "Employee";
    CircleImageView ProfileImg;
    String m_pic,m_name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();

        UID = firebaseAuth.getCurrentUser().getUid();

        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);

//        if (firebaseAuth.getCurrentUser() != null) {
//            //profile ativity
//            finish();
//            startActivity(new Intent(getApplicationContext(), Home.class));
//        }

//        setting mainFragment
        HomeFr fragement = new HomeFr();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameit,fragement);
        fragmentTransaction.commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View des = LayoutInflater.from(getBaseContext()).inflate(R.layout.nav_header_home2, navigationView);

        UserName = (TextView)des.findViewById(R.id.usernaME);
        ProfileImg = (CircleImageView)des.findViewById(R.id.profile_pic);



        progressDialog.setMessage("Please Wait");
        progressDialog.show();


            if (UID != null) {
                mDatabase.child(UID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Employee_details emp_details = dataSnapshot.getValue(Employee_details.class);

                        try {

                            if (emp_details != null) {

                                m_pic = emp_details.employee_Profilepicture;
                                m_name = emp_details.employee_FirstName;


                                //personal data
                                UserName.setText(m_name.toString());
                                Log.d("Name",m_name.toString());


                                //Log.i("ImageUrl", Pro_url);
                                Picasso.get().load(m_pic).placeholder(R.mipmap.place_avatar)
                                        .error(R.mipmap.place_avatar)
                                        .into(ProfileImg);
                                progressDialog.dismiss();
                            }
                        }
                        catch (Exception e) {
                            // This will catch any exception, because they are all descended from Exception
                            System.out.println("Error " + e.getMessage());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Home2.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        navigationView.setNavigationItemSelectedListener(this);





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent i;

        int id = item.getItemId();

        if (id == R.id.nav_Home) {

            HomeFr fragement = new HomeFr();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameit,fragement);
            fragmentTransaction.commit();

        }else if (id == R.id.nav_camera) {

            Profile fragement = new Profile();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameit,fragement);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_gallery) {

            AboutFr fragement = new AboutFr();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameit,fragement);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_slideshow) {

            Feedback fragement = new Feedback();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameit,fragement);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_manage) {

            firebaseAuth.signOut();
            finish();
            i = new Intent(Home2.this, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
//                Log.i("test","check");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

        //} else if (id == R.id.nav_share) {
        //
        //        } else if (id == R.id.nav_send) {
        //
}
