package ru.vlapin.experiments.springbootfundamentals.model;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Setter(PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
public class Cat {

  @Id
  @Exclude
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  UUID id;

  @Version
  int version;

  @NonNull
  String name;

}
