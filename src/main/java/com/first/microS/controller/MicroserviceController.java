package com.first.microS.controller;

import com.first.microS.accountServices.AccountService;
import com.first.microS.constants.AccountConstants;
import com.first.microS.dto.CustomerDto;
import com.first.microS.dto.SuccessResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MicroserviceController {

    private AccountService accountService;

    /**
     * Endpoint to create a new account.
     * <p>
     * This endpoint receives a POST request and returns a JSON response with the
     * status code 201 indicating that the account has been created successfully.
     *
     * @return ResponseEntity containing a SuccessResponseDto object with the status
     *         code 201.
     */
    @PostMapping("/createNewCustomer")
    public ResponseEntity<SuccessResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        SuccessResponseDto response = new SuccessResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
