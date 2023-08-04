package com.raven.label.springboot.app;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.raven.label.springboot.app.service.JpaUserDetailService;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig {
	
	@Autowired
	private JpaUserDetailService userDetailService;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private DataSource dataSource;
    

    /*@Autowired
    public void userDetailsService(AuthenticationManagerBuilder build) throws Exception {
       build.userDetailsService(userDetailService)
       .passwordEncoder(passwordEncoder);
    }*/
    
    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("select username, password, enabled from usuarios where username=?")
                .authoritiesByUsernameQuery("select u.username, a.rol from roles a inner join users u on (a.usuario_id=u.id) where u.username=?")
                .and().build();
    }
    
    
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//		.authorizeHttpRequests((authz) -> {
//			try {
//				authz.requestMatchers("/css/**","/js/**","/img/**","/icons/**","/index").permitAll()
//				.anyRequest().authenticated()
//				.and()
//				.formLogin()
//				.loginPage("/login")
//				.permitAll()
//				.and()
//				.logout()
//				.permitAll();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});

		return http.build();

	}


}
