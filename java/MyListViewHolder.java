package com.travelbuddy.jerko.travelbuddy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyListViewHolder extends RecyclerView.ViewHolder implements MyListRecyclerAdapter.ItemCheckListener{

    private TextView tvName;
    private CheckBox checkBox;
    private ImageView deleteImageView;
    public List<MyListObject> recviewobjlist;
    MyListRecyclerAdapter.ItemCheckListener mItemCheckListener;

    public MyListViewHolder(@NonNull View itemView, List<MyListObject> recviewobjlist, MyListRecyclerAdapter.ItemCheckListener mItemCheckListener) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        checkBox = itemView.findViewById(R.id.checkBox);
        deleteImageView = itemView.findViewById(R.id.deleteImageView);
        this.recviewobjlist = recviewobjlist;
        this.mItemCheckListener = mItemCheckListener;
    }

    public ImageView getDeleteIv()
    {
        return deleteImageView;
    }

    public void setName(String name){
        tvName.setText(name);
    }

    public void listenCheck(boolean check){
        checkBox.setChecked(check);

        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    mItemCheckListener.itemCheckPosition(getAdapterPosition(), true);
                    recviewobjlist.get(getAdapterPosition()).setChecked(true);
                }else{
                    recviewobjlist.get(getAdapterPosition()).setChecked(false);
                    mItemCheckListener.itemCheckPosition(getAdapterPosition(), false);
                }
            }
        });
    }

    @Override
    public void itemCheckPosition(int position, Boolean bool) {

    }
}