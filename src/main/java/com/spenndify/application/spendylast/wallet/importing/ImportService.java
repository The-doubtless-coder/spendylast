//package com.spenndify.application.spendylast.wallet.importing;
//
//import lombok.AllArgsConstructor;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class ImportService {
//    private final SmsRepository smsRepository;
//
//    public void saveRecords(@NotNull SmsRequest smsRequest) {
//        StoredMessages messages = new StoredMessages(smsRequest.get_id(),
//                smsRequest.get_address(),
//                smsRequest.get_msg(),
//                smsRequest.get_readState(),
//                smsRequest.get_time(),
//                smsRequest.get_folderName());
//
//        smsRepository.save(messages);
//
//    }
//
//}
