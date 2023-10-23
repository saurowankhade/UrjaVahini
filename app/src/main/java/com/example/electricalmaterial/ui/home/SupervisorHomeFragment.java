package com.example.electricalmaterial.ui.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.electricalmaterial.AddDetails;
import com.example.electricalmaterial.AddStock;
import com.example.electricalmaterial.CalculaterInApp;
import com.example.electricalmaterial.R;
import com.example.electricalmaterial.StockEntry;
import com.example.electricalmaterial.SupervisorAddMaterial;
import com.example.electricalmaterial.SupervisorReturnViewMaterial;
import com.example.electricalmaterial.SupervisorUsedData;
import com.example.electricalmaterial.SupervisorViewMaterial;
import com.example.electricalmaterial.Survey;
import com.example.electricalmaterial.ViewWorkDone;
import com.example.electricalmaterial.databinding.FragmentSupervisorHomeBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SupervisorHomeFragment extends Fragment {

    private FragmentSupervisorHomeBinding binding;

    CardView addData,viewData,returnData,usedData,addStock,companyDetails,stockEntry,survey,calculator,workDone;


    Dialog dialog;

    TextView updateLink;
    FirebaseFirestore fStore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSupervisorHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ScrollView scrollView = binding.sl;
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        scrollView.setAnimation(animation);


        addData = binding.addData;
        viewData = binding.viewData;
        returnData = binding.returnData;
        usedData = binding.usedData;
        addStock = binding.addStock;
        stockEntry = binding.stockEntry;
        survey = binding.survey;
        calculator = binding.calculator;
        workDone = binding.workDone;

        companyDetails = binding.companyDetails;



        PackageManager pm = getContext().getPackageManager();
        String pkgName = getContext().getPackageName();
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = pm.getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = pkgInfo.versionName;

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.update_dongle);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.back_background);
        updateLink = dialog.findViewById(R.id.update);

        fStore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = fStore.collection("AppVersion")
                .document("appversion");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (!versionName.equals(value.getString("versionName"))){
                    dialog.show();
                }else {
                    dialog.dismiss();
                }
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        getActivity().finish();
                        return true;
                    }
                });


//                return false;
            }
        });

        companyDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddDetails.class));
            }
        });



        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CalculaterInApp.class));
            }
        });

        workDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ViewWorkDone.class));
            }
        });


        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Survey.class));
            }
        });


        addStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), AddStock.class));
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(getActivity(), SupervisorAddMaterial.class));
         }
     });


        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SupervisorViewMaterial.class));
            }
        });

        returnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SupervisorReturnViewMaterial.class));
            }
        });

        usedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SupervisorUsedData.class));
            }
        });

        stockEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StockEntry.class));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}