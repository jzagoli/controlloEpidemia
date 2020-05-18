package com.jgg.controlloEpidemia.model;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TipoTerritorio {

    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
    private Integer id;

    @NotNull
    @Getter
    @Setter
    private String nome;
}
