package project.economy_site;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import project.economy_site.resolver.UserArgumentResolver;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class EconomySiteApplication extends WebMvcConfigurationSupport {
    private final UserArgumentResolver userArgumentResolver;

//    @PostConstruct
//    void setTimeZone() {
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
//    }


    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(userArgumentResolver);
    }

    public static void main(String[] args) {
        SpringApplication.run(EconomySiteApplication.class, args);
    }

}
