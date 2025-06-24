package org.fastcampus.post.domain.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.aplication.PostService;
import org.fastcampus.post.aplication.dto.CreatePostRequestDto;
import org.fastcampus.post.aplication.dto.LikeRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.Test;

public class PostServiceTest {

  private final UserService userService = FakeObjectFactory.getUserService();
  private final PostService postService = FakeObjectFactory.getPostService();

  private final User user = userService.createUser(new CreateUserRequestDto("user1",null));
  private final User otherUser = userService.createUser(new CreateUserRequestDto("user1",null));

  private final CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test content",
      PostPublicationState.PUBLIC);

  @Test
  void givenPostRequestDto_whenCreate_thenReturnPost() {
    //when
    Post savedPost = postService.createPost(dto);

    //then
    Post post = postService.getPost(savedPost.getId());
    assertEquals(savedPost, post);
  }

  @Test
  void givenPostRequestDto_whenLiked_thenReturn1() {
    //given
    Post savedPost = postService.createPost(dto);

    //when
    LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(likeRequestDto);

    //then
    assertEquals(1,savedPost.getLikeCount());
  }
}

