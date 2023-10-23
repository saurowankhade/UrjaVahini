package com.example.electricalmaterial;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electricalmaterial.add_material.AddMaterial;
import com.example.electricalmaterial.material.updation.AdminViewHolder;
import com.example.electricalmaterial.material.updation.AdminViewMaterial;
import com.example.electricalmaterial.material.updation.ModelUpdation;

import java.util.List;

public class AdminCustomAdapter extends RecyclerView.Adapter<AdminViewHolder> {

    AdminViewMaterial adminViewMaterial;
    List<ModelUpdation> modelList;

    public AdminCustomAdapter(AdminViewMaterial adminViewMaterial, List<ModelUpdation> modelList) {
        this.adminViewMaterial = adminViewMaterial;
        this.modelList = modelList;
    }


    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_model,parent,false);
        AdminViewHolder adminViewHolder = new AdminViewHolder(itemView);
        adminViewHolder.setOnClickListener(new AdminViewHolder.ClickListener() {
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
                String state = modelList.get(position).getState();
                String district = modelList.get(position).getDistrict();
                String taluka = modelList.get(position).getTaluka();
                String centerName = modelList.get(position).getCenterName();
                String village = modelList.get(position).getVillage();
                String site = modelList.get(position).getSite();
                String note = modelList.get(position).getNote();
                String materialReceiverName = modelList.get(position).getMaterialReceiverName();
                String materialGiverName = modelList.get(position).getMaterialGiverName();

                Intent intent = new Intent(adminViewMaterial,ViewData.class);
                intent.putExtra("Id",id);
                intent.putExtra("Date",date);
                intent.putExtra("TeamName",teamName);
                intent.putExtra("Line",line);
                intent.putExtra("Tender",tender);
                intent.putExtra("Driver Name",driverName);
                intent.putExtra("Vehical Name",vehicalName);
                intent.putExtra("Consumer Name",consumerName);
                intent.putExtra("State",state);
                intent.putExtra("District",district);
                intent.putExtra("Taluka",taluka);
                intent.putExtra("Center Name",centerName);
                intent.putExtra("Village",village);
                intent.putExtra("Site",site);
                intent.putExtra("Note",note);
                intent.putExtra("Material Receiver Name",materialReceiverName);
                intent.putExtra("Material Giver Name",materialGiverName);

                adminViewMaterial.startActivity(intent);


            }

            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(adminViewMaterial);
                String [] options = {"View","Update","Return Material","Delete"};
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
                        String state = modelList.get(position).getState();
                        String district = modelList.get(position).getDistrict();
                        String taluka = modelList.get(position).getTaluka();
                        String centerName = modelList.get(position).getCenterName();
                        String village = modelList.get(position).getVillage();
                        String site = modelList.get(position).getSite();
                        String note = modelList.get(position).getNote();
                        String materialReceiverName = modelList.get(position).getMaterialReceiverName();
                        String materialGiverName = modelList.get(position).getMaterialGiverName();

                        if (which==0){
                            Intent intent = new Intent(adminViewMaterial,ViewData.class);
                            intent.putExtra("Id",id);
                            intent.putExtra("Date",date);
                            intent.putExtra("TeamName",teamName);
                            intent.putExtra("Line",line);
                            intent.putExtra("Tender",tender);
                            intent.putExtra("Driver Name",driverName);
                            intent.putExtra("Vehical Name",vehicalName);
                            intent.putExtra("Consumer Name",consumerName);
                            intent.putExtra("State",state);
                            intent.putExtra("District",district);
                            intent.putExtra("Taluka",taluka);
                            intent.putExtra("Center Name",centerName);
                            intent.putExtra("Village",village);
                            intent.putExtra("Site",site);
                            intent.putExtra("Note",note);
                            intent.putExtra("Material Receiver Name",materialReceiverName);
                            intent.putExtra("Material Giver Name",materialGiverName);

                            adminViewMaterial.startActivity(intent);

                        }
                        if (which==1){

                            Intent intent = new Intent(adminViewMaterial, AddMaterial.class);
                            intent.putExtra("Id",id);
                            intent.putExtra("ForWhat","Update");
                            intent.putExtra("Date",date);
                            intent.putExtra("TeamName",teamName);
                            intent.putExtra("Line",line);
                            intent.putExtra("Tender",tender);
                            intent.putExtra("Driver Name",driverName);
                            intent.putExtra("Vehical Name",vehicalName);
                            intent.putExtra("Consumer Name",consumerName);
                            intent.putExtra("State",state);
                            intent.putExtra("District",district);
                            intent.putExtra("Taluka",taluka);
                            intent.putExtra("Center Name",centerName);
                            intent.putExtra("Village",village);
                            intent.putExtra("Site",site);
                            intent.putExtra("Note",note);
                            intent.putExtra("Material Receiver Name",materialReceiverName);
                            intent.putExtra("Material Giver Name",materialGiverName);

                            adminViewMaterial.startActivity(intent);

                        }
                        if (which==2){
                            Intent intent = new Intent(adminViewMaterial,AddMaterial.class);
                            intent.putExtra("Id",id);
                            intent.putExtra("ForWhat","Return Material");
                            intent.putExtra("Date",date);
                            intent.putExtra("TeamName",teamName);
                            intent.putExtra("Line",line);
                            intent.putExtra("Tender",tender);
                            intent.putExtra("Driver Name",driverName);
                            intent.putExtra("Vehical Name",vehicalName);
                            intent.putExtra("Consumer Name",consumerName);
                            intent.putExtra("State",state);
                            intent.putExtra("District",district);
                            intent.putExtra("Taluka",taluka);
                            intent.putExtra("Center Name",centerName);
                            intent.putExtra("Village",village);
                            intent.putExtra("Site",site);
                            intent.putExtra("Note",note);
                            intent.putExtra("Material Receiver Name",materialReceiverName);
                            intent.putExtra("Material Giver Name",materialGiverName);

                            adminViewMaterial.startActivity(intent);
                        }
                        if (which==3){
                            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(adminViewMaterial);
                            builder.setIcon(R.drawable.ic_action_delete)
                                    .setTitle("Do you want to Exit ?")
                                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            adminViewMaterial.deleteData(position);
                                        }
                                    })
                                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).create().show();
                        }
                    }
                }).create().show();
            }
        });

        return adminViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        viewHolder.mDate.setText(modelList.get(i).getDate());
        viewHolder.mTeamName.setText(modelList.get(i).getTeamName());
        viewHolder.mTender.setText(modelList.get(i).getTender());
        viewHolder.mConsumerName.setText(modelList.get(i).getConsumerName());
        viewHolder.mSiteName.setText(modelList.get(i).getVillage());
        viewHolder.mCenter.setText(modelList.get(i).getCenterName());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

}
