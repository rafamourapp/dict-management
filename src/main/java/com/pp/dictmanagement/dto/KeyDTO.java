package com.pp.dictmanagement.dto;

import com.pp.dictmanagement.enums.KeyType;
import com.pp.dictmanagement.enums.Status;

import java.util.UUID;

public class KeyDTO {

    private UUID id;
    private String userId;
    private KeyType keyType;
    private String keyValue;
    private Status status;
}
