package org.fastcampus.user.application;

import org.fastcampus.user.application.Interface.UserRelationRepository;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserRelationService {
  private final UserService userService;
  private final UserRelationRepository userRelationRepository;

  public UserRelationService(UserService userService,
      UserRelationRepository userRelationRepository) {
    this.userService = userService;
    this.userRelationRepository = userRelationRepository;
  }

  public void follow(FollowUserRequestDto dto){
    User user = userService.getUser(dto.getUserId());
    User targetUser = userService.getUser(dto.getTargetUserId());

    if(userRelationRepository.isAlreadyFollow(user, targetUser)){
      throw new IllegalArgumentException();
    }
    user.follow(targetUser);
    userRelationRepository.save(user, targetUser);
  }

  public void unFollow(FollowUserRequestDto dto){
    User user = userService.getUser(dto.getUserId());
    User targetUser = userService.getUser(dto.getTargetUserId());

    if(!userRelationRepository.isAlreadyFollow(user, targetUser)){
      throw new IllegalArgumentException();
    }
    user.unfollow(targetUser);
    userRelationRepository.delete(user, targetUser);
  }
}
