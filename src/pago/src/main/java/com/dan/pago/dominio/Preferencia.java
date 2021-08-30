package com.dan.pago.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Preferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    private Instant timestamp;

    public Preferencia() {
        this.timestamp = Instant.now();
    }

    public Preferencia(String url) {
        this.url = url;
        this.timestamp = Instant.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant fechaHora) {
        this.timestamp = fechaHora;
    }
}
