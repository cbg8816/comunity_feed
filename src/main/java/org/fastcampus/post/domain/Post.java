package org.fastcampus.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;

@Builder
@AllArgsConstructor
@Getter
public class Post {
  private Long id;
  private final User author;
  private final Content content;
  private final PositiveIntegerCounter likeCount;
  private PostPublicationState state;

  //정적 생성자를 사용하여 명확한 메소드이름을 정하고 유지보수를 쉽게 만들어줌
  public static Post crestePost(Long id, User author, String content , PostPublicationState state) {
    return new Post(id, author, new PostContent(content), state);
  }
  public static Post cresteDefaultPost(Long id, User author, String content) {
    return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
  }

  public Post(Long id, User author, Content content) {
    this(id, author, content, PostPublicationState.PUBLIC);
  }

  public Post(Long id, User author, Content content , PostPublicationState state) {
    if(author == null){
      throw new IllegalArgumentException();
    }
    this.id = id;
    this.author = author;
    this.content = content;
    this.likeCount = new PositiveIntegerCounter();
    this.state = state;
  }

  public void like(User user){
    if(this.author.equals(user)){
      throw new IllegalArgumentException();
    }
    this.likeCount.increase();
  }

  public void unlike(){
    this.likeCount.decrease();
  }

  public void updatePost(User user, String updateContent, PostPublicationState state){
    if(!this.author.equals(user)){
      throw new IllegalArgumentException();
    }
    this.state = state;
    this.content.updateContent(updateContent);
  }

  public int getLikeCount() {
    return likeCount.getCount();
  }

  public String getContent() {
    return content.getContentText();
  }

  public Content getContentObject() {
    return content;
  }
}
