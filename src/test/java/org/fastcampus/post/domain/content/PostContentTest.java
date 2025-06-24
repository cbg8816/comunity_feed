package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PostContentTest {
  @Test
  void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
    //given
    String text = "This is a test";
    //when
    PostContent content =  new PostContent(text);
    //then
    assertEquals(text, content.contentText);
  }

  @Test
  void givenContentLengthIsOver_whenCreated_thenReturnThrowError() {
    //given
    String context = "a".repeat(501);

    //when, then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(context));

  }

}
