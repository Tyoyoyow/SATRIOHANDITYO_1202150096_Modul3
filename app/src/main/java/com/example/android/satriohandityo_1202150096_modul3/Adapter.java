package com.example.android.satriohandityo_1202150096_modul3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.AirViewHolder> {

    private GradientDrawable mGradient;
    private ArrayList<Minuman> mMinumanData;
    private Context mContext;

    Adapter(Context context, ArrayList<Minuman> minumanData){
        this.mMinumanData = minumanData;
        this.mContext = context;

        mGradient = new GradientDrawable();
        mGradient.setColor(Color.GRAY);

        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.ades);
        if (drawable!=null){
            mGradient.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }


    @Override
    public AirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AirViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.activity_list_items, parent, false), mGradient);
    }

    @Override
    public void onBindViewHolder(Adapter.AirViewHolder holder, int position) {
        Minuman currentMinuman = mMinumanData.get(position);

        holder.bindTo(currentMinuman);
        Glide.with(mContext).load(currentMinuman.getImage()).into(holder.mMinumanImage);
    }

    @Override
    public int getItemCount() {
        return mMinumanData.size();
    }

    class AirViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mMinumanImage;
        private TextView mTitle;
        private TextView mInfo;
        private Context mContext;
        private Minuman mCurrentMinuman;
        private GradientDrawable mGradient;
        private String txtTitle;

        AirViewHolder(Context context, View itemView, GradientDrawable gradientDrawable){
            super(itemView);

            mMinumanImage = (ImageView) itemView.findViewById(R.id.airImage);
            mTitle = (TextView) itemView.findViewById(R.id.labelTitle);
            mInfo = (TextView) itemView.findViewById(R.id.infoTitle);

            mContext = context;
            mGradient = gradientDrawable;

            itemView.setOnClickListener(this);

        }

        void bindTo(Minuman currentMinuman){
            mTitle.setText(currentMinuman.getTitle());
            mInfo.setText(currentMinuman.getInfo());

            mCurrentMinuman = currentMinuman;
            txtTitle = mTitle.getText().toString();
            Glide.with(mContext).load(currentMinuman.getImage()).placeholder(mGradient).into(mMinumanImage);
        }

        @Override
        public void onClick(View view) {
            Intent detail = Minuman.starter(mContext, mCurrentMinuman.getTitle(), mCurrentMinuman.getImage());
            detail.putExtra("title", txtTitle);
            mContext.startActivity(detail);
        }
    }
}
