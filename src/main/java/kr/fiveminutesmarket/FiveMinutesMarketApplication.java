package kr.fiveminutesmarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@MapperScan(basePackageClasses = FiveMinutesMarketApplication.class)
@SpringBootApplication
public class FiveMinutesMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiveMinutesMarketApplication.class, args);
    }

}
