package com.example.electricalmaterial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReturnMaterialCustomAdapter extends RecyclerView.Adapter<ReturnMaterialViewHolder> {
    ViewReturnMaterial viewReturnMaterial;
    List<ReturnMaterialModel> modelList;

    public ReturnMaterialCustomAdapter(ViewReturnMaterial viewReturnMaterial, List<ReturnMaterialModel> modelList) {
        this.viewReturnMaterial = viewReturnMaterial;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ReturnMaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_model,parent,false);
        ReturnMaterialViewHolder returnMaterialViewHolder = new ReturnMaterialViewHolder(itemView);
        returnMaterialViewHolder.setOnClickListener(new ReturnMaterialViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        return returnMaterialViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnMaterialViewHolder holder, int position) {
        holder.mMaterial.setText(modelList.get(position).getMaterial());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
