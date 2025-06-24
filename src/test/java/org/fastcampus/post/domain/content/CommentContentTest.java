package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommentContentTest {

  @Test
  void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContext() {
    //given
    String contentText = "This is a content text";
    //when
    CommentContent commentContent = new CommentContent(contentText);
    //then
    assertEquals(contentText, commentContent.getContentText());
  }
}
