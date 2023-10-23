package com.example.electricalmaterial.add_material;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.electricalmaterial.R;
import com.example.electricalmaterial.getinfromation.GetUserInfromation;
import com.example.electricalmaterial.settingsprinner.CustomSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BacisInfromation extends Fragment {

    private final GetUserInfromation userInfromation  = new GetUserInfromation(); // getting user data

    private final String setError = "Required!";


    protected CustomSpinner csTeamName,csLine,csTender,csDriverName,csVehicalName;

    private TextView tvDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private AutoCompleteTextView actvTeamName;
    private AutoCompleteTextView actvLine;
    private AutoCompleteTextView actvTender;
    private AutoCompleteTextView actvDriverName;
    private AutoCompleteTextView actvVehicalName;

    private int count = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bacis_infromation, container, false);

        // finding Ids
        tvDate = rootView.findViewById(R.id.date);
        actvTeamName = rootView.findViewById(R.id.teamName);
        actvLine = rootView.findViewById(R.id.line);
        actvTender = rootView.findViewById(R.id.tender);
        actvDriverName = rootView.findViewById(R.id.driver);
        actvVehicalName = rootView.findViewById(R.id.vehical);

        // while updating the data
        if ( ((AddMaterial) requireActivity()).getsTeamName() != null ){
            // setting the data for update
            tvDate.setText(((AddMaterial) requireActivity()).getsDate());
            actvTeamName.setText(((AddMaterial) requireActivity()).getsTeamName());
            actvLine.setText(((AddMaterial) requireActivity()).getsLine());
            actvTender.setText(((AddMaterial) requireActivity()).getsTender());
            actvDriverName.setText(((AddMaterial) requireActivity()).getsDriverName());
            actvVehicalName.setText(((AddMaterial) requireActivity()).getsVehicalName());

        }
        
        
        // Date selected
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        requireContext(), R.style.DatePicker1,
                        onDateSetListener,year,month,day);

                //Theme_Material_Light_Dialog_MinWidth

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(230,223,223)));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = "";
                if (month<=9){ //
                    date  = year+"/"+"0"+month+"/"+dayOfMonth;
                }
                else if (dayOfMonth<=9){
                    date  = year+"/"+month+"/"+"0"+dayOfMonth;
                }
                else {
                    date  = year+"/"+month+"/"+dayOfMonth;
                }
                if (month<=9 && dayOfMonth<=9){
                    date  = year+"/"+"0"+month+"/"+"0"+dayOfMonth;
                }
                tvDate.setText(date);
            }
        };
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        tvDate.setText(df.format(c));

        // declaring auto complete text view
        actvTeamName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count==0){
                    showSprineer(csTeamName,"teamName",actvTeamName); // teamName
                    showSprineer(csLine,"Line",actvLine); // line
                    showSprineer(csTender,"Tender",actvTender); // tender
                    showSprineer(csDriverName,"Driver",actvDriverName); // driver name
                    showSprineer(csVehicalName,"Vehical",actvVehicalName); // vehical name
                }
                count++;
            }
        });

        // when click on next button
        rootView.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = tvDate.getText().toString().trim();
                String teamName = actvTeamName.getText().toString().trim();
                String line = actvLine.getText().toString().trim();
                String tender = actvTender.getText().toString().trim();
                String driverName = actvDriverName.getText().toString().trim();
                String vehicalName = actvVehicalName.getText().toString().trim();

                if (date.equalsIgnoreCase("Date")){
                    tvDate.setError(setError);
                }
                else if (teamName.isEmpty()){
                    actvTeamName.setError(setError);
                }  else if (line.isEmpty()){
                    actvLine.setError(setError);
                }
                else if (tender.isEmpty()){
                    actvTender.setError(setError);
                }
                else if (driverName.isEmpty()){
                    actvDriverName.setError(setError);
                }
                else if (vehicalName.isEmpty()){
                    actvVehicalName.setError(setError);
                }

                else {
                    ((AddMaterial) requireActivity()).goToSiteInformation(userInfromation.getCompanyEmail(),date,teamName,line,tender,driverName,vehicalName);

                }


            }
        });

        return rootView;
    }


    private void showSprineer(CustomSpinner customSpinner, String type, AutoCompleteTextView autoCompleteTextView) {
        customSpinner = new CustomSpinner(requireActivity(), userInfromation.getCompanyEmailWithOutExtension(), type);
        autoCompleteTextView.setAdapter(customSpinner.getAdapter());
        customSpinner.retrieveData();
    }
}