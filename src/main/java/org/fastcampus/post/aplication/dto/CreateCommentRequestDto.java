package org.fastcampus.post.aplication.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {

}
