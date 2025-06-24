package org.fastcampus.post.aplication.Interfaces;

import java.util.Optional;
import org.fastcampus.post.aplication.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.Post;

public interface PostRepository {

  Post save(Post post);

  Post findById(Long id);

}
