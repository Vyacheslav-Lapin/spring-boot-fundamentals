package ru.vlapin.experiments.springbootfundamentals.common;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CheckedExceptionTest {

  @SuppressWarnings("unchecked")
  private static <E extends Exception> void throwChecked(Exception e) throws E {
    throw (E) e;
  }

  @Test
  @SneakyThrows
  @DisplayName("We can throw checked exception at runtime")
  void weCanThrowCheckedExceptionAtRuntimeTest() {
    assertThrows(SQLException.class, () ->
                                         doThrow(new SQLException())
    );
  }

  void doThrow(Exception e) {
    throwChecked(e);
  }
}
