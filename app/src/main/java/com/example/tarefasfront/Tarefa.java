package com.example.tarefasfront;

public class Tarefa {
    private int id;
    private String descricao;

    // Construtor, getters e setters
    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

}
