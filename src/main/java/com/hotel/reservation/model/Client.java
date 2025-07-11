package com.hotel.reservation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Client {
    @JsonProperty("idClient")
    private Integer idClient;
    private String nom;
    private String prenom;
    private String email;

    // Constructeurs
    public Client() {}

    // Getters et Setters
    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}