package com.oldandsea.pcb.domain.repository;

import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {

}