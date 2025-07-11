package com.hotel.reservation.service;

import com.hotel.reservation.model.Reservation;
import com.hotel.reservation.dto.CheckInRequest;
import com.hotel.reservation.dto.CheckInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckInService {

    @Autowired
    private PmsService pmsService;

    public CheckInResponse processCheckIn(CheckInRequest request) {
        try {
            // Rechercher la réservation par numéro d'identité
            Optional<Reservation> reservationOpt = pmsService.findReservationByClientIdentity(request.getNumeroIdentite());

            if (reservationOpt.isEmpty()) {
                return new CheckInResponse(false, "Aucune réservation trouvée pour ce numéro d'identité");
            }

            Reservation reservation = reservationOpt.get();

            // Vérifier si le check-in est possible
            if ("CHECKED_IN".equals(reservation.getStatut())) {
                return new CheckInResponse(false, "Check-in déjà effectué pour cette réservation");
            }

            // Effectuer le check-in
            boolean updateSuccess = pmsService.updateReservationStatus(reservation.getIdReservation(), "CHECKED_IN");

            if (updateSuccess) {
                return new CheckInResponse(true, "Check-in effectué avec succès", reservation.getIdReservation());
            } else {
                return new CheckInResponse(false, "Erreur lors de la mise à jour du statut de réservation");
            }

        } catch (Exception e) {
            return new CheckInResponse(false, "Erreur technique: " + e.getMessage());
        }
    }
}