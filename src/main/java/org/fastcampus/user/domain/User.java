package org.fastcampus.user.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;

@Getter
@Builder
@AllArgsConstructor
public class User {
  private final Long id;
  private final UserInfo info;
  private final PositiveIntegerCounter followingCount;
  private final PositiveIntegerCounter followerCount;


  public User(Long id, UserInfo userInfo) {
    if(userInfo == null){
      throw new IllegalArgumentException();
    }
    this.id = id;
    this.info = userInfo;
    this.followingCount = new PositiveIntegerCounter();
    this.followerCount = new PositiveIntegerCounter();
  }

  public void follow(User targetUser) {
    if(this.equals(targetUser)) {
      throw new IllegalArgumentException();
    }
      followingCount.increase();
      targetUser.increaseFollowerCount();
  }

  public void unfollow(User targetUser) {
    if(this.equals(targetUser)) {
      throw new IllegalArgumentException();
    }
    followingCount.decrease();
    targetUser.decreaseFollowerCount();
  }

  // targetUser.followerCount.increase();로 작성하면 캡슐화가 깨지기 때문에 private로 따로 만듦
  private void increaseFollowerCount() {
    followerCount.increase();
  }

  private void decreaseFollowerCount() {
    followerCount.decrease();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return id == user.id;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  public int followerCount() {
    return followerCount.getCount();
  }
  public int followingCount() {
    return followingCount.getCount();
  }

  public String getProfileImage(){
    return info.getProfileImageUrl();
  }

  public String getName(){
    return info.getName();
  }

}
