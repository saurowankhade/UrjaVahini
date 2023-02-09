package com.example.electricalmaterial;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StockEntryViewHolder extends RecyclerView.ViewHolder {

    TextView mDate,mSupplier,mSupplierAddress,mDriverName,mStore,mVehical;
    View mView;

    public StockEntryViewHolder(@NonNull View itemView) {
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

        mDate = itemView.findViewById(R.id.date);
        mSupplier = itemView.findViewById(R.id.supplier);
        mSupplierAddress = itemView.findViewById(R.id.supplierAdd);
        mDriverName = itemView.findViewById(R.id.driver);
        mStore = itemView.findViewById(R.id.storeName);
        mVehical = itemView.findViewById(R.id.vehical);


    }

    private  StockEntryViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(StockEntryViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
