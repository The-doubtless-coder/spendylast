//package com.spenndify.application.spendylast.wallet.importing;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//@Data
//@Entity
//@NoArgsConstructor
//public class StoredMessages {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stored_msg")
//    @SequenceGenerator(name = "stored_msg", sequenceName = "stored_msg", allocationSize = 1)
//    @Column(name = "id", nullable = false)
//    private Long id;
//    private String _id;
//    private String _address;
//    private String _msg;
//    private String _readState; //"0" for have not read sms and "1" for have read sms
//    private String _time;
//    private String _folderName;
//
//    public StoredMessages(String _id, String _address, String _msg, String _readState, String _time, String _folderName) {
//        this._id = _id;
//        this._address = _address;
//        this._msg = _msg;
//        this._readState = _readState;
//        this._time = _time;
//        this._folderName = _folderName;
//    }
//}
