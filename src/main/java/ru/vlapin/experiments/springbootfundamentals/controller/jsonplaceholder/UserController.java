package ru.vlapin.experiments.springbootfundamentals.controller.jsonplaceholder;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder.User;
import ru.vlapin.experiments.springbootfundamentals.dao.jsonplaceholder.UserDao;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

  UserDao userDao;

  @NotNull
  @GetMapping
//  @Contract(pure = true)
  public List<User> all() {
    return userDao.all();
  }

//  @NotNull
  @GetMapping("{id}")
//  @Contract(pure = true)
  public User byId(@PathVariable @NotNull Long id) {
    return userDao.findById(id);
  }
}
