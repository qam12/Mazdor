package com.example.maju.mazdor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.maju.mazdor.Adapter.UserAdapter;
import com.example.maju.mazdor.Model.UserList;
import com.example.maju.mazdor.Model.User_Details;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback extends Fragment {

    public final static String Database_Path = "USERS"; // Root Database Name for Firebase Database.
    static String UID;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog ;
    RecyclerView RV;
    ArrayList<UserList> list;
    UserAdapter adapter;


    public Feedback() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        RV = (RecyclerView) v.findViewById(R.id.Pr_list);
        RV.setLayoutManager( new LinearLayoutManager(getActivity()));


        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();

        UID = firebaseAuth.getCurrentUser().getUid();

        Log.v("Loc", UID);

        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list = new ArrayList<UserList>();

                try {
                    for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                        User_Details user_details = dataSnapshot1.getValue(User_Details.class);
//                        list.add(user_details);

                        UserList dataE = new UserList();
                        String NameFir=user_details.getUser_FirstName();
                        String EmailFir=user_details.getUser_Email();
                        String PhoneFir=user_details.getUser_Telephone();
                        String proIDFir=user_details.getId();


                        dataE.setPrFirstName(NameFir);
                        dataE.setPrEmail(EmailFir);
                        dataE.setPrTelephone(PhoneFir);
                        dataE.setPrid(proIDFir);
                        list.add(dataE);

                        progressDialog.dismiss();

                        System.out.println(NameFir + "Name");
                        System.out.println(EmailFir + "Email");
                        System.out.println(PhoneFir + "Phone");
//                        System.out.println(proIDFir + "ID");
                        Toast.makeText(getContext(),""+NameFir,Toast.LENGTH_LONG).show();


                    }
                    adapter = new UserAdapter(getActivity(),list);
                    RV.setAdapter(adapter);
                }
                catch (Exception e) {
                    Toast.makeText(getContext(), "Exception applied", Toast.LENGTH_LONG).show();
                    Log.e(e.getClass().getName(), e.getMessage(), e);
                    progressDialog.dismiss();
                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

}
