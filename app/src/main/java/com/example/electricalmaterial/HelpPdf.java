package com.example.electricalmaterial;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.github.barteksc.pdfviewer.PDFView;

public class HelpPdf extends AppCompatActivity {
    PDFView pdfView;

    String whichPdfView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_pdf);

        setTitle("Help");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        pdfView = findViewById(R.id.pdfView);
        pdfView.setVisibility(View.GONE);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            whichPdfView = bundle.getString("WhichPdfView");
        }

        switch (whichPdfView){
            case "How to add company details in data libary ?" :
               viewPdf("How to add  company details in data library.pdf");
                break;
            case "How to add material ?" :
                viewPdf("how  to add material.pdf");
                break;
            case "How to add return material ?" :
                viewPdf("How to add return material.pdf");
                break;
            case "How to add survey ?" :
                viewPdf("How to add survey.pdf");
                break;
            case "How to add stock entry ?" :
                viewPdf("How to add stock entry.pdf");
                break;
            case "How to add work done ?" :
                viewPdf("How to add work done.pdf");
                break;
            case "How to check added material ?" :
                viewPdf("How to check added Material.pdf");
                break;
            case "How to check added return material ?" :
                viewPdf("How to check added return Material.pdf");
                break;
            case "How to check used material ?" :
                viewPdf("How to check used Material.pdf");
                break;
            case "How to check current stock ?" :
                viewPdf("How to check current stock.pdf");
                break;
            case "How to export used material ?" :
                viewPdf("How to export excel file.pdf");
                break;
            case "Where is excel file  saved" :
                pdfView.setVisibility(View.GONE);
                break;
            default:
                break;
        }


    }

    private void viewPdf(String s) {
        pdfView.fromAsset(s).load();
        pdfView.setVisibility(View.VISIBLE);
    }


}