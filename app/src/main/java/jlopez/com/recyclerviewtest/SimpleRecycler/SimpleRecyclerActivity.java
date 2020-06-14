package jlopez.com.recyclerviewtest.SimpleRecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jlopez.com.recyclerviewtest.R;
import jlopez.com.recyclerviewtest.shared.Pet;

public class SimpleRecyclerActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnDelete;
    RecyclerView recyclerViewPets;
    EditText editTextName;
    EditText editTextAge;
    EditText editTextIndex;
    AdaptaderRecyclerView adaptaderRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        recyclerViewPets = findViewById(R.id.recyclerViewPets);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextIndex = findViewById(R.id.editTextIndex);

        adaptaderRecyclerView = new AdaptaderRecyclerView(new InterfaceClickRecyclerView() {
            @Override
            public void onClick(View v, Pet p) {
                Toast.makeText(SimpleRecyclerActivity.this, p.toString(), Toast.LENGTH_LONG).show();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SimpleRecyclerActivity.this);
        recyclerViewPets.setLayoutManager(linearLayoutManager);

        recyclerViewPets.addItemDecoration(new DividerItemDecoration(SimpleRecyclerActivity.this, LinearLayoutManager.VERTICAL));

        recyclerViewPets.setAdapter(adaptaderRecyclerView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPet();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePet();
            }
        });

    }

    private void deletePet() {
        String index = editTextIndex.getText().toString();
        if (index.isEmpty()) {
            Toast.makeText(SimpleRecyclerActivity.this, "Write the index", Toast.LENGTH_LONG).show();
            return;
        }

        adaptaderRecyclerView.deletePet(Integer.valueOf(index));

    }


    private void createPet() {
        String name  = editTextName.getText().toString();
        String age = editTextAge.getText().toString();

        if (name.isEmpty() || age.isEmpty()) {
            Toast.makeText(SimpleRecyclerActivity.this, "Fill the fields", Toast.LENGTH_LONG).show();
            return;
        }
        adaptaderRecyclerView.addPet(new Pet(name, Integer.valueOf(age)));
    }
}
