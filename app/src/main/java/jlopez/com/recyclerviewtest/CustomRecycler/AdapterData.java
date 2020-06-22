package jlopez.com.recyclerviewtest.CustomRecycler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

        ViewGroup container = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        int margin = (int)(parent.getMeasuredWidth() * 0.05);
        //params.setMargins(margin,margin,margin,margin);
        container.setLayoutParams(params);



        container.getLayoutParams().width = (int)(parent.getMeasuredWidth() / 3);


        SimpleViewHolder viewHolder = new SimpleViewHolder(container);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        System.out.println("jorge onBindViewHolder " + position);

        Pet currentPet = pets.get(position);
        holder.bind(currentPet);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return  position;
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    @Override
    public void onViewRecycled(@NonNull SimpleViewHolder holder) {
        super.onViewRecycled(holder);
        System.out.println("jorge onViewRecycled");
        holder.recycleContainer();

    }



    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        private ViewGroup container;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            container = (ViewGroup) itemView;
        }

        public void bind(Pet currentPet) {
            CardView cardView = new CardView(container.getContext());
            container.addView(cardView.create(currentPet.getName(),String.valueOf(currentPet.getAge())));
        }

        public void recycleContainer() {
            container.removeAllViews();

        }
    }



}
