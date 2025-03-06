package hieu.javabackendfullstep.service;

import hieu.javabackendfullstep.entity.RedisToken;

public interface RedisTokenService {
    RedisToken save(RedisToken token);
    void delete(String id);

}
