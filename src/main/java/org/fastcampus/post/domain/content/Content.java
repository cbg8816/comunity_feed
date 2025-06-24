package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.common.DatetimeInfo;

public abstract class Content {
  String contentText;
  final DatetimeInfo datetimeInfo;

  protected Content(String contentText) {
    checkText(contentText);
    this.contentText = contentText;
    this.datetimeInfo = new DatetimeInfo();
  }
  //내용을 update하면 기존에 있는 유효성 검사 메소드를 재사용 한다.
  public void updateContent(String updateContent){
    checkText(updateContent);
    this.contentText = updateContent;
    this.datetimeInfo.updateEditDatetime();
  }

  public abstract void checkText(String contentText);

  public String getContentText() {
    return contentText;
  }
}
