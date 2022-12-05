package com.example.healthcareapp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
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
        private BottomSheetDialog bottomSheetDialog;
        private BottomSheetBehavior bottomSheetBehavior;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.doctorName);
            pfp = itemView.findViewById(R.id.pfp);
            bottomSheetDialog = new BottomSheetDialog(context);

            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            showBottomSheet();
        }

        private void showBottomSheet() {
            bottomSheetDialog.setContentView(R.layout.doctor_chat);
            setBottomSheetBehavior();
            setContent();
            bottomSheetDialog.show();
        }

        private void setBottomSheetBehavior() {
            bottomSheetBehavior = bottomSheetDialog.getBehavior();

            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
        }

        private void setContent() {
            Doctor currentDoc = doctorList.get(getAdapterPosition());
            MaterialToolbar chatTopAppBar = bottomSheetDialog.findViewById(R.id.chatTopAppBar);

            chatTopAppBar.setTitle(currentDoc.getName());

            chatTopAppBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            });
        }
    }
}
