package com.example.electricalmaterial.done.animation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.electricalmaterial.Admin;
import com.example.electricalmaterial.R;
import com.example.electricalmaterial.Supervisor;
import com.example.electricalmaterial.TeamLearder;
import com.example.electricalmaterial.getinfromation.GetUserInfromation;
import com.google.android.material.button.MaterialButton;

public class DoneAnimation extends Fragment {

    private final GetUserInfromation userInfromation = new GetUserInfromation();



    @SuppressLint({"Range", "MissingInflatedId", "LocalSuppress"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_done_animation, container, false);
//        Animation aniIV = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in_out);
        Animation aniIV = new AlphaAnimation((float) 1, 0.6F);

        aniIV.setDuration(1000); // duration - half a second
        aniIV.setInterpolator(new LinearInterpolator()); // do not alter
        // animation

        aniIV.setRepeatCount(Animation.INFINITE); //repeating indefinitely
        aniIV.setRepeatMode(Animation.REVERSE);

        ImageView imageView = rootView.findViewById(R.id.iv);
        imageView.setAnimation(aniIV);

        MaterialButton mbDoneButton = rootView.findViewById(R.id.doneButton);
        Animation animationL = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        mbDoneButton.setAnimation(animationL);


        mbDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userRole = userInfromation.getUserRole();
                switch (userRole) {
                    case "ADMIN":
                        startActivity(new Intent(requireActivity(), Admin.class));
                        requireActivity().finish();
                        break;
                    case "SUPERVISOR":
                        startActivity(new Intent(requireActivity(), Supervisor.class));
                        requireActivity().finish();
                        break;
                    case "TEAM LEADER":
                        startActivity(new Intent(requireActivity(), TeamLearder.class));
                        requireActivity().finish();
                        break;
                    default:
                        Toast.makeText(requireActivity(), "Error going to back", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}