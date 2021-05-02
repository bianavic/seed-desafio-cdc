package com.jornadadev.casadocodigo.purchase;

import com.jornadadev.casadocodigo.countrystates.Country;
import com.jornadadev.casadocodigo.countrystates.State;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Purchase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String document;
    @NotBlank
    private String address;
    @NotBlank
    private String complement;
    @NotBlank
    private String city;
    @ManyToOne
    @NotNull
    private Country country;
    @ManyToOne
    private State state;
    @NotBlank
    private String phone;
    @NotBlank
    private String cep;

    @Deprecated
    public Purchase(@NotBlank @Email String email, @NotBlank String name, @NotBlank String surname, @NotBlank String document, @NotBlank String complement, @NotBlank String address, @NotBlank String city, @NotNull Long countryId, Long stateId, @NotBlank String phone, @NotBlank String cep) {}

    public Purchase(String email, String name, String surname, String document, String address, String complement, String city, Country country, State state, String phone, String cep) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.country = country;
        this.state = state;
        this.phone = phone;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", document='" + document + '\'' +
                ", address='" + address + '\'' +
                ", complement='" + complement + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", state=" + state +
                ", phone='" + phone + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }

}
