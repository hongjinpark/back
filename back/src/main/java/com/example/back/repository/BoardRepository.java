package com.example.back.repository;

import com.example.back.entity.Board;
import com.example.back.entity.Region;
import com.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByUserAndRegion(User user, Region region);

    Optional<List<Board>> findAllByUser(User user);
}
