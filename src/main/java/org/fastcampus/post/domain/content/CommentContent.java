package org.fastcampus.post.domain.content;

public class CommentContent extends Content {

  private static final int MAX_CONTENT_LENGTH = 100;

  public CommentContent(String content) {
    super(content);
  }

  @Override
  public void checkText(String contentText) {
    if(contentText == null || contentText.isEmpty()) {
      throw new IllegalArgumentException();
    }
    if(contentText.length() > MAX_CONTENT_LENGTH) {
      throw new IllegalArgumentException();
    }
  }
}
