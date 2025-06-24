package org.fastcampus.user.application.Interface;

import org.fastcampus.user.domain.User;

public interface UserRelationRepository {
  //User 로 파라미터를 받는 이유는 트랜잭션(팔로우나 좋아요 같은 메소드도 업데이트 하기 때문에 도메인으로 한번에)
  boolean isAlreadyFollow(User user, User targetUser);
  void save(User user, User targetUser);
  void delete(User user, User targetUser);

}
