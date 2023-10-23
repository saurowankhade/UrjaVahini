package com.example.electricalmaterial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MaterialCustomAdapter extends RecyclerView.Adapter<MaterialViewHolder>{

    Context adminViewMaterial;
    List<MaterialModel> modelList;

    public MaterialCustomAdapter(Context adminViewMaterial, List<MaterialModel> modelList) {
        this.adminViewMaterial = adminViewMaterial;
        this.modelList = modelList;
    }


    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_model,parent,false);
        MaterialViewHolder materialViewHolder = new MaterialViewHolder(itemView);
        materialViewHolder.setOnClickListener(new MaterialViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        return materialViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        holder.mMaterial.setText(modelList.get(position).getMaterial());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
