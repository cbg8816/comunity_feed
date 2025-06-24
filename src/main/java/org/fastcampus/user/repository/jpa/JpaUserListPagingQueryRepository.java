package org.fastcampus.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.dto.GetUserListRequestDto;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.fastcampus.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

  private final JPAQueryFactory queryFactory;
  private static final QUserEntity user = QUserEntity.userEntity;
  private  static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;
  private final JPAQueryFactory jpaQueryFactory;

  public List<GetUserListRequestDto> getFollowerList(Long userId, Long lastFollowerId) {
    return jpaQueryFactory
        .select(
            Projections.fields(
                GetUserListRequestDto.class
            )
        )
        .from(relation)
        .join(user).on(relation.followingUserId.eq(userId))
        .where(
            relation.followerUserId.eq(userId),
            hasLastData(lastFollowerId)
        )
        .orderBy(user.id.desc())
        .limit(20)
        .fetch();
  }

  private BooleanExpression hasLastData(Long lastId) {
    if(lastId == null){
      return null;
    }
    return user.id.lt(lastId);
  }
}
