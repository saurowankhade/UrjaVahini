package com.example.electricalmaterial;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SupervisorAddReturnMaterial extends AppCompatActivity {
    String r = "Required!";

    String work;

    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String companyEmail;
    String userId;

    //LinerLayout
    LinearLayout homeLL;
    LinearLayout middleLL;
    LinearLayout lastOneLL;
    LinearLayout lastLL;
    LinearLayout animation;

    //date
    TextView mDateFormate;


    //team name
    AutoCompleteTextView teamName;


    //Line
    AutoCompleteTextView line;

    //Tender
    AutoCompleteTextView tender;

    //Driver
    AutoCompleteTextView driver;

    //Driver
    AutoCompleteTextView vehical;

    //Material
    AutoCompleteTextView material1, material2, material3, material4, material5, material6, material7, material8, material9, material10;
    AutoCompleteTextView material11, material12, material13, material14, material15, material16, material17, material18, material19, material20;
    AutoCompleteTextView material21, material22, material23, material24, material25, material26, material27, material28, material29, material30;

    //Receiver
    AutoCompleteTextView receiver;
    ArrayAdapter<String> adapterReceiver;
    ArrayList<String> spinnerDataListReceiver;
    DatabaseReference databaseReferenceReceiver;
    ValueEventListener listenerReceiver;


    //Center
    AutoCompleteTextView center;

    //Village
    AutoCompleteTextView village;


    //Button Home
    MaterialButton homeNextBtn;
    MaterialButton homeBackBtn;
    MaterialButton middleNextBtn;
    MaterialButton nextLastTwo;
    MaterialButton doneBtn;
    MaterialButton lastBackBtn;
    MaterialButton doneAll;

    //TextInputEditText
    TextInputEditText consumer, site;
    TextInputEditText passReceiver;

    String cmp;


    //Unit
    AutoCompleteTextView unit1, unit2, unit3, unit4, unit5, unit6, unit7, unit8, unit9, unit10;
    AutoCompleteTextView unit11, unit12, unit13, unit14, unit15, unit16, unit17, unit18, unit19, unit20;
    AutoCompleteTextView unit21, unit22, unit23, unit24, unit25, unit26, unit27, unit28, unit29, unit30;

    TextInputEditText quantity1, quantity2, quantity3, quantity4, quantity5, quantity6, quantity7, quantity8, quantity9, quantity10;
    TextInputEditText quantity11, quantity12, quantity13, quantity14, quantity15, quantity16, quantity17, quantity18, quantity19, quantity20;
    TextInputEditText quantity21, quantity22, quantity23, quantity24, quantity25, quantity26, quantity27, quantity28, quantity29, quantity30;


    LinearLayout addDataLL;
    ProgressBar progressBar;


    LinearLayout addMaterialLL1, addMaterialLL2, addMaterialLL3, addMaterialLL4, addMaterialLL5, addMaterialLL6;
    ImageView addIV1, addIV2, addIV3, addIV4, addIV5;


    String id,dateS,teamNameS,lineS,tenderS,driverNameS,vehicalNameS,consumerNameS,siteNameS,materialReceiverNameS;

    String material1S,unit1S,quantity1S;
    String material2S,unit2S,quantity2S;
    String material3S,unit3S,quantity3S;
    String material4S,unit4S,quantity4S;
    String material5S,unit5S,quantity5S;
    String material6S,unit6S,quantity6S;
    String material7S,unit7S,quantity7S;
    String material8S,unit8S,quantity8S;
    String material9S,unit9S,quantity9S;
    String material10S,unit10S,quantity10S;
    String material11S,unit11S,quantity11S;
    String material12S,unit12S,quantity12S;
    String material13S,unit13S,quantity13S;
    String material14S,unit14S,quantity14S;
    String material15S,unit15S,quantity15S;
    String material16S,unit16S,quantity16S;
    String material17S,unit17S,quantity17S;
    String material18S,unit18S,quantity18S;
    String material19S,unit19S,quantity19S;
    String material20S,unit20S,quantity20S;
    String material21S,unit21S,quantity21S;
    String material22S,unit22S,quantity22S;
    String material23S,unit23S,quantity23S;
    String material24S,unit24S,quantity24S;
    String material25S,unit25S,quantity25S;
    String material26S,unit26S,quantity26S;
    String material27S,unit27S,quantity27S;
    String material28S,unit28S,quantity28S;
    String material29S,unit29S,quantity29S;
    String material30S,unit30S,quantity30S;





    String centerS,villageS;

    ProgressDialog pd;
    Timer time;
    MediaPlayer mediaPlayer;

    //Stock Material
    List<AddStockModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SupervisorReturnStockAdapter adapterStock;


    //Kg to no
    List<KgToNoModel> modelListKN = new ArrayList<>();
    RecyclerView recyclerViewKN;
    RecyclerView.LayoutManager layoutManagerKN;
    SupervisorReturnKgToNoAdapter adapterStockKN;




    String uMaterial1,uUnit1,uQuantity1;
    String uMaterial2,uUnit2,uQuantity2;
    String uMaterial3,uUnit3,uQuantity3;
    String uMaterial4,uUnit4,uQuantity4;
    String uMaterial5,uUnit5,uQuantity5;
    String uMaterial6,uUnit6,uQuantity6;
    String uMaterial7,uUnit7,uQuantity7;
    String uMaterial8,uUnit8,uQuantity8;
    String uMaterial9,uUnit9,uQuantity9;
    String uMaterial10,uUnit10,uQuantity10;
    String uMaterial11,uUnit11,uQuantity11;
    String uMaterial12,uUnit12,uQuantity12;
    String uMaterial13,uUnit13,uQuantity13;
    String uMaterial14,uUnit14,uQuantity14;
    String uMaterial15,uUnit15,uQuantity15;
    String uMaterial16,uUnit16,uQuantity16;
    String uMaterial17,uUnit17,uQuantity17;
    String uMaterial18,uUnit18,uQuantity18;
    String uMaterial19,uUnit19,uQuantity19;
    String uMaterial20,uUnit20,uQuantity20;
    String uMaterial21,uUnit21,uQuantity21;
    String uMaterial22,uUnit22,uQuantity22;
    String uMaterial23,uUnit23,uQuantity23;
    String uMaterial24,uUnit24,uQuantity24;
    String uMaterial25,uUnit25,uQuantity25;
    String uMaterial26,uUnit26,uQuantity26;
    String uMaterial27,uUnit27,uQuantity27;
    String uMaterial28,uUnit28,uQuantity28;
    String uMaterial29,uUnit29,uQuantity29;
    String uMaterial30,uUnit30,uQuantity30;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_add_return_material);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pd = ProgressDialog.show(this,"Loading...","Please Wait",false,false);

        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                pd.dismiss();
            }
        },3000);

        mediaPlayer = MediaPlayer.create(this,R.raw.sound);

        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        //LinerLayout
        homeLL = findViewById(R.id.homeLL);
        middleLL = findViewById(R.id.middleLL);
        lastOneLL = findViewById(R.id.lastOneLL);
        lastLL = findViewById(R.id.lastLL);
        animation = findViewById(R.id.animation);

        addMaterialLL1 = findViewById(R.id.addMaterialLL1);
        addMaterialLL2 = findViewById(R.id.addMaterialLL2);
        addMaterialLL3 = findViewById(R.id.addMaterialLL3);
        addMaterialLL4 = findViewById(R.id.addMaterialLL4);
        addMaterialLL5 = findViewById(R.id.addMaterialLL5);
        addMaterialLL6 = findViewById(R.id.addMaterialLL6);

        addIV1 = findViewById(R.id.addIV1);
        addIV2 = findViewById(R.id.addIV2);
        addIV3 = findViewById(R.id.addIV3);
        addIV4 = findViewById(R.id.addIV4);
        addIV5 = findViewById(R.id.addIV5);

        //Date
        mDateFormate = findViewById(R.id.date);

        addDataLL = findViewById(R.id.addData);
        progressBar = findViewById(R.id.progressBar);

        //Team Name
        teamName = findViewById(R.id.teamName);

        //Line
        line = findViewById(R.id.line);

        //Tender
        tender = findViewById(R.id.tender);

        //Driver
        driver = findViewById(R.id.driver);

        //Vehical
        vehical = findViewById(R.id.vehical);


        //Center
        center = findViewById(R.id.centerSR);

        //Village
        village = findViewById(R.id.villageSR);

        //Material
        material1 = findViewById(R.id.materialWork1);
        material2 = findViewById(R.id.materialWork2);
        material3 = findViewById(R.id.materialWork3);
        material4 = findViewById(R.id.materialWork4);
        material5 = findViewById(R.id.materialWork5);
        material6 = findViewById(R.id.materialWork6);
        material7 = findViewById(R.id.materialWork7);
        material8 = findViewById(R.id.materialWork8);
        material9 = findViewById(R.id.materialWork9);
        material10 = findViewById(R.id.materialWork10);

        material11 = findViewById(R.id.materialWork11);
        material12 = findViewById(R.id.materialWork12);
        material13 = findViewById(R.id.materialWork13);
        material14 = findViewById(R.id.materialWork14);
        material15 = findViewById(R.id.materialWork15);
        material16 = findViewById(R.id.materialWork16);
        material17 = findViewById(R.id.materialWork17);
        material18 = findViewById(R.id.materialWork18);
        material19 = findViewById(R.id.materialWork19);
        material20 = findViewById(R.id.materialWork20);

        material21 = findViewById(R.id.materialWork21);
        material22 = findViewById(R.id.materialWork22);
        material23 = findViewById(R.id.materialWork23);
        material24 = findViewById(R.id.materialWork24);
        material25 = findViewById(R.id.materialWork25);
        material26 = findViewById(R.id.materialWork26);
        material27 = findViewById(R.id.materialWork27);
        material28 = findViewById(R.id.materialWork28);
        material29 = findViewById(R.id.materialWork29);
        material30 = findViewById(R.id.materialWork30);

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


        //Unit
        unit1 = findViewById(R.id.unit1);
        unit2 = findViewById(R.id.unit2);
        unit3 = findViewById(R.id.unit3);
        unit4 = findViewById(R.id.unit4);
        unit5 = findViewById(R.id.unit5);
        unit6 = findViewById(R.id.unit6);
        unit7 = findViewById(R.id.unit7);
        unit8 = findViewById(R.id.unit8);
        unit9 = findViewById(R.id.unit9);
        unit10 = findViewById(R.id.unit10);
        unit11 = findViewById(R.id.unit11);
        unit12 = findViewById(R.id.unit12);
        unit13 = findViewById(R.id.unit13);
        unit14 = findViewById(R.id.unit14);
        unit15 = findViewById(R.id.unit15);
        unit16 = findViewById(R.id.unit16);
        unit17 = findViewById(R.id.unit17);
        unit18 = findViewById(R.id.unit18);
        unit19 = findViewById(R.id.unit19);
        unit20 = findViewById(R.id.unit20);

        unit21 = findViewById(R.id.unit21);
        unit22 = findViewById(R.id.unit22);
        unit23 = findViewById(R.id.unit23);
        unit24 = findViewById(R.id.unit24);
        unit25 = findViewById(R.id.unit25);
        unit26 = findViewById(R.id.unit26);
        unit27 = findViewById(R.id.unit27);
        unit28 = findViewById(R.id.unit28);
        unit29 = findViewById(R.id.unit29);
        unit30 = findViewById(R.id.unit30);


        //Receiver
        receiver = findViewById(R.id.receiver);

        //Button
        homeNextBtn = findViewById(R.id.nextHome);
        homeBackBtn = findViewById(R.id.backHome);
        middleNextBtn = findViewById(R.id.nextLast);
        nextLastTwo = findViewById(R.id.nextLastTwo);
        doneBtn = findViewById(R.id.done);
        lastBackBtn = findViewById(R.id.backLastOne);
        doneAll = findViewById(R.id.doneAll);


        //TextInputEditText
        consumer = findViewById(R.id.consumer);
        site = findViewById(R.id.siteName);
        passReceiver = findViewById(R.id.pass);


        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                companyEmail = value.getString("companyEmail");
                cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");



                //Stock Material
                recyclerView = findViewById(R.id.recyclerAM);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(SupervisorAddReturnMaterial.this);
                recyclerView.setLayoutManager(layoutManager);
                modelList.clear();
                showData(cmp);


                //Stock Material
                recyclerViewKN = findViewById(R.id.recyclerKN);
                recyclerViewKN.setHasFixedSize(true);
                layoutManagerKN = new LinearLayoutManager(SupervisorAddReturnMaterial.this);
                recyclerViewKN.setLayoutManager(layoutManagerKN);
                modelListKN.clear();
                showDataKN(cmp);


                ActionBar bar = getSupportActionBar();
                assert bar != null;
                bar.hide();


                Bundle bundle = getIntent().getExtras();


                if (bundle!=null){
                    work = bundle.getString("Work");
                    if (work.equals("Return")){
                        id = bundle.getString("Id");
                        dateS = bundle.getString("Date");
                        teamNameS = bundle.getString("TeamName");
                        lineS = bundle.getString("Line");
                        tenderS = bundle.getString("Tender");
                        driverNameS = bundle.getString("Driver Name");
                        vehicalNameS = bundle.getString("Vehical Name");
                        consumerNameS = bundle.getString("Consumer Name");
                        siteNameS = bundle.getString("Site");

                        //material
                        material1S = bundle.getString("Material1");
                        unit1S = bundle.getString("Unit1");
                        quantity1S = bundle.getString("Quantity1");

                        material2S = bundle.getString("Material2");
                        unit2S = bundle.getString("Unit2");
                        quantity2S = bundle.getString("Quantity2");

                        material3S = bundle.getString("Material3");
                        unit3S = bundle.getString("Unit3");
                        quantity3S = bundle.getString("Quantity3");

                        material4S = bundle.getString("Material4");
                        unit4S = bundle.getString("Unit4");
                        quantity4S = bundle.getString("Quantity4");

                        material5S = bundle.getString("Material5");
                        unit5S = bundle.getString("Unit5");
                        quantity5S = bundle.getString("Quantity5");

                        material6S = bundle.getString("Material6");
                        unit6S = bundle.getString("Unit6");
                        quantity6S = bundle.getString("Quantity6");

                        material7S = bundle.getString("Material7");
                        unit7S = bundle.getString("Unit7");
                        quantity7S = bundle.getString("Quantity7");

                        material8S = bundle.getString("Material8");
                        unit8S = bundle.getString("Unit8");
                        quantity8S = bundle.getString("Quantity8");

                        material9S = bundle.getString("Material9");
                        unit9S = bundle.getString("Unit9");
                        quantity9S = bundle.getString("Quantity9");

                        material10S = bundle.getString("Material10");
                        unit10S = bundle.getString("Unit10");
                        quantity10S = bundle.getString("Quantity10");

                        material11S = bundle.getString("Material11");
                        unit11S = bundle.getString("Unit11");
                        quantity11S = bundle.getString("Quantity11");

                        material12S = bundle.getString("Material12");
                        unit12S = bundle.getString("Unit12");
                        quantity12S = bundle.getString("Quantity12");

                        material13S = bundle.getString("Material13");
                        unit13S = bundle.getString("Unit13");
                        quantity13S = bundle.getString("Quantity13");

                        material14S = bundle.getString("Material14");
                        unit14S = bundle.getString("Unit14");
                        quantity14S = bundle.getString("Quantity14");

                        material15S = bundle.getString("Material15");
                        unit15S = bundle.getString("Unit15");
                        quantity15S = bundle.getString("Quantity15");

                        material16S = bundle.getString("Material16");
                        unit16S = bundle.getString("Unit16");
                        quantity16S = bundle.getString("Quantity16");

                        material17S = bundle.getString("Material17");
                        unit17S = bundle.getString("Unit17");
                        quantity17S = bundle.getString("Quantity17");

                        material18S = bundle.getString("Material18");
                        unit18S = bundle.getString("Unit18");
                        quantity18S = bundle.getString("Quantity18");

                        material19S = bundle.getString("Material19");
                        unit19S = bundle.getString("Unit19");
                        quantity19S = bundle.getString("Quantity19");

                        material20S = bundle.getString("Material20");
                        unit20S = bundle.getString("Unit20");
                        quantity20S = bundle.getString("Quantity20");

                        material21S = bundle.getString("Material21");
                        unit21S = bundle.getString("Unit21");
                        quantity21S = bundle.getString("Quantity21");

                        material22S = bundle.getString("Material22");
                        unit22S = bundle.getString("Unit22");
                        quantity22S = bundle.getString("Quantity22");

                        material23S = bundle.getString("Material23");
                        unit23S = bundle.getString("Unit23");
                        quantity23S = bundle.getString("Quantity23");

                        material24S = bundle.getString("Material24");
                        unit24S = bundle.getString("Unit24");
                        quantity24S = bundle.getString("Quantity24");

                        material25S = bundle.getString("Material25");
                        unit25S = bundle.getString("Unit25");
                        quantity25S = bundle.getString("Quantity25");

                        material26S = bundle.getString("Material26");
                        unit26S = bundle.getString("Unit26");
                        quantity26S = bundle.getString("Quantity26");

                        material27S = bundle.getString("Material27");
                        unit27S = bundle.getString("Unit27");
                        quantity27S = bundle.getString("Quantity27");

                        material28S = bundle.getString("Material28");
                        unit28S = bundle.getString("Unit28");
                        quantity28S = bundle.getString("Quantity28");

                        material29S = bundle.getString("Material29");
                        unit29S = bundle.getString("Unit29");
                        quantity29S = bundle.getString("Quantity29");

                        material30S = bundle.getString("Material30");
                        unit30S = bundle.getString("Unit30");
                        quantity30S = bundle.getString("Quantity30");

                        centerS = bundle.getString("Center");
                        villageS = bundle.getString("Village");
                    }
                    else if(work.equals("Add")) {

                        id = bundle.getString("Id");
                        dateS = bundle.getString("Date");
                        teamNameS = bundle.getString("TeamName");
                        lineS = bundle.getString("Line");
                        tenderS = bundle.getString("Tender");
                        driverNameS = bundle.getString("Driver Name");
                        vehicalNameS = bundle.getString("Vehical Name");
                        consumerNameS = bundle.getString("Consumer Name");
                        siteNameS = bundle.getString("Site");

                        //material
                        material1S = bundle.getString("Material1");
                        unit1S = bundle.getString("Unit1");
                        quantity1S = bundle.getString("Quantity1");

                        material2S = bundle.getString("Material2");
                        unit2S = bundle.getString("Unit2");
                        quantity2S = bundle.getString("Quantity2");

                        material3S = bundle.getString("Material3");
                        unit3S = bundle.getString("Unit3");
                        quantity3S = bundle.getString("Quantity3");

                        material4S = bundle.getString("Material4");
                        unit4S = bundle.getString("Unit4");
                        quantity4S = bundle.getString("Quantity4");

                        material5S = bundle.getString("Material5");
                        unit5S = bundle.getString("Unit5");
                        quantity5S = bundle.getString("Quantity5");

                        material6S = bundle.getString("Material6");
                        unit6S = bundle.getString("Unit6");
                        quantity6S = bundle.getString("Quantity6");

                        material7S = bundle.getString("Material7");
                        unit7S = bundle.getString("Unit7");
                        quantity7S = bundle.getString("Quantity7");

                        material8S = bundle.getString("Material8");
                        unit8S = bundle.getString("Unit8");
                        quantity8S = bundle.getString("Quantity8");

                        material9S = bundle.getString("Material9");
                        unit9S = bundle.getString("Unit9");
                        quantity9S = bundle.getString("Quantity9");

                        material10S = bundle.getString("Material10");
                        unit10S = bundle.getString("Unit10");
                        quantity10S = bundle.getString("Quantity10");

                        material11S = bundle.getString("Material11");
                        unit11S = bundle.getString("Unit11");
                        quantity11S = bundle.getString("Quantity11");

                        material12S = bundle.getString("Material12");
                        unit12S = bundle.getString("Unit12");
                        quantity12S = bundle.getString("Quantity12");

                        material13S = bundle.getString("Material13");
                        unit13S = bundle.getString("Unit13");
                        quantity13S = bundle.getString("Quantity13");

                        material14S = bundle.getString("Material14");
                        unit14S = bundle.getString("Unit14");
                        quantity14S = bundle.getString("Quantity14");

                        material15S = bundle.getString("Material15");
                        unit15S = bundle.getString("Unit15");
                        quantity15S = bundle.getString("Quantity15");

                        material16S = bundle.getString("Material16");
                        unit16S = bundle.getString("Unit16");
                        quantity16S = bundle.getString("Quantity16");

                        material17S = bundle.getString("Material17");
                        unit17S = bundle.getString("Unit17");
                        quantity17S = bundle.getString("Quantity17");

                        material18S = bundle.getString("Material18");
                        unit18S = bundle.getString("Unit18");
                        quantity18S = bundle.getString("Quantity18");

                        material19S = bundle.getString("Material19");
                        unit19S = bundle.getString("Unit19");
                        quantity19S = bundle.getString("Quantity19");

                        material20S = bundle.getString("Material20");
                        unit20S = bundle.getString("Unit20");
                        quantity20S = bundle.getString("Quantity20");

                        material21S = bundle.getString("Material21");
                        unit21S = bundle.getString("Unit21");
                        quantity21S = bundle.getString("Quantity21");

                        material22S = bundle.getString("Material22");
                        unit22S = bundle.getString("Unit22");
                        quantity22S = bundle.getString("Quantity22");

                        material23S = bundle.getString("Material23");
                        unit23S = bundle.getString("Unit23");
                        quantity23S = bundle.getString("Quantity23");

                        material24S = bundle.getString("Material24");
                        unit24S = bundle.getString("Unit24");
                        quantity24S = bundle.getString("Quantity24");

                        material25S = bundle.getString("Material25");
                        unit25S = bundle.getString("Unit25");
                        quantity25S = bundle.getString("Quantity25");

                        material26S = bundle.getString("Material26");
                        unit26S = bundle.getString("Unit26");
                        quantity26S = bundle.getString("Quantity26");

                        material27S = bundle.getString("Material27");
                        unit27S = bundle.getString("Unit27");
                        quantity27S = bundle.getString("Quantity27");

                        material28S = bundle.getString("Material28");
                        unit28S = bundle.getString("Unit28");
                        quantity28S = bundle.getString("Quantity28");

                        material29S = bundle.getString("Material29");
                        unit29S = bundle.getString("Unit29");
                        quantity29S = bundle.getString("Quantity29");

                        material30S = bundle.getString("Material30");
                        unit30S = bundle.getString("Unit30");
                        quantity30S = bundle.getString("Quantity30");

                        centerS = bundle.getString("Center");
                        villageS = bundle.getString("Village");

                    }
                }

                mDateFormate.setText(dateS);
                teamName.setText(teamNameS);
                line.setText(lineS);
                tender.setText(tenderS);
                driver.setText(driverNameS);
                vehical.setText(vehicalNameS);
                consumer.setText(consumerNameS);
                site.setText(siteNameS);
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

                unit1.setText(unit1S);
                unit2.setText(unit2S);
                unit3.setText(unit3S);
                unit4.setText(unit4S);
                unit5.setText(unit5S);
                unit6.setText(unit6S);
                unit7.setText(unit7S);
                unit8.setText(unit8S);
                unit9.setText(unit9S);
                unit10.setText(unit10S);
                unit11.setText(unit11S);
                unit12.setText(unit12S);
                unit13.setText(unit13S);
                unit14.setText(unit14S);
                unit15.setText(unit15S);
                unit16.setText(unit16S);
                unit17.setText(unit17S);
                unit18.setText(unit18S);
                unit19.setText(unit19S);
                unit20.setText(unit20S);
                unit21.setText(unit21S);
                unit22.setText(unit22S);
                unit23.setText(unit23S);
                unit24.setText(unit24S);
                unit25.setText(unit25S);
                unit26.setText(unit26S);
                unit27.setText(unit27S);
                unit28.setText(unit28S);
                unit29.setText(unit29S);
                unit30.setText(unit30S);

                center.setText(centerS);
                village.setText(villageS);

                mDateFormate.setEnabled(false);
                teamName.setEnabled(false);
                line.setEnabled(false);
                tender.setEnabled(false);
                driver.setEnabled(false);
                vehical.setEnabled(false);
                consumer.setEnabled(false);
                site.setEnabled(false);
                center.setEnabled(false);
                village.setEnabled(false);

                material1.setEnabled(false);
                material2.setEnabled(false);
                material3.setEnabled(false);
                material4.setEnabled(false);
                material5.setEnabled(false);
                material6.setEnabled(false);
                material7.setEnabled(false);
                material8.setEnabled(false);
                material9.setEnabled(false);
                material10.setEnabled(false);
                material11.setEnabled(false);
                material12.setEnabled(false);
                material13.setEnabled(false);
                material14.setEnabled(false);
                material15.setEnabled(false);
                material16.setEnabled(false);
                material17.setEnabled(false);
                material18.setEnabled(false);
                material19.setEnabled(false);
                material20.setEnabled(false);
                material21.setEnabled(false);
                material22.setEnabled(false);
                material23.setEnabled(false);
                material24.setEnabled(false);
                material25.setEnabled(false);
                material26.setEnabled(false);
                material27.setEnabled(false);
                material28.setEnabled(false);
                material29.setEnabled(false);
                material30.setEnabled(false);

                unit1.setEnabled(false);
                unit2.setEnabled(false);
                unit3.setEnabled(false);
                unit4.setEnabled(false);
                unit5.setEnabled(false);
                unit6.setEnabled(false);
                unit7.setEnabled(false);
                unit8.setEnabled(false);
                unit9.setEnabled(false);
                unit10.setEnabled(false);
                unit11.setEnabled(false);
                unit12.setEnabled(false);
                unit13.setEnabled(false);
                unit14.setEnabled(false);
                unit15.setEnabled(false);
                unit16.setEnabled(false);
                unit17.setEnabled(false);
                unit18.setEnabled(false);
                unit19.setEnabled(false);
                unit20.setEnabled(false);
                unit21.setEnabled(false);
                unit22.setEnabled(false);
                unit23.setEnabled(false);
                unit24.setEnabled(false);
                unit25.setEnabled(false);
                unit26.setEnabled(false);
                unit27.setEnabled(false);
                unit28.setEnabled(false);
                unit29.setEnabled(false);
                unit30.setEnabled(false);

                if (quantity1S.isEmpty()) {
                    quantity1.setEnabled(false);
                }
                if (quantity2S.isEmpty()) {
                    quantity2.setEnabled(false);
                }
                if (quantity3S.isEmpty()) {
                    quantity3.setEnabled(false);
                }
                if (quantity4S.isEmpty()) {
                    quantity4.setEnabled(false);
                }
                if (quantity5S.isEmpty()) {
                    quantity5.setEnabled(false);
                }
                if (quantity6S.isEmpty()) {
                    quantity6.setEnabled(false);
                }
                if (quantity7S.isEmpty()) {
                    quantity7.setEnabled(false);
                }
                if (quantity8S.isEmpty()) {
                    quantity8.setEnabled(false);
                }
                if (quantity9S.isEmpty()) {
                    quantity9.setEnabled(false);
                }
                if (quantity10S.isEmpty()) {
                    quantity10.setEnabled(false);
                }
                if (quantity11S.isEmpty()) {
                    quantity11.setEnabled(false);
                }
                if (quantity12S.isEmpty()) {
                    quantity12.setEnabled(false);
                }
                if (quantity13S.isEmpty()) {
                    quantity13.setEnabled(false);
                }
                if (quantity14S.isEmpty()) {
                    quantity14.setEnabled(false);
                }
                if (quantity15S.isEmpty()) {
                    quantity15.setEnabled(false);
                }
                if (quantity16S.isEmpty()) {
                    quantity16.setEnabled(false);
                }
                if (quantity17S.isEmpty()) {
                    quantity17.setEnabled(false);
                }
                if (quantity18S.isEmpty()) {
                    quantity18.setEnabled(false);
                }
                if (quantity19S.isEmpty()) {
                    quantity19.setEnabled(false);
                }
                if (quantity20S.isEmpty()) {
                    quantity20.setEnabled(false);
                }
                if (quantity21S.isEmpty()) {
                    quantity21.setEnabled(false);
                }
                if (quantity22S.isEmpty()) {
                    quantity22.setEnabled(false);
                }
                if (quantity23S.isEmpty()) {
                    quantity23.setEnabled(false);
                }
                if (quantity24S.isEmpty()) {
                    quantity24.setEnabled(false);
                }
                if (quantity25S.isEmpty()) {
                    quantity25.setEnabled(false);
                }
                if (quantity26S.isEmpty()) {
                    quantity26.setEnabled(false);
                }
                if (quantity27S.isEmpty()) {
                    quantity27.setEnabled(false);
                }
                if (quantity28S.isEmpty()) {
                    quantity28.setEnabled(false);
                }
                if (quantity29S.isEmpty()) {
                    quantity29.setEnabled(false);
                }
                if (quantity30S.isEmpty()) {
                    quantity30.setEnabled(false);
                }

                if (work.equals("Add")) {

                    if (!quantity1S.isEmpty()) {
                        quantity1.setText("0");
                    }
                    if (!quantity2S.isEmpty()) {
                        quantity2.setText("0");
                    }
                    if (!quantity3S.isEmpty()) {
                        quantity3.setText("0");
                    }
                    if (!quantity4S.isEmpty()) {
                        quantity4.setText("0");
                    }
                    if (!quantity5S.isEmpty()) {
                        quantity5.setText("0");
                    }
                    if (!quantity6S.isEmpty()) {
                        quantity6.setText("0");
                    }
                    if (!quantity7S.isEmpty()) {
                        quantity7.setText("0");
                    }
                    if (!quantity8S.isEmpty()) {
                        quantity8.setText("0");
                    }
                    if (!quantity9S.isEmpty()) {
                        quantity9.setText("0");
                    }
                    if (!quantity10S.isEmpty()) {
                        quantity10.setText("0");
                    }
                    if (!quantity11S.isEmpty()) {
                        quantity11.setText("0");
                    }
                    if (!quantity12S.isEmpty()) {
                        quantity12.setText("0");
                    }
                    if (!quantity13S.isEmpty()) {
                        quantity13.setText("0");
                    }
                    if (!quantity14S.isEmpty()) {
                        quantity14.setText("0");
                    }
                    if (!quantity15S.isEmpty()) {
                        quantity15.setText("0");
                    }
                    if (!quantity16S.isEmpty()) {
                        quantity16.setText("0");
                    }
                    if (!quantity17S.isEmpty()) {
                        quantity17.setText("0");
                    }
                    if (!quantity18S.isEmpty()) {
                        quantity18.setText("0");
                    }
                    if (!quantity19S.isEmpty()) {
                        quantity19.setText("0");
                    }
                    if (!quantity20S.isEmpty()) {
                        quantity20.setText("0");
                    }
                    if (!quantity21S.isEmpty()) {
                        quantity21.setText("0");
                    }
                    if (!quantity22S.isEmpty()) {
                        quantity22.setText("0");
                    }
                    if (!quantity23S.isEmpty()) {
                        quantity23.setText("0");
                    }
                    if (!quantity24S.isEmpty()) {
                        quantity24.setText("0");
                    }
                    if (!quantity25S.isEmpty()) {
                        quantity25.setText("0");
                    }
                    if (!quantity26S.isEmpty()) {
                        quantity26.setText("0");
                    }
                    if (!quantity27S.isEmpty()) {
                        quantity27.setText("0");
                    }
                    if (!quantity28S.isEmpty()) {
                        quantity28.setText("0");
                    }
                    if (!quantity29S.isEmpty()) {
                        quantity29.setText("0");
                    }
                    if (!quantity30S.isEmpty()) {
                        quantity30.setText("0");
                    }


                }

                else if (work.equals("Return")){


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



                }


                //Receiver
                databaseReferenceReceiver = FirebaseDatabase.getInstance().getReference(companyEmail + " Receiver");
                spinnerDataListReceiver = new ArrayList<>();
                adapterReceiver = new ArrayAdapter<String>(SupervisorAddReturnMaterial.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataListReceiver);
                receiver.setAdapter(adapterReceiver);
                retrieveDataReceiver();


                //Material Add Btn
                addIV1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Materials
                        String uMaterial1 = material1.getText().toString().trim();
                        String uQuantity1 = quantity1.getText().toString().trim();
                        String uUnit1 = unit1.getText().toString().trim();

                        String uMaterial2 = material2.getText().toString().trim();
                        String uQuantity2 = quantity2.getText().toString().trim();
                        String uUnit2 = unit2.getText().toString().trim();

                        String uMaterial3 = material3.getText().toString().trim();
                        String uQuantity3 = quantity3.getText().toString().trim();
                        String uUnit3 = unit3.getText().toString().trim();

                        String uMaterial4 = material4.getText().toString().trim();
                        String uQuantity4 = quantity4.getText().toString().trim();
                        String uUnit4 = unit4.getText().toString().trim();

                        String uMaterial5 = material5.getText().toString().trim();
                        String uQuantity5 = quantity5.getText().toString().trim();
                        String uUnit5 = unit5.getText().toString().trim();


                        if (uMaterial1.isEmpty() || uQuantity1.isEmpty() || uUnit1.isEmpty() || uMaterial2.isEmpty() || uQuantity2.isEmpty() || uUnit2.isEmpty() ||
                                uMaterial3.isEmpty() || uQuantity3.isEmpty() || uUnit3.isEmpty() || uMaterial4.isEmpty() || uQuantity4.isEmpty() || uUnit4.isEmpty() ||
                                uMaterial5.isEmpty() || uQuantity5.isEmpty() || uUnit5.isEmpty()) {
                            showMessage("Fill above information first");
                        } else {
                            addIV1.setVisibility(View.GONE);
                            addMaterialLL2.setVisibility(View.VISIBLE);
                        }
                    }
                });

                addIV2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Materials
                        String uMaterial6 = material6.getText().toString().trim();
                        String uQuantity6 = quantity6.getText().toString().trim();
                        String uUnit6 = unit6.getText().toString().trim();

                        String uMaterial7 = material7.getText().toString().trim();
                        String uQuantity7 = quantity7.getText().toString().trim();
                        String uUnit7 = unit7.getText().toString().trim();

                        String uMaterial8 = material8.getText().toString().trim();
                        String uQuantity8 = quantity8.getText().toString().trim();
                        String uUnit8 = unit8.getText().toString().trim();

                        String uMaterial9 = material9.getText().toString().trim();
                        String uQuantity9 = quantity9.getText().toString().trim();
                        String uUnit9 = unit9.getText().toString().trim();

                        String uMaterial10 = material10.getText().toString().trim();
                        String uQuantity10 = quantity10.getText().toString().trim();
                        String uUnit10 = unit10.getText().toString().trim();


                        if (uMaterial6.isEmpty() || uQuantity6.isEmpty() || uUnit6.isEmpty() || uMaterial7.isEmpty() || uQuantity7.isEmpty() || uUnit7.isEmpty() ||
                                uMaterial8.isEmpty() || uQuantity8.isEmpty() || uUnit8.isEmpty() || uMaterial9.isEmpty() || uQuantity9.isEmpty() || uUnit9.isEmpty() ||
                                uMaterial10.isEmpty() || uQuantity10.isEmpty() || uUnit10.isEmpty()) {
                            showMessage("Fill above information first");
                        } else {
                            addIV2.setVisibility(View.GONE);
                            addMaterialLL3.setVisibility(View.VISIBLE);
                        }
                    }
                });

                addIV3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Materials
                        String uMaterial11 = material11.getText().toString().trim();
                        String uQuantity11 = quantity11.getText().toString().trim();
                        String uUnit11 = unit11.getText().toString().trim();

                        String uMaterial12 = material12.getText().toString().trim();
                        String uQuantity12 = quantity12.getText().toString().trim();
                        String uUnit12 = unit12.getText().toString().trim();

                        String uMaterial13 = material13.getText().toString().trim();
                        String uQuantity13 = quantity13.getText().toString().trim();
                        String uUnit13 = unit13.getText().toString().trim();

                        String uMaterial14 = material4.getText().toString().trim();
                        String uQuantity14 = quantity14.getText().toString().trim();
                        String uUnit14 = unit14.getText().toString().trim();

                        String uMaterial15 = material15.getText().toString().trim();
                        String uQuantity15 = quantity15.getText().toString().trim();
                        String uUnit15 = unit15.getText().toString().trim();


                        if (uMaterial11.isEmpty() || uQuantity11.isEmpty() || uUnit11.isEmpty() || uMaterial12.isEmpty() || uQuantity12.isEmpty() || uUnit12.isEmpty() ||
                                uMaterial13.isEmpty() || uQuantity13.isEmpty() || uUnit13.isEmpty() || uMaterial14.isEmpty() || uQuantity14.isEmpty() || uUnit14.isEmpty() ||
                                uMaterial15.isEmpty() || uQuantity15.isEmpty() || uUnit15.isEmpty()) {
                            showMessage("Fill above information first");
                        } else {
                            addIV3.setVisibility(View.GONE);
                            addMaterialLL4.setVisibility(View.VISIBLE);
                        }
                    }
                });

                addIV4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Materials
                        String uMaterial16 = material16.getText().toString().trim();
                        String uQuantity16 = quantity16.getText().toString().trim();
                        String uUnit16 = unit16.getText().toString().trim();

                        String uMaterial17 = material17.getText().toString().trim();
                        String uQuantity17 = quantity17.getText().toString().trim();
                        String uUnit17 = unit17.getText().toString().trim();

                        String uMaterial18 = material18.getText().toString().trim();
                        String uQuantity18 = quantity18.getText().toString().trim();
                        String uUnit18 = unit18.getText().toString().trim();

                        String uMaterial19 = material19.getText().toString().trim();
                        String uQuantity19 = quantity19.getText().toString().trim();
                        String uUnit19 = unit19.getText().toString().trim();

                        String uMaterial20 = material20.getText().toString().trim();
                        String uQuantity20 = quantity20.getText().toString().trim();
                        String uUnit20 = unit20.getText().toString().trim();

                        if (uMaterial16.isEmpty() || uQuantity16.isEmpty() || uUnit16.isEmpty() || uMaterial17.isEmpty() || uQuantity17.isEmpty() || uUnit17.isEmpty() ||
                                uMaterial18.isEmpty() || uQuantity18.isEmpty() || uUnit18.isEmpty() || uMaterial19.isEmpty() || uQuantity19.isEmpty() || uUnit19.isEmpty() ||
                                uMaterial20.isEmpty() || uQuantity20.isEmpty() || uUnit20.isEmpty()) {
                            showMessage("Fill above information first");
                        } else {
                            addIV4.setVisibility(View.GONE);
                            addMaterialLL5.setVisibility(View.VISIBLE);
                        }
                    }
                });

                addIV5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Materials
                        String uMaterial21 = material21.getText().toString().trim();
                        String uQuantity21 = quantity21.getText().toString().trim();
                        String uUnit21 = unit21.getText().toString().trim();

                        String uMaterial22 = material22.getText().toString().trim();
                        String uQuantity22 = quantity22.getText().toString().trim();
                        String uUnit22 = unit22.getText().toString().trim();

                        String uMaterial23 = material23.getText().toString().trim();
                        String uQuantity23 = quantity23.getText().toString().trim();
                        String uUnit23 = unit23.getText().toString().trim();

                        String uMaterial24 = material4.getText().toString().trim();
                        String uQuantity24 = quantity24.getText().toString().trim();
                        String uUnit24 = unit24.getText().toString().trim();

                        String uMaterial25 = material25.getText().toString().trim();
                        String uQuantity25 = quantity25.getText().toString().trim();
                        String uUnit25 = unit25.getText().toString().trim();


                        if (uMaterial21.isEmpty() || uQuantity21.isEmpty() || uUnit21.isEmpty() || uMaterial22.isEmpty() || uQuantity22.isEmpty() || uUnit22.isEmpty() ||
                                uMaterial23.isEmpty() || uQuantity23.isEmpty() || uUnit23.isEmpty() || uMaterial24.isEmpty() || uQuantity24.isEmpty() || uUnit24.isEmpty() ||
                                uMaterial25.isEmpty() || uQuantity25.isEmpty() || uUnit25.isEmpty()) {
                            showMessage("Fill above information first");
                        } else {
                            addIV5.setVisibility(View.GONE);
                            addMaterialLL6.setVisibility(View.VISIBLE);
                        }
                    }
                });


                //HomeBtn
                homeNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String team = teamName.getText().toString().trim();
                        String lineS = line.getText().toString().trim();
                        String tenderS = tender.getText().toString().trim();
                        String driverS = driver.getText().toString().trim();
                        String vehicalS = vehical.getText().toString().trim();
                        String dateS = mDateFormate.getText().toString().trim();

                        if (dateS.isEmpty()) {
                            mDateFormate.setError(r);
                            mDateFormate.requestFocus();
                        } else if (team.isEmpty()) {
                            teamName.setError(r);
                            teamName.requestFocus();
                        } else if (lineS.isEmpty()) {
                            line.setError(r);
                            line.requestFocus();
                        } else if (tenderS.isEmpty()) {
                            tender.setError(r);
                            tender.requestFocus();
                        } else if (driverS.isEmpty()) {
                            driver.setError(r);
                            driver.requestFocus();
                        } else if (vehicalS.isEmpty()) {
                            vehical.setError(r);
                            vehical.requestFocus();
                        } else {
                            homeLL.setVisibility(View.GONE);
                            middleLL.setVisibility(View.VISIBLE);
                        }

                    }
                });

                //Home Back
                homeBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        homeLL.setVisibility(View.VISIBLE);
                        middleLL.setVisibility(View.GONE);
                    }
                });

                //Middle Next
                middleNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String consumerName = consumer.getText().toString().trim();
                        String siteName = site.getText().toString().trim();
                        String centerS = center.getText().toString().trim();
                        String villageS = village.getText().toString().trim();
                        if (consumerName.isEmpty()) {
                            consumer.setError(r);
                            consumer.requestFocus();
                        }
                        else if (siteName.isEmpty()) {
                            site.setError(r);
                            site.requestFocus();
                        }
                        else if (centerS.isEmpty()) {
                            center.setError(r);
                            center.requestFocus();
                        }
                        else if (villageS.isEmpty()) {
                            village.setError(r);
                            village.requestFocus();
                        }
                        else {
                            middleLL.setVisibility(View.GONE);
                            lastOneLL.setVisibility(View.VISIBLE);
                        }
                    }
                });

                //NextLastTwo
                nextLastTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lastOneLL.setVisibility(View.GONE);
                        lastLL.setVisibility(View.VISIBLE);
                    }
                });

                lastBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lastOneLL.setVisibility(View.VISIBLE);
                        lastLL.setVisibility(View.GONE);
                    }
                });

                doneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pass = passReceiver.getText().toString().trim();

                        //Home
                        String uDate = mDateFormate.getText().toString().trim();
                        String uTeamName = teamName.getText().toString().trim();
                        String uLine = line.getText().toString().trim();
                        String uTender = tender.getText().toString().trim();
                        String uDriverName = driver.getText().toString().trim();
                        String uVehicalName = vehical.getText().toString().trim();

                        //Site
                        String uConsumerName = consumer.getText().toString().trim();
                        String uSite = (site.getText().toString().trim());
                        //Material
                        uMaterial1 = material1.getText().toString().trim();
                        uQuantity1 = quantity1.getText().toString().trim();
                        uUnit1 = unit1.getText().toString().trim();

                        uMaterial2 = material2.getText().toString().trim();
                        uQuantity2 = quantity2.getText().toString().trim();
                        uUnit2 = unit2.getText().toString().trim();

                        uMaterial3 = material3.getText().toString().trim();
                        uQuantity3 = quantity3.getText().toString().trim();
                        uUnit3 = unit3.getText().toString().trim();

                        uMaterial4 = material4.getText().toString().trim();
                        uQuantity4 = quantity4.getText().toString().trim();
                        uUnit4 = unit4.getText().toString().trim();

                        uMaterial5 = material5.getText().toString().trim();
                        uQuantity5 = quantity5.getText().toString().trim();
                        uUnit5 = unit5.getText().toString().trim();

                        uMaterial6 = material6.getText().toString().trim();
                        uQuantity6 = quantity6.getText().toString().trim();
                        uUnit6 = unit6.getText().toString().trim();

                        uMaterial7 = material7.getText().toString().trim();
                        uQuantity7 = quantity7.getText().toString().trim();
                        uUnit7 = unit7.getText().toString().trim();

                        uMaterial8 = material8.getText().toString().trim();
                        uQuantity8 = quantity8.getText().toString().trim();
                        uUnit8 = unit8.getText().toString().trim();

                        uMaterial9 = material9.getText().toString().trim();
                        uQuantity9 = quantity9.getText().toString().trim();
                        uUnit9 = unit9.getText().toString().trim();

                        uMaterial10 = material10.getText().toString().trim();
                        uQuantity10 = quantity10.getText().toString().trim();
                        uUnit10 = unit10.getText().toString().trim();

                        uMaterial11 = material11.getText().toString().trim();
                        uQuantity11 = quantity11.getText().toString().trim();
                        uUnit11 = unit11.getText().toString().trim();

                        uMaterial12 = material12.getText().toString().trim();
                        uQuantity12 = quantity12.getText().toString().trim();
                        uUnit12 = unit12.getText().toString().trim();

                        uMaterial13 = material13.getText().toString().trim();
                        uQuantity13 = quantity13.getText().toString().trim();
                        uUnit13 = unit13.getText().toString().trim();

                        uMaterial14 = material14.getText().toString().trim();
                        uQuantity14 = quantity14.getText().toString().trim();
                        uUnit14 = unit14.getText().toString().trim();

                        uMaterial15 = material15.getText().toString().trim();
                        uQuantity15 = quantity15.getText().toString().trim();
                        uUnit15 = unit15.getText().toString().trim();

                        uMaterial16 = material16.getText().toString().trim();
                        uQuantity16 = quantity16.getText().toString().trim();
                        uUnit16 = unit16.getText().toString().trim();

                        uMaterial17 = material17.getText().toString().trim();
                        uQuantity17 = quantity17.getText().toString().trim();
                        uUnit17 = unit17.getText().toString().trim();

                        uMaterial18 = material18.getText().toString().trim();
                        uQuantity18 = quantity18.getText().toString().trim();
                        uUnit18 = unit18.getText().toString().trim();

                        uMaterial19 = material19.getText().toString().trim();
                        uQuantity19 = quantity19.getText().toString().trim();
                        uUnit19 = unit19.getText().toString().trim();

                        uMaterial20 = material20.getText().toString().trim();
                        uQuantity20 = quantity20.getText().toString().trim();
                        uUnit20 = unit20.getText().toString().trim();


                        uMaterial21 = material21.getText().toString().trim();
                        uQuantity21 = quantity21.getText().toString().trim();
                        uUnit21 = unit21.getText().toString().trim();

                        uMaterial22 = material22.getText().toString().trim();
                        uQuantity22 = quantity22.getText().toString().trim();
                        uUnit22 = unit22.getText().toString().trim();

                        uMaterial23 = material23.getText().toString().trim();
                        uQuantity23 = quantity23.getText().toString().trim();
                        uUnit23 = unit23.getText().toString().trim();

                        uMaterial24 = material24.getText().toString().trim();
                        uQuantity24 = quantity24.getText().toString().trim();
                        uUnit24 = unit24.getText().toString().trim();

                        uMaterial25 = material25.getText().toString().trim();
                        uQuantity25 = quantity25.getText().toString().trim();
                        uUnit25 = unit25.getText().toString().trim();

                        uMaterial26 = material26.getText().toString().trim();
                        uQuantity26 = quantity26.getText().toString().trim();
                        uUnit26 = unit26.getText().toString().trim();

                        uMaterial27 = material27.getText().toString().trim();
                        uQuantity27 = quantity27.getText().toString().trim();
                        uUnit27 = unit27.getText().toString().trim();

                        uMaterial28 = material28.getText().toString().trim();
                        uQuantity28 = quantity28.getText().toString().trim();
                        uUnit28 = unit28.getText().toString().trim();

                        uMaterial29 = material29.getText().toString().trim();
                        uQuantity29 = quantity29.getText().toString().trim();
                        uUnit29 = unit19.getText().toString().trim();

                        uMaterial30 = material30.getText().toString().trim();
                        uQuantity30 = quantity30.getText().toString().trim();
                        uUnit30 = unit30.getText().toString().trim();

                        String centerS = center.getText().toString().trim();
                        String villageS = village.getText().toString().trim();

                        //Receiver
                        String materialReceiver = receiver.getText().toString().trim();

                        if (materialReceiver.isEmpty()) {
                            receiver.setError(r);
                            receiver.requestFocus();
                        } else if (pass.isEmpty()) {
                            passReceiver.setError(r);
                            passReceiver.requestFocus();
                        } else {
                            verifyPassReceiver(materialReceiver, pass, cmp, uDate, uTeamName, uLine, uTender, uDriverName, uVehicalName, uConsumerName, uSite, uMaterial1, uQuantity1, uMaterial2, uQuantity2, uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5, uQuantity5,
                                    uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10
                                    , uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15,
                                    uMaterial16, uQuantity16, uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20
                                    , uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10
                                    , uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11

                                    , uMaterial21, uQuantity21, uMaterial22, uQuantity22, uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25,
                                    uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28, uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30
                                    , uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30
                                    ,centerS,villageS

                            );

                        }
                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doneAll.setEnabled(true);
                    }
                }, 1500);
                doneAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), Supervisor.class));
                        finish();
                    }
                });


//                return false;
            }
        });

    }

    private void verifyPassReceiver(String materialReceiver, String pass, String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30,String centerS,String villageS) {
        DocumentReference a = fStore.collection(cmp + " ProfilePass")
                .document(materialReceiver.toLowerCase());
        a.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String passReceiverS = value.getString("Password");

                if (pass.equals(passReceiverS)) {
                    Toast.makeText(getApplicationContext(), "Password Match!!", Toast.LENGTH_SHORT).show();
                    uploadData(cmp, uDate, uTeamName, uLine, uTender, uDriverName, uVehicalName, uConsumerName, uSite, uMaterial1, uQuantity1, uMaterial2, uQuantity2, uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5, uQuantity5,
                            uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10
                            , uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15,
                            uMaterial16, uQuantity16, uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20
                            , uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10
                            , uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11
                            , uMaterial21, uQuantity21, uMaterial22, uQuantity22, uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25,
                            uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28, uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30
                            , uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30, materialReceiver,centerS,villageS);



                } else {
                    passReceiver.setError("Password does not Match!!");
                    passReceiver.requestFocus();
                    Toast.makeText(getApplicationContext(), "Password does not Match!!", Toast.LENGTH_SHORT).show();

                }
//                return false;
            }
        });

    }

    private void uploadData(String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver,String centerS,String villageS) {
        addDataLL.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Map<String, Object> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("Date", uDate);
        doc.put("SearchDate", uDate.toLowerCase());
        doc.put("Team Name", uTeamName);
        doc.put("SearchTeamName", uTeamName.toLowerCase());
        doc.put("Line", uLine);
        doc.put("Tender", uTender);
        doc.put("SearchTender", uTender.toLowerCase());
        doc.put("Driver Name", uDriverName);
        doc.put("Vehical Name", uVehicalName);
        doc.put("Consumer Name", uConsumerName);
        doc.put("Site Name", uSite);
        //Material
        doc.put("Material 1", uMaterial1);
        doc.put("Material 2", uMaterial2);
        doc.put("Material 3", uMaterial3);
        doc.put("Material 4", uMaterial4);
        doc.put("Material 5", uMaterial5);
        doc.put("Material 6", uMaterial6);
        doc.put("Material 7", uMaterial7);
        doc.put("Material 8", uMaterial8);
        doc.put("Material 9", uMaterial9);
        doc.put("Material 10", uMaterial10);

        doc.put("Material 11", uMaterial11);
        doc.put("Material 12", uMaterial12);
        doc.put("Material 13", uMaterial13);
        doc.put("Material 14", uMaterial14);
        doc.put("Material 15", uMaterial15);
        doc.put("Material 16", uMaterial16);
        doc.put("Material 17", uMaterial17);
        doc.put("Material 18", uMaterial18);
        doc.put("Material 19", uMaterial19);
        doc.put("Material 20", uMaterial20);

        doc.put("Material 21", uMaterial21);
        doc.put("Material 22", uMaterial22);
        doc.put("Material 23", uMaterial23);
        doc.put("Material 24", uMaterial24);
        doc.put("Material 25", uMaterial25);
        doc.put("Material 26", uMaterial26);
        doc.put("Material 27", uMaterial27);
        doc.put("Material 28", uMaterial28);
        doc.put("Material 29", uMaterial29);
        doc.put("Material 30", uMaterial30);
        //Quantity
        doc.put("Quantity 1", uQuantity1);
        doc.put("Quantity 2", uQuantity2);
        doc.put("Quantity 3", uQuantity3);
        doc.put("Quantity 4", uQuantity4);
        doc.put("Quantity 5", uQuantity5);
        doc.put("Quantity 6", uQuantity6);
        doc.put("Quantity 7", uQuantity7);
        doc.put("Quantity 8", uQuantity8);
        doc.put("Quantity 9", uQuantity9);
        doc.put("Quantity 10", uQuantity10);
        doc.put("Quantity 11", uQuantity11);
        doc.put("Quantity 12", uQuantity12);
        doc.put("Quantity 13", uQuantity13);
        doc.put("Quantity 14", uQuantity14);
        doc.put("Quantity 15", uQuantity15);
        doc.put("Quantity 16", uQuantity16);
        doc.put("Quantity 17", uQuantity17);
        doc.put("Quantity 18", uQuantity18);
        doc.put("Quantity 19", uQuantity19);
        doc.put("Quantity 20", uQuantity20);

        doc.put("Quantity 21", uQuantity21);
        doc.put("Quantity 22", uQuantity22);
        doc.put("Quantity 23", uQuantity23);
        doc.put("Quantity 24", uQuantity24);
        doc.put("Quantity 25", uQuantity25);
        doc.put("Quantity 26", uQuantity26);
        doc.put("Quantity 27", uQuantity27);
        doc.put("Quantity 28", uQuantity28);
        doc.put("Quantity 29", uQuantity29);
        doc.put("Quantity 30", uQuantity30);
        //Unit
        doc.put("Unit 1", uUnit1);
        doc.put("Unit 2", uUnit2);
        doc.put("Unit 3", uUnit3);
        doc.put("Unit 4", uUnit4);
        doc.put("Unit 5", uUnit5);
        doc.put("Unit 6", uUnit6);
        doc.put("Unit 7", uUnit7);
        doc.put("Unit 8", uUnit8);
        doc.put("Unit 9", uUnit9);
        doc.put("Unit 10", uUnit10);
        doc.put("Unit 11", uUnit11);
        doc.put("Unit 12", uUnit12);
        doc.put("Unit 13", uUnit13);
        doc.put("Unit 14", uUnit14);
        doc.put("Unit 15", uUnit15);
        doc.put("Unit 16", uUnit16);
        doc.put("Unit 17", uUnit17);
        doc.put("Unit 18", uUnit18);
        doc.put("Unit 19", uUnit19);
        doc.put("Unit 20", uUnit20);

        doc.put("Unit 21", uUnit21);
        doc.put("Unit 22", uUnit22);
        doc.put("Unit 23", uUnit23);
        doc.put("Unit 24", uUnit24);
        doc.put("Unit 25", uUnit25);
        doc.put("Unit 26", uUnit26);
        doc.put("Unit 27", uUnit27);
        doc.put("Unit 28", uUnit28);
        doc.put("Unit 29", uUnit29);
        doc.put("Unit 30", uUnit30);

        doc.put("Material Receiver Name", materialReceiver);

        doc.put("Center",centerS);
        doc.put("Village",villageS);
        doc.put("SearchCenter",centerS.toLowerCase());
        doc.put("SearchVillage",villageS.toLowerCase());

        fStore.collection(cmp + " AddReturnData").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Data Added !!", Toast.LENGTH_SHORT).show();
                addDataLL.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                animation.setVisibility(View.VISIBLE);
                lastLL.setVisibility(View.GONE);
                mediaPlayer.start();

                if (work.equals("Return")) {
                    ReturnUpdateMaterial(cmp, uDate, uTeamName, uLine, uTender, uDriverName, uVehicalName, uConsumerName, uSite, uMaterial1, uQuantity1, uMaterial2, uQuantity2, uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5, uQuantity5,
                            uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10
                            , uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15,
                            uMaterial16, uQuantity16, uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20
                            , uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10
                            , uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11
                            , uMaterial21, uQuantity21, uMaterial22, uQuantity22, uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25,
                            uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28, uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30
                            , uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30, materialReceiver, centerS, villageS);
                }
                if (work.equals("Add")){
                    returnMaterial(cmp, uDate, uTeamName, uLine, uTender, uDriverName, uVehicalName, uConsumerName, uSite, uMaterial1, uQuantity1, uMaterial2, uQuantity2, uMaterial3, uQuantity3, uMaterial4, uQuantity4, uMaterial5, uQuantity5,
                            uMaterial6, uQuantity6, uMaterial7, uQuantity7, uMaterial8, uQuantity8, uMaterial9, uQuantity9, uMaterial10, uQuantity10
                            , uQuantity11, uMaterial12, uQuantity12, uMaterial13, uQuantity13, uMaterial14, uQuantity14, uMaterial15, uQuantity15,
                            uMaterial16, uQuantity16, uMaterial17, uQuantity17, uMaterial18, uQuantity18, uMaterial19, uQuantity19, uMaterial20, uQuantity20
                            , uUnit1, uUnit2, uUnit3, uUnit4, uUnit5, uUnit6, uUnit7, uUnit8, uUnit9, uUnit10
                            , uUnit11, uUnit12, uUnit13, uUnit14, uUnit15, uUnit16, uUnit17, uUnit18, uUnit19, uUnit20, uMaterial11
                            , uMaterial21, uQuantity21, uMaterial22, uQuantity22, uMaterial23, uQuantity23, uMaterial24, uQuantity24, uMaterial25, uQuantity25,
                            uMaterial26, uQuantity26, uMaterial27, uQuantity27, uMaterial28, uQuantity28, uMaterial29, uQuantity29, uMaterial30, uQuantity30
                            , uUnit21, uUnit22, uUnit23, uUnit24, uUnit25, uUnit26, uUnit27, uUnit28, uUnit29, uUnit30, materialReceiver, centerS, villageS);

                }

                stock(cmp,uMaterial1,uQuantity1,uMaterial2,uQuantity2,uMaterial3,uQuantity3,uMaterial4,uQuantity4,uMaterial5,uQuantity5,
                        uMaterial6,uQuantity6,uMaterial7,uQuantity7,uMaterial8,uQuantity8,uMaterial9,uQuantity9,uMaterial10,uQuantity10
                        ,uQuantity11,uMaterial12,uQuantity12,uMaterial13,uQuantity13,uMaterial14,uQuantity14,uMaterial15,uQuantity15,
                        uMaterial16,uQuantity16,uMaterial17,uQuantity17,uMaterial18,uQuantity18,uMaterial19,uQuantity19,uMaterial20,uQuantity20
                        ,uUnit1,uUnit2,uUnit3,uUnit4,uUnit5,uUnit6,uUnit7,uUnit8,uUnit9,uUnit10
                        ,uUnit11,uUnit12,uUnit13,uUnit14,uUnit15,uUnit16,uUnit17,uUnit18,uUnit19,uUnit20,uMaterial11
                        ,uMaterial21,uQuantity21,uMaterial22,uQuantity22,uMaterial23,uQuantity23,uMaterial24,uQuantity24,uMaterial25,uQuantity25,
                        uMaterial26,uQuantity26,uMaterial27,uQuantity27,uMaterial28,uQuantity28,uMaterial29,uQuantity29,uMaterial30,uQuantity30
                        ,uUnit21,uUnit22,uUnit23,uUnit24,uUnit25,uUnit26,uUnit27,uUnit28,uUnit29,uUnit30);

            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is added Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        addDataLL.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                });


    }

    private void ReturnUpdateMaterial(String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uMQuantity1, String uMaterial2, String uMQuantity2, String uMaterial3, String uMQuantity3, String uMaterial4, String uMQuantity4, String uMaterial5, String uMQuantity5, String uMaterial6, String uMQuantity6, String uMaterial7, String uMQuantity7, String uMaterial8, String uMQuantity8, String uMaterial9, String uMQuantity9, String uMaterial10, String uMQuantity10, String uMQuantity11, String uMaterial12, String uMQuantity12, String uMaterial13, String uMQuantity13, String uMaterial14, String uMQuantity14, String uMaterial15, String uMQuantity15, String uMaterial16, String uMQuantity16, String uMaterial17, String uMQuantity17, String uMaterial18, String uMQuantity18, String uMaterial19, String uMQuantity19, String uMaterial20, String uMQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uMQuantity21, String uMaterial22, String uMQuantity22, String uMaterial23, String uMQuantity23, String uMaterial24, String uMQuantity24, String uMaterial25, String uMQuantity25, String uMaterial26, String uMQuantity26, String uMaterial27, String uMQuantity27, String uMaterial28, String uMQuantity28, String uMaterial29, String uMQuantity29, String uMaterial30, String uMQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver,String centerS,String villageS) {
        addDataLL.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Map<String, Object> doc = new HashMap<>();

        DecimalFormat dec = new DecimalFormat();
        dec.setMaximumFractionDigits(2);

        DocumentReference documentReference = fStore.collection(cmp+" AddData")
                .document(id);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                final String q = "0";

                String Quantity1 = value.getString("Quantity 1");
                String Quantity2 = value.getString("Quantity 2");
                String Quantity3 = value.getString("Quantity 3");
                String Quantity4 = value.getString("Quantity 4");
                String Quantity5 = value.getString("Quantity 5");
                String Quantity6 = value.getString("Quantity 6");
                String Quantity7 = value.getString("Quantity 7");
                String Quantity8 = value.getString("Quantity 8");
                String Quantity9 = value.getString("Quantity 9");
                String Quantity10 = value.getString("Quantity 10");
                String Quantity11 = value.getString("Quantity 11");
                String Quantity12 = value.getString("Quantity 12");
                String Quantity13 = value.getString("Quantity 13");
                String Quantity14 = value.getString("Quantity 14");
                String Quantity15 = value.getString("Quantity 15");
                String Quantity16 = value.getString("Quantity 16");
                String Quantity17 = value.getString("Quantity 17");
                String Quantity18 = value.getString("Quantity 18");
                String Quantity19 = value.getString("Quantity 19");
                String Quantity20 = value.getString("Quantity 20");
                String Quantity21 = value.getString("Quantity 21");
                String Quantity22 = value.getString("Quantity 22");
                String Quantity23 = value.getString("Quantity 23");
                String Quantity24 = value.getString("Quantity 24");
                String Quantity25 = value.getString("Quantity 25");
                String Quantity26 = value.getString("Quantity 26");
                String Quantity27 = value.getString("Quantity 27");
                String Quantity28 = value.getString("Quantity 28");
                String Quantity29 = value.getString("Quantity 29");
                String Quantity30 = value.getString("Quantity 30");


                if (Quantity1.isEmpty()){
                    Quantity1 = q;
                }
                if (Quantity2.isEmpty()){
                    Quantity2 = q;
                }
                if (Quantity3.isEmpty()){
                    Quantity3 = q;
                }
                if (Quantity4.isEmpty()){
                    Quantity4 = q;
                }
                if (Quantity5.isEmpty()){
                    Quantity5 = q;
                }
                if (Quantity6.isEmpty()){
                    Quantity6 = q;
                }
                if (Quantity7.isEmpty()){
                    Quantity7 = q;
                }
                if ( Quantity8.isEmpty()){
                    Quantity8 = q;
                }
                if ( Quantity9.isEmpty()){
                    Quantity9 = q;
                }
                if (Quantity10.isEmpty()){
                    Quantity10 = q;
                }
                if ( Quantity11.isEmpty()){
                    Quantity11 = q;
                }
                if ( Quantity12.isEmpty()){
                    Quantity12 = q;
                }
                if (Quantity13.isEmpty()){
                    Quantity13 = q;
                }
                if ( Quantity14.isEmpty()){
                    Quantity14 = q;
                }
                if ( Quantity15.isEmpty()){
                    Quantity15 = q;
                }
                if (Quantity16.isEmpty()){
                    Quantity16 = q;
                }
                if ( Quantity17.isEmpty()){
                    Quantity17 = q;
                }
                if ( Quantity18.isEmpty()){
                    Quantity18 = q;
                }
                if ( Quantity19.isEmpty()){
                    Quantity19 = q;
                }
                if (Quantity20.isEmpty()){
                    Quantity20 = q;
                }
                if ( Quantity21.isEmpty()){
                    Quantity21 = q;
                }
                if (Quantity22.isEmpty()){
                    Quantity22 = q;
                }
                if ( Quantity23.isEmpty()){
                    Quantity23 = q;
                }
                if ( Quantity24.isEmpty()){
                    Quantity24 = q;
                }
                if (Quantity25.isEmpty()){
                    Quantity25 = q;
                }
                if ( Quantity26.isEmpty()){
                    Quantity26 = q;
                }
                if ( Quantity27.isEmpty()){
                    Quantity27 = q;
                }
                if (Quantity28.isEmpty()){
                    Quantity28 = q;
                }
                if ( Quantity29.isEmpty()){
                    Quantity29 = q;
                }
                if ( Quantity30.isEmpty()){
                    Quantity30 = q;
                }


                if (uQuantity1.isEmpty()) {
                    uQuantity1 = q;
                }
                if (uQuantity2.isEmpty()) {
                    uQuantity2 = q;
                }
                if (uQuantity3.isEmpty()) {
                    uQuantity3 = q;
                }
                if (uQuantity4.isEmpty()) {
                    uQuantity4 = q;
                }
                if (uQuantity5.isEmpty()) {
                    uQuantity5 = q;
                }
                if (uQuantity6.isEmpty()) {
                    uQuantity6 = q;
                }
                if (uQuantity7.isEmpty()) {
                    uQuantity7 = q;
                }
                if (uQuantity8.isEmpty()) {
                    uQuantity8 = q;
                }
                if (uQuantity9.isEmpty()) {
                    uQuantity9 = q;
                }
                if (uQuantity10.isEmpty()) {
                    uQuantity10 = q;
                }
                if (uQuantity11.isEmpty()) {
                    uQuantity11 = q;
                }
                if (uQuantity12.isEmpty()) {
                    uQuantity12 = q;
                }
                if (uQuantity13.isEmpty()) {
                    uQuantity13 = q;
                }
                if (uQuantity14.isEmpty()) {
                    uQuantity14 = q;
                }
                if (uQuantity15.isEmpty()) {
                    uQuantity15 = q;
                }
                if (uQuantity16.isEmpty()) {
                    uQuantity16 = q;
                }
                if (uQuantity17.isEmpty()) {
                    uQuantity17 = q;
                }
                if (uQuantity18.isEmpty()) {
                    uQuantity18 = q;
                }
                if (uQuantity19.isEmpty()) {
                    uQuantity19 = q;
                }
                if (uQuantity20.isEmpty()) {
                    uQuantity20 = q;
                }

                if (uQuantity21.isEmpty()) {
                    uQuantity21 = q;
                }
                if (uQuantity22.isEmpty()) {
                    uQuantity22 = q;
                }
                if (uQuantity23.isEmpty()) {
                    uQuantity23 = q;
                }
                if (uQuantity24.isEmpty()) {
                    uQuantity24 = q;
                }
                if (uQuantity25.isEmpty()) {
                    uQuantity25 = q;
                }
                if (uQuantity26.isEmpty()) {
                    uQuantity26 = q;
                }
                if (uQuantity27.isEmpty()) {
                    uQuantity27 = q;
                }
                if (uQuantity28.isEmpty()) {
                    uQuantity28 = q;
                }
                if (uQuantity29.isEmpty()) {
                    uQuantity29 = q;
                }
                if (uQuantity30.isEmpty()) {
                    uQuantity30 = q;
                }



                String q1 = dec.format( Float.parseFloat(Quantity1) - Float.parseFloat(uQuantity1) );
                String q2 = dec.format( Float.parseFloat(Quantity2) - Float.parseFloat(uQuantity2) );
                String q3 = dec.format( Float.parseFloat(Quantity3) - Float.parseFloat(uQuantity3) );
                String q4 = dec.format( Float.parseFloat(Quantity4) - Float.parseFloat(uQuantity4) );
                String q5 = dec.format( Float.parseFloat(Quantity5) - Float.parseFloat(uQuantity5) );
                String q6 = dec.format( Float.parseFloat(Quantity6) - Float.parseFloat(uQuantity6) );
                String q7 = dec.format( Float.parseFloat(Quantity7) - Float.parseFloat(uQuantity7) );
                String q8 = dec.format( Float.parseFloat(Quantity8) - Float.parseFloat(uQuantity8) );
                String q9 = dec.format( Float.parseFloat(Quantity9) - Float.parseFloat(uQuantity9) );
                String q10 = dec.format( Float.parseFloat(Quantity10) - Float.parseFloat(uQuantity10) );
                String q11 = dec.format( Float.parseFloat(Quantity11) - Float.parseFloat(uQuantity11) );
                String q12 = dec.format( Float.parseFloat(Quantity12) - Float.parseFloat(uQuantity12) );
                String q13 = dec.format( Float.parseFloat(Quantity13) - Float.parseFloat(uQuantity13) );
                String q14 = dec.format( Float.parseFloat(Quantity14) - Float.parseFloat(uQuantity14) );
                String q15 = dec.format( Float.parseFloat(Quantity15) - Float.parseFloat(uQuantity15) );
                String q16 = dec.format( Float.parseFloat(Quantity16) - Float.parseFloat(uQuantity16) );
                String q17 = dec.format( Float.parseFloat(Quantity17) - Float.parseFloat(uQuantity17) );
                String q18 = dec.format( Float.parseFloat(Quantity18) - Float.parseFloat(uQuantity18) );
                String q19 = dec.format( Float.parseFloat(Quantity19) - Float.parseFloat(uQuantity19) );
                String q20 = dec.format( Float.parseFloat(Quantity20) - Float.parseFloat(uQuantity20) );
                String q21 = dec.format( Float.parseFloat(Quantity21) - Float.parseFloat(uQuantity21) );
                String q22 = dec.format( Float.parseFloat(Quantity22) - Float.parseFloat(uQuantity22) );
                String q23 = dec.format( Float.parseFloat(Quantity23) - Float.parseFloat(uQuantity23) );
                String q24 = dec.format( Float.parseFloat(Quantity24) - Float.parseFloat(uQuantity24) );
                String q25 = dec.format( Float.parseFloat(Quantity25) - Float.parseFloat(uQuantity25) );
                String q26 = dec.format( Float.parseFloat(Quantity26) - Float.parseFloat(uQuantity26) );
                String q27 = dec.format( Float.parseFloat(Quantity27) - Float.parseFloat(uQuantity27) );
                String q28 = dec.format( Float.parseFloat(Quantity28) - Float.parseFloat(uQuantity28) );
                String q29 = dec.format( Float.parseFloat(Quantity29) - Float.parseFloat(uQuantity29) );
                String q30 = dec.format( Float.parseFloat(Quantity30) - Float.parseFloat(uQuantity30) );



                if (q1.equals("0")) {
                    q1 = "";
                }
                if (q2.equals("0")) {
                    q2 = "";
                }
                if (q3.equals("0")) {
                    q3 = "";
                }
                if (q4.equals("0")) {
                    q4 = "";
                }
                if (q5.equals("0")) {
                    q5 = "";
                }
                if (q6.equals("0")) {
                    q6 = "";
                }
                if (q7.equals("0")) {
                    q7 = "";
                }
                if (q8.equals("0")) {
                    q8 = "";
                }
                if (q9.equals("0")) {
                    q9 = "";
                }
                if (q10.equals("0")) {
                    q10 = "";
                }
                if (q11.equals("0")) {
                    q11 = "";
                }
                if (q12.equals("0")) {
                    q12 = "";
                }
                if (q13.equals("0")) {
                    q13 = "";
                }
                if (q14.equals("0")) {
                    q14 = "";
                }
                if (q15.equals("0")) {
                    q15 = "";
                }
                if (q16.equals("0")) {
                    q16 = "";
                }
                if (q17.equals("0")) {
                    q17 = "";
                }
                if (q18.equals("0")) {
                    q18 = "";
                }
                if (q19.equals("0")) {
                    q19 = "";
                }
                if (q20.equals("0")) {
                    q20 = "";
                }
                if (q21.equals("0")) {
                    q21 = "";
                }
                if (q22.equals("0")) {
                    q22 = "";
                }
                if (q23.equals("0")) {
                    q23 = "";
                }
                if (q24.equals("0")) {
                    q24 = "";
                }
                if (q25.equals("0")) {
                    q25 = "";
                }
                if (q26.equals("0")) {
                    q26 = "";
                }
                if (q27.equals("0")) {
                    q27 = "";
                }
                if (q28.equals("0")) {
                    q28 = "";
                }
                if (q29.equals("0")) {
                    q29 = "";
                }
                if (q30.equals("0")) {
                    q30 = "";
                }

                doc.put("id", id);
                doc.put("Date", uDate);
                doc.put("SearchDate", uDate.toLowerCase());
                doc.put("Team Name", uTeamName);
                doc.put("SearchTeamName", uTeamName.toLowerCase());
                doc.put("Line", uLine);
                doc.put("Tender", uTender);
                doc.put("SearchTender", uTender.toLowerCase());
                doc.put("Driver Name", uDriverName);
                doc.put("Vehical Name", uVehicalName);
                doc.put("Consumer Name", uConsumerName);
                doc.put("Site Name", uSite);
                //Material
                doc.put("Material 1", uMaterial1);
                doc.put("Material 2", uMaterial2);
                doc.put("Material 3", uMaterial3);
                doc.put("Material 4", uMaterial4);
                doc.put("Material 5", uMaterial5);
                doc.put("Material 6", uMaterial6);
                doc.put("Material 7", uMaterial7);
                doc.put("Material 8", uMaterial8);
                doc.put("Material 9", uMaterial9);
                doc.put("Material 10", uMaterial10);
                doc.put("Material 11", uMaterial11);
                doc.put("Material 12", uMaterial12);
                doc.put("Material 13", uMaterial13);
                doc.put("Material 14", uMaterial14);
                doc.put("Material 15", uMaterial15);
                doc.put("Material 16", uMaterial16);
                doc.put("Material 17", uMaterial17);
                doc.put("Material 18", uMaterial18);
                doc.put("Material 19", uMaterial19);
                doc.put("Material 20", uMaterial20);
                doc.put("Material 21", uMaterial21);
                doc.put("Material 22", uMaterial22);
                doc.put("Material 23", uMaterial23);
                doc.put("Material 24", uMaterial24);
                doc.put("Material 25", uMaterial25);
                doc.put("Material 26", uMaterial26);
                doc.put("Material 27", uMaterial27);
                doc.put("Material 28", uMaterial28);
                doc.put("Material 29", uMaterial29);
                doc.put("Material 30", uMaterial30);
                //Quantity
                doc.put("Quantity 1", q1);
                doc.put("Quantity 2", q2);
                doc.put("Quantity 3", q3);
                doc.put("Quantity 4", q4);
                doc.put("Quantity 5", q5);
                doc.put("Quantity 6", q6);
                doc.put("Quantity 7", q7);
                doc.put("Quantity 8", q8);
                doc.put("Quantity 9", q9);
                doc.put("Quantity 10", q10);
                doc.put("Quantity 11", q11);
                doc.put("Quantity 12", q12);
                doc.put("Quantity 13", q13);
                doc.put("Quantity 14", q14);
                doc.put("Quantity 15", q15);
                doc.put("Quantity 16", q16);
                doc.put("Quantity 17", q17);
                doc.put("Quantity 18", q18);
                doc.put("Quantity 19", q19);
                doc.put("Quantity 20", q20);
                doc.put("Quantity 21", q21);
                doc.put("Quantity 22", q22);
                doc.put("Quantity 23", q23);
                doc.put("Quantity 24", q24);
                doc.put("Quantity 25", q25);
                doc.put("Quantity 26", q26);
                doc.put("Quantity 27", q27);
                doc.put("Quantity 28", q28);
                doc.put("Quantity 29", q29);
                doc.put("Quantity 30", q30);
                //Unit
                doc.put("Unit 1", uUnit1);
                doc.put("Unit 2", uUnit2);
                doc.put("Unit 3", uUnit3);
                doc.put("Unit 4", uUnit4);
                doc.put("Unit 5", uUnit5);
                doc.put("Unit 6", uUnit6);
                doc.put("Unit 7", uUnit7);
                doc.put("Unit 8", uUnit8);
                doc.put("Unit 9", uUnit9);
                doc.put("Unit 10", uUnit10);
                doc.put("Unit 11", uUnit11);
                doc.put("Unit 12", uUnit12);
                doc.put("Unit 13", uUnit13);
                doc.put("Unit 14", uUnit14);
                doc.put("Unit 15", uUnit15);
                doc.put("Unit 16", uUnit16);
                doc.put("Unit 17", uUnit17);
                doc.put("Unit 18", uUnit18);
                doc.put("Unit 19", uUnit19);
                doc.put("Unit 20", uUnit20);
                doc.put("Unit 21", uUnit21);
                doc.put("Unit 22", uUnit22);
                doc.put("Unit 23", uUnit23);
                doc.put("Unit 24", uUnit24);
                doc.put("Unit 25", uUnit25);
                doc.put("Unit 26", uUnit26);
                doc.put("Unit 27", uUnit27);
                doc.put("Unit 28", uUnit28);
                doc.put("Unit 29", uUnit29);
                doc.put("Unit 30", uUnit30);

                doc.put("Material Receiver Name", materialReceiver);
                doc.put("Center", centerS);
                doc.put("Village", villageS);
                doc.put("SearchCenter", centerS.toLowerCase());
                doc.put("SearchVillage", villageS.toLowerCase());

                fStore.collection(cmp + " RemainingMaterial").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added Successfully
                        Toast.makeText(getApplicationContext(), "Data Added !!", Toast.LENGTH_SHORT).show();
                        addDataLL.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        animation.setVisibility(View.VISIBLE);
                        lastLL.setVisibility(View.GONE);
                        mediaPlayer.start();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when data is added Failed
                        Toast.makeText(getApplicationContext(), "Failed to add data " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        addDataLL.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                });


//                return false;
            }
        });
    }



    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void retrieveDataReceiver() {
        listenerReceiver = databaseReferenceReceiver.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    spinnerDataListReceiver.add(item.getValue().toString());
                }
                adapterReceiver.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void returnMaterial(String cmp, String uDate, String uTeamName, String uLine, String uTender, String uDriverName, String uVehicalName, String uConsumerName, String uSite, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30, String materialReceiver,String centerS,String villageS) {
        addDataLL.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Map<String, Object> doc = new HashMap<>();

        if (uQuantity1.equals("")||quantity1S.equals("")){
            uQuantity1 = "0";
            quantity1S = "0";
        }
        if (uQuantity2.equals("")||quantity2S.equals("")){
            uQuantity2 = "0";
            quantity2S = "0";
        }
        if (uQuantity3.equals("")||quantity3S.equals("")){
            uQuantity3 = "0";
            quantity3S = "0";
        }
        if (uQuantity4.equals("")||quantity4S.equals("")){
            uQuantity4 = "0";
            quantity4S = "0";
        }
        if (uQuantity5.equals("")||quantity5S.equals("")){
            uQuantity5 = "0";
            quantity5S = "0";
        }
        if (uQuantity6.equals("")||quantity6S.equals("")){
            uQuantity6 = "0";
            quantity6S = "0";
        }
        if (uQuantity7.equals("")||quantity7S.equals("")){
            uQuantity7 = "0";
            quantity7S = "0";
        }
        if (uQuantity8.equals("")||quantity8S.equals("")){
            uQuantity8 = "0";
            quantity8S = "0";
        }
        if (uQuantity9.equals("")||quantity9S.equals("")){
            uQuantity9 = "0";
            quantity9S = "0";
        }
        if (uQuantity10.equals("")||quantity10S.equals("")){
            uQuantity10 = "0";
            quantity10S = "0";
        }
        if (uQuantity11.equals("")||quantity11S.equals("")){
            uQuantity11 = "0";
            quantity11S = "0";
        }
        if (uQuantity12.equals("")||quantity12S.equals("")){
            uQuantity12 = "0";
            quantity12S = "0";
        }
        if (uQuantity13.equals("")||quantity13S.equals("")){
            uQuantity13 = "0";
            quantity13S = "0";
        }
        if (uQuantity14.equals("")||quantity14S.equals("")){
            uQuantity14 = "0";
            quantity14S = "0";
        }
        if (uQuantity15.equals("")||quantity15S.equals("")){
            uQuantity15 = "0";
            quantity15S = "0";
        }
        if (uQuantity16.equals("")||quantity16S.equals("")){
            uQuantity16 = "0";
            quantity16S = "0";
        }
        if (uQuantity17.equals("")||quantity17S.equals("")){
            uQuantity17 = "0";
            quantity17S = "0";
        }
        if (uQuantity18.equals("")||quantity18S.equals("")){
            uQuantity18 = "0";
            quantity18S = "0";
        }
        if (uQuantity19.equals("")||quantity19S.equals("")){
            uQuantity19 = "0";
            quantity19S = "0";
        }
        if (uQuantity20.equals("")||quantity20S.equals("")){
            uQuantity20 = "0";
            quantity20S = "0";
        }
        if (uQuantity21.equals("")||quantity21S.equals("")){
            uQuantity21 = "0";
            quantity21S = "0";
        }
        if (uQuantity22.equals("")||quantity22S.equals("")){
            uQuantity22 = "0";
            quantity22S = "0";
        }
        if (uQuantity23.equals("")||quantity23S.equals("")){
            uQuantity23 = "0";
            quantity23S = "0";
        }
        if (uQuantity24.equals("")||quantity24S.equals("")){
            uQuantity24 = "0";
            quantity24S = "0";
        }
        if (uQuantity25.equals("")||quantity25S.equals("")){
            uQuantity25 = "0";
            quantity25S = "0";
        }
        if (uQuantity26.equals("")||quantity26S.equals("")){
            uQuantity26 = "0";
            quantity26S = "0";
        }
        if (uQuantity27.equals("")||quantity27S.equals("")){
            uQuantity27 = "0";
            quantity27S = "0";
        }
        if (uQuantity28.equals("")||quantity28S.equals("")){
            uQuantity28 = "0";
            quantity28S = "0";
        }
        if (uQuantity29.equals("")||quantity29S.equals("")){
            uQuantity29 = "0";
            quantity29S = "0";
        }
        if (uQuantity30.equals("")||quantity30S.equals("")){
            uQuantity30 = "0";
            quantity30S = "0";
        }

        DecimalFormat dec = new DecimalFormat();
        dec.setMaximumFractionDigits(2);

        String q1 = dec.format(Float.parseFloat(quantity1S) - Float.parseFloat(uQuantity1));
        String q2 = dec.format(Float.parseFloat(quantity2S) - Float.parseFloat(uQuantity2));
        String q3 = dec.format(Float.parseFloat(quantity3S) - Float.parseFloat(uQuantity3));
        String q4 = dec.format(Float.parseFloat(quantity4S) - Float.parseFloat(uQuantity4));
        String q5 = dec.format(Float.parseFloat(quantity5S) - Float.parseFloat(uQuantity5));
        String q6 = dec.format(Float.parseFloat(quantity6S) - Float.parseFloat(uQuantity6));
        String q7 = dec.format(Float.parseFloat(quantity7S) - Float.parseFloat(uQuantity7));
        String q8 = dec.format(Float.parseFloat(quantity8S) - Float.parseFloat(uQuantity8));
        String q9 = dec.format(Float.parseFloat(quantity9S) - Float.parseFloat(uQuantity9));
        String q10 = dec.format(Float.parseFloat(quantity10S) - Float.parseFloat(uQuantity10));
        String q11 = dec.format(Float.parseFloat(quantity11S) - Float.parseFloat(uQuantity11));
        String q12 = dec.format(Float.parseFloat(quantity12S) - Float.parseFloat(uQuantity12));
        String q13 = dec.format(Float.parseFloat(quantity13S) - Float.parseFloat(uQuantity13));
        String q14 = dec.format(Float.parseFloat(quantity14S) - Float.parseFloat(uQuantity14));
        String q15 = dec.format(Float.parseFloat(quantity15S) - Float.parseFloat(uQuantity15));
        String q16 = dec.format(Float.parseFloat(quantity16S) - Float.parseFloat(uQuantity16));
        String q17 = dec.format(Float.parseFloat(quantity17S) - Float.parseFloat(uQuantity17));
        String q18 = dec.format(Float.parseFloat(quantity18S) - Float.parseFloat(uQuantity18));
        String q19 = dec.format(Float.parseFloat(quantity19S) - Float.parseFloat(uQuantity19));
        String q20 = dec.format(Float.parseFloat(quantity20S) - Float.parseFloat(uQuantity20));
        String q21 = dec.format(Float.parseFloat(quantity21S) - Float.parseFloat(uQuantity21));
        String q22 = dec.format(Float.parseFloat(quantity22S) - Float.parseFloat(uQuantity22));
        String q23 = dec.format(Float.parseFloat(quantity23S) - Float.parseFloat(uQuantity23));
        String q24 = dec.format(Float.parseFloat(quantity24S) - Float.parseFloat(uQuantity24));
        String q25 = dec.format(Float.parseFloat(quantity25S) - Float.parseFloat(uQuantity25));
        String q26 = dec.format(Float.parseFloat(quantity26S) - Float.parseFloat(uQuantity26));
        String q27 = dec.format(Float.parseFloat(quantity27S) - Float.parseFloat(uQuantity27));
        String q28 = dec.format(Float.parseFloat(quantity28S) - Float.parseFloat(uQuantity28));
        String q29 = dec.format(Float.parseFloat(quantity29S) - Float.parseFloat(uQuantity29));
        String q30 = dec.format(Float.parseFloat(quantity30S) - Float.parseFloat(uQuantity30));

        if (q1.equals("0")){
            q1 = "";
        }
        if (q2.equals("0")){
            q2 = "";
        }
        if (q3.equals("0")){
            q3 = "";
        }
        if (q4.equals("0")){
            q4 = "";
        }
        if (q5.equals("0")){
            q5 = "";
        }
        if (q6.equals("0")){
            q6 = "";
        }
        if (q7.equals("0")){
            q7 = "";
        }
        if (q8.equals("0")){
            q8 = "";
        }
        if (q9.equals("0")){
            q9 = "";
        }
        if (q10.equals("0")){
            q10 = "";
        }
        if (q11.equals("0")){
            q11 = "";
        }
        if (q12.equals("0")){
            q12 = "";
        }
        if (q13.equals("0")){
            q13 = "";
        }
        if (q14.equals("0")){
            q14 = "";
        }
        if (q15.equals("0")){
            q15 = "";
        }
        if (q16.equals("0")){
            q16 = "";
        }
        if (q17.equals("0")){
            q17 = "";
        }
        if (q18.equals("0")){
            q18 = "";
        }
        if (q19.equals("0")){
            q19 = "";
        }
        if (q20.equals("0")){
            q20 = "";
        }
        if (q21.equals("0")){
            q21 = "";
        }
        if (q22.equals("0")){
            q22 = "";
        }
        if (q23.equals("0")){
            q23 = "";
        }
        if (q24.equals("0")){
            q24 = "";
        }
        if (q25.equals("0")){
            q25 = "";
        }
        if (q26.equals("0")){
            q26 = "";
        }
        if (q27.equals("0")){
            q27 = "";
        }
        if (q28.equals("0")){
            q28 = "";
        }
        if (q29.equals("0")){
            q29 = "";
        }
        if (q30.equals("0")){
            q30 = "";
        }

        doc.put("id", id);
        doc.put("Date",uDate);
        doc.put("SearchDate",uDate.toLowerCase());
        doc.put("Team Name",uTeamName);
        doc.put("SearchTeamName",uTeamName.toLowerCase());
        doc.put("Line",uLine);
        doc.put("Tender",uTender);
        doc.put("SearchTender",uTender.toLowerCase());
        doc.put("Driver Name",uDriverName);
        doc.put("Vehical Name",uVehicalName);
        doc.put("Consumer Name",uConsumerName);
        doc.put("Site Name",uSite);
        //Material
        doc.put("Material 1",uMaterial1);
        doc.put("Material 2",uMaterial2);
        doc.put("Material 3",uMaterial3);
        doc.put("Material 4",uMaterial4);
        doc.put("Material 5",uMaterial5);
        doc.put("Material 6",uMaterial6);
        doc.put("Material 7",uMaterial7);
        doc.put("Material 8",uMaterial8);
        doc.put("Material 9",uMaterial9);
        doc.put("Material 10",uMaterial10);
        doc.put("Material 11",uMaterial11);
        doc.put("Material 12",uMaterial12);
        doc.put("Material 13",uMaterial13);
        doc.put("Material 14",uMaterial14);
        doc.put("Material 15",uMaterial15);
        doc.put("Material 16",uMaterial16);
        doc.put("Material 17",uMaterial17);
        doc.put("Material 18",uMaterial18);
        doc.put("Material 19",uMaterial19);
        doc.put("Material 20",uMaterial20);
        doc.put("Material 21",uMaterial21);
        doc.put("Material 22",uMaterial22);
        doc.put("Material 23",uMaterial23);
        doc.put("Material 24",uMaterial24);
        doc.put("Material 25",uMaterial25);
        doc.put("Material 26",uMaterial26);
        doc.put("Material 27",uMaterial27);
        doc.put("Material 28",uMaterial28);
        doc.put("Material 29",uMaterial29);
        doc.put("Material 30",uMaterial30);
        //Quantity
        doc.put("Quantity 1",q1);
        doc.put("Quantity 2",q2);
        doc.put("Quantity 3",q3);
        doc.put("Quantity 4",q4);
        doc.put("Quantity 5",q5);
        doc.put("Quantity 6",q6);
        doc.put("Quantity 7",q7);
        doc.put("Quantity 8",q8);
        doc.put("Quantity 9",q9);
        doc.put("Quantity 10",q10);
        doc.put("Quantity 11",q11);
        doc.put("Quantity 12",q12);
        doc.put("Quantity 13",q13);
        doc.put("Quantity 14",q14);
        doc.put("Quantity 15",q15);
        doc.put("Quantity 16",q16);
        doc.put("Quantity 17",q17);
        doc.put("Quantity 18",q18);
        doc.put("Quantity 19",q19);
        doc.put("Quantity 20",q20);
        doc.put("Quantity 21",q21);
        doc.put("Quantity 22",q22);
        doc.put("Quantity 23",q23);
        doc.put("Quantity 24",q24);
        doc.put("Quantity 25",q25);
        doc.put("Quantity 26",q26);
        doc.put("Quantity 27",q27);
        doc.put("Quantity 28",q28);
        doc.put("Quantity 29",q29);
        doc.put("Quantity 30",q30);
        //Unit
        doc.put("Unit 1",uUnit1);
        doc.put("Unit 2",uUnit2);
        doc.put("Unit 3",uUnit3);
        doc.put("Unit 4",uUnit4);
        doc.put("Unit 5",uUnit5);
        doc.put("Unit 6",uUnit6);
        doc.put("Unit 7",uUnit7);
        doc.put("Unit 8",uUnit8);
        doc.put("Unit 9",uUnit9);
        doc.put("Unit 10",uUnit10);
        doc.put("Unit 11",uUnit11);
        doc.put("Unit 12",uUnit12);
        doc.put("Unit 13",uUnit13);
        doc.put("Unit 14",uUnit14);
        doc.put("Unit 15",uUnit15);
        doc.put("Unit 16",uUnit16);
        doc.put("Unit 17",uUnit17);
        doc.put("Unit 18",uUnit18);
        doc.put("Unit 19",uUnit19);
        doc.put("Unit 20",uUnit20);
        doc.put("Unit 21",uUnit21);
        doc.put("Unit 22",uUnit22);
        doc.put("Unit 23",uUnit23);
        doc.put("Unit 24",uUnit24);
        doc.put("Unit 25",uUnit25);
        doc.put("Unit 26",uUnit26);
        doc.put("Unit 27",uUnit27);
        doc.put("Unit 28",uUnit28);
        doc.put("Unit 29",uUnit29);
        doc.put("Unit 30",uUnit30);

        doc.put("Center",centerS);
        doc.put("Village",villageS);
        doc.put("SearchCenter",centerS.toLowerCase());
        doc.put("SearchVillage",villageS.toLowerCase());

        doc.put("Material Receiver Name",materialReceiver);

        fStore.collection(cmp+" RemainingMaterial").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Data Added !!", Toast.LENGTH_SHORT).show();
                addDataLL.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                animation.setVisibility(View.VISIBLE);
                lastLL.setVisibility(View.GONE);
                mediaPlayer.start();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();
                addDataLL.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });


    }

    private void stock(String cmp, String uMaterial1, String uQuantity1, String uMaterial2, String uQuantity2, String uMaterial3, String uQuantity3, String uMaterial4, String uQuantity4, String uMaterial5, String uQuantity5, String uMaterial6, String uQuantity6, String uMaterial7, String uQuantity7, String uMaterial8, String uQuantity8, String uMaterial9, String uQuantity9, String uMaterial10, String uQuantity10, String uQuantity11, String uMaterial12, String uQuantity12, String uMaterial13, String uQuantity13, String uMaterial14, String uQuantity14, String uMaterial15, String uQuantity15, String uMaterial16, String uQuantity16, String uMaterial17, String uQuantity17, String uMaterial18, String uQuantity18, String uMaterial19, String uQuantity19, String uMaterial20, String uQuantity20, String uUnit1, String uUnit2, String uUnit3, String uUnit4, String uUnit5, String uUnit6, String uUnit7, String uUnit8, String uUnit9, String uUnit10, String uUnit11, String uUnit12, String uUnit13, String uUnit14, String uUnit15, String uUnit16, String uUnit17, String uUnit18, String uUnit19, String uUnit20, String uMaterial11, String uMaterial21, String uQuantity21, String uMaterial22, String uQuantity22, String uMaterial23, String uQuantity23, String uMaterial24, String uQuantity24, String uMaterial25, String uQuantity25, String uMaterial26, String uQuantity26, String uMaterial27, String uQuantity27, String uMaterial28, String uQuantity28, String uMaterial29, String uQuantity29, String uMaterial30, String uQuantity30, String uUnit21, String uUnit22, String uUnit23, String uUnit24, String uUnit25, String uUnit26, String uUnit27, String uUnit28, String uUnit29, String uUnit30) {

        Bundle bundle = getIntent().getExtras();
        int p=0,q=0;

        if (bundle!=null){
            for (int i=0;i<modelList.size();i++){

                if (modelList.get(i).getMaterial().equals(uMaterial1)){
                    if (modelList.get(i).getQuantity().equals(uUnit1)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity1);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial1)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity1);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial2)){
                    if (modelList.get(i).getQuantity().equals(uUnit2)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity2);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial2)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity2);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial3)){
                    if (modelList.get(i).getQuantity().equals(uUnit3)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity3);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial3)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity3);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial4)){
                    if (modelList.get(i).getQuantity().equals(uUnit4)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity4);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial4)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity4);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial5)){
                    if (modelList.get(i).getQuantity().equals(uUnit5)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity5);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial5)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity5);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial6)){
                    if (modelList.get(i).getQuantity().equals(uUnit6)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity6);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial6)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity6);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial7)){
                    if (modelList.get(i).getQuantity().equals(uUnit7)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity7);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial7)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity7);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial8)){
                    if (modelList.get(i).getQuantity().equals(uUnit8)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity8);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial8)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity8);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial9)){
                    if (modelList.get(i).getQuantity().equals(uUnit9)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity9);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial9)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity9);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial10)){
                    if (modelList.get(i).getQuantity().equals(uUnit10)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity10);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial10)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity10);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial11)){
                    if (modelList.get(i).getQuantity().equals(uUnit11)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity11);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial11)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity11);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial12)){
                    if (modelList.get(i).getQuantity().equals(uUnit12)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity12);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial12)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity12);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial13)){
                    if (modelList.get(i).getQuantity().equals(uUnit13)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity13);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial13)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity13);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial14)){
                    if (modelList.get(i).getQuantity().equals(uUnit14)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity14);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial14)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity14);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial15)){
                    if (modelList.get(i).getQuantity().equals(uUnit15)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity15);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial15)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity15);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial16)){
                    if (modelList.get(i).getQuantity().equals(uUnit16)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity16);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial16)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity16);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial17)){
                    if (modelList.get(i).getQuantity().equals(uUnit17)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity17);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial17)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity17);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial18)){
                    if (modelList.get(i).getQuantity().equals(uUnit18)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity18);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial18)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity18);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial19)){
                    if (modelList.get(i).getQuantity().equals(uUnit19)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity19);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial19)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity19);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial20)){
                    if (modelList.get(i).getQuantity().equals(uUnit20)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity20);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial20)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity20);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial21)){
                    if (modelList.get(i).getQuantity().equals(uUnit21)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity21);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial21)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity21);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial22)){
                    if (modelList.get(i).getQuantity().equals(uUnit22)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity22);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial22)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity22);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial23)){
                    if (modelList.get(i).getQuantity().equals(uUnit23)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity23);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial23)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity23);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial24)){
                    if (modelList.get(i).getQuantity().equals(uUnit24)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity24);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial24)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity24);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial25)){
                    if (modelList.get(i).getQuantity().equals(uUnit25)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity25);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial25)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity25);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial26)){
                    if (modelList.get(i).getQuantity().equals(uUnit26)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity26);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial26)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity26);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial27)){
                    if (modelList.get(i).getQuantity().equals(uUnit27)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity27);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial27)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity27);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial28)){
                    if (modelList.get(i).getQuantity().equals(uUnit28)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity28);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial28)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity28);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial29)){
                    if (modelList.get(i).getQuantity().equals(uUnit29)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity29);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial29)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity29);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }

                if (modelList.get(i).getMaterial().equals(uMaterial30)){
                    if (modelList.get(i).getQuantity().equals(uUnit30)){
                        float a = Float.parseFloat(modelList.get(i).getUnit()) + Float.parseFloat(uQuantity30);
                        addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),a);
                        if (a<5){
                            sendMail(cmp,modelList.get(i).getMaterial());
                        }
                    }
                    else {
                        for (int j=0;j<modelListKN.size();j++){
                            if (modelListKN.get(j).getMaterial().equals(uMaterial30)){
                                float stock = Float.parseFloat(modelList.get(i).getUnit());
                                float quantity = Float.parseFloat(uQuantity30);
                                float conversion = Float.parseFloat(modelListKN.get(j).getNumber());
                                float division = (quantity / conversion);
                                float result = stock + division ;
                                addStock(cmp,modelList.get(i).getMaterial(),modelList.get(i).getQuantity(),result);
                                if (result<5){
                                    sendMail(cmp,modelList.get(i).getMaterial());
                                }
                            }

                        }
                    }

                }











            }
        }
    }


    private void sendMail(String cmp, String material) {
        String message = "You have less material quantity of " + material;
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, cmp, "You have less material quantity of "+material, message);
        javaMailAPI.execute();

    }

    private void addStock(String cmp, String uMaterial1, String uUnit1, float a) {

        String quantity = String.valueOf(a);

        Map<String, Object> doc = new HashMap<>();
        doc.put("Id",uMaterial1);
        doc.put("Material",uMaterial1);
        doc.put("Unit",uUnit1);
        doc.put("Quantity",quantity);

        fStore.collection(cmp+" AddStock").document(uMaterial1).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //this will be called when data is added Successfully
                Toast.makeText(getApplicationContext(), "Add Stock "+a, Toast.LENGTH_SHORT).show();
                animation.setVisibility(View.VISIBLE);
                mediaPlayer.start();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this will be called when data is added Failed
                Toast.makeText(getApplicationContext(), "Failed to add data "+e.getMessage(), Toast.LENGTH_SHORT).show();
                animation.setVisibility(View.GONE);
            }
        });


    }

    private void showData(String cmp) {
        fStore.collection(cmp+" AddStock").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelList.clear();
                pd.dismiss();
                for (DocumentSnapshot doc : task.getResult()){
                    AddStockModel model = new AddStockModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Unit"),
                            doc.getString("Quantity")
                    );
                    modelList.add(model);
                }
                adapterStock = new SupervisorReturnStockAdapter(SupervisorAddReturnMaterial.this,modelList);
                recyclerView.setAdapter(adapterStock);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(SupervisorAddReturnMaterial.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDataKN(String cmp) {
        fStore.collection(cmp+" KgToNo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                modelListKN.clear();
                pd.dismiss();
                for (DocumentSnapshot doc : task.getResult()){
                    KgToNoModel model = new KgToNoModel(
                            doc.getString("Id"),
                            doc.getString("Material"),
                            doc.getString("Number"),
                            doc.getString("Unit")
                    );
                    modelListKN.add(model);
                }
                adapterStockKN = new SupervisorReturnKgToNoAdapter(SupervisorAddReturnMaterial.this,modelListKN);
                recyclerViewKN.setAdapter(adapterStockKN);
            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(SupervisorAddReturnMaterial.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}


