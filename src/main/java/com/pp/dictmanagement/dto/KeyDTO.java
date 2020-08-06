package com.pp.dictmanagement.dto;

import com.pp.dictmanagement.enums.KeyType;
import com.pp.dictmanagement.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyDTO {

    private UUID id;
    private String userId;
    private KeyType keyType;
    private String keyValue;
    private Status status;
}
