package jlopez.com.recyclerviewtest.shared;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private String name;
    private int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static List<Pet> dummyData() {
        List<Pet> pets = new ArrayList<>();

        for(int i=0; i<50; i++) {
            pets.add(new Pet("Pet " + i, i));
        }

        return pets;
    }

}
