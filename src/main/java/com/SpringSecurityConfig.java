package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/*
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
*/
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.models.service.JpaUserDetailsService;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private LoginSuccessHandler successHandler;

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*
	 * @Bean static BCryptPasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * @Bean UserDetailsService userDetailsService() throws Exception {
	 * 
	 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 * manager.createUser(User.withUsername("user1").password(passwordEncoder().
	 * encode("user")).roles("USER").build()); manager.createUser(
	 * User.withUsername("admin").password(passwordEncoder().encode("admin")).roles(
	 * "ADMIN", "USER").build());
	 * 
	 * return manager; }
	 */

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests(requests -> requests.antMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
				/*
				 * .antMatchers("/show/**").hasAnyRole("USER")
				 * .antMatchers("/uploads/**").hasAnyRole("USER")
				 * .antMatchers("/form/**").hasAnyRole("ADMIN")
				 * .antMatchers("/delete/**").hasAnyRole("ADMIN")
				 */
				.anyRequest().authenticated())
				.formLogin(login -> login.successHandler(successHandler).loginPage("/login").permitAll())
				.logout(logout -> logout.permitAll())
				.exceptionHandling(handling -> handling.accessDeniedPage("/error403"));

		return http.build();
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}

}
