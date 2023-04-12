package com.ani.rest.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ani.rest.domain.BankAccount;
import com.ani.rest.dto.BankAccountDto;
import com.ani.rest.repository.BankAccountRepository;
import com.ani.rest.util.DmDtConverter;

import lombok.AllArgsConstructor;

@AllArgsConstructor // to enable constructor injection
@Service
public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccountRepository repository;
    private final DmDtConverter converter;

    @Override
    public Integer createNewAccount(BankAccountDto dto) {
        repository.save(converter.toDomain(dto));
        return 1;
    }

    @Override
    public Collection<BankAccountDto> findAll() {
        return repository.findAll()
                            .stream()
                            .map(ba -> converter.toDto(ba))
                            .collect(Collectors.toList());
    }
}