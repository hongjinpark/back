package com.example.back.repository;

import com.example.back.entity.Region;
import com.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByUserAndAndRegionName(User user, String regionName);
    Optional<List<Region>> findAllByUser(User user);
}
