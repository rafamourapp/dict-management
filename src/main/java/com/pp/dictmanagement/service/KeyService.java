package com.pp.dictmanagement.service;

import com.pp.dictmanagement.dto.KeyDTO;
import com.pp.dictmanagement.entity.Key;
import com.pp.dictmanagement.repository.KeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeyService {

    private final KeyRepository repository;

    public KeyDTO create(Key key){

        List<Key> userKeys = listKeysByUser(key.getUserId());

        checkIfUserAlreadyHasKeys(userKeys, key);
        checkKeyLimit(userKeys);
        validatesTypes(userKeys,key);
        checkKeyRecorder(key);

        return repository.save(key).toKeyDTO();
    }

    public List<Key> listKeysByUser(String userId){
        return repository.findAllActivatedByUser(userId);
    }

    //verifica se já existem 5 chaves
    private void checkKeyLimit(List<Key> keys){
        if(keys.size() >= 5)
            throw new RuntimeException("Key limit activated");
    }

    //verifica se o usuario já possui essa chave
    private void checkIfUserAlreadyHasKeys(List<Key> userKeys,Key key){
        if(userKeys.stream().anyMatch(k -> k.getKeyValue().equals(key.getKeyValue())))
            throw new RuntimeException("This key is already active for this user");
    }

    //verifica se a chave já esta registrada por outro usuario
    private void checkKeyRecorder(Key key){
        Optional<Key> existent = repository.findByKeyValue(key.getKeyValue());

        if(existent.isPresent())
            throw new RuntimeException("Key already registered with another user");
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
        //verifica se o usuario já tem cpf cadastrado
        //verificar se é um cpf valido
    }
    private void validatesCnpj(Key key, List<Key> keys){
        //verifica se o usuario já tem cnpj cadastrado
        //verificar se é um cpf valido
    }
    private void validatesEmail(Key key){
        //envia email?
    }
    private void validatesPhone(Key key){
        //envia SMS?
    }
}
