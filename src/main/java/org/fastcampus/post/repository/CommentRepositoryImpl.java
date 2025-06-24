package org.fastcampus.post.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.aplication.Interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.repository.jpa.JpaCommentRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

  private final JpaCommentRepository jpaCommentRepository;

  @Override
  public Comment save(Comment comment) {
    CommentEntity commentEntity = new CommentEntity(comment);
    jpaCommentRepository.save(commentEntity);
    return comment;
  }

  @Override
  public Comment findById(Long id) {
    CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
    return commentEntity.toComment();
  }
}
