package com.example.electricalmaterial;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ViewAllProfileViewHolder extends RecyclerView.ViewHolder {

    TextView fullName,mobileNo;
    ImageView imageView;
    ImageView call;
    StorageReference storageReference;

    View mView;

    Dialog dialog;
    ImageView profileImage;

    LinearLayout cardView;


    public ViewAllProfileViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,getAbsoluteAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v,getAbsoluteAdapterPosition());
                return true;
            }
        });


        imageView = itemView.findViewById(R.id.setImage);
        fullName = itemView.findViewById(R.id.name);
        mobileNo = itemView.findViewById(R.id.mobileNo);
        call = itemView.findViewById(R.id.call);
        cardView = itemView.findViewById(R.id.cardView);
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    private ViewAllProfileViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(ViewAllProfileViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
