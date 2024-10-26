package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class autor {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private int id_autor;

    @Column(name= "Nombre_autor")
    private String nombre_autor;

    @Column(name= "Correo_autor")
    private String correo_autor;


    //Constructor y metodos

    public autor() {}

    public Integer getId_autor() {return id_autor;}

    public void setId_autor(Integer id_autor) {this.id_autor = id_autor;}

    public String getNombre_autor() {return nombre_autor;}

    public void setNombre_autor(String nombre_autor) {this.nombre_autor = nombre_autor;}

    public String getCorreo_autor() {return correo_autor;}

    public void setCorreo_autor(String correo_autor) {this.correo_autor = correo_autor;}

}
