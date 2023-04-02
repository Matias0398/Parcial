package com.example.parcial.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="detalleOrden")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="cantidad")
    private int cantidad;

    @Column(name="precio")
    private double precio;

    @Column(name="total")
    private double total;

    @ManyToOne
    private Orden orden;

    @ManyToOne
    private Producto producto;
}
