package org.fastcampus.fake;

import org.fastcampus.post.aplication.CommentService;
import org.fastcampus.post.aplication.Interfaces.CommentRepository;
import org.fastcampus.post.aplication.Interfaces.LikeRepository;
import org.fastcampus.post.aplication.Interfaces.PostRepository;
import org.fastcampus.post.aplication.PostService;
import org.fastcampus.post.domain.repository.FakeCommentRepository;
import org.fastcampus.post.domain.repository.FakeLikeRepository;
import org.fastcampus.post.domain.repository.FakePostRepository;
import org.fastcampus.user.application.Interface.UserRelationRepository;
import org.fastcampus.user.application.Interface.UserRepository;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepossitory;

public class FakeObjectFactory {

  private static final UserRepository fakeUserRepository = new FakeUserRepossitory();
  private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
  private static final PostRepository fakePostRepository = new FakePostRepository();
  private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
  private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

  private static final UserService userService = new UserService(fakeUserRepository);
  private static final UserRelationService userRelationService = new UserRelationService(userService,fakeUserRelationRepository);
  private static final PostService postService = new PostService(userService,fakePostRepository,fakeLikeRepository);
  private static final CommentService commentService = new CommentService(userService,postService,fakeCommentRepository,fakeLikeRepository);


  private FakeObjectFactory() {}

  public static UserService getUserService() {
    return userService;
  }
  public static UserRelationService getUserRelationService() {
    return userRelationService;
  }
  public static PostService getPostService() {
    return postService;
  }
  public static CommentService getCommentService() {
    return commentService;
  }
}
