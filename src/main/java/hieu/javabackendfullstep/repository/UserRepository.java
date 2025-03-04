package hieu.javabackendfullstep.repository;

import hieu.javabackendfullstep.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Query(value = "select * from users u where u.username like :keyword order by u.id limit :limit offset :offset", nativeQuery = true)
    List<UserEntity> findByKeyword(@Param("keyword") String keyword, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "select count(*) as cnt from users u where u.username like :keyword", nativeQuery = true)
    int countByKeyword(@Param("keyword") String keyword);

    Optional<UserEntity> findById(Long userId);
}
