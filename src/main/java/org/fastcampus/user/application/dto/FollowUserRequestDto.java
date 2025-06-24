package org.fastcampus.user.application.dto;

public class FollowUserRequestDto {

  private Long userId;
  private Long targetUserId;

  public FollowUserRequestDto(Long userId, Long targetUserId) {
    this.userId = userId;
    this.targetUserId = targetUserId;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getTargetUserId() {
    return targetUserId;
  }
}
