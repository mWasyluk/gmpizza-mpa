package pl.mvasio.pizzeria.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Orders")
public class Order {
    @Id
    private String id;
    private String username;
    private Date createDate;
    @NotBlank (message = "To pole jest wymagane.")
    private String name;
    @NotBlank (message = "To pole jest wymagane.")
    private String street;
    @NotBlank (message = "To pole jest wymagane.")
    private String city;
    @Pattern(regexp = "^(([0-9]{2})-([0-9]{3}))$", message = "Niepoprawny kod pocztowy. Wymagany format to 00-000.")
    private String zipCode;
    @CreditCardNumber (message = "Niepoprawny numer karty kredytowej.")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])(\\/)([2-9][0-9])$", message = "Niepoprawna data ekspiracji. Wymagany format to MM/RR.")
    private String ccExpiration;
    @Pattern(regexp = "^([0-9]{3})$", message = "Niepoprawny numer CVV. Numer powinien składać się z trzech cyfr.")
    private String ccCVV;
    private List<Pizza> pizzas = new LinkedList<>();

    public boolean addPizza ( Pizza pizza ){
        return this.pizzas.add(pizza);
    }
    public void removePizzaById(String id ){
//        pizzas.parallelStream().filter(pizza -> pizza.getId().equals(id)).findAny().map(pizza -> pizzas.remove(pizza));
        pizzas.forEach((p)->{
            if (p.getId().equals(id)) pizzas.remove(p);
            System.out.println(p + " removed");
        });
    }

    public void removeAllPizzas(){
        pizzas.clear();
    }
}
