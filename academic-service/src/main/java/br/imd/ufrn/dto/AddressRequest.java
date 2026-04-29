package br.imd.ufrn.dto;


public record AddressRequest(
        String street,
        String number,
        String city
) {}