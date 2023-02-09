package com.example.electricalmaterial;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddStockViewHolder extends RecyclerView.ViewHolder {

    TextView mMaterial,mQuantity,mUnit;
    View mView;

    public AddStockViewHolder(@NonNull View itemView) {
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
        mQuantity = itemView.findViewById(R.id.quantity);
        mUnit = itemView.findViewById(R.id.unit);

    }

    private  AddStockViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(AddStockViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
