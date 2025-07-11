package com.hotel.reservation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Chambre {
    @JsonProperty("idChambre")
    private Integer idChambre;
    private Integer numeroChambre;
    private String type;
    private String statut;

    // Constructeurs
    public Chambre() {}

    // Getters et Setters
    public Integer getIdChambre() { return idChambre; }
    public void setIdChambre(Integer idChambre) { this.idChambre = idChambre; }
    public Integer getNumeroChambre() { return numeroChambre; }
    public void setNumeroChambre(Integer numeroChambre) { this.numeroChambre = numeroChambre; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}