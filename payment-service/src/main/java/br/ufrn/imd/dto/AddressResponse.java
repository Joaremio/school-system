package br.ufrn.imd.dto;

public record AddressResponse(
        Long id,
        String street,
        String number,
        String city
) {}