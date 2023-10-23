package com.example.electricalmaterial;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.electricalmaterial.databinding.ActivitySupervisorBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Supervisor extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivitySupervisorBinding binding;


    //authorization
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userId;
    ProgressDialog pd;

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySupervisorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setSupportActionBar(binding.appBarSupervisor.toolbar);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_share,R.id.nav_rate,R.id.nav_privacy)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_supervisor);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        //Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference();



        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        pd = ProgressDialog.show(this,"Loading...","Please Wait",false,false);
        pd.dismiss();


        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String companyEmail = value.getString("companyEmail");
                String cmp = companyEmail;
                companyEmail = companyEmail.replace("@", "");
                companyEmail = companyEmail.replace(".", "");
                String uName = value.getString("fullName");
                String uEmail = value.getString("email");

                updateNavHeader(uEmail,uName);

//                return false;
            }

        });






    }


    public void updateNavHeader(String email,String name){

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.uName);
        TextView navUserMail = headerView.findViewById(R.id.uEmail);
        CircleImageView photo = headerView.findViewById(R.id.imageView);
        navUserMail.setText(email);
        navUsername.setText(name);

        LinearLayout profile = headerView.findViewById(R.id.profileL);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });

//        pd.show();
        StorageReference profileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                pd.dismiss();
                Picasso.get().load(uri).into(photo);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_supervisor);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                return  true;
            case R.id.logOut:
                AlertDialog.Builder builder = new AlertDialog.Builder(Supervisor.this);
                String[] options = {"Yes", "No"};
                builder.setTitle("Are you sure to Logout ?");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).create().show();
                return  true;
            default:

        }



        return super.onOptionsItemSelected(item);
    }

}