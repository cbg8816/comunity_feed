package org.fastcampus.post.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

public class PostTest {

  private final UserInfo userInfo = new UserInfo("name", "url");
  private final User user = new User(1L, userInfo);
  private final User anotherUser = new User(2L, userInfo);

  private final Post post = new Post(1L, user, new PostContent("content"));

  @Test
  void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
    //when
    post.like(anotherUser);

    //then
    assertEquals(1, post.getLikeCount());
  }

  @Test
  void givenPostCreated_whenLikeByAnotherUser_thenThrowException() {
    //when, then
    assertThrows(IllegalArgumentException.class,() -> post.like(user));
  }

  @Test
  void givenPostCreatedAndLike_whenUnLike_thenLikeCountShouldBe0() {
    //given
    post.like(anotherUser);

    //when
    post.unlike();

    //then
    assertEquals(0, post.getLikeCount());
  }

  @Test
  void givenPostCreated_whenUnLike_thenLikeCountShouldBe0() {
    //when
    post.unlike();

    //then
    assertEquals(0, post.getLikeCount());
  }

  @Test
  void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
    //given
    String newPostContent = "new content";

    //when
    post.updatePost(user,newPostContent,PostPublicationState.PUBLIC);

    //then
    assertEquals(newPostContent, post.getContent());
  }

  @Test
  void givenPostCreated_whenUpdateOtherUserContent_thenThrowsException() {
    //given
    String newPostContent = "new content";

    //when, then
    assertThrows(IllegalArgumentException.class, () -> post.updatePost(anotherUser,newPostContent,PostPublicationState.PUBLIC));
  }
}
