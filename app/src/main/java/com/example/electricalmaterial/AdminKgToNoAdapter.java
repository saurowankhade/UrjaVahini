package com.example.electricalmaterial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminKgToNoAdapter extends RecyclerView.Adapter<KgToNoViewHolder> {
    AddMaterial addStock;
    List<KgToNoModel> modelList;

    public AdminKgToNoAdapter(AddMaterial addStock, List<KgToNoModel> modelList) {
        this.addStock = addStock;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public KgToNoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kg_to_no_model,parent,false);
        KgToNoViewHolder addStockViewHolder = new KgToNoViewHolder(itemView);
        addStockViewHolder.setOnClickListener(new KgToNoViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        return addStockViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KgToNoViewHolder holder, int position) {
        holder.mMaterial.setText(modelList.get(position).getMaterial());
        holder.mQuantity.setText(modelList.get(position).getUnit());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
