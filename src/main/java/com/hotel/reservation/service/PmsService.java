package com.hotel.reservation.service;

import com.hotel.reservation.model.Reservation;
import com.hotel.reservation.model.Client;
import com.hotel.reservation.model.Chambre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PmsService {

    @Value("${pms.base.url:http://localhost:3000}")
    private String pmsBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Reservation> getAllReservations() {
        try {
            Reservation[] reservations = restTemplate.getForObject(pmsBaseUrl + "/reservations", Reservation[].class);
            return Arrays.asList(reservations != null ? reservations : new Reservation[0]);
        } catch (RestClientException e) {
            throw new RuntimeException("Erreur lors de la récupération des réservations: " + e.getMessage());
        }
    }

    public Optional<Reservation> findReservationByClientIdentity(String numeroIdentite) {
        try {
            // Récupérer toutes les réservations
            List<Reservation> reservations = getAllReservations();

            // Filtrer par numéro d'identité (simulation - dans un vrai PMS, il faudrait une API dédiée)
            return reservations.stream()
                    .filter(r -> r.getClient() != null &&
                            numeroIdentite.equals(r.getClient().getEmail())) // Simulation avec email
                    .filter(r -> "CONFIRMED".equals(r.getStatut()))
                    .filter(r -> isArrivalToday(r.getDateArrivee()))
                    .findFirst();

        } catch (RestClientException e) {
            throw new RuntimeException("Erreur lors de la recherche de réservation: " + e.getMessage());
        }
    }

    public boolean updateReservationStatus(Integer reservationId, String newStatus) {
        try {
            String url = pmsBaseUrl + "/reservations/" + reservationId;

            // Récupérer la réservation actuelle
            Reservation reservation = restTemplate.getForObject(url, Reservation.class);
            if (reservation == null) {
                return false;
            }

            // Mettre à jour le statut
            reservation.setStatut(newStatus);

            // Envoyer la mise à jour
            HttpEntity<Reservation> requestEntity = new HttpEntity<>(reservation);
            ResponseEntity<Reservation> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Reservation.class);

            return response.getStatusCode().is2xxSuccessful();

        } catch (RestClientException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la réservation: " + e.getMessage());
        }
    }

    public Optional<Reservation> getReservationWithDetails(Integer reservationId) {
        try {
            Reservation reservation = restTemplate.getForObject(pmsBaseUrl + "/reservations/" + reservationId, Reservation.class);

            if (reservation != null) {
                // Récupérer les détails du client et de la chambre
                if (reservation.getClientId() != null) {
                    Client client = restTemplate.getForObject(pmsBaseUrl + "/clients/" + reservation.getClientId(), Client.class);
                    reservation.setClient(client);
                }

                if (reservation.getRoomId() != null) {
                    Chambre chambre = restTemplate.getForObject(pmsBaseUrl + "/chambres/" + reservation.getRoomId(), Chambre.class);
                    reservation.setChambre(chambre);
                }
            }

            return Optional.ofNullable(reservation);

        } catch (RestClientException e) {
            throw new RuntimeException("Erreur lors de la récupération des détails de réservation: " + e.getMessage());
        }
    }

    private boolean isArrivalToday(LocalDate dateArrivee) {
        return dateArrivee != null && dateArrivee.equals(LocalDate.now());
    }
}