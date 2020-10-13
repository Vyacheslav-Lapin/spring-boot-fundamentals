package ru.vlapin.experiments.springbootfundamentals.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AntiErasureGenericsTest {

  @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
  ArrayList<Float> listOfNumbers = new FloatList();

  @Test
  @DisplayName("Generics works without erasure")
  void genericsWorksWithoutErasureTest() {
    assertThat((ParameterizedType) listOfNumbers.getClass().getGenericSuperclass())
        .hasToString("java.util.ArrayList<java.lang.Float>")
        .extracting(type -> (Class<?>) type.getActualTypeArguments()[0])
        .hasToString("class java.lang.Float");
  }

  static class FloatList extends ArrayList<Float> {
  }
}
