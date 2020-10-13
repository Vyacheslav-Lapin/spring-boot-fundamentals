package ru.vlapin.experiments.springbootfundamentals.controller.jsonplaceholder;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder.Album;
import ru.vlapin.experiments.springbootfundamentals.dao.jsonplaceholder.AlbumDao;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/albums")
public class AlbumController {

  AlbumDao albumDao;

  @PostConstruct
  private void init() {
    System.out.println("Phase 2");
  }

  @NotNull
  @GetMapping
  @Contract(pure = true)
  public List<Album> get() {
    return albumDao.all();
  }

  @NotNull
  @GetMapping("{id}")
  @Contract(pure = true)
  public Album get(@PathVariable @NotNull Long id) {
    return albumDao.findById(id);
  }

  @EventListener
  public void afterProxy(ContextRefreshedEvent __) {
    System.out.println("Phase 3");
  }

  //  @NotNull
//  @GetMapping
//  @Contract(pure = true)
//  public List<AlbumImpl> getByPostId(@RequestParam @NotNull Long postId) {
//    return client.albumsByPostId(postId);
//  }
}
