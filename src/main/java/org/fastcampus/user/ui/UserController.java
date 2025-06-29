package org.fastcampus.user.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.GetUserListRequestDto;
import org.fastcampus.user.application.dto.GetUserResponseDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.jpa.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JpaUserListQueryRepository userListQueryRepository;

  @PostMapping
  public Response<Long> create(@RequestBody CreateUserRequestDto dto) {
    User user = userService.createUser(dto);
    return Response.ok(user.getId());
  }

  @GetMapping("/userId")
  public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId) {
    return Response.ok(userService.getUserProfile(userId));
  }

  @GetMapping("/{userId}/following")
  public Response<List<GetUserListRequestDto>> getFollowingList(@PathVariable(name = "userId") Long userId) {
    List<GetUserListRequestDto> result = userListQueryRepository.getFollowingUserList(userId);
    return Response.ok(result);
  }

  @GetMapping("/{userId}/follower")
  public Response<List<GetUserListRequestDto>> getFollowerList(@PathVariable(name = "userId") Long userId) {
    List<GetUserListRequestDto> result = userListQueryRepository.getFollowerUserList(userId);
    return Response.ok(result);
  }
}
