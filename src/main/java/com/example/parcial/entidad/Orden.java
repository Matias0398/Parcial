package com.example.parcial.entidad;

import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="orden")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orden {
	
	//entidad orden
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="numero")
    private String numero;

    @Column(name="fechaCreacion")
    private Date fechaCreacion;

    @Column(name="fechaRecibida")
    private Date fechaRecibida;

    @Column(name="total")
    private double total;


    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;
}
