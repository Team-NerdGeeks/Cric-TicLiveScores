package com.nerdgeeks.nerdcrict20.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.models.Batting;
import com.nerdgeeks.nerdcrict20.models.Bowling;
import com.nerdgeeks.nerdcrict20.models.Score_;
import com.nerdgeeks.nerdcrict20.models.Score__;

import java.util.List;

/**
 * Created by IMRAN on 5/31/2017.
 */

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder>
        implements DoubleHeaderAdapter<SummaryAdapter.HeaderHolder, SummaryAdapter.SubHeaderHolder>{
    private Context context;
    private List<Score__> battings;
    private List<Bowling> bowlings;

    public SummaryAdapter(Context context, List<Score__> battings, List<Bowling> bowlings) {
        this.context = context;
        this.battings = battings;
        this.bowlings = bowlings;
    }

    @Override
    public SummaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_summary,parent,false);
        return new SummaryAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(SummaryAdapter.ViewHolder holder, int position) {
        holder.name.setText(battings.get(position).getBatsman());
        holder.dismiss.setText(battings.get(position).getDismissalInfo());
        holder.run_over.setText(battings.get(position).getR());
        holder.ball_maiden.setText(battings.get(position).getB());
        holder.fours_run.setText(battings.get(position).get4s());
        holder.sixes_wicket.setText(battings.get(position).get6s());
    }

    @Override
    public int getItemCount() {
        return battings.size();
    }

    @Override
    public long getHeaderId(int position) {
        return position/13;
    }

    @Override
    public long getSubHeaderId(int position) {
        return position/13;
    }

    @Override
    public SummaryAdapter.HeaderHolder onCreateHeaderHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
        return new SummaryAdapter.HeaderHolder(rootView);
    }

    @Override
    public SummaryAdapter.SubHeaderHolder onCreateSubHeaderHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.sub_header, parent, false);
        return new SummaryAdapter.SubHeaderHolder(rootView);
    }

    @Override
    public void onBindHeaderHolder(SummaryAdapter.HeaderHolder viewholder, int position) {
        //viewholder.state.setText(battings.get(position).getTitle());
        if (position+12>battings.size()){
            viewholder.total_score.setText(battings.get(battings.size()-1).getR()+"-");
            viewholder.run_rate.setText(battings.get(battings.size()-1).getB());
            viewholder.overs.setText(battings.get(battings.size()-1).getDismissalInfo());
        } else {
            viewholder.total_score.setText(battings.get(position+12).getR()+"-");
            viewholder.run_rate.setText(battings.get(position+12).getB());
            viewholder.overs.setText(battings.get(position+12).getDismissalInfo());
        }
    }

    @Override
    public void onBindSubHeaderHolder(SummaryAdapter.SubHeaderHolder viewholder, int position) {
        viewholder.state.setText("BATTING");
        viewholder.run_over.setText("R");
        viewholder.ball_maiden.setText("B");
        viewholder.fours_run.setText("4s");
        viewholder.sixes_wicket.setText("6s");
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, dismiss, run_over, ball_maiden, fours_run, sixes_wicket;
        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            dismiss = (TextView) itemView.findViewById(R.id.dismissal);
            run_over = (TextView) itemView.findViewById(R.id.run_over);
            ball_maiden = (TextView) itemView.findViewById(R.id.ball_maiden);
            fours_run = (TextView) itemView.findViewById(R.id.fours_run);
            sixes_wicket = (TextView) itemView.findViewById(R.id.sixes_wicket);
        }
    }

     class HeaderHolder extends RecyclerView.ViewHolder{
         private ImageView img_flag;
         private TextView state, total_score, overs, run_rate;
         HeaderHolder(View itemView) {
            super(itemView);
             img_flag = (ImageView) itemView.findViewById(R.id.img_flag);
             state = (TextView) itemView.findViewById(R.id.state);
             total_score = (TextView) itemView.findViewById(R.id.total_score);
             overs = (TextView) itemView.findViewById(R.id.overs);
             run_rate = (TextView) itemView.findViewById(R.id.run_rate);
         }
    }

    class SubHeaderHolder extends RecyclerView.ViewHolder{
        private TextView state,run_over, ball_maiden, fours_run, sixes_wicket;
        SubHeaderHolder(View itemView) {
            super(itemView);
            state = (TextView) itemView.findViewById(R.id.state);
            run_over = (TextView) itemView.findViewById(R.id.run_over);
            ball_maiden = (TextView) itemView.findViewById(R.id.ball_maiden);
            fours_run = (TextView) itemView.findViewById(R.id.fours_run);
            sixes_wicket = (TextView) itemView.findViewById(R.id.sixes_wicket);
        }
    }
}
