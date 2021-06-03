package com.softper.userservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.softper.userservice.config.JwtAuthEntryPoint;
import com.softper.userservice.security.JWTAuthorizationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableFeignClients
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	

  @Bean
  public ModelMapper modelMapper() {return new ModelMapper();}

  @Autowired

  private JwtAuthEntryPoint authEntryPoint;

  @EnableWebSecurity
  @Configuration
  class WebSecurityConfig extends WebSecurityConfigurerAdapter {

      @Bean
      public CorsFilter corsFilter() {
          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
          CorsConfiguration config = new CorsConfiguration();
          config.setAllowCredentials(true);
          config.addAllowedOrigin("*");
          config.addAllowedHeader("*");
          config.addAllowedMethod("OPTIONS");
          config.addAllowedMethod("GET");
          config.addAllowedMethod("POST");
          config.addAllowedMethod("PUT");
          config.addAllowedMethod("DELETE");
          source.registerCorsConfiguration("/**", config);
          return new CorsFilter(source);
      }




      @Override
      public void configure(WebSecurity web) throws Exception {
          web.ignoring().antMatchers("/index.html","/ts-api-docs","/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui**", "/webjars/**");
      }


      /*
      * Ya esta configurado en httsecurity http
      * solo falta la configuracion web de swagger
      * */

      @Override
      protected void configure(HttpSecurity http) throws Exception {
          /*http
                  .csrf().disable()
                  .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                  .authorizeRequests()
                  .antMatchers(HttpMethod.POST, "/api/authentication/**").permitAll()
                  .anyRequest().authenticated();
           */
          http
                  .csrf().disable()
                  .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                  .authorizeRequests().
                  anyRequest().permitAll();
      }
  }

}

@RefreshScope
@RestController
class MessageRestController {

  @Value("${message:Hello default}")
  private String message;

  @RequestMapping("/message")
  String getMessage() {
    return this.message;
  }
}