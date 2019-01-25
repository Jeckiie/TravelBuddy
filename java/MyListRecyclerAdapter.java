package com.travelbuddy.jerko.travelbuddy;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyListRecyclerAdapter extends RecyclerView.Adapter<MyListViewHolder>{

    public interface ItemCheckListener
    {
        void itemCheckPosition(int position, Boolean bool);
    }

    public interface RemoveItemListener
    {
        void removeCellItem(int position);
    }

    private ItemCheckListener mItemCheckListener;
    private RemoveItemListener mRemoveItemListener;
    public List<MyListObject> recviewobjlist = new ArrayList<>();
    private int position;

    public MyListRecyclerAdapter(RemoveItemListener mRemoveItemListener, ItemCheckListener mItemCheckListener) {
        this.mRemoveItemListener = mRemoveItemListener;
        this.mItemCheckListener = mItemCheckListener;
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View cellView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mylist_item, viewGroup, false);
        return new MyListViewHolder(cellView, recviewobjlist, mItemCheckListener);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyListViewHolder viewHolder, int i) {
        viewHolder.setName(recviewobjlist.get(i).getName());
        viewHolder.listenCheck(recviewobjlist.get(i).getChecked());
        position = i;
        viewHolder.getDeleteIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRemoveItemListener.removeCellItem(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recviewobjlist.size();
    }

    public void addData(List<MyListObject> recviewobjlist){
        this.recviewobjlist.clear();
        this.recviewobjlist.addAll(recviewobjlist);
        notifyDataSetChanged();
    }


    public void removeCell(int position)
    {
        if(recviewobjlist.size() > position)
        {
            recviewobjlist.remove(position);
            notifyItemRemoved(position);
        }
    }



}