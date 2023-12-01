package com.example.tarefasfront;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TarefaAdapter extends ArrayAdapter<Tarefa> {
    private TarefaService tarefaService;
    public TarefaAdapter(Context context, List<Tarefa> tarefas) {
        super(context, 0, tarefas);
        this.tarefaService = new TarefaService();
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
            tarefaService.deleteTarefa(tarefa.getId(), new TarefaCallback() {
                @Override
                public void onResult(List<Tarefa> listaTarefas) {
                }

                @Override
                public void onSuccess() {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            remove(tarefa);
                            notifyDataSetChanged();
                        }
                    });
                }

                @Override
                public void onError(Exception e) {
                    Log.e("TarefaAdapter", "Erro ao excluir tarefa", e);
                }
            });
        });


        return convertView;
    }
}
