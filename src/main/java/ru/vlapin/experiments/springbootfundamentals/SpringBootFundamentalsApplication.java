package ru.vlapin.experiments.springbootfundamentals;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableHypermediaSupport(type = HAL)
@EnableFeignClients//("ru.vlapin.experiments.springbootfundamentals.dao")
public class SpringBootFundamentalsApplication {

  int x = 55;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFundamentalsApplication.class, args);
    new SpringBootFundamentalsApplication()
        .met("kjhdfg");
	}

	@SneakyThrows
	@Contract(pure = true)
	public final @NotNull String met(SpringBootFundamentalsApplication this, @NotNull String s) {
	  return this.x + "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
	}

}
