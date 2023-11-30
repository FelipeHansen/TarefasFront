package com.example.tarefasfront;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.widget.Button;
import android.widget.EditText;


import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nomeTarefaText = findViewById(R.id.nomeTarefaText);
        Button buttonCriarTarefa = findViewById(R.id.buttonCriarTarefa);

        buttonCriarTarefa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String descricaoTarefa = nomeTarefaText.getText().toString();
                criarTarefa(descricaoTarefa);
            }
        });
    }

    private void criarTarefa(String descricao) {
        Log.d("CriarTarefa", "Iniciando o método criarTarefa");

        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, "{\"descricao\": \"" + descricao + "\"}");
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/tarefas")
                    .post(body)
                    .build();

            try {
                Log.d("CriarTarefa", "Enviando requisição HTTP");
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                Log.d("CriarTarefa", "Resposta recebida: " + responseData);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("CriarTarefa", "Erro: " + e.getMessage());
            }
        }).start();

        Log.d("CriarTarefa", "Método criarTarefa finalizado");
    }
}
