package org.fastcampus.post.domain.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class DatetimeInfoTest {

  @Test
  void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() {
    //given
    DatetimeInfo dateTimeInfo = new DatetimeInfo();
    LocalDateTime localDateTime = dateTimeInfo.getDatetime();

    //when
    dateTimeInfo.updateEditDatetime();

    //then
    assertTrue(dateTimeInfo.isEdited());
  }
}
