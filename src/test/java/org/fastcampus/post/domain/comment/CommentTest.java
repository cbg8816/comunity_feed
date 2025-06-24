package org.fastcampus.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

public class CommentTest {

  UserInfo userInfo = new UserInfo("test", "url");
  User user = new User(1L, userInfo);
  User anotherUser = new User(2L, userInfo);

  Post post = new Post(1L, user, new PostContent("post content"));
  Comment comment = new Comment(1L, post, user, new CommentContent("comment content"));

  @Test
  void givenCommentCreated_whenUpdateComment_thenReturnTrue(){
    //given
    String commentContent = "new comment";
    //when
    comment.updateComment(user, commentContent);
    //then
    assertEquals(commentContent, comment.getContent());
  }

  @Test
  void givenCommentCreated_whenLikeAnotherUser_thenResultIs1(){
    //when
    comment.like(anotherUser);
    //then
    assertEquals(1, comment.getLikeCount());
  }

  @Test
  void givenCommentCreated_whenUnLikeAnotherUser_thenResultIs0(){
    //when
    comment.unlike();
    //then
    assertEquals(0, comment.getLikeCount());
  }

  @Test
  void givenCommentLength101_whenUpdateComment_thenThrowException(){
    //given
    String a = "a".repeat(101);
    //when, then
    assertThrows(IllegalArgumentException.class, ()->{comment.updateComment(user, a);});
  }
}
