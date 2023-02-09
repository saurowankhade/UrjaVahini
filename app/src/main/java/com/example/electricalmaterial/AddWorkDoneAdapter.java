package com.example.electricalmaterial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddWorkDoneAdapter extends RecyclerView.Adapter<AddWorkDoneHolder>{

    ViewWorkDone workDone;
    List<AddWorkDoneModel> modelList;

    public AddWorkDoneAdapter(ViewWorkDone workDone, List<AddWorkDoneModel> modelList) {
        this.workDone = workDone;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public AddWorkDoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_work_done_model,parent,false);
        AddWorkDoneHolder adminViewHolder = new AddWorkDoneHolder(itemView);
        adminViewHolder.setOnClickListener(new AddWorkDoneHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                String id = modelList.get(position).getId();
                String date = modelList.get(position).getDate();
                String line = modelList.get(position).getLine();
                String tender = modelList.get(position).getTender();
                String teamName = modelList.get(position).getTeamName();
                String consumerName = modelList.get(position).getConsumerName();
                String site = modelList.get(position).getSite();

                String material1 = modelList.get(position).getMaterial1();
                String quantity1 = modelList.get(position).getQuantity1();

                String material2 = modelList.get(position).getMaterial2();
                String quantity2 = modelList.get(position).getQuantity2();

                String material3 = modelList.get(position).getMaterial3();
                String quantity3 = modelList.get(position).getQuantity3();

                String material4 = modelList.get(position).getMaterial4();
                String quantity4 = modelList.get(position).getQuantity4();

                String material5 = modelList.get(position).getMaterial5();
                String quantity5 = modelList.get(position).getQuantity5();

                String material6 = modelList.get(position).getMaterial6();
                String quantity6 = modelList.get(position).getQuantity6();

                String material7 = modelList.get(position).getMaterial7();
                String quantity7 = modelList.get(position).getQuantity7();

                String material8 = modelList.get(position).getMaterial8();
                String quantity8 = modelList.get(position).getQuantity8();

                String material9 = modelList.get(position).getMaterial9();
                String quantity9 = modelList.get(position).getQuantity9();

                String material10 = modelList.get(position).getMaterial10();
                String quantity10 = modelList.get(position).getQuantity10();

                String material11 = modelList.get(position).getMaterial11();
                String quantity11 = modelList.get(position).getQuantity11();

                String material12 = modelList.get(position).getMaterial12();
                String quantity12 = modelList.get(position).getQuantity12();

                String material13 = modelList.get(position).getMaterial13();
                String quantity13 = modelList.get(position).getQuantity13();

                String material14 = modelList.get(position).getMaterial14();
                String quantity14 = modelList.get(position).getQuantity14();

                String material15 = modelList.get(position).getMaterial15();
                String quantity15 = modelList.get(position).getQuantity15();

                String material16 = modelList.get(position).getMaterial16();
                String quantity16 = modelList.get(position).getQuantity16();

                String material17 = modelList.get(position).getMaterial17();
                String quantity17 = modelList.get(position).getQuantity17();

                String material18 = modelList.get(position).getMaterial18();
                String quantity18 = modelList.get(position).getQuantity18();

                String material19 = modelList.get(position).getMaterial19();
                String quantity19 = modelList.get(position).getQuantity19();

                String material20 = modelList.get(position).getMaterial20();
                String quantity20 = modelList.get(position).getQuantity20();

                String material21 = modelList.get(position).getMaterial21();
                String quantity21 = modelList.get(position).getQuantity21();

                String material22 = modelList.get(position).getMaterial22();
                String quantity22 = modelList.get(position).getQuantity22();

                String material23 = modelList.get(position).getMaterial23();
                String quantity23 = modelList.get(position).getQuantity23();

                String material24 = modelList.get(position).getMaterial24();
                String quantity24 = modelList.get(position).getQuantity24();

                String material25 = modelList.get(position).getMaterial25();
                String quantity25 = modelList.get(position).getQuantity25();

                String material26 = modelList.get(position).getMaterial26();
                String quantity26 = modelList.get(position).getQuantity26();

                String material27 = modelList.get(position).getMaterial27();
                String quantity27 = modelList.get(position).getQuantity27();

                String material28 = modelList.get(position).getMaterial28();
                String quantity28 = modelList.get(position).getQuantity28();

                String material29 = modelList.get(position).getMaterial29();
                String quantity29 = modelList.get(position).getQuantity29();

                String material30 = modelList.get(position).getMaterial30();
                String quantity30 = modelList.get(position).getQuantity30();

                String material31 = modelList.get(position).getMaterial31();
                String quantity31 = modelList.get(position).getQuantity31();

                String material32 = modelList.get(position).getMaterial32();
                String quantity32 = modelList.get(position).getQuantity32();

                String material33 = modelList.get(position).getMaterial33();
                String quantity33 = modelList.get(position).getQuantity33();

                String material34 = modelList.get(position).getMaterial34();
                String quantity34 = modelList.get(position).getQuantity34();

                String material35 = modelList.get(position).getMaterial35();
                String quantity35 = modelList.get(position).getQuantity35();

                String material36 = modelList.get(position).getMaterial36();
                String quantity36 = modelList.get(position).getQuantity36();

                String material37 = modelList.get(position).getMaterial37();
                String quantity37 = modelList.get(position).getQuantity37();

                String material38 = modelList.get(position).getMaterial38();
                String quantity38 = modelList.get(position).getQuantity38();

                String material39 = modelList.get(position).getMaterial39();
                String quantity39 = modelList.get(position).getQuantity39();

                String material40 = modelList.get(position).getMaterial40();
                String quantity40 = modelList.get(position).getQuantity40();

                String center = modelList.get(position).getCenter();
                String village = modelList.get(position).getVillage();

                String district = modelList.get(position).getDistrict();
                String taluka = modelList.get(position).getTaluka();
                String state = modelList.get(position).getState();
                String note = modelList.get(position).getNote();

                Intent intent = new Intent(workDone,ViewWorkDoneMaterialList.class);
                intent.putExtra("Id",id);
                intent.putExtra("Date",date);
                intent.putExtra("Line",line);
                intent.putExtra("Tender",tender);
                intent.putExtra("Team Name",teamName);
                intent.putExtra("Consumer Name",consumerName);
                intent.putExtra("Site",site);
                intent.putExtra("District",district);
                intent.putExtra("Taluka",taluka);
                intent.putExtra("State",state);
                intent.putExtra("State",state);
                intent.putExtra("Note",note);

                intent.putExtra("Material1",material1);
                intent.putExtra("Quantity1",quantity1);

                intent.putExtra("Material2",material2);
                intent.putExtra("Quantity2",quantity2);

                intent.putExtra("Material3",material3);
                intent.putExtra("Quantity3",quantity3);

                intent.putExtra("Material4",material4);
                intent.putExtra("Quantity4",quantity4);

                intent.putExtra("Material5",material5);
                intent.putExtra("Quantity5",quantity5);

                intent.putExtra("Material6",material6);
                intent.putExtra("Quantity6",quantity6);

                intent.putExtra("Material7",material7);
                intent.putExtra("Quantity7",quantity7);

                intent.putExtra("Material8",material8);
                intent.putExtra("Quantity8",quantity8);

                intent.putExtra("Material9",material9);
                intent.putExtra("Quantity9",quantity9);

                intent.putExtra("Material10",material10);
                intent.putExtra("Quantity10",quantity10);

                intent.putExtra("Material11",material11);
                intent.putExtra("Quantity11",quantity11);

                intent.putExtra("Material12",material12);
                intent.putExtra("Quantity12",quantity12);

                intent.putExtra("Material13",material13);
                intent.putExtra("Quantity13",quantity13);

                intent.putExtra("Material14",material14);
                intent.putExtra("Quantity14",quantity14);

                intent.putExtra("Material15",material15);
                intent.putExtra("Quantity15",quantity15);

                intent.putExtra("Material16",material16);
                intent.putExtra("Quantity16",quantity16);

                intent.putExtra("Material17",material17);
                intent.putExtra("Quantity17",quantity17);

                intent.putExtra("Material18",material18);
                intent.putExtra("Quantity18",quantity18);

                intent.putExtra("Material19",material19);
                intent.putExtra("Quantity19",quantity19);

                intent.putExtra("Material20",material20);
                intent.putExtra("Quantity20",quantity20);

                intent.putExtra("Material21",material21);
                intent.putExtra("Quantity21",quantity21);

                intent.putExtra("Material22",material22);
                intent.putExtra("Quantity22",quantity22);

                intent.putExtra("Material23",material23);
                intent.putExtra("Quantity23",quantity23);

                intent.putExtra("Material24",material24);
                intent.putExtra("Quantity24",quantity24);

                intent.putExtra("Material25",material25);
                intent.putExtra("Quantity25",quantity25);

                intent.putExtra("Material26",material26);
                intent.putExtra("Quantity26",quantity26);

                intent.putExtra("Material27",material27);
                intent.putExtra("Quantity27",quantity27);

                intent.putExtra("Material28",material28);
                intent.putExtra("Quantity28",quantity28);

                intent.putExtra("Material29",material29);
                intent.putExtra("Quantity29",quantity29);

                intent.putExtra("Material30",material30);
                intent.putExtra("Quantity30",quantity30);


                intent.putExtra("Material31",material31);
                intent.putExtra("Quantity31",quantity31);

                intent.putExtra("Material32",material32);
                intent.putExtra("Quantity32",quantity32);

                intent.putExtra("Material33",material33);
                intent.putExtra("Quantity33",quantity33);

                intent.putExtra("Material34",material34);
                intent.putExtra("Quantity34",quantity34);

                intent.putExtra("Material35",material35);
                intent.putExtra("Quantity35",quantity35);

                intent.putExtra("Material36",material36);
                intent.putExtra("Quantity36",quantity36);

                intent.putExtra("Material37",material37);
                intent.putExtra("Quantity37",quantity37);

                intent.putExtra("Material38",material38);
                intent.putExtra("Quantity38",quantity38);

                intent.putExtra("Material39",material39);
                intent.putExtra("Quantity39",quantity39);

                intent.putExtra("Material40",material40);
                intent.putExtra("Quantity40",quantity40);


                intent.putExtra("Center",center);
                intent.putExtra("Village",village);

                workDone.startActivity(intent);


            }

            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(workDone);
                String [] options = {"View","Update","Delete"};
                builder.setTitle("Select any one");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String id = modelList.get(position).getId();
                        String date = modelList.get(position).getDate();
                        String line = modelList.get(position).getLine();
                        String teamName = modelList.get(position).getTeamName();
                        String tender = modelList.get(position).getTender();
                        String consumerName = modelList.get(position).getConsumerName();
                        String site = modelList.get(position).getSite();

                        String material1 = modelList.get(position).getMaterial1();
                        String quantity1 = modelList.get(position).getQuantity1();

                        String material2 = modelList.get(position).getMaterial2();
                        String quantity2 = modelList.get(position).getQuantity2();

                        String material3 = modelList.get(position).getMaterial3();
                        String quantity3 = modelList.get(position).getQuantity3();

                        String material4 = modelList.get(position).getMaterial4();
                        String quantity4 = modelList.get(position).getQuantity4();

                        String material5 = modelList.get(position).getMaterial5();
                        String quantity5 = modelList.get(position).getQuantity5();

                        String material6 = modelList.get(position).getMaterial6();
                        String quantity6 = modelList.get(position).getQuantity6();

                        String material7 = modelList.get(position).getMaterial7();
                        String quantity7 = modelList.get(position).getQuantity7();

                        String material8 = modelList.get(position).getMaterial8();
                        String quantity8 = modelList.get(position).getQuantity8();

                        String material9 = modelList.get(position).getMaterial9();
                        String quantity9 = modelList.get(position).getQuantity9();

                        String material10 = modelList.get(position).getMaterial10();
                        String quantity10 = modelList.get(position).getQuantity10();

                        String material11 = modelList.get(position).getMaterial11();
                        String quantity11 = modelList.get(position).getQuantity11();

                        String material12 = modelList.get(position).getMaterial12();
                        String quantity12 = modelList.get(position).getQuantity12();

                        String material13 = modelList.get(position).getMaterial13();
                        String quantity13 = modelList.get(position).getQuantity13();

                        String material14 = modelList.get(position).getMaterial14();
                        String quantity14 = modelList.get(position).getQuantity14();

                        String material15 = modelList.get(position).getMaterial15();
                        String quantity15 = modelList.get(position).getQuantity15();

                        String material16 = modelList.get(position).getMaterial16();
                        String quantity16 = modelList.get(position).getQuantity16();

                        String material17 = modelList.get(position).getMaterial17();
                        String quantity17 = modelList.get(position).getQuantity17();

                        String material18 = modelList.get(position).getMaterial18();
                        String quantity18 = modelList.get(position).getQuantity18();

                        String material19 = modelList.get(position).getMaterial19();
                        String quantity19 = modelList.get(position).getQuantity19();

                        String material20 = modelList.get(position).getMaterial20();
                        String quantity20 = modelList.get(position).getQuantity20();

                        String material21 = modelList.get(position).getMaterial21();
                        String quantity21 = modelList.get(position).getQuantity21();

                        String material22 = modelList.get(position).getMaterial22();
                        String quantity22 = modelList.get(position).getQuantity22();

                        String material23 = modelList.get(position).getMaterial23();
                        String quantity23 = modelList.get(position).getQuantity23();

                        String material24 = modelList.get(position).getMaterial24();
                        String quantity24 = modelList.get(position).getQuantity24();

                        String material25 = modelList.get(position).getMaterial25();
                        String quantity25 = modelList.get(position).getQuantity25();

                        String material26 = modelList.get(position).getMaterial26();
                        String quantity26 = modelList.get(position).getQuantity26();

                        String material27 = modelList.get(position).getMaterial27();
                        String quantity27 = modelList.get(position).getQuantity27();

                        String material28 = modelList.get(position).getMaterial28();
                        String quantity28 = modelList.get(position).getQuantity28();

                        String material29 = modelList.get(position).getMaterial29();
                        String quantity29 = modelList.get(position).getQuantity29();

                        String material30 = modelList.get(position).getMaterial30();
                        String quantity30 = modelList.get(position).getQuantity30();

                        String material31 = modelList.get(position).getMaterial31();
                        String quantity31 = modelList.get(position).getQuantity31();

                        String material32 = modelList.get(position).getMaterial32();
                        String quantity32 = modelList.get(position).getQuantity32();

                        String material33 = modelList.get(position).getMaterial33();
                        String quantity33 = modelList.get(position).getQuantity33();

                        String material34 = modelList.get(position).getMaterial34();
                        String quantity34 = modelList.get(position).getQuantity34();

                        String material35 = modelList.get(position).getMaterial35();
                        String quantity35 = modelList.get(position).getQuantity35();

                        String material36 = modelList.get(position).getMaterial36();
                        String quantity36 = modelList.get(position).getQuantity36();

                        String material37 = modelList.get(position).getMaterial37();
                        String quantity37 = modelList.get(position).getQuantity37();

                        String material38 = modelList.get(position).getMaterial38();
                        String quantity38 = modelList.get(position).getQuantity38();

                        String material39 = modelList.get(position).getMaterial39();
                        String quantity39 = modelList.get(position).getQuantity39();

                        String material40 = modelList.get(position).getMaterial40();
                        String quantity40 = modelList.get(position).getQuantity40();

                        String center = modelList.get(position).getCenter();
                        String village = modelList.get(position).getVillage();

                        String district = modelList.get(position).getDistrict();
                        String taluka = modelList.get(position).getTaluka();
                        String state = modelList.get(position).getState();
                        String note = modelList.get(position).getNote();

                        if (which==0){
                            Intent intent = new Intent(workDone,ViewWorkDoneMaterialList.class);
                            intent.putExtra("Id",id);
                            intent.putExtra("Date",date);
                            intent.putExtra("Line",line);
                            intent.putExtra("Tender",tender);
                            intent.putExtra("Team Name",teamName);
                            intent.putExtra("Consumer Name",consumerName);
                            intent.putExtra("Site",site);
                            intent.putExtra("District",district);
                            intent.putExtra("Taluka",taluka);
                            intent.putExtra("State",state);
                            intent.putExtra("State",state);
                            intent.putExtra("Note",note);

                            intent.putExtra("Material1",material1);
                            intent.putExtra("Quantity1",quantity1);

                            intent.putExtra("Material2",material2);
                            intent.putExtra("Quantity2",quantity2);

                            intent.putExtra("Material3",material3);
                            intent.putExtra("Quantity3",quantity3);

                            intent.putExtra("Material4",material4);
                            intent.putExtra("Quantity4",quantity4);

                            intent.putExtra("Material5",material5);
                            intent.putExtra("Quantity5",quantity5);

                            intent.putExtra("Material6",material6);
                            intent.putExtra("Quantity6",quantity6);

                            intent.putExtra("Material7",material7);
                            intent.putExtra("Quantity7",quantity7);

                            intent.putExtra("Material8",material8);
                            intent.putExtra("Quantity8",quantity8);

                            intent.putExtra("Material9",material9);
                            intent.putExtra("Quantity9",quantity9);

                            intent.putExtra("Material10",material10);
                            intent.putExtra("Quantity10",quantity10);

                            intent.putExtra("Material11",material11);
                            intent.putExtra("Quantity11",quantity11);

                            intent.putExtra("Material12",material12);
                            intent.putExtra("Quantity12",quantity12);

                            intent.putExtra("Material13",material13);
                            intent.putExtra("Quantity13",quantity13);

                            intent.putExtra("Material14",material14);
                            intent.putExtra("Quantity14",quantity14);

                            intent.putExtra("Material15",material15);
                            intent.putExtra("Quantity15",quantity15);

                            intent.putExtra("Material16",material16);
                            intent.putExtra("Quantity16",quantity16);

                            intent.putExtra("Material17",material17);
                            intent.putExtra("Quantity17",quantity17);

                            intent.putExtra("Material18",material18);
                            intent.putExtra("Quantity18",quantity18);

                            intent.putExtra("Material19",material19);
                            intent.putExtra("Quantity19",quantity19);

                            intent.putExtra("Material20",material20);
                            intent.putExtra("Quantity20",quantity20);

                            intent.putExtra("Material21",material21);
                            intent.putExtra("Quantity21",quantity21);

                            intent.putExtra("Material22",material22);
                            intent.putExtra("Quantity22",quantity22);

                            intent.putExtra("Material23",material23);
                            intent.putExtra("Quantity23",quantity23);

                            intent.putExtra("Material24",material24);
                            intent.putExtra("Quantity24",quantity24);

                            intent.putExtra("Material25",material25);
                            intent.putExtra("Quantity25",quantity25);

                            intent.putExtra("Material26",material26);
                            intent.putExtra("Quantity26",quantity26);

                            intent.putExtra("Material27",material27);
                            intent.putExtra("Quantity27",quantity27);

                            intent.putExtra("Material28",material28);
                            intent.putExtra("Quantity28",quantity28);

                            intent.putExtra("Material29",material29);
                            intent.putExtra("Quantity29",quantity29);

                            intent.putExtra("Material30",material30);
                            intent.putExtra("Quantity30",quantity30);


                            intent.putExtra("Material31",material31);
                            intent.putExtra("Quantity31",quantity31);

                            intent.putExtra("Material32",material32);
                            intent.putExtra("Quantity32",quantity32);

                            intent.putExtra("Material33",material33);
                            intent.putExtra("Quantity33",quantity33);

                            intent.putExtra("Material34",material34);
                            intent.putExtra("Quantity34",quantity34);

                            intent.putExtra("Material35",material35);
                            intent.putExtra("Quantity35",quantity35);

                            intent.putExtra("Material36",material36);
                            intent.putExtra("Quantity36",quantity36);

                            intent.putExtra("Material37",material37);
                            intent.putExtra("Quantity37",quantity37);

                            intent.putExtra("Material38",material38);
                            intent.putExtra("Quantity38",quantity38);

                            intent.putExtra("Material39",material39);
                            intent.putExtra("Quantity39",quantity39);

                            intent.putExtra("Material40",material40);
                            intent.putExtra("Quantity40",quantity40);

                            intent.putExtra("Center",center);
                            intent.putExtra("Village",village);

                            workDone.startActivity(intent);

                        }

                        if (which==1){
                            Intent intent = new Intent(workDone,AddWorkDone.class);
                            intent.putExtra("Id",id);
                            intent.putExtra("Date",date);
                            intent.putExtra("Line",line);
                            intent.putExtra("Tender",tender);
                            intent.putExtra("Team Name",teamName);
                            intent.putExtra("Consumer Name",consumerName);
                            intent.putExtra("Site",site);
                            intent.putExtra("District",district);
                            intent.putExtra("Taluka",taluka);
                            intent.putExtra("State",state);
                            intent.putExtra("Note",note);

                            intent.putExtra("Material1",material1);
                            intent.putExtra("Quantity1",quantity1);

                            intent.putExtra("Material2",material2);
                            intent.putExtra("Quantity2",quantity2);

                            intent.putExtra("Material3",material3);
                            intent.putExtra("Quantity3",quantity3);

                            intent.putExtra("Material4",material4);
                            intent.putExtra("Quantity4",quantity4);

                            intent.putExtra("Material5",material5);
                            intent.putExtra("Quantity5",quantity5);

                            intent.putExtra("Material6",material6);
                            intent.putExtra("Quantity6",quantity6);

                            intent.putExtra("Material7",material7);
                            intent.putExtra("Quantity7",quantity7);

                            intent.putExtra("Material8",material8);
                            intent.putExtra("Quantity8",quantity8);

                            intent.putExtra("Material9",material9);
                            intent.putExtra("Quantity9",quantity9);

                            intent.putExtra("Material10",material10);
                            intent.putExtra("Quantity10",quantity10);

                            intent.putExtra("Material11",material11);
                            intent.putExtra("Quantity11",quantity11);

                            intent.putExtra("Material12",material12);
                            intent.putExtra("Quantity12",quantity12);

                            intent.putExtra("Material13",material13);
                            intent.putExtra("Quantity13",quantity13);

                            intent.putExtra("Material14",material14);
                            intent.putExtra("Quantity14",quantity14);

                            intent.putExtra("Material15",material15);
                            intent.putExtra("Quantity15",quantity15);

                            intent.putExtra("Material16",material16);
                            intent.putExtra("Quantity16",quantity16);

                            intent.putExtra("Material17",material17);
                            intent.putExtra("Quantity17",quantity17);

                            intent.putExtra("Material18",material18);
                            intent.putExtra("Quantity18",quantity18);

                            intent.putExtra("Material19",material19);
                            intent.putExtra("Quantity19",quantity19);

                            intent.putExtra("Material20",material20);
                            intent.putExtra("Quantity20",quantity20);

                            intent.putExtra("Material21",material21);
                            intent.putExtra("Quantity21",quantity21);

                            intent.putExtra("Material22",material22);
                            intent.putExtra("Quantity22",quantity22);

                            intent.putExtra("Material23",material23);
                            intent.putExtra("Quantity23",quantity23);

                            intent.putExtra("Material24",material24);
                            intent.putExtra("Quantity24",quantity24);

                            intent.putExtra("Material25",material25);
                            intent.putExtra("Quantity25",quantity25);

                            intent.putExtra("Material26",material26);
                            intent.putExtra("Quantity26",quantity26);

                            intent.putExtra("Material27",material27);
                            intent.putExtra("Quantity27",quantity27);

                            intent.putExtra("Material28",material28);
                            intent.putExtra("Quantity28",quantity28);

                            intent.putExtra("Material29",material29);
                            intent.putExtra("Quantity29",quantity29);

                            intent.putExtra("Material30",material30);
                            intent.putExtra("Quantity30",quantity30);

                            intent.putExtra("Material31",material31);
                            intent.putExtra("Quantity31",quantity31);

                            intent.putExtra("Material32",material32);
                            intent.putExtra("Quantity32",quantity32);

                            intent.putExtra("Material33",material33);
                            intent.putExtra("Quantity33",quantity33);

                            intent.putExtra("Material34",material34);
                            intent.putExtra("Quantity34",quantity34);

                            intent.putExtra("Material35",material35);
                            intent.putExtra("Quantity35",quantity35);

                            intent.putExtra("Material36",material36);
                            intent.putExtra("Quantity36",quantity36);

                            intent.putExtra("Material37",material37);
                            intent.putExtra("Quantity37",quantity37);

                            intent.putExtra("Material38",material38);
                            intent.putExtra("Quantity38",quantity38);

                            intent.putExtra("Material39",material39);
                            intent.putExtra("Quantity39",quantity39);

                            intent.putExtra("Material40",material40);
                            intent.putExtra("Quantity40",quantity40);


                            intent.putExtra("Center",center);
                            intent.putExtra("Village",village);

                            workDone.startActivity(intent);
                        }

                        if (which==2){
                            String [] options = {"No","Yes"};
                            builder.setTitle("Are you Sure ?");
                            builder.setIcon(R.drawable.ic_action_delete);
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which==0){
                                        String not = "Cancel";
                                        Toast.makeText(workDone, not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        workDone.deleteData(position);
                                    }
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
    public void onBindViewHolder(@NonNull AddWorkDoneHolder viewHolder, int i) {

        viewHolder.mDate.setText(modelList.get(i).getDate());
        viewHolder.mTeamName.setText(modelList.get(i).getTeamName());
        viewHolder.mLine.setText(modelList.get(i).getLine());
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
