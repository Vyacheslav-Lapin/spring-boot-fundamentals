package ru.vlapin.experiments.springbootfundamentals.common.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.vlapin.experiments.springbootfundamentals.common.annotation.AnnotationUtils.wrapAnnotationWithAliasForFunctionality;

import java.lang.annotation.Retention;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Retention(RUNTIME)
@interface AliasAnnotation {

  @AliasFor("param1")
  String value() default "";

  @AliasFor("value")
  String param1() default "";

  int param2() default 1;
}

@AliasAnnotation("Мама мыла раму")
class A {
}

class AnnotationUtilsAliasForTest {

  @Test
  @DisplayName("AliasFor works correctly")
  void aliasForWorksCorrectlyTest() {
    assertThat(wrapAnnotationWithAliasForFunctionality(A.class.getAnnotation(AliasAnnotation.class)))
        .isNotNull()
        .isInstanceOf(AliasAnnotation.class)
        .extracting(AliasAnnotation::param1)
        .isEqualTo("Мама мыла раму");
  }
}
