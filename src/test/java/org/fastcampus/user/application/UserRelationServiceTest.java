package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRelationServiceTest {

  private final UserService userService = FakeObjectFactory.getUserService();
  private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

  private User user1;
  private User user2;

  private FollowUserRequestDto requestDto;

  @BeforeEach
  void init(){
    CreateUserRequestDto dto = new CreateUserRequestDto("test","");
    this.user1 = userService.createUser(dto);
    this.user2 = userService.createUser(dto);

    this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
  }

  @Test
  void givenCreateTwoUser_whenFollow_thenUserFollowSaved(){
    //when
    userRelationService.follow(requestDto);

    //then
    assertEquals(1,user1.followingCount());
    assertEquals(1,user2.followerCount());
  }

  @Test
  void givenCreateTwoUserFollowed_whenFollow_thenUserTrowError(){
    //given
    userRelationService.follow(requestDto);
    //when , then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));

  }
  @Test
  void givenCreateOneUser_whenFollow_thenUserTrowError(){
    //given
    FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());
    //then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
  }

  @Test
  void givenCreateTwoUser_whenUnfollow_thenUserFollowSaved(){
    //given
    userRelationService.follow(requestDto);
    //when
    userRelationService.unFollow(requestDto);

    //then
    assertEquals(0,user1.followingCount());
    assertEquals(0,user2.followerCount());
  }

  @Test
  void givenCreateTwoUserUnfollowed_whenUnfollow_thenUserTrowError(){

    //when , then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.unFollow(requestDto));

  }
  @Test
  void givenCreateOneUser_whenUnfollow_thenUserTrowError(){
    //given
    FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());
    //then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.unFollow(sameUser));
  }
}
