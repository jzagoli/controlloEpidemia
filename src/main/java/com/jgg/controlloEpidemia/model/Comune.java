package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comune {

    @Id
    @Getter
    @Setter
    @NotNull
    private Integer codiceIstat;

    @NotNull
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Getter
    @Setter
    private Date dataIstituzione;

    @NotNull
    @Getter
    @Setter
    private Integer superficie;

    @NotNull
    @Getter
    @Setter
    private Boolean siAffacciaSulMare;

    @NotNull
    @Getter
    @Setter
    @ManyToOne
    private TipoTerritorio tipoTerritorio;

    @NotNull
    @Getter
    @OneToMany
    final private List<MalattieSettimanali> malattieSettimanali = new ArrayList<>();

}
