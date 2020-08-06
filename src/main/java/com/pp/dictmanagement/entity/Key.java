package com.pp.dictmanagement.entity;

import com.pp.dictmanagement.dto.KeyDTO;
import com.pp.dictmanagement.enums.KeyType;
import com.pp.dictmanagement.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_KEY")
public class Key implements Serializable {

    private static final long serialVersionUID = -7169638148702718527L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(updatable = false, nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false, nullable = false)
    private KeyType keyType;

    @Column(nullable = false)
    private String keyValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.CLAIMED;

    public KeyDTO toKeyDTO(){
        KeyDTO.builder()
                .id(this.id)
                .keyType(this.keyType)
                .keyValue(this.keyValue)
                .status(this.status)
                .userId(this.userId)
                .build();
    }

}
