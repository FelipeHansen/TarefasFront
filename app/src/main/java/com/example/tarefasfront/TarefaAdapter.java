package com.example.tarefasfront;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TarefaAdapter extends ArrayAdapter<Tarefa> {
    public TarefaAdapter(Context context, List<Tarefa> tarefas) {
        super(context, 0, tarefas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tarefa tarefa = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tarefa_item, parent, false);
        }

        TextView textViewDescricao = convertView.findViewById(R.id.textViewDescricao);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewDescricao.setText(tarefa.getDescricao());
        buttonDelete.setOnClickListener(v -> {
            // Implemente a lógica de exclusão aqui
            remove(tarefa); // Remove o item da lista
            notifyDataSetChanged(); // Notifica que os dados foram alterados
        });

        return convertView;
    }
}
