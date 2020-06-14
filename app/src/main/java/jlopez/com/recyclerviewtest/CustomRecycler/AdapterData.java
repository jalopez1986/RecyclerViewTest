package jlopez.com.recyclerviewtest.CustomRecycler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jlopez.com.recyclerviewtest.shared.Pet;

public class AdapterData extends RecyclerView.Adapter<AdapterData.SimpleViewHolder> {

    private List<Pet> pets;
    private Context context;

    public AdapterData(List<Pet> pets, Context context) {
        this.pets = pets;
        this.context = context;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("jorge onCreateViewHolder " + viewType );

        CardView cardView = new CardView(parent.getContext());

        SimpleViewHolder viewHolder = new SimpleViewHolder(cardView.create("TestName", "TestAge"));

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        System.out.println("jorge onBindViewHolder " + position);

        Pet currentPet = pets.get(position);
        holder.cardView.setInfo(currentPet.getName(), String.valueOf(currentPet.getAge()));
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    @Override
    public void onViewRecycled(@NonNull SimpleViewHolder holder) {
        super.onViewRecycled(holder);
        System.out.println("jorge onViewRecycled");

    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
        }
    }



}
