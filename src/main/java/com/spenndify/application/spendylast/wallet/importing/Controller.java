//package com.spenndify.application.spendylast.wallet.importing;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController("/receive/sms")
//@AllArgsConstructor
//public class Controller {
//    private final ImportService importService;
//    @PostMapping
//    public ResponseEntity<String> getSmsMessages(SmsRequest smsRequest){
//        importService.saveRecords(smsRequest);
//        return new ResponseEntity<String>("user imports successfully saved", HttpStatus.CREATED);
//    }
//}
