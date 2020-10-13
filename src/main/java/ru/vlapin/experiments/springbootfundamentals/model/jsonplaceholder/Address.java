package ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder;

import static lombok.AccessLevel.PRIVATE;

import lombok.Data;
import lombok.Setter;

@Data
@Setter(PRIVATE)
public class Address {

  String street;
  String suite;
  String city;
  String zipcode;
  Geo geo;
}
