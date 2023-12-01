package com.example.tarefasfront;

import java.util.List;

public interface TarefaCallback {
    void onResult(List<Tarefa> listaTarefas);
    void onSuccess();
    void onError(Exception e);
}
