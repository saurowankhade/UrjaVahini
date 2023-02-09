package com.example.electricalmaterial;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamLeaderViewHolder extends RecyclerView.ViewHolder{

    TextView mDate,mTeamName,mTender,mConsumerName,mSiteName,mCenter;
    View mView;

    public TeamLeaderViewHolder(@NonNull View itemView) {
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
    }

    private TeamLeaderViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(TeamLeaderViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
