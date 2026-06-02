package com.example.dao;

import com.example.model.StatusMaster;

public class StatusMasterDao {
    public StatusMaster findByCode(String code) {
        // TODO: lookup status master from DB
        return new StatusMaster(code, code);
    }
}
