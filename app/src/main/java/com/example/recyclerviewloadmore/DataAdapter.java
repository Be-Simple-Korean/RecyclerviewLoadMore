package com.example.recyclerviewloadmore;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Data> dar;
    int size;

    public DataAdapter(int size,ArrayList<Data> dar) {
        this.size=size;
        this.dar = dar;
        Log.e("get size",dar.size()+"");

    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else if(size>dar.size()&&position==dar.size()-1){
            Log.e("수행","수행");
            return 1;
        }else{
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if(viewType==0){
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.head_item,parent,false);
            return new HeaderHolder(v);
        }else if(viewType==1){
            Log.e("수행","수행2");
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.more_item,parent,false);
            return new LoadMoreHolder(v);
        }else{
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
            CustomViewHolder holder=new CustomViewHolder(v);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("pos",position+"");
        Log.e("cur data",dar.get(position).getData());
        int type=getItemViewType(position);
        switch (type){
            case 0:
                //헤더 이벤트처리
                break;
            case 1:
                //버튼 처리
                if(position==dar.size()-1&&size==dar.size()-1){
                    ((LoadMoreHolder)holder).btn.setVisibility(View.GONE);
                }
                ((LoadMoreHolder)holder).btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dar.remove(dar.size()-1);
                        MainActivity main=new MainActivity();
                        Log.e("수행","수행 버튼 클릭");
                        main.getData(dar);
                    }
                });
                Log.e("여기는","ㅇㅁㄴㅇ");
                break;
            case 2:
                ((CustomViewHolder)holder).text.setText(dar.get(position).getData());
                break;
        }
    }


    @Override
    public int getItemCount() {
        return dar.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView text;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text);
        }
    }
    public class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class LoadMoreHolder extends RecyclerView.ViewHolder {
        protected Button btn;
        public LoadMoreHolder(@NonNull View itemView) {
            super(itemView);
            btn=itemView.findViewById(R.id.btn);
        }
    }
}
