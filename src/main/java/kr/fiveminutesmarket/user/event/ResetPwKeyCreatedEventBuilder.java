package kr.fiveminutesmarket.user.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import kr.fiveminutesmarket.outbox.event.OutBoxEvent;
import kr.fiveminutesmarket.outbox.event.OutBoxEventBuilder;
import kr.fiveminutesmarket.user.domain.ResetPwKey;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ResetPwKeyCreatedEventBuilder implements OutBoxEventBuilder<ResetPwKeyCreated> {

    // JSON 형태 변환
    @Override
    public OutBoxEvent createOutBoxEvent(ResetPwKeyCreated domainEvent) {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // Custom Serializer 부여(DateTime Format 규격화)
        javaTimeModule.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
        mapper.registerModule(javaTimeModule);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        JsonNode jsonNode = mapper.convertValue(domainEvent, JsonNode.class);

        return new OutBoxEvent(
                domainEvent.getResetPwKeyId(),
                ResetPwKey.class.getSimpleName(),
                domainEvent.getClass().getSimpleName(),
                jsonNode.toString());
    }
}
