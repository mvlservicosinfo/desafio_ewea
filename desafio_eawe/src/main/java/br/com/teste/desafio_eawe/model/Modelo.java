package br.com.teste.desafio_eawe.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Table(name="geraXML")
@Setter
@ToString
@EqualsAndHashCode
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer codigo;
    private LocalDate data;
    private String regiao_sigla;
    private double geracao_valor;
    private double compra_valor;
    private double precoMedio;

}
