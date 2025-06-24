package org.fastcampus.post.domain.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.post.aplication.Interfaces.PostRepository;
import org.fastcampus.post.aplication.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.Post;

public class FakePostRepository implements PostRepository {
  private final Map<Long, Post> store = new HashMap<>();

  @Override
  public Post save(Post pos) {
    if(pos.getId() != null) {
      store.put(pos.getId(), pos);
      return pos;
    }
    Long id = store.size() + 1L;
    Post newPost = new Post(id,pos.getAuthor(),pos.getContentObject());
    store.put(id, newPost);
    return newPost;
  }

  @Override
  public Post findById(Long id) {
    return store.get(id);
  }

}
