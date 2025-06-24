package org.fastcampus.post.aplication.dto;

import org.fastcampus.post.domain.PostPublicationState;

public record UpdatePostRequestDto (Long userId, String content, PostPublicationState state) {

}
