package com.pp.dictmanagement.service;

import com.pp.dictmanagement.dto.KeyDTO;
import com.pp.dictmanagement.entity.Key;
import com.pp.dictmanagement.repository.KeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeyServiceImpl implements KeyService {

    private final KeyRepository repository;

    public KeyDTO createKey(Key key){

        List<Key> userKeys = listKeysByUser(key.getId());

        if(userKeys.size() >= 5)
            throw new RuntimeException("Key limit activated");

        return repository.save(key).toKeyDTO();
    }

    public List<Key> listKeysByUser(UUID userId){
        return repository.findByUserId(userId);
    }

    private void validatesTypes(List<Key> userKeys, Key key){
        switch (key.getKeyType()) {
            case CPF -> validatesCpf(key, userKeys);
            case CNPJ -> validatesCnpj(key, userKeys);
            case EMAIL -> validatesEmail(key);
            case PHONE -> validatesPhone(key);
            default -> throw new RuntimeException("Invalid key type");
        }
    }

    private void validatesCpf(Key key, List<Key> keys){
        //verificar se a chave ja exite em outro lugar
        //verificar se é um cpf valido
    }
    private void validatesCnpj(Key key, List<Key> keys){
        //verificar se a chave ja exite em outro lugar
        //verificar se é um cpf valido
    }
    private void validatesEmail(Key key){
        //verificar se a chave ja exite em outro lugar
    }
    private void validatesPhone(Key key){
        //verificar se a chave ja exite em outro lugar
    }
}
