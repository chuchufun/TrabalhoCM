package com.example.chuch.trabalho;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DoacaoAdapter extends RecyclerView.Adapter<DoacaoAdapter.ViewHolder>{

    private Context context;
    private List<Doacao> list;

    public DoacaoAdapter(Context context, List<Doacao> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Doacao doacao = list.get(i);

        viewHolder.textTitle.setText( String.valueOf(doacao.getData()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;

        public ViewHolder(View itemView){
            super(itemView);

            textTitle = itemView.findViewById(R.id.main_title);
        }

    }
}
