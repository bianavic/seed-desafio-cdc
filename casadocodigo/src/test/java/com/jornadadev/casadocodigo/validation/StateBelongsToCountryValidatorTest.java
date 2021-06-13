package com.jornadadev.casadocodigo.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jornadadev.casadocodigo.countrystate.Country;
import com.jornadadev.casadocodigo.countrystate.State;
import com.jornadadev.casadocodigo.order.NewOrderItemRequest;
import com.jornadadev.casadocodigo.order.NewOrderRequest;
import com.jornadadev.casadocodigo.purchase.NewPurchaseRequest;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

class StateBelongsToCountryValidatorTest {

  private EntityManager manager = Mockito.mock(EntityManager.class);
  private Country countryDefault = new Country("test");
  private List<NewOrderItemRequest> items = List.of(new NewOrderItemRequest(1l, 5));
  private NewOrderRequest order = new NewOrderRequest(BigDecimal.TEN, items);
  private NewPurchaseRequest request = new NewPurchaseRequest("email@email", "name", "surname", "111111", "address", "111", "city",
      1l, "999999999",  "111111111-11", order);

  @Test
  @DisplayName("should validate a purchase with country and state belonging to the country")
  void test1() throws Exception {
    State stateOfCountry = new State("state", countryDefault);
    Mockito.when(manager.find(Country.class, 1l)).thenReturn(countryDefault);
    Mockito.when(manager.find(State.class, 1l)).thenReturn(stateOfCountry);
    request.setStateId(1l);

    Errors errors = new BeanPropertyBindingResult(request, "target");
    StateBelongsToCountryValidator validator = new StateBelongsToCountryValidator(manager);
    validator.validate(request, errors);

    assertFalse(errors.hasErrors());
  }

  @Test
  @DisplayName("should block purchase with country and state not belonging to the purchase")
  void test2() throws Exception {
    Country country2 = new Country("anotherCaountry");
    State stateFromAnotherCountry = new State("state", country2);
    Mockito.when(manager.find(State.class, 2l)).thenReturn(stateFromAnotherCountry);

    Mockito.when(manager.find(Country.class, 1l)).thenReturn(countryDefault);
    request.setStateId(2l);

    Errors errors = new BeanPropertyBindingResult(request, "target");
    StateBelongsToCountryValidator validator = new StateBelongsToCountryValidator(manager);
    validator.validate(request, errors);

    assertTrue(errors.getAllErrors().size() == 1);
    assertTrue(errors.hasFieldErrors("stateId"));
  }

  @Test
  @DisplayName("should pass if the purchase is stateless")
  void test3() throws Exception {
    Errors errors = new BeanPropertyBindingResult(request, "target");
    StateBelongsToCountryValidator validator = new StateBelongsToCountryValidator(manager);
    validator.validate(request, errors);

    assertFalse(errors.hasErrors());
  }

  @Test
  @DisplayName("should stop if there is already a validation error in the flow")
  void test4() throws Exception{

    Errors errors = new BeanPropertyBindingResult(request, "target");
    errors.reject("anycode");

    StateBelongsToCountryValidator validator = new StateBelongsToCountryValidator(manager);
    validator.validate(request, errors);

    assertTrue(errors.getAllErrors().size() == 1);
    assertEquals("anycode", errors.getGlobalErrors().get(0).getCode());
  }
}