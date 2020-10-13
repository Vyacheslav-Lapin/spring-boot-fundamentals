package ru.vlapin.experiments.springbootfundamentals.controller.jsonplaceholder;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder.Photo;
import ru.vlapin.experiments.springbootfundamentals.dao.jsonplaceholder.PhotoDao;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/photo")
public class PhotoController {

  PhotoDao photoDao;

  @NotNull
  @GetMapping
  @Contract(pure = true)
  public List<Photo> get() {
    return photoDao.all();
  }

  @NotNull
  @GetMapping("{id}")
  @Contract(pure = true)
  public Photo get(@PathVariable @NotNull Long id) {
    return photoDao.findById(id);
  }

//  @NotNull
//  @GetMapping
//  @Contract(pure = true)
//  public List<AlbumImpl> getByPostId(@RequestParam @NotNull Long postId) {
//    return client.albumsByPostId(postId);
//  }

}
