package com.example.tarefasfront;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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

    public void listarTarefas(TarefaCallback callback) {
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/tarefas")
                    .get()
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();

                Gson gson = new Gson();
                Type listType = new TypeToken<List<Tarefa>>(){}.getType();
                List<Tarefa> listaTarefas = gson.fromJson(responseData, listType);

                callback.onResult(listaTarefas);
            } catch (IOException e) {
                e.printStackTrace();
                callback.onError(e);
            }
        }).start();
    }

    public void deleteTarefa(int tarefaId, TarefaCallback callback) {
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/tarefas/" + tarefaId)
                    .delete()
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    callback.onSuccess();
                } else {
                    throw new IOException("Erro ao excluir a tarefa");
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onError(e);
            }
        }).start();
    }



}