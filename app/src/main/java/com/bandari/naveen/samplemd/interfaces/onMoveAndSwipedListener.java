package com.bandari.naveen.samplemd.interfaces;

/**
 * Created by suresh on 12/3/18.
 */

public interface onMoveAndSwipedListener {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

}