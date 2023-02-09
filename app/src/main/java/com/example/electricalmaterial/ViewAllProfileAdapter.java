package com.example.electricalmaterial;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewAllProfileAdapter extends RecyclerView.Adapter<ViewAllProfileViewHolder> {

    ViewAllProfile viewAllProfile;
    List<ViewAllProfileModel> modelList;



    public ViewAllProfileAdapter(List<ViewAllProfileModel> modelList) {
        this.modelList = modelList;
    }



    public ViewAllProfileAdapter(ViewAllProfile viewAllProfile, List<ViewAllProfileModel> modelList) {
        this.viewAllProfile = viewAllProfile;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewAllProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_view_all_profile,parent,false);
        ViewAllProfileViewHolder viewAllProfileViewHolder = new ViewAllProfileViewHolder(itemView);
        viewAllProfileViewHolder.setOnClickListener(new ViewAllProfileViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = modelList.get(position).getId();
                String fullName = modelList.get(position).getFullName();
                String currentAddress = modelList.get(position).getCurrentAddress();
                String permanentAddress = modelList.get(position).getPermanentAddress();
                String mobileNumber = modelList.get(position).getMobileNumber();
                String email = modelList.get(position).getEmail();
                String profile = modelList.get(position).getProfile();
                String companyName = modelList.get(position).getCompanyName();
                String companyEmail = modelList.get(position).getCompanyEmail();
                String education = modelList.get(position).getEducation();

                Intent intent = new Intent(viewAllProfile,ViewAllProfileWithAllDetails.class);
                intent.putExtra("Id",id);
                intent.putExtra("fullName",fullName);
                intent.putExtra("currentAddress",currentAddress);
                intent.putExtra("permanentAddress",permanentAddress);
                intent.putExtra("mobileNumber",mobileNumber);
                intent.putExtra("email",email);
                intent.putExtra("profile",profile);
                intent.putExtra("companyName",companyName);
                intent.putExtra("companyEmail",companyEmail);
                intent.putExtra("education",education);
                viewAllProfile.startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(viewAllProfile);
                String [] options = {"View","Delete"};
                builder.setTitle("Select any one");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==1){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(viewAllProfile);
                            builder1.setTitle("Are You sure to delete ?")
                                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            viewAllProfile.deleteData(position);
                                        }
                                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(viewAllProfile, "Cancel", Toast.LENGTH_SHORT).show();
                                        }
                                    }).create().show();
                        }
                        if (which==0){
                            String id = modelList.get(position).getId();
                            String fullName = modelList.get(position).getFullName();
                            String currentAddress = modelList.get(position).getCurrentAddress();
                            String permanentAddress = modelList.get(position).getPermanentAddress();
                            String mobileNumber = modelList.get(position).getMobileNumber();
                            String email = modelList.get(position).getEmail();
                            String profile = modelList.get(position).getProfile();
                            String companyName = modelList.get(position).getCompanyName();
                            String companyEmail = modelList.get(position).getCompanyEmail();
                            String education = modelList.get(position).getEducation();

                            Intent intent = new Intent(viewAllProfile,ViewAllProfileWithAllDetails.class);
                            intent.putExtra("Id",id);
                            intent.putExtra("fullName",fullName);
                            intent.putExtra("currentAddress",currentAddress);
                            intent.putExtra("permanentAddress",permanentAddress);
                            intent.putExtra("mobileNumber",mobileNumber);
                            intent.putExtra("email",email);
                            intent.putExtra("profile",profile);
                            intent.putExtra("companyName",companyName);
                            intent.putExtra("companyEmail",companyEmail);
                            intent.putExtra("education",education);
                            viewAllProfile.startActivity(intent);
                        }
                    }
                }).create().show();
            }
        });
        return viewAllProfileViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllProfileViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.mobileNo.setText(modelList.get(position).getMobileNumber());
        holder.fullName.setText(modelList.get(position).getFullName());

        holder.dialog = new Dialog(viewAllProfile);
        holder.dialog.setContentView(R.layout.image_display);
        holder.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        holder.dialog.getWindow().setBackgroundDrawableResource(R.drawable.back_background);
        holder.profileImage = holder.dialog.findViewById(R.id.profileImage);

        StorageReference profileRef = holder.storageReference.child("users/"+modelList.get(position).getId()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.imageView);
                Picasso.get().load(uri).into(holder.profileImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Uri uri = Uri.parse("android.resource://com.example.electricalmaterial/drawable/userphoto");
                Picasso.get().load(uri).into(holder.imageView);
                Picasso.get().load(uri).into(holder.profileImage);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.dialog.show();

            }
        });

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(viewAllProfile, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                    String link = ("tel:"+modelList.get(position ).getMobileNumber());
                    Uri uri = Uri.parse(link);
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    viewAllProfile.startActivity(intent);

                }

                else{
                    ActivityCompat.requestPermissions(viewAllProfile,new String[] {Manifest.permission.CALL_PHONE}, 100);
                    Toast.makeText(viewAllProfile, "Give Permission", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


}
