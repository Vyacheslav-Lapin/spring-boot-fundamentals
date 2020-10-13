package ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder;

import static lombok.AccessLevel.PRIVATE;

import lombok.Data;
import lombok.Setter;

@Data
@Setter(PRIVATE)
public class User {

  Long id;
  String name;
  String username;
  String email;
  Address address;
  String phone;
  String website;
  Company company;
}
