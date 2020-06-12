package jlopez.com.recyclerviewtest.test1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jlopez.com.recyclerviewtest.R;

public class AdaptaderRecyclerView extends RecyclerView.Adapter<ViewHolderPet> {

    private List<Pet> pets;
    private InterfaceClickRecyclerView interfaceClickRecyclerView;

    public AdaptaderRecyclerView(List<Pet> pets) {
        this.pets = pets;
    }

    public AdaptaderRecyclerView(InterfaceClickRecyclerView interfaceClickRecyclerView) {
        this.interfaceClickRecyclerView = interfaceClickRecyclerView;
        this.pets = new ArrayList<>();
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
        this.notifyItemInserted(this.pets.size() - 1);
    }

    public void deletePet(int index) {
        if (index < 0 || index > this.getItemCount()) return;
        this.pets.remove(index);
        this.notifyItemRemoved(index);
    }

    @NonNull
    @Override
    public ViewHolderPet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_row, parent, false);
        final ViewHolderPet viewHolderPet = new ViewHolderPet(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceClickRecyclerView.onClick(view, pets.get(viewHolderPet.getAdapterPosition()));
            }
        });
        return viewHolderPet;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPet holder, int position) {
        Pet pet = this.pets.get(position);
        holder.getTextViewAge().setText(String.valueOf(pet.getAge()));
        holder.getTextViewName().setText(pet.getName());
    }

    @Override
    public int getItemCount() {
        return this.pets.size();
    }
}
