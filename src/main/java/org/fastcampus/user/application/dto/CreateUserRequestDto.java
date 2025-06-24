package org.fastcampus.user.application.dto;

public class CreateUserRequestDto {

  private final String name;
  private final String profileimageUrl;

  public CreateUserRequestDto(String name, String profileimageUrl) {
    this.name = name;
    this.profileimageUrl = profileimageUrl;
  }

  public String getName() {
    return name;
  }

  public String getProfileimageUrl() {
    return profileimageUrl;
  }
}
