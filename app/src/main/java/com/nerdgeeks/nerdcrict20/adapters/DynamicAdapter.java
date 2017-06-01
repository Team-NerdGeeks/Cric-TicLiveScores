package com.nerdgeeks.nerdcrict20.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.models.Batting;
import com.nerdgeeks.nerdcrict20.models.Bowling;
import com.nerdgeeks.nerdcrict20.models.Calendar;
import com.nerdgeeks.nerdcrict20.models.Score__;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IMRAN on 6/1/2017.
 */

public class DynamicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Score__> firstList;
    private List<Bowling> secondList;
    private Context context;

    private static final int FIRST_LIST_ITEM_VIEW = 1;
    private static final int FIRST_LIST_HEADER_VIEW = 2;
    private static final int SECOND_LIST_ITEM_VIEW = 3;
    private static final int SECOND_LIST_HEADER_VIEW = 4;

    public DynamicAdapter(Context context, List<Score__> firstList, List<Bowling> secondList) {
        this.firstList = firstList;
        this.secondList = secondList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // List items of first list
        private TextView mTextDescription1;
        private TextView mListItemTitle1;

        // List items of second list
        private TextView mTextDescription2;
        private TextView mListItemTitle2;


        ViewHolder(final View itemView) {
            super(itemView);

            // Get the view of the elements of first list
            mTextDescription1 = (TextView) itemView.findViewById(R.id.dismissal);
            mListItemTitle1 = (TextView) itemView.findViewById(R.id.name);

            // Get the view of the elements of second list
            mTextDescription2 = (TextView) itemView.findViewById(R.id.name);
            mListItemTitle2 = (TextView) itemView.findViewById(R.id.dismissal);
        }

        void bindViewSecondList(int pos) {

            if (firstList == null) pos = pos - 1;
            else {
                if (firstList.size() == 0) pos = pos - 1;
                else pos = pos - firstList.size() - 2;
            }

            final String description = secondList.get(pos).getScores().get(pos).getO();
            final String title = secondList.get(pos).getScores().get(pos).getBowler();

            mTextDescription2.setText(title);
            mListItemTitle2.setText(description);
        }

        void bindViewFirstList(int pos) {

            // Decrease pos by 1 as there is a header view now.
            pos = pos - 1;

            final String description = firstList.get(pos).getDismissalInfo();
            final String title = firstList.get(pos).getBatsman();

            mTextDescription1.setText(description);
            mListItemTitle1.setText(title);
        }
    }

    private class FirstListHeaderViewHolder extends ViewHolder {
        FirstListHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class FirstListItemViewHolder extends ViewHolder {
        FirstListItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class SecondListHeaderViewHolder extends ViewHolder {
        SecondListHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class SecondListItemViewHolder extends ViewHolder {
        SecondListItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        if (viewType == FIRST_LIST_ITEM_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summary, parent, false);
            FirstListItemViewHolder vh = new FirstListItemViewHolder(v);
            return vh;

        } else if (viewType == FIRST_LIST_HEADER_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_header, parent, false);
            FirstListHeaderViewHolder vh = new FirstListHeaderViewHolder(v);
            return vh;

        } else if (viewType == SECOND_LIST_HEADER_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_header, parent, false);
            SecondListHeaderViewHolder vh = new SecondListHeaderViewHolder(v);
            return vh;

        } else {
            // SECOND_LIST_ITEM_VIEW
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summary, parent, false);
            SecondListItemViewHolder vh = new SecondListItemViewHolder(v);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {
            if (holder instanceof SecondListItemViewHolder) {
                SecondListItemViewHolder vh = (SecondListItemViewHolder) holder;
                vh.bindViewSecondList(position);

            } else if (holder instanceof FirstListHeaderViewHolder) {
                FirstListHeaderViewHolder vh = (FirstListHeaderViewHolder) holder;

            } else if (holder instanceof FirstListItemViewHolder) {
                FirstListItemViewHolder vh = (FirstListItemViewHolder) holder;
                vh.bindViewFirstList(position);

            } else if (holder instanceof SecondListHeaderViewHolder) {
                SecondListHeaderViewHolder vh = (SecondListHeaderViewHolder) holder;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        int firstListSize = 0;
        int secondListSize = 0;

        if (secondList == null && firstList == null) return 0;

        if (secondList != null)
            secondListSize = secondList.size();
        if (firstList != null)
            firstListSize = firstList.size();

        if (secondListSize > 0 && firstListSize > 0)
            return 1 + firstListSize + 1 + secondListSize + 1;   // first list header, first list size, second list header , second list size, footer
        else if (secondListSize > 0 && firstListSize == 0)
            return 1 + secondListSize + 1;                       // second list header, second list size, footer
        else if (secondListSize == 0 && firstListSize > 0)
            return 1 + firstListSize;                            // first list header , first list size
        else return 0;
    }

    @Override
    public int getItemViewType(int position) {

        int firstListSize = 0;
        int secondListSize = 0;

        if (secondList == null && firstList == null)
            return super.getItemViewType(position);

        if (secondList != null)
            secondListSize = secondList.size();
        if (firstList != null)
            firstListSize = firstList.size();

        if (secondListSize > 0 && firstListSize > 0) {
            if (position == 0) return FIRST_LIST_HEADER_VIEW;
            else if (position == firstListSize + 1)
                return SECOND_LIST_HEADER_VIEW;
            else if (position > firstListSize + 1)
                return SECOND_LIST_ITEM_VIEW;
            else return FIRST_LIST_ITEM_VIEW;

        } else if (secondListSize > 0 && firstListSize == 0) {
            if (position == 0) return SECOND_LIST_HEADER_VIEW;
            else return SECOND_LIST_ITEM_VIEW;

        } else if (secondListSize == 0 && firstListSize > 0) {
            if (position == 0) return FIRST_LIST_HEADER_VIEW;
            else return FIRST_LIST_ITEM_VIEW;
        }

        return super.getItemViewType(position);
    }
}