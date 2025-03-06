package hieu.javabackendfullstep.repository;

import hieu.javabackendfullstep.entity.RedisToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}
