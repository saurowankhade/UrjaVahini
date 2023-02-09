package com.example.electricalmaterial;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;

public class CustonDialogBox {
    Context context;
    Dialog dialog;


    public CustonDialogBox(Context context) {
        this.context = context;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loading_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

    }

    public void show(){
        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }


}
