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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getCustomerDtsByPhone")
    public ResponseEntity<CustomerDto> getCustomerDtsByPhone(@RequestHeader String customerPhone) {
        CustomerDto customerDto = accountService.getCustomerDtsByPhone(customerPhone);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<SuccessResponseDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if (isUpdated){
            SuccessResponseDto response = new SuccessResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new SuccessResponseDto(AccountConstants.STATUS_500,AccountConstants.MESSAGE_500));
    }
}
