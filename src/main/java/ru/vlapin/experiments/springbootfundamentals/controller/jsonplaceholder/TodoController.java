package ru.vlapin.experiments.springbootfundamentals.controller.jsonplaceholder;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder.Todo;
import ru.vlapin.experiments.springbootfundamentals.dao.jsonplaceholder.TodoDao;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/todos")
public class TodoController {

  TodoDao todoDao;

  @NotNull
  @GetMapping
  public List<Todo> todos() {
    return todoDao.all();
  }

  @GetMapping("{id}")
  public Todo todo(@PathVariable @NotNull Long id) {
    return todoDao.findById(id);
  }
}
