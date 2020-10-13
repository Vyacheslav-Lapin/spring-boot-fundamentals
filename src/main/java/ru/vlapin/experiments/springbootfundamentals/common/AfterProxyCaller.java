package ru.vlapin.experiments.springbootfundamentals.common;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction3;
import io.vavr.Function1;
import io.vavr.Function3;
import io.vavr.Tuple;
import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostProxyCaller implements ApplicationListener<ContextRefreshedEvent> {

  static Function1<String, Class<?>> GET_CLASS = CheckedFunction1
                                                     .<String, Class<?>>of(Class::forName)
                                                     .unchecked();

  Function3<Class<?>, String, Class<?>[], Method> GET_METHOD = CheckedFunction3
                                                                   .<Class<?>, String, Class<?>[], Method>of(Class::getMethod)
                                                                   .unchecked();

  Function3<Method, Object, Object[], Object> METHOD_INVOKE = CheckedFunction3
                                                                  .<Method, Object, Object[], Object>of(Method::invoke)
                                                                  .unchecked();

  ConfigurableListableBeanFactory beanFactory;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    val applicationContext = event.getApplicationContext();

    Arrays.stream(applicationContext.getBeanDefinitionNames())
        .map(beanName -> Tuple.of(beanName, beanFactory.getBeanDefinition(beanName)))
        .map(beanNameAndBeanDefinition -> Tuple.of(beanNameAndBeanDefinition._1, beanNameAndBeanDefinition._2.getBeanClassName()))
        .map(beanNameAndBeanClassName -> Tuple.of(beanNameAndBeanClassName._1, GET_CLASS.apply(beanNameAndBeanClassName._2)))
        .map(beanNameAndBeanClass -> Tuple.of(beanNameAndBeanClass._1, beanNameAndBeanClass._2.getMethods()))
        .flatMap(beanNameAndBeanMethods -> Arrays.stream(beanNameAndBeanMethods._2).map(method -> Tuple.of(beanNameAndBeanMethods._1, method)))
        .filter(beanNameAndBeanMethod -> beanNameAndBeanMethod._2.isAnnotationPresent(PostProxy.class))
        .map(beanNameAndBeanMethod -> Tuple.of(applicationContext.getBean(beanNameAndBeanMethod._1), beanNameAndBeanMethod._2))
        .map(beanAndBeanMethod -> Tuple.of(beanAndBeanMethod._1, GET_METHOD.apply(beanAndBeanMethod._1.getClass(), beanAndBeanMethod._2.getName(), beanAndBeanMethod._2.getParameterTypes())))
        .forEachOrdered(beanAndCurrentBeanMethod -> METHOD_INVOKE.apply(beanAndCurrentBeanMethod._2, beanAndCurrentBeanMethod._1, new Object[]{}));
  }
}
