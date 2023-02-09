package com.example.electricalmaterial;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;

public class Help extends AppCompatActivity {
    final String which = "WhichPdfView";

    SearchView searchView;
    ListView myList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle("Help");

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        searchView = findViewById(R.id.searchView);
        myList = findViewById(R.id.myList);
        
        list = new ArrayList<String>();




        list.add("How to add company details in data libary ?");//done
        list.add("How to add material ?");//done
        list.add("How to add return material ?");//done
        list.add("How to add survey ?");//done
        list.add("How to add stock entry ?");//done
        list.add("How to add work done ?");//done
        list.add("How to check added material ?");//done
        list.add("How to check added return material ?");//done
        list.add("How to check used material ?");//done
        list.add("How to check current stock ?");//done
        list.add("How to export used material ?");//done

        list.add("Where is excel file  saved ?");//edit with textview

        adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list);
        myList.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);

                return false;
            }
        });
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String n = myList.getItemAtPosition(position).toString();

               Intent intent = new Intent(getApplicationContext(),HelpPdf.class);
               intent.putExtra(which,n);
               startActivity(intent);
           }
       });



    }
}