package com.example.tarefasfront;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import android.util.Log;

public class ListaDeTarefas extends AppCompatActivity {

    private TarefaService tarefaService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_tarefas);

        tarefaService = new TarefaService();

        tarefaService.listarTarefas(new TarefaCallback() {
            @Override
            public void onResult(List<Tarefa> listaTarefas) {
                for (Tarefa tarefa : listaTarefas) {
                    Log.d("ListaTarefas", "Tarefa: ID=" + tarefa.getId() + ", Descrição=" + tarefa.getDescricao());
                }
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }

}
