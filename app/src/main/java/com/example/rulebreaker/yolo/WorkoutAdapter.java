package com.example.rulebreaker.yolo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkoutAdapter extends RecyclerView.Adapter <WorkoutAdapter.ViewHolder> {

    private final String[] mworkoutUrls;
    private final String[] mworkoutText;
    private final LayoutInflater mLayoutInflater;
    public WorkoutAdapter(Context context, String[] workout_urls, String[] workout_text) {
        mLayoutInflater=LayoutInflater.from(context);
        mworkoutUrls=workout_urls;
        mworkoutText=workout_text;
     
    }


    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = mLayoutInflater.inflate(R.layout.workout_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutAdapter.ViewHolder holder, int position) {

        String workoutUrl=mworkoutUrls[position];
        holder.workoutTextview.setText(mworkoutText[position]);
        Picasso.with(holder.workoutImageview.getContext()).load(workoutUrl).fit().into(holder.workoutImageview);


    }

    @Override
    public int getItemCount() {
        return mworkoutUrls.length;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.workoutImageView)
        ImageView workoutImageview;
        @BindView(R.id.workoutTextView)
        TextView workoutTextview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), "Exercise Selected is " + workoutTextview.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
