package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockEntryAddStockAdapter extends RecyclerView.Adapter<AddStockViewHolder> {

    StockEntry addStock;
    List<AddStockModel> modelList;

    public StockEntryAddStockAdapter(StockEntry addStock, List<AddStockModel> modelList) {
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
                Toast.makeText(addStock, "Press Long to add !!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(addStock);
                String [] options = {"Add"};
                builder.setTitle("Select any one");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            Intent intent = new Intent(addStock,Stock.class);
                            addStock.startActivity(intent);
                        }
                    }

                }).create().show();
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

        if (Float.parseFloat(modelList.get(position).getUnit()) <= 5){
            holder.mMaterial.setTextColor(Color.rgb(200,0,0));
            holder.mUnit.setTextColor(Color.rgb(200,0,0));
            holder.mQuantity.setTextColor(Color.rgb(200,0,0));
        }
        else {
            holder.mMaterial.setTextColor(Color.rgb(0,0,0));
            holder.mUnit.setTextColor(Color.rgb(0,0,0));
            holder.mQuantity.setTextColor(Color.rgb(0,0,0));
        }

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
