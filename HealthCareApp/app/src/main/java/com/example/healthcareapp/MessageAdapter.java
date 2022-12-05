package com.example.healthcareapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Message> msgArray;

    private static final int MSG_SENDER = 0, MSG_RECEIVER = 1;

    public MessageAdapter(Context context, ArrayList<Message> msgArray) {
        this.context = context;
        this.msgArray = msgArray;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView msg;
        private ImageView doctorPfp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            msg = itemView.findViewById(R.id.message);
            doctorPfp = itemView.findViewById(R.id.doctorPfp);
        }

        @Override
        public void onClick(View v) {
            Message currentMsg = msgArray.get(getAdapterPosition());
            String msg = currentMsg.getMsg();


        }

        private int getViewType(int position) {
            // TODO: Add logics to check if the message belongs to sender or receiver
            // Return either MSG_SENDER or MSG_RECEIVER
            return 0;
        }
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_RECEIVER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_receive, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_send, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Message currentMsg = msgArray.get(position);

        holder.msg.setText(currentMsg.getMsg());

        // TODO: Add logics to add pfp if the msg belongs to receiver
    }

    @Override
    public int getItemCount() {
        return msgArray.size();
    }
}
