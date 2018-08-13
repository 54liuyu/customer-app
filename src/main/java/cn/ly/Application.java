package cn.ly;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuyu685
 * @since 2018/8/13
 */
@SpringBootApplication
public class Application {

  // @Bean 应用在方法上，用来将方法返回值设为为bean
  @Bean
  //@LoadBalanced // @LoadBalanced实现负载均衡
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(Application.class).run(args);
  }
}
