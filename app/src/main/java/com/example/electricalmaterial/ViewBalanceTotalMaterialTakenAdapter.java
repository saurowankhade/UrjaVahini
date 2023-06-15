package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewBalanceTotalMaterialTakenAdapter extends RecyclerView.Adapter<ViewBalanceMaterialDataViewHolder> {

    ViewBalanceData addStock;
    List<AddTotalMaterialTakenModel> modelList;

    public ViewBalanceTotalMaterialTakenAdapter(ViewBalanceData addStock, List<AddTotalMaterialTakenModel> modelList) {
        this.addStock = addStock;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewBalanceMaterialDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_balance_material_model,parent,false);
        ViewBalanceMaterialDataViewHolder addStockViewHolder = new ViewBalanceMaterialDataViewHolder(itemView);
        addStockViewHolder.setOnClickListener(new ViewBalanceMaterialDataViewHolder.ClickListener() {
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
    public void onBindViewHolder(@NonNull ViewBalanceMaterialDataViewHolder holder, int position) {

        holder.mMaterial.setText(modelList.get(position).getMaterial());
        holder.mUnit.setText(modelList.get(position).getUnit());
        holder.mQuantity.setText(modelList.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }






}
