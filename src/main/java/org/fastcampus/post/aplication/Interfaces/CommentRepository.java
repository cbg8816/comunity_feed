package org.fastcampus.post.aplication.Interfaces;

import java.util.Optional;
import org.fastcampus.post.domain.comment.Comment;

public interface CommentRepository {

  Comment save(Comment comment);
  Comment findById(Long id);
}
