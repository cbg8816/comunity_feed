package org.fastcampus.user.repository.jpa;

import java.util.List;
import org.fastcampus.user.application.dto.GetUserListRequestDto;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity,Long> {

  @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListRequestDto(u.name, u.profileImage)"
      + " FROM UserRelationEntity ur"
      + " INNER JOIN UserEntity u ON ur.followerUserId = u.id"
      + " WHERE ur.followingUserId = :userId")
  List<GetUserListRequestDto> getFollowingUserList(Long userId);

  @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListRequestDto(u.name, u.profileImage)"
      + " FROM UserRelationEntity ur"
      + " INNER JOIN UserEntity u ON ur.followingUserId = u.id"
      + " WHERE ur.followerUserId = :userId")
  List<GetUserListRequestDto> getFollowerUserList(Long userId);
}
