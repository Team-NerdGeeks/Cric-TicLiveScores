package com.nerdgeeks.nerdcrict20.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.models.Score_;

import java.util.List;

/**
 * Created by IMRAN on 5/31/2017.
 */

public class BowlingAdapter extends RecyclerView.Adapter<BowlingAdapter.ViewHolder>
        implements DoubleHeaderAdapter<BowlingAdapter.HeaderHolder, BowlingAdapter.SubHeaderHolder>{
    private Context context;
    private List<Score_> bowling;

    public BowlingAdapter(Context context, List<Score_> bowling) {
        this.context = context;
        this.bowling = bowling;
    }

    @Override
    public BowlingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_summary,parent,false);
        return new BowlingAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(BowlingAdapter.ViewHolder holder, int position) {
        holder.name.setText(bowling.get(position).getBowler());
        holder.run_over.setText(bowling.get(position).getO());
        holder.ball_maiden.setText(bowling.get(position).getM());
        holder.fours_run.setText(bowling.get(position).getR());
        holder.sixes_wicket.setText(bowling.get(position).getW());
    }

    @Override
    public int getItemCount() {
        return bowling.size();
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
    public BowlingAdapter.HeaderHolder onCreateHeaderHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
        return new BowlingAdapter.HeaderHolder(rootView);
    }

    @Override
    public BowlingAdapter.SubHeaderHolder onCreateSubHeaderHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.sub_header, parent, false);
        return new BowlingAdapter.SubHeaderHolder(rootView);
    }

    @Override
    public void onBindHeaderHolder(BowlingAdapter.HeaderHolder viewholder, int position) {
//        if (position+12>bowling.size()){
//            viewholder.state.setText(bowling.);
//            viewholder.total_score.setText(bowling.get(bowling.size()-1).getR()+"-");
//            viewholder.run_rate.setText(battings.get(battings.size()-1).getB());
//            viewholder.overs.setText(battings.get(battings.size()-1).getDismissalInfo());
//        } else {
//            viewholder.total_score.setText(battings.get(position+12).getR()+"-");
//            viewholder.run_rate.setText(battings.get(position+12).getB());
//            viewholder.overs.setText(battings.get(position+12).getDismissalInfo());
//        }
    }

    @Override
    public void onBindSubHeaderHolder(BowlingAdapter.SubHeaderHolder viewholder, int position) {
        viewholder.state.setText("BOWLING");
        viewholder.run_over.setText("0");
        viewholder.ball_maiden.setText("M");
        viewholder.fours_run.setText("R");
        viewholder.sixes_wicket.setText("W");
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
