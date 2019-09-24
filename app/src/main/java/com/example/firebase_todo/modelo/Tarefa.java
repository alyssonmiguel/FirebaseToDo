package com.example.firebase_todo.modelo;

public class Tarefa {

    private String uid;
    private String nome;
    private String imageSrc = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT1ypFx9ibrs38nFQpsiu567028P_7wyikYTsVhVmHSb3nN5MkS";
    private boolean status;

    public Tarefa(){

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
