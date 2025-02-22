package com.nerdgeeks.nerdcrict20.adapters;

/**
 * Created by IMRAN on 5/31/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface DoubleHeaderAdapter<H extends RecyclerView.ViewHolder, S extends RecyclerView.ViewHolder> {

    /**
     * Returns the header id for the item at the given position.
     *
     * @param position the item position
     * @return the header id
     */
    long getHeaderId(int position);

    /**
     * Returns the sub-header id for the item at the given position.
     *
     * @param position the item position
     * @return the sub-header id
     */
    long getSubHeaderId(int position);

    /**
     * Creates a new header ViewHolder.
     *
     * @param parent the header's view parent
     * @return a view holder for the created header view
     */
    H onCreateHeaderHolder(ViewGroup parent);

    /**
     * Creates a new sub-header ViewHolder.
     *
     * @param parent the sub-header's view parent
     * @return a view holder for the created sub-header view
     */
    S onCreateSubHeaderHolder(ViewGroup parent);

    /**
     * Updates the header view to reflect the header data for the given position
     *
     * @param viewholder the header view holder
     * @param position   the header's item position
     */
    void onBindHeaderHolder(H viewholder, int position);

    /**
     * Updates the sub-header view to reflect the header data for the given position
     *
     * @param viewholder the sub-header view holder
     * @param position   the sub-header's item position
     */
    void onBindSubHeaderHolder(S viewholder, int position);
}