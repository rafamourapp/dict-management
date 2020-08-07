package com.pp.dictmanagement.service;

import com.pp.dictmanagement.api.form.KeyForm;
import com.pp.dictmanagement.dto.KeyDTO;
import com.pp.dictmanagement.entity.Key;
import com.pp.dictmanagement.repository.KeyRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeyService {

    private final KeyRepository repository;

    public KeyDTO create(KeyForm keyForm){

        List<Key> userKeys = listKeysByUser(keyForm.getUserId());

        checkIfUserAlreadyHasKeys(userKeys, keyForm);
        checkKeyLimit(userKeys);
        validatesTypes(userKeys,keyForm);
        checkKeyRecorder(keyForm);

        checklinkAtBacen(keyForm);

        Key key = new Key(keyForm);
        return repository.save(key).toKeyDTO();
    }

    public List<Key> listKeysByUser(String userId){
        return repository.findAllActivatedByUser(userId);
    }

    //verifica se já existem 5 chaves
    private void checkKeyLimit(List<Key> keys){
        if(keys.size() >= 5)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Key limit activated");
    }

    //verifica se o usuario já possui essa chave
    private void checkIfUserAlreadyHasKeys(List<Key> userKeys,KeyForm key){
        if(userKeys.stream().anyMatch(k -> k.getKeyValue().equals(key.getKeyValue())))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This key is already active for this user");
    }

    //verifica se a chave já esta registrada por outro usuario
    private void checkKeyRecorder(KeyForm key){
        Optional<Key> existent = repository.findByKeyValue(key.getKeyValue());

        if(existent.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Key already registered with another user");
    }

    private void checklinkAtBacen(KeyForm key){
        //verifica se a chave já possui vinculo no bacen
    }

    private void validatesTypes(List<Key> userKeys, KeyForm key){
        switch (key.getKeyType()) {
            case CPF -> validatesCpf(key, userKeys);
            case CNPJ -> validatesCnpj(key, userKeys);
            case EMAIL -> validatesEmail(key);
            case PHONE -> validatesPhone(key);
            default -> throw new ResponseStatusException(HttpStatus.CONFLICT, "Invalid key type");
        }
    }

    private void validatesCpf(KeyForm key, List<Key> keys){
        //verifica se o usuario já tem cpf cadastrado
        //verificar se é um cpf valido
    }
    private void validatesCnpj(KeyForm key, List<Key> keys){
        //verifica se o usuario já tem cnpj cadastrado
        //verificar se é um cpf valido
    }
    private void validatesEmail(KeyForm key){
        //envia email?
    }
    private void validatesPhone(KeyForm key){
        //envia SMS?
    }
}
