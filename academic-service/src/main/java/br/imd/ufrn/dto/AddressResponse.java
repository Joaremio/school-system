package br.imd.ufrn.dto;

public record AddressResponse(
        Long id,
        String street,
        String number,
        String city
) {}