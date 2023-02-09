package com.example.electricalmaterial;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ViewWorkDoneMaterialList extends AppCompatActivity {

    TextView date,teamName,line,tender,consumerName,siteName,center,village,taluka,district,state,note;


    TextView material1,material2,material3,material4,material5,material6,material7,material8,material9,material10;
    TextView material11,material12,material13,material14,material15,material16,material17,material18,material19,material20;
    TextView material21,material22,material23,material24,material25,material26,material27,material28,material29,material30;
    TextView material31,material32,material33,material34,material35,material36,material37,material38,material39,material40;

    TextView quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10;
    TextView quantity11,quantity12,quantity13,quantity14,quantity15,quantity16,quantity17,quantity18,quantity19,quantity20;
    TextView quantity21,quantity22,quantity23,quantity24,quantity25,quantity26,quantity27,quantity28,quantity29,quantity30;
    TextView quantity31,quantity32,quantity33,quantity34,quantity35,quantity36,quantity37,quantity38,quantity39,quantity40;




    String id,dateS,teamNameS,lineS,tenderS,consumerNameS,siteNameS,centerS,villageS,talukaS,districtS,stateS,noteS;

    String material1S,quantity1S;
    String material2S,quantity2S;
    String material3S,quantity3S;
    String material4S,quantity4S;
    String material5S,quantity5S;
    String material6S,quantity6S;
    String material7S,quantity7S;
    String material8S,quantity8S;
    String material9S,quantity9S;
    String material10S,quantity10S;
    String material11S,quantity11S;
    String material12S,quantity12S;
    String material13S,quantity13S;
    String material14S,quantity14S;
    String material15S,quantity15S;
    String material16S,quantity16S;
    String material17S,quantity17S;
    String material18S,quantity18S;
    String material19S,quantity19S;
    String material20S,quantity20S;
    String material21S,quantity21S;
    String material22S,quantity22S;
    String material23S,quantity23S;
    String material24S,quantity24S;
    String material25S,quantity25S;
    String material26S,quantity26S;
    String material27S,quantity27S;
    String material28S,quantity28S;
    String material29S,quantity29S;
    String material30S,quantity30S;
    String material31S,quantity31S;
    String material32S,quantity32S;
    String material33S,quantity33S;
    String material34S,quantity34S;
    String material35S,quantity35S;
    String material36S,quantity36S;
    String material37S,quantity37S;
    String material38S,quantity38S;
    String material39S,quantity39S;
    String material40S,quantity40S;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_work_done_material_list);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);



        date = findViewById(R.id.dateTV);
        teamName = findViewById(R.id.teamNameTV);
        line = findViewById(R.id.lineTV);
        tender = findViewById(R.id.tenderTV);
        consumerName = findViewById(R.id.consumerNameTV);
        siteName = findViewById(R.id.siteTV);
        center = findViewById(R.id.centerTV);
        village = findViewById(R.id.villageTV);

        taluka = findViewById(R.id.talukaTV);
        state = findViewById(R.id.stateTV);
        district = findViewById(R.id.districtTV);
        note = findViewById(R.id.noteTv);

        //Material
        material1 = findViewById(R.id.material1);
        material2 = findViewById(R.id.material2);
        material3 = findViewById(R.id.material3);
        material4 = findViewById(R.id.material4);
        material5 = findViewById(R.id.material5);
        material6 = findViewById(R.id.material6);
        material7 = findViewById(R.id.material7);
        material8 = findViewById(R.id.material8);
        material9 = findViewById(R.id.material9);
        material10 = findViewById(R.id.material10);
        material11 = findViewById(R.id.material11);
        material12 = findViewById(R.id.material12);
        material13 = findViewById(R.id.material13);
        material14 = findViewById(R.id.material14);
        material15 = findViewById(R.id.material15);
        material16 = findViewById(R.id.material16);
        material17 = findViewById(R.id.material17);
        material18 = findViewById(R.id.material18);
        material19 = findViewById(R.id.material19);
        material20 = findViewById(R.id.material20);
        material21 = findViewById(R.id.material21);
        material22 = findViewById(R.id.material22);
        material23 = findViewById(R.id.material23);
        material24 = findViewById(R.id.material24);
        material25 = findViewById(R.id.material25);
        material26 = findViewById(R.id.material26);
        material27 = findViewById(R.id.material27);
        material28 = findViewById(R.id.material28);
        material29 = findViewById(R.id.material29);
        material30 = findViewById(R.id.material30);


        material31 = findViewById(R.id.material31);
        material32 = findViewById(R.id.material32);
        material33 = findViewById(R.id.material33);
        material34 = findViewById(R.id.material34);
        material35 = findViewById(R.id.material35);
        material36 = findViewById(R.id.material36);
        material37 = findViewById(R.id.material37);
        material38 = findViewById(R.id.material38);
        material39 = findViewById(R.id.material39);
        material40 = findViewById(R.id.material40);


        //Quantity
        quantity1 = findViewById(R.id.quantity1);
        quantity2 = findViewById(R.id.quantity2);
        quantity3 = findViewById(R.id.quantity3);
        quantity4 = findViewById(R.id.quantity4);
        quantity5 = findViewById(R.id.quantity5);
        quantity6 = findViewById(R.id.quantity6);
        quantity7 = findViewById(R.id.quantity7);
        quantity8 = findViewById(R.id.quantity8);
        quantity9 = findViewById(R.id.quantity9);
        quantity10 = findViewById(R.id.quantity10);
        quantity11 = findViewById(R.id.quantity11);
        quantity12 = findViewById(R.id.quantity12);
        quantity13 = findViewById(R.id.quantity13);
        quantity14 = findViewById(R.id.quantity14);
        quantity15 = findViewById(R.id.quantity15);
        quantity16 = findViewById(R.id.quantity16);
        quantity17 = findViewById(R.id.quantity17);
        quantity18 = findViewById(R.id.quantity18);
        quantity19 = findViewById(R.id.quantity19);
        quantity20 = findViewById(R.id.quantity20);
        quantity21 = findViewById(R.id.quantity21);
        quantity22 = findViewById(R.id.quantity22);
        quantity23 = findViewById(R.id.quantity23);
        quantity24 = findViewById(R.id.quantity24);
        quantity25 = findViewById(R.id.quantity25);
        quantity26 = findViewById(R.id.quantity26);
        quantity27 = findViewById(R.id.quantity27);
        quantity28 = findViewById(R.id.quantity28);
        quantity29 = findViewById(R.id.quantity29);
        quantity30 = findViewById(R.id.quantity30);

        quantity31 = findViewById(R.id.quantity31);
        quantity32 = findViewById(R.id.quantity32);
        quantity33 = findViewById(R.id.quantity33);
        quantity34 = findViewById(R.id.quantity34);
        quantity35 = findViewById(R.id.quantity35);
        quantity36 = findViewById(R.id.quantity36);
        quantity37 = findViewById(R.id.quantity37);
        quantity38 = findViewById(R.id.quantity38);
        quantity39 = findViewById(R.id.quantity39);
        quantity40 = findViewById(R.id.quantity40);








        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            id = bundle.getString("Id");
            dateS = bundle.getString("Date");
            lineS = bundle.getString("Line");
            tenderS = bundle.getString("Tender");
            consumerNameS = bundle.getString("Consumer Name");
            siteNameS = bundle.getString("Site");
            teamNameS = bundle.getString("Team Name");

            stateS = bundle.getString("State");
            districtS = bundle.getString("District");
            talukaS = bundle.getString("Taluka");
            noteS = bundle.getString("Note");

            //material
            material1S = bundle.getString("Material1");
            quantity1S = bundle.getString("Quantity1");

            material2S = bundle.getString("Material2");
            quantity2S = bundle.getString("Quantity2");

            material3S = bundle.getString("Material3");
            quantity3S = bundle.getString("Quantity3");

            material4S = bundle.getString("Material4");
            quantity4S = bundle.getString("Quantity4");

            material5S = bundle.getString("Material5");
            quantity5S = bundle.getString("Quantity5");

            material6S = bundle.getString("Material6");
            quantity6S = bundle.getString("Quantity6");

            material7S = bundle.getString("Material7");
            quantity7S = bundle.getString("Quantity7");

            material8S = bundle.getString("Material8");
            quantity8S = bundle.getString("Quantity8");

            material9S = bundle.getString("Material9");
            quantity9S = bundle.getString("Quantity9");

            material10S = bundle.getString("Material10");
            quantity10S = bundle.getString("Quantity10");

            material11S = bundle.getString("Material11");
            quantity11S = bundle.getString("Quantity11");

            material12S = bundle.getString("Material12");
            quantity12S = bundle.getString("Quantity12");

            material13S = bundle.getString("Material13");
            quantity13S = bundle.getString("Quantity13");

            material14S = bundle.getString("Material14");
            quantity14S = bundle.getString("Quantity14");

            material15S = bundle.getString("Material15");
            quantity15S = bundle.getString("Quantity15");

            material16S = bundle.getString("Material16");
            quantity16S = bundle.getString("Quantity16");

            material17S = bundle.getString("Material17");
            quantity17S = bundle.getString("Quantity17");

            material18S = bundle.getString("Material18");
            quantity18S = bundle.getString("Quantity18");

            material19S = bundle.getString("Material19");
            quantity19S = bundle.getString("Quantity19");

            material20S = bundle.getString("Material20");
            quantity20S = bundle.getString("Quantity20");

            material21S = bundle.getString("Material21");
            quantity21S = bundle.getString("Quantity21");

            material22S = bundle.getString("Material22");
            quantity22S = bundle.getString("Quantity22");

            material23S = bundle.getString("Material23");
            quantity23S = bundle.getString("Quantity23");

            material24S = bundle.getString("Material24");
            quantity24S = bundle.getString("Quantity24");

            material25S = bundle.getString("Material25");
            quantity25S = bundle.getString("Quantity25");

            material26S = bundle.getString("Material26");
            quantity26S = bundle.getString("Quantity26");

            material27S = bundle.getString("Material27");
            quantity27S = bundle.getString("Quantity27");

            material28S = bundle.getString("Material28");
            quantity28S = bundle.getString("Quantity28");

            material29S = bundle.getString("Material29");
            quantity29S = bundle.getString("Quantity29");

            material30S = bundle.getString("Material30");
            quantity30S = bundle.getString("Quantity30");





            material31S = bundle.getString("Material31");
            quantity31S = bundle.getString("Quantity31");

            material32S = bundle.getString("Material32");
            quantity32S = bundle.getString("Quantity32");

            material33S = bundle.getString("Material33");
            quantity33S = bundle.getString("Quantity33");

            material34S = bundle.getString("Material34");
            quantity34S = bundle.getString("Quantity34");

            material35S = bundle.getString("Material35");
            quantity35S = bundle.getString("Quantity35");

            material36S = bundle.getString("Material36");
            quantity36S = bundle.getString("Quantity36");

            material37S = bundle.getString("Material37");
            quantity37S = bundle.getString("Quantity37");

            material38S = bundle.getString("Material38");
            quantity38S = bundle.getString("Quantity38");

            material39S = bundle.getString("Material39");
            quantity39S = bundle.getString("Quantity39");

            material40S = bundle.getString("Material40");
            quantity40S = bundle.getString("Quantity40");








            centerS = bundle.getString("Center");
            villageS = bundle.getString("Village");


            date.setText(dateS);

            line.setText(lineS);
            tender.setText(tenderS);
            consumerName.setText(consumerNameS);
            siteName.setText(siteNameS);

            state.setText(stateS);
            district.setText(districtS);
            taluka.setText(talukaS);
            teamName.setText(teamNameS);

            note.setText(noteS);

            center.setText(centerS);
            village.setText(villageS);

            material1.setText(material1S);
            material2.setText(material2S);
            material3.setText(material3S);
            material4.setText(material4S);
            material5.setText(material5S);
            material6.setText(material6S);
            material7.setText(material7S);
            material8.setText(material8S);
            material9.setText(material9S);
            material10.setText(material10S);
            material11.setText(material11S);
            material12.setText(material12S);
            material13.setText(material13S);
            material14.setText(material14S);
            material15.setText(material15S);
            material16.setText(material16S);
            material17.setText(material17S);
            material18.setText(material18S);
            material19.setText(material19S);
            material20.setText(material20S);
            material21.setText(material21S);
            material22.setText(material22S);
            material23.setText(material23S);
            material24.setText(material24S);
            material25.setText(material25S);
            material26.setText(material26S);
            material27.setText(material27S);
            material28.setText(material28S);
            material29.setText(material29S);
            material30.setText(material30S);

            material31.setText(material31S);
            material32.setText(material32S);
            material33.setText(material33S);
            material34.setText(material34S);
            material35.setText(material35S);
            material36.setText(material36S);
            material37.setText(material37S);
            material38.setText(material38S);
            material39.setText(material39S);
            material40.setText(material40S);

            quantity1.setText(quantity1S);
            quantity2.setText(quantity2S);
            quantity3.setText(quantity3S);
            quantity4.setText(quantity4S);
            quantity5.setText(quantity5S);
            quantity6.setText(quantity6S);
            quantity7.setText(quantity7S);
            quantity8.setText(quantity8S);
            quantity9.setText(quantity9S);
            quantity10.setText(quantity10S);
            quantity11.setText(quantity11S);
            quantity12.setText(quantity12S);
            quantity13.setText(quantity13S);
            quantity14.setText(quantity14S);
            quantity15.setText(quantity15S);
            quantity16.setText(quantity16S);
            quantity17.setText(quantity17S);
            quantity18.setText(quantity18S);
            quantity19.setText(quantity19S);
            quantity20.setText(quantity20S);
            quantity21.setText(quantity21S);
            quantity22.setText(quantity22S);
            quantity23.setText(quantity23S);
            quantity24.setText(quantity24S);
            quantity25.setText(quantity25S);
            quantity26.setText(quantity26S);
            quantity27.setText(quantity27S);
            quantity28.setText(quantity28S);
            quantity29.setText(quantity29S);
            quantity30.setText(quantity30S);

            quantity31.setText(quantity31S);
            quantity32.setText(quantity32S);
            quantity33.setText(quantity33S);
            quantity34.setText(quantity34S);
            quantity35.setText(quantity35S);
            quantity36.setText(quantity36S);
            quantity37.setText(quantity37S);
            quantity38.setText(quantity38S);
            quantity39.setText(quantity39S);
            quantity40.setText(quantity40S);

        }


    }
}