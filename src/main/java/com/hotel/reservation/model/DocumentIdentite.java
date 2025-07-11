package com.hotel.reservation.model;

import java.time.LocalDate;

public class DocumentIdentite {
    private Integer idDocument;
    private String numero;
    private LocalDate dateExpiration;

    // Constructeurs
    public DocumentIdentite() {}

    // Getters et Setters
    public Integer getIdDocument() { return idDocument; }
    public void setIdDocument(Integer idDocument) { this.idDocument = idDocument; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public LocalDate getDateExpiration() { return dateExpiration; }
    public void setDateExpiration(LocalDate dateExpiration) { this.dateExpiration = dateExpiration; }
}
