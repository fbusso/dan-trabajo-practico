package com.dan.usuario.dominio;

import javax.persistence.*;

@Entity
public class Empleado implements Registrable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mail;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "usuario_cliente",
            joinColumns = {@JoinColumn(name = "cliente_id")},
            inverseJoinColumns = {@JoinColumn(name = "usuario_id")})
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String nombreUsuario() {
        return mail;
    }

    @Override
    public TipoUsuario tipoUsuario() {
        return TipoUsuario.VENDEDOR;
    }
}
