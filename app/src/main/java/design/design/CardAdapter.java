package design.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private String[] names;
    private int[] colors;

    public CardAdapter(@NonNull Context context, String[] names, int[] colors) {
        this.names = names;
        this.colors = colors;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.element3, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardText.setText(names[position % names.length]);
        holder.card.setCardBackgroundColor(colors[position % names.length]);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final CardView card;
        final TextView cardText;

        ViewHolder(View view) {
            super(view);
            card = view.findViewById(R.id.card);
            cardText = view.findViewById(R.id.card_text);
        }
    }
}
