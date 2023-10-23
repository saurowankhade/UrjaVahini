package com.example.electricalmaterial.add_material;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.electricalmaterial.AddTotalMaterialTakenModel;
import com.example.electricalmaterial.R;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AddTotalMaterialTakenModel> dataList;

    public CustomListAdapter(Context context, ArrayList<AddTotalMaterialTakenModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public AddTotalMaterialTakenModel getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_balance_material_model, null); // Use your custom row layout here
        }

        // Get the data item for this position
        AddTotalMaterialTakenModel item = dataList.get(position);

        // Find and set the views in your custom row layout
        TextView materialTextView = convertView.findViewById(R.id.material); // Replace with your view IDs
        TextView quantityTextView = convertView.findViewById(R.id.quantity);
        TextView unitTextView = convertView.findViewById(R.id.unit);

        materialTextView.setText(item.getMaterial());
        quantityTextView.setText(item.getQuantity());
        unitTextView.setText(item.getUnit());

        return convertView;
    }
}
