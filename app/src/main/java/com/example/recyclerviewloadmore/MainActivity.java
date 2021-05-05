package com.example.recyclerviewloadmore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Data> sar;
    public static DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        ScrollView scrollView;

        ArrayList<Data> mar = new ArrayList<>();
        sar = new ArrayList<>();
        mar.add(new Data("")); //헤더처리
        for(int i=1;i<=20;i++){
            sar.add(new Data(String.valueOf(i)));
        }
        for(int i=0;i<15;i++){
            mar.add(sar.get(i));
        }

        for(int i=0;i<15;i++){
           sar.remove(0);
        }
        int size=20;
        if(size>15){
            mar.add(new Data(""));
        }
        Log.e("size",mar.size()+"");
        Log.e("last data",sar.get(sar.size()-1).getData()+"");
        dataAdapter = new DataAdapter(size,mar);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(layoutManager);

    }
    public void getData(ArrayList<Data> dar){
        Log.e("getData sar size",sar.size()+"");
        for(int i=0;i<sar.size();i++){
            dar.add(sar.get(i));
        }
        dataAdapter.notifyDataSetChanged();
    }
}