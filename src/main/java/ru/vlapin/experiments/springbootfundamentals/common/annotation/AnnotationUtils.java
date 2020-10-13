package com.epam.courses.java.fundamentals.oop.annotations;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import io.vavr.CheckedFunction1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface AnnotationUtils {

  @NotNull
  @Contract(pure = true)
  static <T extends Annotation> Optional<T> getDeepAnnotation(@NotNull Class<?> targetClass,
                                                              @NotNull Class<T> annotationClass) {
    return getDeepAnnotation(annotationClass, targetClass.getAnnotations());
  }

  /**
   * Works for methods and constructors
   */
  @NotNull
  @Contract(pure = true)
  static <T extends Annotation> Optional<T> getDeepAnnotation(@NotNull Executable targetMethod,
                                                              @NotNull Class<T> annotationClass) {
    return getDeepAnnotation(annotationClass, targetMethod.getAnnotations());
  }

  @NotNull
  @Contract(pure = true)
  static <T extends Annotation> Optional<T> getDeepAnnotation(@NotNull Parameter targetParam,
                                                              @NotNull Class<T> annotationClass) {
    return getDeepAnnotation(annotationClass, targetParam.getAnnotations());
  }

  @Contract(pure = true)
  static <T extends Annotation> boolean isDeepAnnotationPresent(@NotNull Class<?> targetClass,
                                                                @NotNull Class<T> annotationClass) {
    return isDeepAnnotationPresent(annotationClass, targetClass.getAnnotations());
  }

  /**
   * Works for methods and constructors
   */
  @Contract(pure = true)
  static <T extends Annotation> boolean isDeepAnnotationPresent(@NotNull Executable targetMethod,
                                                                @NotNull Class<T> annotationClass) {
    return isDeepAnnotationPresent(annotationClass, targetMethod.getAnnotations());
  }

  @Contract(pure = true)
  static <T extends Annotation> boolean isDeepAnnotationPresent(@NotNull Parameter targetParam,
                                                                @NotNull Class<T> annotationClass) {
    return isDeepAnnotationPresent(annotationClass, targetParam.getAnnotations());
  }

  @Contract(pure = true)
  private static <T extends Annotation> boolean isDeepAnnotationPresent(@NotNull Class<T> annotationClass,
                                                                        @NotNull Annotation... annotations) {
    return deepAnnotations(Arrays.stream(annotations), 1)
               .map(Tuple2::_1)
               .anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
  }

  @NotNull
  @Contract(pure = true)
  @SuppressWarnings("unchecked")
  private static <T extends Annotation> Optional<T> getDeepAnnotation(@NotNull Class<T> annotationClass,
                                                                      @NotNull Annotation... annotations) {
    //noinspection unchecked
    return (Optional<T>) deepAnnotations(Arrays.stream(annotations), 1)
                             .sorted((o1, o2) -> o2._2 - o1._2)
                             .map(Tuple2::_1)
                             .filter(annotation -> annotation.annotationType().equals(annotationClass))
                             .findFirst();
  }

  @NotNull
  @Contract(pure = true)
  static Stream<Tuple2<Annotation, Integer>> deepAnnotations(@NotNull Stream<Annotation> annotations,
                                                             int deepLevel) {
    return annotations
               .flatMap(annotation -> Stream.concat(
                   Stream.of(Tuple.of(annotation, deepLevel)),
                   deepAnnotations(
                       Stream.of(annotation.annotationType().getAnnotations())
                           .filter(anno ->
                                       !anno.annotationType().getPackageName().equals("java.lang.annotation")),
                       deepLevel + 1)));
  }

  @Contract(pure = true)
  static @NotNull <T extends Annotation> T wrapAnnotationWithAliasForFunctionality(@NotNull T annotation) {

    Class<? extends Annotation> annotationType = annotation.annotationType();

    //noinspection unchecked
    return (T) Proxy.newProxyInstance(
        AnnotationUtils.class.getClassLoader(),
        new Class[]{annotationType},
        (__, method, args) -> {

          Method realMethod = annotationType
                                  .getMethod(method.getName(), method.getParameterTypes());

          Object result = realMethod.invoke(annotation, args);

          return !method.getDeclaringClass().equals(annotationType)
                     || !isPseudoNullValue(method.getReturnType(), result)
                     ? result
                     : Optional.ofNullable(realMethod.getDeclaredAnnotation(AliasFor.class))
                           .map(AliasFor::value)
                           .map(CheckedFunction1.<String, Method>narrow(annotationType::getMethod).unchecked())
                           .map(CheckedFunction1.<Method, Object>narrow(method1 -> method1.invoke(annotation)).unchecked())
                           .orElse(result);
        });
  }

  @Contract(pure = true)
  static boolean isPseudoNullValue(@NotNull Object result) {
    return isPseudoNullValue(result.getClass(), result);
  }

  @Contract(pure = true)
  private static boolean isPseudoNullValue(@NotNull Class<?> paramType, @NotNull Object result) {
    return Match(paramType).of(
        Case($(t -> t.equals(String.class)), () -> result.equals("")),
        Case($(Class::isArray), () -> Array.getLength(result) == 0),
        Case($(t -> t.equals(Class.class)), () -> result.equals(Object.class)),
        Case($(t -> !t.isPrimitive()), false), // enum - always false
        Case($(t -> t.equals(int.class)), () -> (int) result == 0),
        Case($(t -> t.equals(long.class)), () -> (long) result == 0L),
        Case($(t -> t.equals(double.class)), () -> (double) result == .0),
        Case($(t -> t.equals(float.class)), () -> (float) result == .0f),
        Case($(t -> t.equals(short.class)), () -> (short) result == 0),
        Case($(t -> t.equals(byte.class)), () -> (byte) result == 0),
        Case($(t -> t.equals(char.class)), () -> (char) result == '\0'),
        Case($(), () -> !((boolean) result)) // for boolean - false is pseudo-null
    );
  }
}
