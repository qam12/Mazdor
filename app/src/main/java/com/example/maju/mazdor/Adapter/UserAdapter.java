package com.example.maju.mazdor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maju.mazdor.Activites.ChatActivity;
import com.example.maju.mazdor.Activites.ChatMessagesActivity;
import com.example.maju.mazdor.Model.UserList;
import com.example.maju.mazdor.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by qamber.haider on 11/28/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserList> Userprofiles;
    private ItemClickListener mClickListener;

    public static final String KEY_UID="User_ID";
    public static final String KEY_NAME="User_Name";

    public UserAdapter(Context c , ArrayList<UserList> p)
    {
        context = c;
        Userprofiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.user_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final UserList info = Userprofiles.get(position);
        holder._userName.setText(Userprofiles.get(position).getPrFirstName());
        holder._userEmail.setText(Userprofiles.get(position).getPrEmail());
        holder._userPhone.setText(Userprofiles.get(position).getPrTelephone());
        holder._userId.setText(Userprofiles.get(position).getPrid());

//      Picasso.get().load(profiles.get(position).getProfilePic()).into(holder.profilePic);
        holder.sendMessaAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "chat", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,ChatMessagesActivity.class);
                intent.putExtra(KEY_UID, info.getPrid());
                intent.putExtra(KEY_NAME, info.getPrFirstName());
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return Userprofiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView _userName , _userEmail, _userPhone,_userId;
        RelativeLayout sendMessaAc;

//        ImageView profilePic;
//        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);

            _userName = (TextView) itemView.findViewById(R.id.titleUser);
            _userEmail = (TextView) itemView.findViewById(R.id.emailUser);
            _userPhone = (TextView) itemView.findViewById(R.id.phonUser);
            _userId = (TextView) itemView.findViewById(R.id.userRep_UID);
            sendMessaAc = (RelativeLayout)itemView.findViewById(R.id.message_userID);

//            profilePic = (ImageView) itemView.findViewById(R.id.profilePic);
//            btn = (Button) itemView.findViewById(R.id.checkDetails);
        }

    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}