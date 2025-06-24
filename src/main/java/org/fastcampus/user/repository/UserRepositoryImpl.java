package org.fastcampus.user.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.Interface.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final JpaUserRepository repository;

  @Override
  public User save(User user) {
    UserEntity entity = new UserEntity(user);
    entity = repository.save(entity);
    return entity.toUser();
  }

  @Override
  public User findById(Long id) {
    UserEntity entity = repository
        .findById(id)
        .orElseThrow(IllegalArgumentException::new);
    return entity.toUser();
  }
}
