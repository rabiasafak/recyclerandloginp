package com.example.notes;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter {






    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView,contentTextView,timestampTextView;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title_text_view);
            contentTextView = itemView.findViewById(R.id.not_content_text_view);
            timestampTextView = itemView.findViewById(R.id.note_timestamp_text_view);
        }
    }
}
