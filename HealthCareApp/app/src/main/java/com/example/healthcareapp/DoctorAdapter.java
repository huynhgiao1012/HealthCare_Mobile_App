package com.example.healthcareapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private ArrayList<Doctor> doctorList;
    private Context context;

    public DoctorAdapter(Context context, ArrayList<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor currentDoc = doctorList.get(position);
        holder.name.setText(currentDoc.getName());

        // TODO: Replace with another method to load image more efficiently
        holder.pfp.setImageResource(currentDoc.getPfp());
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;
        private final ImageView pfp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.doctorName);
            pfp = itemView.findViewById(R.id.pfp);

            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            showBottomSheet();
        }

        private void showBottomSheet() {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            bottomSheetDialog.setContentView(R.layout.doctor_chat);

            bottomSheetDialog.show();
        }
    }
}
