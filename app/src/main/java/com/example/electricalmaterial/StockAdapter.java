package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<AddStockViewHolder> {
    Stock addStock;
    List<AddStockModel> modelList;

    public StockAdapter(Stock addStock, List<AddStockModel> modelList) {
        this.addStock = addStock;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public AddStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_model,parent,false);
        AddStockViewHolder addStockViewHolder = new AddStockViewHolder(itemView);
        addStockViewHolder.setOnClickListener(new AddStockViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        return addStockViewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AddStockViewHolder holder, int position) {

        holder.mMaterial.setText(modelList.get(position).getMaterial());
        holder.mUnit.setText(modelList.get(position).getQuantity());
        holder.mQuantity.setText(modelList.get(position).getUnit());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
