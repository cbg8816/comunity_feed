package org.fastcampus.post.aplication.dto;

import org.fastcampus.post.domain.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {

}
