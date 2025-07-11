package com.hotel.reservation.controller;

import com.hotel.reservation.dto.CheckInRequest;
import com.hotel.reservation.dto.CheckInResponse;
import com.hotel.reservation.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkin")
@CrossOrigin(origins = "*")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping
    public ResponseEntity<CheckInResponse> checkIn(@RequestBody CheckInRequest request) {
        CheckInResponse response = checkInService.processCheckIn(request);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}