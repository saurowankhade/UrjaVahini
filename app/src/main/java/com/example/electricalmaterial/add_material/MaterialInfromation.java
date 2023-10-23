package com.example.electricalmaterial.add_material;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.electricalmaterial.AddTotalMaterialTakenModel;
import com.example.electricalmaterial.R;
import com.example.electricalmaterial.getinfromation.GetUserInfromation;
import com.example.electricalmaterial.settingsprinner.CustomSpinner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MaterialInfromation extends Fragment {
    private final GetUserInfromation userInfromation = new GetUserInfromation(); // getting user data

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private String userId;

    protected CustomSpinner csMaterial, csUnit;

    private AutoCompleteTextView actvMaterial;
    private AutoCompleteTextView actvUnit;
    private TextInputEditText tietQuantity;

    private MaterialButton mbAddMaterial;
    private ListView lvListView;

    private ArrayList<AddTotalMaterialTakenModel> materialTakenModelArrayList = new ArrayList<>();
    private CustomListAdapter adapter; // Custom adapter for your data
    private int positionOfList = -1;
    private int count = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_material_infromation, container, false);

        // finding ids
        actvMaterial = rootView.findViewById(R.id.material);
        tietQuantity = rootView.findViewById(R.id.quantity);
        actvUnit = rootView.findViewById(R.id.unit);
        mbAddMaterial = rootView.findViewById(R.id.addMaterial);
        lvListView = rootView.findViewById(R.id.listView);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        // when we want update
        showMainAddData();

        // declaring auto complete text view
        actvMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count==0){
                    showSprineer(csMaterial,"Material",actvMaterial); // material
                    showSprineer(csUnit,"Unit",actvUnit); // unit

                }
                count++;
            }
        });

        // when click on button add material

        mbAddMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String material = actvMaterial.getText().toString().trim();
                String quantity = tietQuantity.getText().toString().trim();
                String unit = actvUnit.getText().toString().trim();

                if (material.isEmpty()||quantity.isEmpty()||unit.isEmpty()){
                    Toast.makeText(requireActivity(), "Add all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (positionOfList < 0) {
                        materialTakenModelArrayList.add(new AddTotalMaterialTakenModel(material, material, unit, quantity));
                    } else {
                        materialTakenModelArrayList.set(positionOfList, new AddTotalMaterialTakenModel(material, material, unit, quantity));
                        positionOfList = -1;
                    }
                    adapter.notifyDataSetChanged();
                    actvMaterial.setText("");
                    tietQuantity.setText("");
                    actvUnit.setText("");
                }
            }
        });


        adapter = new CustomListAdapter(requireActivity(), materialTakenModelArrayList);
        lvListView.setAdapter(adapter);

        // Handle item clicks in the ListView
        lvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the adapter
                AddTotalMaterialTakenModel selectedItem = adapter.getItem(position);

                // Update the fields with the data from the selected item

                positionOfList = position;
                actvMaterial.setText(selectedItem.getMaterial());
                tietQuantity.setText(selectedItem.getQuantity());
                actvUnit.setText(selectedItem.getUnit());
            }
        });

        lvListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogBox(position);
                return true;
            }
        });



        // when click on back
        rootView.findViewById(R.id.pre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((AddMaterial) requireActivity()).goToSiteInformation();
            }
        });

        // when click on next
        rootView.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (materialTakenModelArrayList.isEmpty()){
                    Toast.makeText(requireContext(), "Selected Material ", Toast.LENGTH_SHORT).show();
                } else {
                    ((AddMaterial) requireActivity()).goToEndInformation(materialTakenModelArrayList);
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

    private void showDialogBox(int position) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(requireActivity());
        builder.setIcon(R.drawable.delete_icon)
                .setTitle("Do you want to delete ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position >= 0 && position < materialTakenModelArrayList.size()) {
                            materialTakenModelArrayList.remove(position);
                            adapter.notifyDataSetChanged(); // Notify the adapter of data change
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }


    private void showMainAddData() {
        DocumentReference documentReference = fStore.collection("Users")
                .document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


                String cmp = value.getString("companyEmail");

                fStore.collection(cmp + " MainAddData")
                        .document(((AddMaterial) requireActivity()).getId())
                        .collection("MaterialList")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                materialTakenModelArrayList.clear();
                                for (DocumentSnapshot doc : task.getResult()) {
                                    AddTotalMaterialTakenModel model = new AddTotalMaterialTakenModel(
                                            doc.getString("Id"),
                                            doc.getString("Material"),
                                            doc.getString("Unit"),
                                            doc.getString("Quantity")
                                    );
                                    materialTakenModelArrayList.add(model);
                                }
                                adapter = new CustomListAdapter(requireActivity(), materialTakenModelArrayList);
                                lvListView.setAdapter(adapter);
                                //Toast.makeText(requireContext(), "Data : "+materialTakenModelArrayList, Toast.LENGTH_SHORT).show();


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });


    }

}