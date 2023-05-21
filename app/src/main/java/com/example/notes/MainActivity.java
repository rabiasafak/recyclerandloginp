package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.notes.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        RecyclerView recyclerView=findViewById(R.id.recyclerview);

        List <Item> items= new ArrayList<Item>();
        items.add(new Item("Mühendislik F.","muhendislik@ibu.edu.tr","Prof. Dr. Ömer ÖZYURT",R.drawable.a));
        items.add(new Item("Eğitim F.","egitim_fakultesi@ibu.edu.tr","Prof. Dr. Erkan TEKİNARSLAN",R.drawable.b));
        items.add(new Item("Fen Edebiyat F.","fef@ibu.edu.tr","Prof. Dr. Ekrem GÜREL",R.drawable.c));
        items.add(new Item("İlahiyat F.","ilahiyat@ibu.edu.tr","Prof. Dr. Bilal GÖKKIR",R.drawable.d));
        items.add(new Item("Hukuk F.","hukuk.fakultesi@ibu.edu.tr","Prof. Dr. Şaban KAYIHAN",R.drawable.e));
        items.add(new Item("Tıp F.","aibutipdekanlik@ibu.edu.tr","Prof. Dr. Muhammet Güzel KURTOĞLU",R.drawable.f));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new myAdapter(getApplicationContext(),items));
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item ->{


            switch (item.getItemId()){
                case R.id.menu:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.duyuru:
                    replaceFragment(new AnnouncementFragment());
                    break;

            }
            return true;

        });


    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }

}