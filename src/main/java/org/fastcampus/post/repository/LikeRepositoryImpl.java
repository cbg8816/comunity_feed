package org.fastcampus.post.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.aplication.Interfaces.LikeRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.repository.entity.like.LikeEntity;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.jpa.JpaCommentRepository;
import org.fastcampus.post.repository.jpa.JpaLikeRepository;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

  private final JpaPostRepository jpaPostRepository;
  private final JpaCommentRepository jpaCommentRepository;
  private final JpaLikeRepository jpaLikeRepository;

  @Override
  public boolean checkLike(Post post, User user) {
    LikeEntity likeEntity = new LikeEntity(post, user);
    return jpaLikeRepository.existsById(likeEntity.getId());
  }

  @Override
  @Transactional
  public void like(Post post, User user) {
    LikeEntity likeEntity = new LikeEntity(post, user);
    jpaLikeRepository.save(likeEntity);
    jpaPostRepository.save(new PostEntity(post));
  }

  @Override
  @Transactional
  public void unlike(Post post, User user) {
    LikeEntity likeEntity = new LikeEntity(post, user);
    jpaLikeRepository.deleteById(likeEntity.getId());
    jpaPostRepository.save(new PostEntity(post));
  }

  @Override
  public boolean checkLike(Comment comment, User user) {
    LikeEntity likeEntity = new LikeEntity(comment, user);
    return jpaLikeRepository.existsById(likeEntity.getId());
  }

  @Override
  @Transactional
  public void like(Comment comment, User user) {
    LikeEntity likeEntity = new LikeEntity(comment, user);
    jpaLikeRepository.save(likeEntity);
    jpaCommentRepository.save(new CommentEntity(comment));
  }

  @Override
  @Transactional
  public void unlike(Comment comment, User user) {
    LikeEntity likeEntity = new LikeEntity(comment, user);
    jpaLikeRepository.deleteById(likeEntity.getId());
    jpaCommentRepository.save(new CommentEntity(comment));
  }
}
