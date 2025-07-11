package com.hotel.reservation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Reservation {
    @JsonProperty("idReservation")
    private Integer idReservation;
    private Integer clientId;
    private Integer roomId;
    private LocalDate dateArrivee;
    private LocalDate dateDepart;
    private String statut;

    // Relations
    private Client client;
    private Chambre chambre;

    // Constructeurs
    public Reservation() {}

    // Getters et Setters
    public Integer getIdReservation() { return idReservation; }
    public void setIdReservation(Integer idReservation) { this.idReservation = idReservation; }
    public Integer getClientId() { return clientId; }
    public void setClientId(Integer clientId) { this.clientId = clientId; }
    public Integer getRoomId() { return roomId; }
    public void setRoomId(Integer roomId) { this.roomId = roomId; }
    public LocalDate getDateArrivee() { return dateArrivee; }
    public void setDateArrivee(LocalDate dateArrivee) { this.dateArrivee = dateArrivee; }
    public LocalDate getDateDepart() { return dateDepart; }
    public void setDateDepart(LocalDate dateDepart) { this.dateDepart = dateDepart; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Chambre getChambre() { return chambre; }
    public void setChambre(Chambre chambre) { this.chambre = chambre; }
}

