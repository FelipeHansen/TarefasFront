package com.example.tarefasfront;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import android.util.Log;
import android.widget.ListView;

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
                runOnUiThread(() -> {
                    ListView listView = findViewById(R.id.listViewTarefas);
                    TarefaAdapter adapter = new TarefaAdapter(ListaDeTarefas.this, listaTarefas);
                    listView.setAdapter(adapter);
                });
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }

}
