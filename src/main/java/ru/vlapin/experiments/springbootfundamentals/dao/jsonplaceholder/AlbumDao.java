package ru.vlapin.experiments.springbootfundamentals.dao.jsonplaceholder;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder.Album;

@FeignClient(
    name = "AlbumJsonPlaceHolder",
    url = "https://jsonplaceholder.typicode.com",
    path = "albums")
public interface AlbumDao {

  @GetMapping
  List<Album> all();

  @GetMapping("{id}")
  Album findById(@PathVariable Long id);

  @GetMapping
  List<Album> albumsByPostId(@RequestParam @NotNull Long postId);
}
