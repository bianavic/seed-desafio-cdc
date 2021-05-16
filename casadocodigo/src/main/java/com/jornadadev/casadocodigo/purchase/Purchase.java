package com.jornadadev.casadocodigo.purchase;

import com.jornadadev.casadocodigo.countrystate.Country;
import com.jornadadev.casadocodigo.countrystate.State;
import com.jornadadev.casadocodigo.coupon.Coupon;
import com.jornadadev.casadocodigo.coupon.CouponApplied;
import com.jornadadev.casadocodigo.order.Order;
import java.util.function.Function;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToOne(mappedBy = "purchase", cascade = CascadeType.PERSIST)
    private Order order;
    @Embedded
    private CouponApplied couponApplied;

    public Purchase(
        @NotBlank @Email String email, @NotBlank String name,
        @NotBlank String surname, @NotBlank String document,
        @NotBlank String address,
        @NotBlank String complement,
        @NotNull Country country, @NotBlank String phone,
        @NotBlank String cep,
        Function<Purchase, Order> createOrderFunction) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.country = country;
        this.phone = phone;
        this.cep = cep;
        this.order = createOrderFunction.apply(this);
    }

    @Override
    public String toString() {
        return "Purchase[" +
            ", id= + id +"
            + "email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", document='" + document + '\'' +
            ", surname='" + surname + '\'' +
            ", address='" + address + '\'' +
            ", country=" + country +
            ", state=" + state +
            ", phone='" + phone + '\'' +
            ", cep='" + cep + '\'' +
            ", complement='" + complement + '\'' +
            ", order=" + order +
            ", couponApplied=" + couponApplied +
            ']';
    }

    public void setState(@NotNull @Valid State state) {
        Assert.notNull(country, "You cant associate a state if a country is null");
        Assert.isTrue(state.belongsToCountry(country),
            "This state is not from the Country associated with the purchase");
        this.state = state;
    }

    // TODO melhorar validacao cupons
    public void applyCoupon(Coupon coupon) {
        Assert.isTrue(coupon.valid(), "This coupon isn't valid anymore");
        Assert.isNull(couponApplied, "You can't change your coupon between purchases");
        this.couponApplied = new CouponApplied(coupon);
    }

}
