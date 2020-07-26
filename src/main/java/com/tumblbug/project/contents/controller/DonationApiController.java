package com.tumblbug.project.contents.controller;

import com.tumblbug.project.contents.dto.CreateDonation;
import com.tumblbug.project.contents.service.DonationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Donation")
@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationApiController {

    private final DonationService donationService;

    @ApiOperation(value = "후원 등룩")
    @ApiResponse(code = 204, message = "")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public ResponseEntity<?> createDonation(@Valid @RequestBody final CreateDonation req) {
        donationService.createDonation(req);
        return ResponseEntity.noContent().build();
    }
}
