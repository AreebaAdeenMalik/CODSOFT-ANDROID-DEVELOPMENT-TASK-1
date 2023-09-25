package com.example.quotesapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuoteRecyclerAdapter extends  RecyclerView.Adapter<QuoteViewHolder> {

    Context context;
    List<QuoteResponse> list;
    CopyListener listener;

    public QuoteRecyclerAdapter(Context context, List<QuoteResponse> list, CopyListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuoteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_quote, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {

        QuoteResponse quote = list.get(position);
        holder.textView_quote.setText(list.get(position).getText());
        holder.textView_author.setText(list.get(position).getAuthor());

        holder.button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCopyClicked(list.get(holder.getAdapterPosition()).getText());
            }
        });
        holder.button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQuote(quote.getText());
            }
        });
        holder.button_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite(quote);
            }
        });
        // Set the favorite button icon based on the quote's favorite status
        if (quote.isFavorite) {
            holder.button_favorite.setBackgroundResource(R.drawable.baseline_favorite_24);
        } else {
            holder.button_favorite.setBackgroundResource(R.drawable.baseline_favorite_border_24);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void shareQuote(String quoteText) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, quoteText);
        context.startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void toggleFavorite(QuoteResponse quote) {
        quote.setFavorite(!quote.isFavorite);
        notifyDataSetChanged();
        Toast.makeText(context, quote.isFavorite ? "Added to favorites" : "Removed from favorites", Toast.LENGTH_SHORT).show();
    }
}
    class QuoteViewHolder extends RecyclerView.ViewHolder {
        TextView textView_quote, textView_author;
        Button button_copy;
        ImageButton button_share, button_favorite;

        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_quote = itemView.findViewById(R.id.textView_quote);
            textView_author = itemView.findViewById(R.id.textView_author);
            button_share = itemView.findViewById(R.id.button_share);
            button_favorite = itemView.findViewById(R.id.button_favorite);
            button_copy = itemView.findViewById(R.id.button_copy);
        }
    }

