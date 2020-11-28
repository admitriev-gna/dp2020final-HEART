package com.example.digitalrush;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.CardViewHolder> {

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
                    if (mContext instanceof Training) {
                        ((Training)mContext).TrainGIF(currentCardPosition);
                    }
                }
            });
        }
    }

    List<Integer> cards;

    TrainingAdapter(List<Integer> cards) {
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_training, parent, false);
        TrainingAdapter.CardViewHolder pvh = new TrainingAdapter.CardViewHolder(v, v.getContext());
        return pvh;    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardView cardView = holder.cv;
        TextView title = cardView.findViewById(R.id.text_exp);
        ImageView gif = cardView.findViewById(R.id.imageView);
        gif.setImageResource(cards.get(position));
        title.setText("Упражнение №" + position);
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
