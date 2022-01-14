package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 어노테이션 기반 SQL 조회: 특정 게시글(article)의 모든 댓글(comment)
    @Query(value =
            "SELECT * " +
            "FROM comment " +
            "WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);
    // XML 기반 SQL 조회: 특정 닉네임(nickname)의 모든 댓글(comment)
    List<Comment> findByNickname(@Param("nickname") String nickname);


}
