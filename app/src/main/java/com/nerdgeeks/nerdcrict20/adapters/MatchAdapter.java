package com.nerdgeeks.nerdcrict20.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.models.Match;
import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.utils.OnItemClickListener;

import java.util.List;

/**
 * Created by hp on 5/7/2017.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private Context context;
    private List<Match> matches;
    private OnItemClickListener onItemClickListener;

    public MatchAdapter(Context context, List<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_matches,parent,false);
        return new ViewHolder(rootView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Boolean isMatchStarted = matches.get(position).getMatchStarted();

        if(!isMatchStarted){
            holder.listItem.setText(String.valueOf(matches.get(position).getMatchStarted()));
        }
//        holder.listItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onItemClickListener.onClick(view, position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView listItem;
        private  OnItemClickListener onItemClickListener;
        ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            listItem = (TextView) itemView.findViewById(R.id.textView);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(view, getAdapterPosition());
    }
    }
}
