package com.example.electricalmaterial;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddedDataChangesCustomAdapter extends RecyclerView.Adapter<AdminViewHolder> {

    AddDetails adminViewMaterial;
    List<AdminModel> modelList;

    public AddedDataChangesCustomAdapter(AddDetails adminViewMaterial, List<AdminModel> modelList) {
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
                String site = modelList.get(position).getSite();
                String materialReceiverName = modelList.get(position).getMaterialReceiverName();

                String material1 = modelList.get(position).getMaterial1();
                String unit1 = modelList.get(position).getUnit1();
                String quantity1 = modelList.get(position).getQuantity1();

                String material2 = modelList.get(position).getMaterial2();
                String unit2 = modelList.get(position).getQuantity2();
                String quantity2 = modelList.get(position).getUnit2();

                String material3 = modelList.get(position).getMaterial3();
                String unit3 = modelList.get(position).getUnit3();
                String quantity3 = modelList.get(position).getQuantity3();

                String material4 = modelList.get(position).getMaterial4();
                String unit4 = modelList.get(position).getUnit4();
                String quantity4 = modelList.get(position).getQuantity4();

                String material5 = modelList.get(position).getMaterial5();
                String unit5 = modelList.get(position).getUnit5();
                String quantity5 = modelList.get(position).getQuantity5();

                String material6 = modelList.get(position).getMaterial6();
                String unit6 = modelList.get(position).getUnit6();
                String quantity6 = modelList.get(position).getQuantity6();

                String material7 = modelList.get(position).getMaterial7();
                String unit7 = modelList.get(position).getUnit7();
                String quantity7 = modelList.get(position).getQuantity7();

                String material8 = modelList.get(position).getMaterial8();
                String unit8 = modelList.get(position).getUnit8();
                String quantity8 = modelList.get(position).getQuantity8();

                String material9 = modelList.get(position).getMaterial9();
                String unit9 = modelList.get(position).getUnit9();
                String quantity9 = modelList.get(position).getQuantity9();

                String material10 = modelList.get(position).getMaterial10();
                String unit10 = modelList.get(position).getUnit10();
                String quantity10 = modelList.get(position).getQuantity10();

                String material11 = modelList.get(position).getMaterial11();
                String unit11 = modelList.get(position).getUnit11();
                String quantity11 = modelList.get(position).getQuantity11();

                String material12 = modelList.get(position).getMaterial12();
                String unit12 = modelList.get(position).getUnit12();
                String quantity12 = modelList.get(position).getQuantity12();

                String material13 = modelList.get(position).getMaterial13();
                String unit13 = modelList.get(position).getUnit13();
                String quantity13 = modelList.get(position).getQuantity13();

                String material14 = modelList.get(position).getMaterial14();
                String unit14 = modelList.get(position).getUnit14();
                String quantity14 = modelList.get(position).getQuantity14();

                String material15 = modelList.get(position).getMaterial15();
                String unit15 = modelList.get(position).getUnit15();
                String quantity15 = modelList.get(position).getQuantity15();

                String material16 = modelList.get(position).getMaterial16();
                String unit16 = modelList.get(position).getUnit16();
                String quantity16 = modelList.get(position).getQuantity16();

                String material17 = modelList.get(position).getMaterial17();
                String unit17 = modelList.get(position).getUnit17();
                String quantity17 = modelList.get(position).getQuantity17();

                String material18 = modelList.get(position).getMaterial18();
                String unit18 = modelList.get(position).getUnit18();
                String quantity18 = modelList.get(position).getQuantity18();

                String material19 = modelList.get(position).getMaterial19();
                String unit19 = modelList.get(position).getUnit19();
                String quantity19 = modelList.get(position).getQuantity19();

                String material20 = modelList.get(position).getMaterial20();
                String unit20 = modelList.get(position).getUnit20();
                String quantity20 = modelList.get(position).getQuantity20();

                String material21 = modelList.get(position).getMaterial21();
                String unit21 = modelList.get(position).getUnit21();
                String quantity21 = modelList.get(position).getQuantity21();

                String material22 = modelList.get(position).getMaterial22();
                String unit22 = modelList.get(position).getUnit22();
                String quantity22 = modelList.get(position).getQuantity22();

                String material23 = modelList.get(position).getMaterial23();
                String unit23 = modelList.get(position).getUnit23();
                String quantity23 = modelList.get(position).getQuantity23();

                String material24 = modelList.get(position).getMaterial24();
                String unit24 = modelList.get(position).getUnit24();
                String quantity24 = modelList.get(position).getQuantity24();

                String material25 = modelList.get(position).getMaterial25();
                String unit25 = modelList.get(position).getUnit25();
                String quantity25 = modelList.get(position).getQuantity25();

                String material26 = modelList.get(position).getMaterial26();
                String unit26 = modelList.get(position).getUnit26();
                String quantity26 = modelList.get(position).getQuantity26();

                String material27 = modelList.get(position).getMaterial27();
                String unit27 = modelList.get(position).getUnit27();
                String quantity27 = modelList.get(position).getQuantity27();

                String material28 = modelList.get(position).getMaterial28();
                String unit28 = modelList.get(position).getUnit28();
                String quantity28 = modelList.get(position).getQuantity28();

                String material29 = modelList.get(position).getMaterial29();
                String unit29 = modelList.get(position).getUnit29();
                String quantity29 = modelList.get(position).getQuantity29();

                String material30 = modelList.get(position).getMaterial30();
                String unit30 = modelList.get(position).getUnit30();
                String quantity30 = modelList.get(position).getQuantity30();

                String center = modelList.get(position).getCenter();
                String village = modelList.get(position).getVillage();

                Intent intent = new Intent(adminViewMaterial,ViewData.class);
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
                intent.putExtra("Material1",material1);
                intent.putExtra("Unit1",unit1);
                intent.putExtra("Quantity1",quantity1);

                intent.putExtra("Material2",material2);
                intent.putExtra("Unit2",unit2);
                intent.putExtra("Quantity2",quantity2);

                intent.putExtra("Material3",material3);
                intent.putExtra("Unit3",unit3);
                intent.putExtra("Quantity3",quantity3);

                intent.putExtra("Material4",material4);
                intent.putExtra("Unit4",unit4);
                intent.putExtra("Quantity4",quantity4);

                intent.putExtra("Material5",material5);
                intent.putExtra("Unit5",unit5);
                intent.putExtra("Quantity5",quantity5);

                intent.putExtra("Material6",material6);
                intent.putExtra("Unit6",unit6);
                intent.putExtra("Quantity6",quantity6);

                intent.putExtra("Material7",material7);
                intent.putExtra("Unit7",unit7);
                intent.putExtra("Quantity7",quantity7);

                intent.putExtra("Material8",material8);
                intent.putExtra("Unit8",unit8);
                intent.putExtra("Quantity8",quantity8);

                intent.putExtra("Material9",material9);
                intent.putExtra("Unit9",unit9);
                intent.putExtra("Quantity9",quantity9);

                intent.putExtra("Material10",material10);
                intent.putExtra("Unit10",unit10);
                intent.putExtra("Quantity10",quantity10);

                intent.putExtra("Material11",material11);
                intent.putExtra("Unit11",unit11);
                intent.putExtra("Quantity11",quantity11);

                intent.putExtra("Material12",material12);
                intent.putExtra("Unit12",unit12);
                intent.putExtra("Quantity12",quantity12);

                intent.putExtra("Material13",material13);
                intent.putExtra("Unit13",unit13);
                intent.putExtra("Quantity13",quantity13);

                intent.putExtra("Material14",material14);
                intent.putExtra("Unit14",unit14);
                intent.putExtra("Quantity14",quantity14);

                intent.putExtra("Material15",material15);
                intent.putExtra("Unit15",unit15);
                intent.putExtra("Quantity15",quantity15);

                intent.putExtra("Material16",material16);
                intent.putExtra("Unit16",unit16);
                intent.putExtra("Quantity16",quantity16);

                intent.putExtra("Material17",material17);
                intent.putExtra("Unit17",unit17);
                intent.putExtra("Quantity17",quantity17);

                intent.putExtra("Material18",material18);
                intent.putExtra("Unit18",unit18);
                intent.putExtra("Quantity18",quantity18);

                intent.putExtra("Material19",material19);
                intent.putExtra("Unit19",unit19);
                intent.putExtra("Quantity19",quantity19);

                intent.putExtra("Material20",material20);
                intent.putExtra("Unit20",unit20);
                intent.putExtra("Quantity20",quantity20);

                intent.putExtra("Material21",material21);
                intent.putExtra("Unit21",unit21);
                intent.putExtra("Quantity21",quantity21);

                intent.putExtra("Material22",material22);
                intent.putExtra("Unit22",unit22);
                intent.putExtra("Quantity22",quantity22);

                intent.putExtra("Material23",material23);
                intent.putExtra("Unit23",unit23);
                intent.putExtra("Quantity23",quantity23);

                intent.putExtra("Material24",material24);
                intent.putExtra("Unit24",unit24);
                intent.putExtra("Quantity24",quantity24);

                intent.putExtra("Material25",material25);
                intent.putExtra("Unit25",unit25);
                intent.putExtra("Quantity25",quantity25);

                intent.putExtra("Material26",material26);
                intent.putExtra("Unit26",unit26);
                intent.putExtra("Quantity26",quantity26);

                intent.putExtra("Material27",material27);
                intent.putExtra("Unit27",unit27);
                intent.putExtra("Quantity27",quantity27);

                intent.putExtra("Material28",material28);
                intent.putExtra("Unit28",unit28);
                intent.putExtra("Quantity28",quantity28);

                intent.putExtra("Material29",material29);
                intent.putExtra("Unit29",unit29);
                intent.putExtra("Quantity29",quantity29);

                intent.putExtra("Material30",material30);
                intent.putExtra("Unit30",unit30);
                intent.putExtra("Quantity30",quantity30);
                intent.putExtra("Center",center);
                intent.putExtra("Village",village);

                adminViewMaterial.startActivity(intent);


            }

            @Override
            public void onItemLongClick(View view, int position) {

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
        viewHolder.mCenter.setText(modelList.get(i).getCenter());


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

}
