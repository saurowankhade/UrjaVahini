package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReturnAddTotalMaterialTakenAdapter extends RecyclerView.Adapter<AddStockViewHolder> {

    AddReturnMaterial addStock;
    List<AddTotalMaterialTakenModel> modelList;

    public ReturnAddTotalMaterialTakenAdapter(AddReturnMaterial addStock, List<AddTotalMaterialTakenModel> modelList) {
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
        holder.mUnit.setText(modelList.get(position).getUnit());
        holder.mQuantity.setText(modelList.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }






}
