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
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.electricalmaterial.BuildConfig;
import com.example.electricalmaterial.CalculaterInApp;
import com.example.electricalmaterial.R;
import com.example.electricalmaterial.SupervisorUsedData;
import com.example.electricalmaterial.Survey;
import com.example.electricalmaterial.TeamLeaderReturnViewData;
import com.example.electricalmaterial.TeamLeaderViewData;
import com.example.electricalmaterial.ViewWorkDone;
import com.example.electricalmaterial.databinding.FragmentTeamleaderHomeBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TeamLeaderHomeFragment extends Fragment {

    private FragmentTeamleaderHomeBinding binding;
    CardView viewData,returnData,usedData,survey,calculator,workDone;


    Dialog dialog;

    TextView updateLink;
    FirebaseFirestore fStore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentTeamleaderHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ScrollView scrollView = binding.sl;
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        scrollView.setAnimation(animation);
        
        viewData = binding.viewData;
        returnData = binding.returnData;
        usedData = binding.usedData;
        survey = binding.survey;
        calculator = binding.calculator;
        workDone = binding.workDone;







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

                updateLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String appLink = "https://play.google.com/store/apps/details?="+BuildConfig.APPLICATION_ID;
                        Uri uri = Uri.parse(appLink);
                        startActivity(new Intent(Intent.ACTION_VIEW,uri));

                    }
                });

            }
        });















        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Survey.class));
            }
        });

        workDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ViewWorkDone.class));
            }
        });




        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CalculaterInApp.class));
            }
        });


        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TeamLeaderViewData.class));
            }
        });

        returnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TeamLeaderReturnViewData.class));
            }
        });

        usedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SupervisorUsedData.class));
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