package org.fastcampus.user.application;

import org.fastcampus.user.application.Interface.UserRepository;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.GetUserResponseDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(CreateUserRequestDto dto) {
    UserInfo info = new UserInfo(dto.getName(), dto.getProfileimageUrl());
    User user = new User(null, info);
    return userRepository.save(user);
  }

  public User getUser(long id) {
    return userRepository.findById(id);
  }

  public GetUserResponseDto getUserProfile(Long id) {
    User user = getUser(id);
    return new GetUserResponseDto(user);
  }
}
