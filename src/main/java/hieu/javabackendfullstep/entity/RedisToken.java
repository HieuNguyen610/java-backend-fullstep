package hieu.javabackendfullstep.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("RedisToken")
public class RedisToken implements Serializable {
    private String id;
    private String accessToken;
    private String refreshToken;
    private String resetToken;
}
