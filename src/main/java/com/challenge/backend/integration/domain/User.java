package com.challenge.backend.integration.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Address address;
    private int id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private String phone;
    private int __v; // Ou pode ser um tipo mais adequado, dependendo do contexto

    public static class Address {
        private Geolocation geolocation;
        private String city;
        private String street;
        private int number;
        private String zipcode;

    }

    public static class Geolocation {
        private String lat;
        private String lon;

    }

    public static class Name {
        private String firstname;
        private String lastname;

    }

}
