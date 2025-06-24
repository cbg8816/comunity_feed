package org.fastcampus.post.domain.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.post.aplication.Interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;

public class FakeCommentRepository implements CommentRepository {
  private final Map<Long, Comment> store = new HashMap<Long, Comment>();

  @Override
  public Comment save(Comment comment) {
    if(comment.getId() != null) {
      store.put(comment.getId(), comment);
      return comment;
    }
    Long id = comment.getId();
    Comment newComment = new Comment(id,comment.getPost(),comment.getAuthor(),comment.getContentContent());
    store.put(id, newComment);
    return newComment;
  }

  @Override
  public Comment findById(Long id) {
    return store.get(id);
  }
}
