package com.example.digitalrush;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    ArrayList<String> cards;

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        int currentCardPosition;
        Context mContext;

        public CardViewHolder(@NonNull CardView itemView, Context context) {
            super(itemView);
            cv = itemView;
            mContext = context;
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof Second) {
                        ((Second)mContext).showSnackbar(currentCardPosition);
                        ((Second)mContext).goToTraining(currentCardPosition);
                    }
                }
            });
        }
    }

    RVAdapter(ArrayList<String> cards) {
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_complex, parent, false);
        CardViewHolder pvh = new CardViewHolder(v, v.getContext());
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardView cardView = holder.cv;
        TextView title = cardView.findViewById(R.id.complex_id);
        title.setText("Комплекс " + cards.get(position));
        holder.currentCardPosition = position;
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
