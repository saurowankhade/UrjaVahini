package com.example.electricalmaterial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BalanceMaterialCustomAdapterInAddActucalMater extends RecyclerView.Adapter<RemainingMaterialViewHolder>{

    AddActualMaterial adminViewMaterial;
    List<BalanceMaterialModel> modelList;

    public BalanceMaterialCustomAdapterInAddActucalMater(AddActualMaterial adminViewMaterial, List<BalanceMaterialModel> modelList) {
        this.adminViewMaterial = adminViewMaterial;
        this.modelList = modelList;
    }


    @NonNull
    @Override
    public RemainingMaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_model,parent,false);
        RemainingMaterialViewHolder adminViewHolder = new RemainingMaterialViewHolder(itemView);
        adminViewHolder.setOnClickListener(new RemainingMaterialViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = modelList.get(position).getId();
                String date = modelList.get(position).getDate();
                String teamName = modelList.get(position).getTeamName();
                String line = modelList.get(position).getLine();
                String tender = modelList.get(position).getTender();
                String driverName = modelList.get(position).getDriverName();
                String vehicalName = modelList.get(position).getVehicalName();
                String consumerName = modelList.get(position).getConsumerName();
                String site = modelList.get(position).getSite();
                String materialReceiverName = modelList.get(position).getMaterialReceiverName();

                String center = modelList.get(position).getCenter();
                String village = modelList.get(position).getVillage();


                Intent intent = new Intent(adminViewMaterial,ViewBalanceData.class);
                intent.putExtra("Id",id);
                intent.putExtra("Date",date);
                intent.putExtra("TeamName",teamName);
                intent.putExtra("Line",line);
                intent.putExtra("Tender",tender);
                intent.putExtra("Driver Name",driverName);
                intent.putExtra("Vehical Name",vehicalName);
                intent.putExtra("Consumer Name",consumerName);
                intent.putExtra("Site",site);
                intent.putExtra("Material Receiver Name",materialReceiverName);

                intent.putExtra("Center",center);
                intent.putExtra("Village",village);

                adminViewMaterial.startActivity(intent);


            }

            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(adminViewMaterial);
                String [] options = {"View","Delete"};
                builder.setTitle("Select any one");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = modelList.get(position).getId();
                        String date = modelList.get(position).getDate();
                        String teamName = modelList.get(position).getTeamName();
                        String line = modelList.get(position).getLine();
                        String tender = modelList.get(position).getTender();
                        String driverName = modelList.get(position).getDriverName();
                        String vehicalName = modelList.get(position).getVehicalName();
                        String consumerName = modelList.get(position).getConsumerName();
                        String site = modelList.get(position).getSite();
                        String materialReceiverName = modelList.get(position).getMaterialReceiverName();

                        String center = modelList.get(position).getCenter();
                        String village = modelList.get(position).getVillage();


                        if (which==0){

                            Intent intent = new Intent(adminViewMaterial,ViewBalanceData.class);
                            intent.putExtra("Id",id);
                            intent.putExtra("Date",date);
                            intent.putExtra("TeamName",teamName);
                            intent.putExtra("Line",line);
                            intent.putExtra("Tender",tender);
                            intent.putExtra("Driver Name",driverName);
                            intent.putExtra("Vehical Name",vehicalName);
                            intent.putExtra("Consumer Name",consumerName);
                            intent.putExtra("Site",site);
                            intent.putExtra("Material Receiver Name",materialReceiverName);


                            intent.putExtra("Center",center);
                            intent.putExtra("Village",village);

                            adminViewMaterial.startActivity(intent);

                        }


                    }
                }).create().show();
            }
        });

        return adminViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RemainingMaterialViewHolder viewHolder, int i) {
        viewHolder.mDate.setText(modelList.get(i).getDate());
        viewHolder.mTeamName.setText(modelList.get(i).getTeamName());
        viewHolder.mTender.setText(modelList.get(i).getTender());
        viewHolder.mConsumerName.setText(modelList.get(i).getConsumerName());
        viewHolder.mSiteName.setText(modelList.get(i).getVillage());
        viewHolder.mCenter.setText(modelList.get(i).getCenter());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
