package ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder;

import static lombok.AccessLevel.PRIVATE;

import lombok.Data;
import lombok.Setter;

@Data
@Setter(PRIVATE)
public class Comment {

  Long postId;
  Long id;
  String name;
  String email;
  String body;
}
