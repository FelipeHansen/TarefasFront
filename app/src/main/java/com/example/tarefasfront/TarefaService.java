package com.example.tarefasfront;

import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class TarefaService {

    public void criarTarefa(String descricao) {
        Log.d("TarefaService", "Iniciando o método criarTarefa");

        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, "{\"descricao\": \"" + descricao + "\"}");
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/tarefas")
                    .post(body)
                    .build();

            try {
                Log.d("TarefaService", "Enviando requisição HTTP");
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                Log.d("TarefaService", "Resposta recebida: " + responseData);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("TarefaService", "Erro: " + e.getMessage());
            }
        }).start();

        Log.d("TarefaService", "Método criarTarefa finalizado");
    }
}