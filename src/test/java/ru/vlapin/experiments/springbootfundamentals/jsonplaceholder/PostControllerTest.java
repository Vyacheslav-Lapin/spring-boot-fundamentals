package ru.vlapin.experiments.springbootfundamentals.jsonplaceholder;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vlapin.experiments.springbootfundamentals.model.jsonplaceholder.Post;
import ru.vlapin.experiments.springbootfundamentals.dao.jsonplaceholder.PostDao;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class PostControllerTest {

  long id = 57L;
  PostDao postDao;

  @Test
  @SneakyThrows
  @DisplayName("Get method works correctly")
  void get() {
    assertThat(postDao.all())
        .isNotNull()
        .isNotEmpty()
        .hasSize(100);
  }

  @Test
  @SneakyThrows
  @DisplayName("Get one post method works correctly")
  void getOnePostMethodWorksCorrectlyTest() {

    val idCondition = new Condition<Post>(post -> post.getId() == id, "id is equals");
    val bodyCondition = new Condition<Post>(post -> post.getBody().equals("""
        at pariatur consequuntur earum quidem
        quo est laudantium soluta voluptatem
        qui ullam et est
        et cum voluptas voluptatum repellat est"""), "body is equals");

    assertThat(postDao.findById(id))
        .isNotNull()
        .satisfies(idCondition)
        .satisfies(bodyCondition);
  }
}
