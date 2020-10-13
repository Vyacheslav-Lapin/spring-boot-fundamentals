package ru.vlapin.experiments.springbootfundamentals.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(METHOD)
@Retention(RUNTIME)
public @interface AliasFor {
  String value();
  Class<? extends Annotation> annotation() default Annotation.class;
}
