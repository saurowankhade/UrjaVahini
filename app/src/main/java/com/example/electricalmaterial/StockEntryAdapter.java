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

public class StockEntryAdapter extends RecyclerView.Adapter<StockEntryViewHolder> {
    StockEntry addStock;
    List<StockEntryModel> modelList;

    public StockEntryAdapter(StockEntry addStock, List<StockEntryModel> modelList) {
        this.addStock = addStock;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public StockEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_entry_model,parent,false);
        StockEntryViewHolder addStockViewHolder = new StockEntryViewHolder(itemView);
        addStockViewHolder.setOnClickListener(new StockEntryViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = modelList.get(position).getId();
                String date = modelList.get(position).getDate();
                String supplierName = modelList.get(position).getSupplier();
                String supplierAddress = modelList.get(position).getSupplierAdd();
                String store = modelList.get(position).getStoreName();
                String driverName = modelList.get(position).getDriver();
                String vehicalName = modelList.get(position).getVehical();
                String materialReceiver = modelList.get(position).getMaterialReceiver();
                
                //Material
                String material1 = modelList.get(position).getuMaterial1();
                String unit1 = modelList.get(position).getuUnit1();
                String quantity1 = modelList.get(position).getuQuantity1();

                String material2 = modelList.get(position).getuMaterial2();
                String unit2 = modelList.get(position).getuQuantity2();
                String quantity2 = modelList.get(position).getuUnit2();

                String material3 = modelList.get(position).getuMaterial3();
                String unit3 = modelList.get(position).getuUnit3();
                String quantity3 = modelList.get(position).getuQuantity3();

                String material4 = modelList.get(position).getuMaterial4();
                String unit4 = modelList.get(position).getuUnit4();
                String quantity4 = modelList.get(position).getuQuantity4();

                String material5 = modelList.get(position).getuMaterial5();
                String unit5 = modelList.get(position).getuUnit5();
                String quantity5 = modelList.get(position).getuQuantity5();

                String material6 = modelList.get(position).getuMaterial6();
                String unit6 = modelList.get(position).getuUnit6();
                String quantity6 = modelList.get(position).getuQuantity6();

                String material7 = modelList.get(position).getuMaterial7();
                String unit7 = modelList.get(position).getuUnit7();
                String quantity7 = modelList.get(position).getuQuantity7();

                String material8 = modelList.get(position).getuMaterial8();
                String unit8 = modelList.get(position).getuUnit8();
                String quantity8 = modelList.get(position).getuQuantity8();

                String material9 = modelList.get(position).getuMaterial9();
                String unit9 = modelList.get(position).getuUnit9();
                String quantity9 = modelList.get(position).getuQuantity9();

                String material10 = modelList.get(position).getuMaterial10();
                String unit10 = modelList.get(position).getuUnit10();
                String quantity10 = modelList.get(position).getuQuantity10();

                String material11 = modelList.get(position).getuMaterial11();
                String unit11 = modelList.get(position).getuUnit11();
                String quantity11 = modelList.get(position).getuQuantity11();

                String material12 = modelList.get(position).getuMaterial12();
                String unit12 = modelList.get(position).getuUnit12();
                String quantity12 = modelList.get(position).getuQuantity12();

                String material13 = modelList.get(position).getuMaterial13();
                String unit13 = modelList.get(position).getuUnit13();
                String quantity13 = modelList.get(position).getuQuantity13();

                String material14 = modelList.get(position).getuMaterial14();
                String unit14 = modelList.get(position).getuUnit14();
                String quantity14 = modelList.get(position).getuQuantity14();

                String material15 = modelList.get(position).getuMaterial15();
                String unit15 = modelList.get(position).getuUnit15();
                String quantity15 = modelList.get(position).getuQuantity15();

                String material16 = modelList.get(position).getuMaterial16();
                String unit16 = modelList.get(position).getuUnit16();
                String quantity16 = modelList.get(position).getuQuantity16();

                String material17 = modelList.get(position).getuMaterial17();
                String unit17 = modelList.get(position).getuUnit17();
                String quantity17 = modelList.get(position).getuQuantity17();

                String material18 = modelList.get(position).getuMaterial18();
                String unit18 = modelList.get(position).getuUnit18();
                String quantity18 = modelList.get(position).getuQuantity18();

                String material19 = modelList.get(position).getuMaterial19();
                String unit19 = modelList.get(position).getuUnit19();
                String quantity19 = modelList.get(position).getuQuantity19();

                String material20 = modelList.get(position).getuMaterial20();
                String unit20 = modelList.get(position).getuUnit20();
                String quantity20 = modelList.get(position).getuQuantity20();

                String material21 = modelList.get(position).getuMaterial21();
                String unit21 = modelList.get(position).getuUnit21();
                String quantity21 = modelList.get(position).getuQuantity21();

                String material22 = modelList.get(position).getuMaterial22();
                String unit22 = modelList.get(position).getuUnit22();
                String quantity22 = modelList.get(position).getuQuantity22();

                String material23 = modelList.get(position).getuMaterial23();
                String unit23 = modelList.get(position).getuUnit23();
                String quantity23 = modelList.get(position).getuQuantity23();

                String material24 = modelList.get(position).getuMaterial24();
                String unit24 = modelList.get(position).getuUnit24();
                String quantity24 = modelList.get(position).getuQuantity24();

                String material25 = modelList.get(position).getuMaterial25();
                String unit25 = modelList.get(position).getuUnit25();
                String quantity25 = modelList.get(position).getuQuantity25();

                String material26 = modelList.get(position).getuMaterial26();
                String unit26 = modelList.get(position).getuUnit26();
                String quantity26 = modelList.get(position).getuQuantity26();

                String material27 = modelList.get(position).getuMaterial27();
                String unit27 = modelList.get(position).getuUnit27();
                String quantity27 = modelList.get(position).getuQuantity27();

                String material28 = modelList.get(position).getuMaterial28();
                String unit28 = modelList.get(position).getuUnit28();
                String quantity28 = modelList.get(position).getuQuantity28();

                String material29 = modelList.get(position).getuMaterial29();
                String unit29 = modelList.get(position).getuUnit29();
                String quantity29 = modelList.get(position).getuQuantity29();

                String material30 = modelList.get(position).getuMaterial30();
                String unit30 = modelList.get(position).getuUnit30();
                String quantity30 = modelList.get(position).getuQuantity30();


                Intent intent = new Intent(addStock,StockEntryViewData.class);


                intent.putExtra("id",id);
                intent.putExtra("Date",date);
                intent.putExtra("Supplier Name",supplierName);
                intent.putExtra("Store",store);
                intent.putExtra("Supplier Address",supplierAddress);
                intent.putExtra("Driver Name",driverName);
                intent.putExtra("Vehical Name",vehicalName);


                intent.putExtra("Material Receiver Name",materialReceiver);

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

                addStock.startActivity(intent);


            }
            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(addStock);
                String [] options = {"View","Update","Delete"};
                builder.setTitle("Select any one");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String id = modelList.get(position).getId();
                        String date = modelList.get(position).getDate();
                        String supplierName = modelList.get(position).getSupplier();
                        String supplierAddress = modelList.get(position).getSupplierAdd();
                        String store = modelList.get(position).getStoreName();
                        String driverName = modelList.get(position).getDriver();
                        String vehicalName = modelList.get(position).getVehical();
                        String materialReceiver = modelList.get(position).getMaterialReceiver();

                        //Material
                        String material1 = modelList.get(position).getuMaterial1();
                        String unit1 = modelList.get(position).getuUnit1();
                        String quantity1 = modelList.get(position).getuQuantity1();

                        String material2 = modelList.get(position).getuMaterial2();
                        String unit2 = modelList.get(position).getuQuantity2();
                        String quantity2 = modelList.get(position).getuUnit2();

                        String material3 = modelList.get(position).getuMaterial3();
                        String unit3 = modelList.get(position).getuUnit3();
                        String quantity3 = modelList.get(position).getuQuantity3();

                        String material4 = modelList.get(position).getuMaterial4();
                        String unit4 = modelList.get(position).getuUnit4();
                        String quantity4 = modelList.get(position).getuQuantity4();

                        String material5 = modelList.get(position).getuMaterial5();
                        String unit5 = modelList.get(position).getuUnit5();
                        String quantity5 = modelList.get(position).getuQuantity5();

                        String material6 = modelList.get(position).getuMaterial6();
                        String unit6 = modelList.get(position).getuUnit6();
                        String quantity6 = modelList.get(position).getuQuantity6();

                        String material7 = modelList.get(position).getuMaterial7();
                        String unit7 = modelList.get(position).getuUnit7();
                        String quantity7 = modelList.get(position).getuQuantity7();

                        String material8 = modelList.get(position).getuMaterial8();
                        String unit8 = modelList.get(position).getuUnit8();
                        String quantity8 = modelList.get(position).getuQuantity8();

                        String material9 = modelList.get(position).getuMaterial9();
                        String unit9 = modelList.get(position).getuUnit9();
                        String quantity9 = modelList.get(position).getuQuantity9();

                        String material10 = modelList.get(position).getuMaterial10();
                        String unit10 = modelList.get(position).getuUnit10();
                        String quantity10 = modelList.get(position).getuQuantity10();

                        String material11 = modelList.get(position).getuMaterial11();
                        String unit11 = modelList.get(position).getuUnit11();
                        String quantity11 = modelList.get(position).getuQuantity11();

                        String material12 = modelList.get(position).getuMaterial12();
                        String unit12 = modelList.get(position).getuUnit12();
                        String quantity12 = modelList.get(position).getuQuantity12();

                        String material13 = modelList.get(position).getuMaterial13();
                        String unit13 = modelList.get(position).getuUnit13();
                        String quantity13 = modelList.get(position).getuQuantity13();

                        String material14 = modelList.get(position).getuMaterial14();
                        String unit14 = modelList.get(position).getuUnit14();
                        String quantity14 = modelList.get(position).getuQuantity14();

                        String material15 = modelList.get(position).getuMaterial15();
                        String unit15 = modelList.get(position).getuUnit15();
                        String quantity15 = modelList.get(position).getuQuantity15();

                        String material16 = modelList.get(position).getuMaterial16();
                        String unit16 = modelList.get(position).getuUnit16();
                        String quantity16 = modelList.get(position).getuQuantity16();

                        String material17 = modelList.get(position).getuMaterial17();
                        String unit17 = modelList.get(position).getuUnit17();
                        String quantity17 = modelList.get(position).getuQuantity17();

                        String material18 = modelList.get(position).getuMaterial18();
                        String unit18 = modelList.get(position).getuUnit18();
                        String quantity18 = modelList.get(position).getuQuantity18();

                        String material19 = modelList.get(position).getuMaterial19();
                        String unit19 = modelList.get(position).getuUnit19();
                        String quantity19 = modelList.get(position).getuQuantity19();

                        String material20 = modelList.get(position).getuMaterial20();
                        String unit20 = modelList.get(position).getuUnit20();
                        String quantity20 = modelList.get(position).getuQuantity20();

                        String material21 = modelList.get(position).getuMaterial21();
                        String unit21 = modelList.get(position).getuUnit21();
                        String quantity21 = modelList.get(position).getuQuantity21();

                        String material22 = modelList.get(position).getuMaterial22();
                        String unit22 = modelList.get(position).getuUnit22();
                        String quantity22 = modelList.get(position).getuQuantity22();

                        String material23 = modelList.get(position).getuMaterial23();
                        String unit23 = modelList.get(position).getuUnit23();
                        String quantity23 = modelList.get(position).getuQuantity23();

                        String material24 = modelList.get(position).getuMaterial24();
                        String unit24 = modelList.get(position).getuUnit24();
                        String quantity24 = modelList.get(position).getuQuantity24();

                        String material25 = modelList.get(position).getuMaterial25();
                        String unit25 = modelList.get(position).getuUnit25();
                        String quantity25 = modelList.get(position).getuQuantity25();

                        String material26 = modelList.get(position).getuMaterial26();
                        String unit26 = modelList.get(position).getuUnit26();
                        String quantity26 = modelList.get(position).getuQuantity26();

                        String material27 = modelList.get(position).getuMaterial27();
                        String unit27 = modelList.get(position).getuUnit27();
                        String quantity27 = modelList.get(position).getuQuantity27();

                        String material28 = modelList.get(position).getuMaterial28();
                        String unit28 = modelList.get(position).getuUnit28();
                        String quantity28 = modelList.get(position).getuQuantity28();

                        String material29 = modelList.get(position).getuMaterial29();
                        String unit29 = modelList.get(position).getuUnit29();
                        String quantity29 = modelList.get(position).getuQuantity29();

                        String material30 = modelList.get(position).getuMaterial30();
                        String unit30 = modelList.get(position).getuUnit30();
                        String quantity30 = modelList.get(position).getuQuantity30();

                        if (which==0){
                            Intent intent = new Intent(addStock,StockEntryViewData.class);


                            intent.putExtra("id",id);
                            intent.putExtra("Date",date);
                            intent.putExtra("Supplier Name",supplierName);
                            intent.putExtra("Store",store);
                            intent.putExtra("Supplier Address",supplierAddress);
                            intent.putExtra("Driver Name",driverName);
                            intent.putExtra("Vehical Name",vehicalName);


                            intent.putExtra("Material Receiver Name",materialReceiver);

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

                            addStock.startActivity(intent);
                        }

                        if (which==1){
                            Intent intent = new Intent(addStock,Stock.class);

                            intent.putExtra("id",id);
                            intent.putExtra("Date",date);
                            intent.putExtra("Supplier Name",supplierName);
                            intent.putExtra("Store",store);
                            intent.putExtra("Supplier Address",supplierAddress);
                            intent.putExtra("Driver Name",driverName);
                            intent.putExtra("Vehical Name",vehicalName);


                            intent.putExtra("Material Receiver Name",materialReceiver);

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

                            addStock.startActivity(intent);
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
                                        Toast.makeText(addStock, not.toUpperCase(), Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        addStock.deleteData(position);
                                    }
                                }
                            }).create().show();
                        }
                    }
                }).create().show();
            }
        });
        return addStockViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StockEntryViewHolder holder, int i) {
        holder.mDate.setText(modelList.get(i).getDate());
        holder.mSupplier.setText(modelList.get(i).getSupplier());
        holder.mSupplierAddress.setText(modelList.get(i).getSupplierAdd());
        holder.mDriverName.setText(modelList.get(i).getDriver());
        holder.mStore.setText(modelList.get(i).getStoreName());
        holder.mVehical.setText(modelList.get(i).getVehical());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
