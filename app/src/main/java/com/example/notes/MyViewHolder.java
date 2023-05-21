package com.example.notes;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder  extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView nameView,epostaView,dekanView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageview);
        nameView=itemView.findViewById(R.id.name);
        epostaView=itemView.findViewById(R.id.eposta);
        dekanView=itemView.findViewById(R.id.dekan);


    }
}
