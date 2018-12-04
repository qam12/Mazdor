package com.example.maju.mazdor.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maju.mazdor.Model.ChatMessage;
import com.example.maju.mazdor.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by qamber.haider on 11/30/2018.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
    public static final int ITEM_TYPE_SENT = 0;
    public static final int ITEM_TYPE_RECEIVED = 1;
    public static String UID;


    private List<ChatMessage> mMessagesList;
    private Context mContext;

    private FirebaseAuth firebaseAuth  = FirebaseAuth.getInstance();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView messageTextView;
        public TextView messageTimes;
        public TextView messageDates;
        public TextView messageSenderName;
        public TextView RecivernAme;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            messageTextView = (TextView) v.findViewById(R.id.chatMsgTextView);
            messageTimes = (TextView) v.findViewById(R.id.Timemsg);
            messageDates = (TextView) v.findViewById(R.id.DateMsg);
            messageSenderName = (TextView)v.findViewById(R.id.Sender_name);
            RecivernAme = (TextView)v.findViewById(R.id.RecivernAme);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView messageTextView;
        public TextView messageTimes;
        public TextView messageDates;
        public TextView messageSenderName;
        public TextView RecivernAme;


        public View layout;

        public ViewHolder2(View v) {
            super(v);
            layout = v;
            messageTextView = (TextView) v.findViewById(R.id.chatMsgTextView);
            messageTimes = (TextView) v.findViewById(R.id.Timemsg);
            messageDates = (TextView) v.findViewById(R.id.DateMsg);
            messageSenderName = (TextView)v.findViewById(R.id.Sender_name);
            RecivernAme = (TextView)v.findViewById(R.id.RecivernAme);


        }
    }


    public void add(int position, ChatMessage message) {
        mMessagesList.add(position, message);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mMessagesList.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MessagesAdapter(List<ChatMessage> myDataset, Context context) {
        mMessagesList = myDataset;
        mContext = context;

    }

    @Override
    public int getItemViewType(int position) {
        UID = firebaseAuth.getCurrentUser().getUid();

        if (mMessagesList.get(position).getSenderId().equals(UID)){
            return ITEM_TYPE_SENT;
        } else {
            return ITEM_TYPE_RECEIVED;
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = null;
        if (viewType == ITEM_TYPE_SENT) {
            v = LayoutInflater.from(mContext).inflate(R.layout.sent_msg_row, null);
        } else if (viewType == ITEM_TYPE_RECEIVED) {
            v = LayoutInflater.from(mContext).inflate(R.layout.received_msg_row, null);
        }
        return new ViewHolder(v); // view holder for header items
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ChatMessage msg = mMessagesList.get(position);
        holder.messageTextView.setText(msg.getMessage());
//        holder.messageSenderName.setText(msg.getMessageSenderName());
        holder.messageTimes.setText(msg.getMessageTime());
        holder.messageDates.setText(msg.getMessageDate());
//        holder.RecivernAme.setText(msg.getMessageReceiverName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }
}