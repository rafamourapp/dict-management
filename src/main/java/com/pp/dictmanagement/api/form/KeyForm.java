package com.pp.dictmanagement.api.form;

import com.pp.dictmanagement.enums.KeyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeyForm {

    private String userId;
    private KeyType keyType;
    private String keyValue;

}
