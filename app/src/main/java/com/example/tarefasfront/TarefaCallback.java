package com.example.tarefasfront;

import java.util.List;

public interface TarefaCallback {
    void onResult(List<Tarefa> listaTarefas);
    void onError(Exception e);
}
