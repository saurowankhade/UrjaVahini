package com.example.electricalmaterial;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddWorkDoneHolder extends RecyclerView.ViewHolder {
    TextView mDate,mLine,mTender,mConsumerName,mSiteName,mCenter,mTeamName;
    View mView;
    public AddWorkDoneHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,getAbsoluteAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v,getAbsoluteAdapterPosition());
                return true;
            }
        });
        mDate = itemView.findViewById(R.id.date);
        mLine = itemView.findViewById(R.id.line);
        mTender = itemView.findViewById(R.id.tender);
        mConsumerName = itemView.findViewById(R.id.consumerName);
        mSiteName = itemView.findViewById(R.id.siteName);
        mCenter = itemView.findViewById(R.id.center);
        mTeamName = itemView.findViewById(R.id.teamName);
    }

    private AddWorkDoneHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(AddWorkDoneHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
