package com.example.firebase_todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firebase_todo.R;
import com.example.firebase_todo.modelo.Tarefa;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TarefaAdapter extends ArrayAdapter<Tarefa> {

    private Context context;
    private List<Tarefa> tarefas;

    public TarefaAdapter(Context context, ArrayList<Tarefa> tarefas){
        super(context, 0, tarefas);
        this.context = context;
        this.tarefas = tarefas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listaItem = convertView;
        if (listaItem == null){
            listaItem = LayoutInflater.from(context)
                    .inflate(R.layout.layout_lista_tarefa, parent, false);
        }

        Tarefa tarefaSelecionada = tarefas.get(position);

        TextView textViewNome = listaItem.findViewById(R.id.textViewNome);
        TextView textViewStatus = listaItem.findViewById(R.id.textViewStatus);

        textViewNome.setText(tarefaSelecionada.getNome());
        textViewStatus.setText(tarefaSelecionada.isStatus() ? "Concluída" : "Não Concluída");

        ImageView imageView = listaItem.findViewById(R.id.imageViewTarefa);
        Picasso.get()
                .load(tarefaSelecionada.getImageSrc())
                .resize(200,200)
                .centerCrop()
                .into(imageView);

        return listaItem;
    }
}
