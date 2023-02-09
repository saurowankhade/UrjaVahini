package com.example.electricalmaterial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RemainingCustomAdapter extends RecyclerView.Adapter<RemainingViewHolder>{

    RemainingMaterial adminViewMaterial;
    List<RemainingModel> modelList;

    public RemainingCustomAdapter(RemainingMaterial adminViewMaterial, List<RemainingModel> modelList) {
        this.adminViewMaterial = adminViewMaterial;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public RemainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_model,parent,false);
        RemainingViewHolder materialViewHolder = new RemainingViewHolder(itemView);
        materialViewHolder.setOnClickListener(new RemainingViewHolder.ClickListener() {
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
    public void onBindViewHolder(@NonNull RemainingViewHolder holder, int position) {
        holder.mMaterial.setText(modelList.get(position).getMaterial());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
