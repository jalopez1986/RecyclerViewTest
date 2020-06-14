package jlopez.com.recyclerviewtest.SimpleRecycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import jlopez.com.recyclerviewtest.R;

public class ViewHolderPet extends RecyclerView.ViewHolder {
    private TextView textViewAge;
    private  TextView textViewName;

    public ViewHolderPet(@NonNull View itemView) {
        super(itemView);

        textViewName = itemView.findViewById(R.id.textViewName);
        textViewAge = itemView.findViewById(R.id.textViewAge);

    }

    TextView getTextViewAge() {
        return textViewAge;
    }

    TextView getTextViewName() {
        return  textViewName;
    }
}
