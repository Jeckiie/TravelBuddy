package com.travelbuddy.jerko.travelbuddy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemViewHolder>  {

    private OnButtonClicked onButtonClickedListener;
    private List<String> data = new ArrayList<>();
    int imageResource = 0;
    int noOfDefaultItems = 8;

    public interface OnButtonClicked {
        void itemClicked(String text);
        void removeItemClicked(int position);
    }



    public ItemRecyclerAdapter(OnButtonClicked onButtonClickedListener) {
        this.onButtonClickedListener = onButtonClickedListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View cellView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ItemViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int i) {
        if(i < noOfDefaultItems)
        {
            itemViewHolder.getImageView().setImageResource(imageResource);
        }
        itemViewHolder.setName(data.get(i));
        itemViewHolder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickedListener.itemClicked(data.get(i));
            }
        });
        itemViewHolder.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickedListener.removeItemClicked(itemViewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addData(List<String> data)
    {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void removeCell(int position)
    {
        if(data.size() > position)
        {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

}
