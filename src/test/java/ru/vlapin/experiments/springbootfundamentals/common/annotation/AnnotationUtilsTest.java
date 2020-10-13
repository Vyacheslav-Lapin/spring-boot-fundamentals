package ru.vlapin.experiments.springbootfundamentals.common.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.vlapin.experiments.springbootfundamentals.common.annotation.AnnotationUtils.getDeepAnnotation;

import java.lang.annotation.Retention;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Retention(RUNTIME)
@interface MyDeepAnnotation {
  String value();
}

@Retention(RUNTIME)
@MyDeepAnnotation("Lorem ipsum dolor sit amet")
@interface MyAnnotation {
}

@MyAnnotation
class B {
}

class AnnotationUtilsTest {

  @Test
  @SneakyThrows
  @DisplayName("Annotation inheritance works correctly")
  void annotationInheritanceWorksCorrectlyTest() {
    assertThat(getDeepAnnotation(B.class, MyDeepAnnotation.class))
        .isNotNull()
        .containsInstanceOf(MyDeepAnnotation.class)
        .get()
        .extracting(MyDeepAnnotation::value)
        .isEqualTo("Lorem ipsum dolor sit amet");
  }
}
