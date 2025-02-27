package aula.continuandocomspringnoturno.Modelo.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

public class Noticia {
    private int id;
    private String titulo;
    private String lide;
    private String corpo;
    private Timestamp data;
    private Reporter reporter;

    public Noticia() {
    }

    public Noticia(int id, String titulo, String lide, String corpo, Timestamp data, Reporter reporter) {
        this.id = id;
        this.titulo = titulo;
        this.lide = lide;
        this.corpo = corpo;
        this.data = data;
        this.reporter = reporter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLide() {
        return lide;
    }

    public void setLide(String lide) {
        this.lide = lide;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Noticia noticia = (Noticia) o;
        return id == noticia.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}