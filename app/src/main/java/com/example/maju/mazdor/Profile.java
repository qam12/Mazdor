package com.example.maju.mazdor;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {


    public Profile() {
        // Required empty public constructor
    }


    public final static String Storage_Path = "Employee_profile/";
    public final static String Database_Path = "Employee";
    static String UID;
    CircleImageView ProfileImg , Act_ProfileImg;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private FirebaseAuth firebaseAuth;
    TextView emp_Name,emp_desg,emp_email,emp_phon,emp_pass,emp_nic;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile,container, false);


        //database refrence
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();


        UID =firebaseAuth.getCurrentUser().getUid();

        progressDialog = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);


        emp_Name  = (TextView)view.findViewById(R.id.Empname);
        emp_desg  = (TextView)view.findViewById(R.id.designation);
        emp_email  = (TextView)view.findViewById(R.id.Empemail);
        emp_phon  = (TextView)view.findViewById(R.id.Empphone);
        emp_nic  = (TextView)view.findViewById(R.id.Emppnic);
        Act_ProfileImg = (CircleImageView)view.findViewById(R.id.profile);


        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        if (UID != null) {
                mDatabase.child(UID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Employee_details emp_details = dataSnapshot.getValue(Employee_details.class);

                        if (emp_details != null) {

                            String m_pic = emp_details.employee_Profilepicture;
                            String m_name = emp_details.employee_FirstName;
                            String m_desg = emp_details.employee_Designation;
                            String m_email = emp_details.employee_Email;
                            String m_phone = emp_details.employee_Telephone;
                            String m_nic = emp_details.employee_Nic;


                            //personal data
                            emp_Name.setText(m_name.toString());
                            emp_desg.setText(m_desg.toString());
                            emp_email.setText(m_email.toString());
                            emp_phon.setText(m_phone.toString());
                            emp_nic.setText(m_nic.toString());

                            //Log.i("ImageUrl", Pro_url);
                            Picasso.get().load(m_pic).placeholder(R.mipmap.place_avatar)
                                    .error(R.mipmap.place_avatar)
                                    .into(Act_ProfileImg);

                            progressDialog.dismiss();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        }

        return view;

    }

}
