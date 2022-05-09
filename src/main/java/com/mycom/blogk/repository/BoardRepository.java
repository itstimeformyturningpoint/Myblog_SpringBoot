package com.mycom.blogk.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.blogk.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer> {

}
	