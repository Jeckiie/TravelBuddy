package com.travelbuddy.jerko.travelbuddy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemViewHolder extends RecyclerView.ViewHolder {


    private TextView itemName;
    private Button itemBtn;
    private ImageView deleteItemIv;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.itemTv);
        itemBtn = itemView.findViewById(R.id.itemBtn);
        deleteItemIv = itemView.findViewById(R.id.deleteImageView);
    }

    public Button getButton() {
        return itemBtn;
    }

    public ImageView getImageView(){return deleteItemIv;}

    public void setName(String name){
        itemName.setText(name);
    }

}