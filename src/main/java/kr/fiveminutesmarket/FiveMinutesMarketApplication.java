package kr.fiveminutesmarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = FiveMinutesMarketApplication.class)
@SpringBootApplication
public class FiveMinutesMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiveMinutesMarketApplication.class, args);
    }

}
