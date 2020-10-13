package ru.vlapin.experiments.springbootfundamentals.common;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 3rd phase of initialization method
 */
@Retention(RUNTIME)
@Target({ANNOTATION_TYPE, METHOD})
public @interface AfterProxy {
}
