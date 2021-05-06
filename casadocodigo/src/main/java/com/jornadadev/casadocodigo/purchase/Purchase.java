package com.jornadadev.casadocodigo.purchase;

import com.jornadadev.casadocodigo.countrystate.Country;
import com.jornadadev.casadocodigo.countrystate.State;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;

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
    public Purchase(
        @NotBlank @Email String email, @NotBlank String name,
        @NotBlank String surname, @NotBlank String document,
        @NotBlank String address,
        @NotBlank String complement,
        @NotNull Country country, @NotBlank String phone,
        @NotBlank String cep) {}

    public Purchase(String email, String name, String surname, String document,
        String address, String complement, Country country,
        State state, String phone, String cep) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.country = country;
        this.state = state;
        this.phone = phone;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Purchase{" +
            ", email='" + email + '\'' +
            "id=" + id +
            ", name='" + name + '\'' +
            ", document='" + document + '\'' +
            ", surname='" + surname + '\'' +
            ", address='" + address + '\'' +
            ", country=" + country +
            ", state=" + state +
            ", phone='" + phone + '\'' +
            ", cep='" + cep + '\'' +
            ", complement='" + complement + '\'' +
            '}';
    }

    public void setState(@NotNull @Valid State state) {
        Assert.notNull(country, "You cant associate a state while country is null");
        Assert.isTrue(state.belongsToCountry(country), "This state is not from the Country associated with the purchase");
        this.state = state;
    }
}
