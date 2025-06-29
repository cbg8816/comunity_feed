package org.fastcampus.user.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.Interface.UserRelationRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationIdEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {


  private final JpaUserRelationRepository jpaUserRelationRepository;
  private final JpaUserRepository jpaUserRepository;

  @Override
  public boolean isAlreadyFollow(User user, User targetUser) {
    UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
    return jpaUserRelationRepository.existsById(id);
  }

  @Override
  @Transactional
  public void save(User user, User targetUser) {
    UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
    jpaUserRelationRepository.save(entity);
    jpaUserRepository.saveAll(List.of(new UserEntity(user),new UserEntity(targetUser)));
  }

  @Override
  @Transactional
  public void delete(User user, User targetUser) {
    UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
    jpaUserRelationRepository.deleteById(id);
    jpaUserRepository.saveAll(List.of(new UserEntity(user),new UserEntity(targetUser)));

  }
}
