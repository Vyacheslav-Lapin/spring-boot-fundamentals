package ru.vlapin.experiments.springbootfundamentals.dao.jsonplaceholder;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder.Photo;

@FeignClient(
    name = "PhotoJsonPlaceHolder",
    url = "https://jsonplaceholder.typicode.com",
    path = "photos")
public interface PhotoDao {

  @GetMapping
  List<Photo> all();

  @GetMapping("{id}")
  Photo findById(@PathVariable Long id);
}
