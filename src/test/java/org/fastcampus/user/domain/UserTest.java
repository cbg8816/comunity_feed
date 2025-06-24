package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

  private final UserInfo userInfo = new UserInfo("test","");
  private User user1;
  private User user2;

  @BeforeEach
  void init(){
    user1 = new User(1L, userInfo);
    user2 = new User(2L, userInfo);
  }

  @Test
  void givenTwoUser_whenEqual_thenReturnFalse(){
    //when
    boolean value = user1.equals(user2);
    //then
    assertFalse(value);
  }

  @Test
  void givenTwoSameUser_whenEqual_thenReturnTrue(){
    //given
    User sameUser = new User(1L, userInfo);
    //when
    boolean value = user1.equals(sameUser);
    //then
    assertTrue(value);
  }

  @Test
  void givenTwoUser_whenHashCode_thenReturnFalse(){
    //when
    int hashCode1 = user1.hashCode();
    int hashCode2 = user2.hashCode();

    //then
    assertNotEquals(hashCode1, hashCode2);
  }

  @Test
  void givenTwoSameIdUser_whenHashCode_thenEqual(){
    //given
    User sameUser = new User(1L, userInfo);
    //when
    int hashCode1 = user1.hashCode();
    int hashCode2 = sameUser.hashCode();

    //then
    assertEquals(hashCode1, hashCode2);
  }

  @Test
  void givenTwoUser_whenUserFollowUser2_thenIncreaseUserCount(){
    //when
    user1.follow(user2);

    //then
    assertEquals(1,user1.followingCount());
    assertEquals(0,user1.followerCount());
    assertEquals(0,user2.followingCount());
    assertEquals(1,user2.followerCount());
  }

  @Test
  void givenTwoUser_whenUserUnFollowUser2_thenDecreaseUserCount(){
    //when
    user1.unfollow(user2);

    //then
    assertEquals(0,user1.followingCount());
    assertEquals(0,user1.followerCount());
    assertEquals(0,user2.followingCount());
    assertEquals(0,user2.followerCount());
  }

}
