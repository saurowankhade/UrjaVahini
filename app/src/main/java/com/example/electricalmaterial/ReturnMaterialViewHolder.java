package com.example.electricalmaterial;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReturnMaterialViewHolder extends RecyclerView.ViewHolder{
    TextView mMaterial;
    View mView;

    public ReturnMaterialViewHolder(@NonNull View itemView) {
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

        mMaterial = itemView.findViewById(R.id.material);
    }
    private ReturnMaterialViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(ReturnMaterialViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
