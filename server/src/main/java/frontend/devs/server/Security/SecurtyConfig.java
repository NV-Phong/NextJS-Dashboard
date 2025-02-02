package frontend.devs.server.Security;

import frontend.devs.server.Service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurtyConfig
{

	private final JwtTokenProvider         jwtTokenProvider;
	private final CustomUserDetailsService customUserDetailsService;

	@Lazy
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public UserDetailsService userDetailsService()
		{
			return customUserDetailsService;
		}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
		{
			return new BCryptPasswordEncoder();
		}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			  throws Exception
		{
			return authenticationConfiguration.getAuthenticationManager();
		}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
		{
			http.csrf(csrf -> csrf.disable())
					  .authorizeHttpRequests(
								 auth -> auth.requestMatchers("/Auth/**")
											.permitAll()
											.anyRequest()
											.authenticated())
					  .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			return http.build();
		}
}
