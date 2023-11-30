package com.example.tarefasfront;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private TarefaService tarefaService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tarefaService = new TarefaService();

        EditText nomeTarefaText = findViewById(R.id.nomeTarefaText);
        Button buttonCriarTarefa = findViewById(R.id.buttonCriarTarefa);

        buttonCriarTarefa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String descricaoTarefa = nomeTarefaText.getText().toString();
                tarefaService.criarTarefa(descricaoTarefa);
            }
        });
    }
}
