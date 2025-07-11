package com.hotel.reservation.dto;

public class CheckInResponse {
    private boolean success;
    private String message;
    private Integer reservationId;

    public CheckInResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CheckInResponse(boolean success, String message, Integer reservationId) {
        this.success = success;
        this.message = message;
        this.reservationId = reservationId;
    }

    // Getters et Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Integer getReservationId() { return reservationId; }
    public void setReservationId(Integer reservationId) { this.reservationId = reservationId; }
}