package org.fastcampus.post.aplication.dto;

public record UpdateCommentRequestDto(Long commentId, Long userId, String content) {

}
