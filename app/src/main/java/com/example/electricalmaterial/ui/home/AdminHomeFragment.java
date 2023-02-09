package com.example.electricalmaterial.ui.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.electricalmaterial.AddDetails;
import com.example.electricalmaterial.AddMaterial;
import com.example.electricalmaterial.AddStock;
import com.example.electricalmaterial.AdminViewMaterial;
import com.example.electricalmaterial.CalculaterInApp;
import com.example.electricalmaterial.R;
import com.example.electricalmaterial.RemainingMaterial;
import com.example.electricalmaterial.StockEntry;
import com.example.electricalmaterial.Survey;
import com.example.electricalmaterial.ViewActualMaterial;
import com.example.electricalmaterial.ViewBalanceMaterial;
import com.example.electricalmaterial.ViewReturnMaterial;
import com.example.electricalmaterial.ViewWorkDone;
import com.example.electricalmaterial.databinding.FragmentAdminHomeBinding;
import com.google.firebase.BuildConfig;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AdminHomeFragment extends Fragment {


    private  FragmentAdminHomeBinding binding;

    CardView addData,viewData,returnData,usedData,companyDetails,addStock,stockEntry,survey,calculator,workDone,actualMaterial,balanceMaterial;

    Dialog dialog;

    TextView updateLink;
    FirebaseFirestore fStore;


    TextView popularTask,paymentTask;
    LinearLayout popularTaskLL,paymentTaskLL;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
    

        binding = FragmentAdminHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ScrollView scrollView = binding.sl;
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        scrollView.setAnimation(animation);



        addData = binding.addData;
        viewData = binding.viewData;
        returnData = binding.returnData;
        usedData = binding.usedData;
        companyDetails = binding.companyDetails;
        addStock = binding.addStock;
        stockEntry = binding.stockEntry;
        survey = binding.survey;
        calculator = binding.calculator;
        workDone = binding.workDone;
        actualMaterial = binding.actualMaterial;
        balanceMaterial = binding.balanceMaterial;

        popularTask = binding.popularTask;
        popularTaskLL = binding.popularTaskLL;
        paymentTask = binding.paymentTask;
        paymentTaskLL = binding.paymentTaskLL;






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
                }
                else {
                    dialog.dismiss();
                }


                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        getActivity().finish();
                        return true;
                    }
                });

                updateLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String appLink = "https://play.google.com/store/apps/details?="+ BuildConfig.APPLICATION_ID;
                        Uri uri = Uri.parse(appLink);
                        startActivity(new Intent(Intent.ACTION_VIEW,uri));

                    }
                });

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
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CalculaterInApp.class));
            }
        });
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), AddMaterial.class));
            }
        });
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), AdminViewMaterial.class));

            }
        });
        returnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ViewReturnMaterial.class));

            }
        });
        usedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), RemainingMaterial.class));

            }
        });

        actualMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ViewActualMaterial.class));

            }
        });

        balanceMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ViewBalanceMaterial.class));

            }
        });

        companyDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddDetails.class));
            }
        });

        addStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddStock.class));
            }
        });

        stockEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StockEntry.class));
            }
        });

        popularTask.setBackgroundResource(R.drawable.admin_home_tab_background);

        popularTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentTaskLL.setVisibility(View.GONE);
                popularTaskLL.setVisibility(View.VISIBLE);
                popularTask.setBackgroundResource(R.drawable.admin_home_tab_background);
                paymentTask.setBackground(null);
            }
        });

        paymentTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentTaskLL.setVisibility(View.VISIBLE);
                popularTaskLL.setVisibility(View.GONE);
                paymentTask.setBackgroundResource(R.drawable.admin_home_tab_background);
                popularTask.setBackground(null);
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