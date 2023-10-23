package com.example.electricalmaterial.material.updation;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electricalmaterial.R;

public class AdminViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;

    public TextView mDate;
    public TextView mTeamName;
    public TextView mTender;
    public TextView mConsumerName;
    public TextView mSiteName;
    public TextView mCenter;
    private View mView;

    public AdminViewHolder(@NonNull View itemView) {
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
        mTeamName = itemView.findViewById(R.id.teamName);
        mTender = itemView.findViewById(R.id.tender);
        mConsumerName = itemView.findViewById(R.id.consumerName);
        mSiteName = itemView.findViewById(R.id.siteName);
        mCenter = itemView.findViewById(R.id.center);
        cardView = itemView.findViewById(R.id.cardView);
    }

    private AdminViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

  public void setOnClickListener(AdminViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
  }

}
