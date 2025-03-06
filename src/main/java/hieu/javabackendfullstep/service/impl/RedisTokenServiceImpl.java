package hieu.javabackendfullstep.service.impl;

import hieu.javabackendfullstep.entity.RedisToken;
import hieu.javabackendfullstep.repository.RedisTokenRepository;
import hieu.javabackendfullstep.service.RedisTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisTokenServiceImpl implements RedisTokenService {

    private final RedisTokenRepository repository;

    @Override
    public RedisToken save(RedisToken token) {
        RedisToken redisToken = repository.save(token);
        return redisToken;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
