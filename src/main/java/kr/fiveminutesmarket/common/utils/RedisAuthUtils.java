package kr.fiveminutesmarket.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.exception.errors.JsonDeserializeFailedException;
import kr.fiveminutesmarket.common.exception.errors.JsonSerializeFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisAuthUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisAuthUtils.class);

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper mapper;

    public RedisAuthUtils(RedisTemplate<String, Object> redisTemplate, ObjectMapper mapper) {
        this.redisTemplate = redisTemplate;
        this.mapper = mapper;
    }

    public UserSessionDto getSession(String authKey) {
        String jsonResult = (String) redisTemplate.opsForValue().get(authKey);

        if (jsonResult != null) {
            try {
                return mapper.readValue(jsonResult, UserSessionDto.class);
            } catch (JsonProcessingException e) {
                redisTemplate.delete(authKey);
                throw new JsonDeserializeFailedException();
            }
        }

        return null;
    }

    public String setSession(UserSessionDto userSession) {
        String authKey = UUID.randomUUID().toString();

        try {
            String json = mapper.writeValueAsString(mapper.convertValue(userSession, Map.class));
            redisTemplate.opsForValue().set(authKey, json);
            redisTemplate.expire(authKey, 1800, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            throw new JsonSerializeFailedException();
        }

        return authKey;
    }

    public void renewSessionExpire(UserSessionDto sessionDto, String authKey) {

        if(sessionDto != null) {
            logger.info("Before 만료시간 연장: " + redisTemplate.getExpire(authKey, TimeUnit.SECONDS));
            redisTemplate.expire(authKey, 1800, TimeUnit.SECONDS);
            logger.info("After 만료시간 연장: " + redisTemplate.getExpire(authKey, TimeUnit.SECONDS));
        }
    }
}
