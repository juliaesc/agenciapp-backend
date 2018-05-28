package ar.com.buildingways.agenciapp.security;
//package ar.com.buildingways.iplycbackend.security;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Autowired
//	private DataSource dataSource;
//	
//	/**
//	  * Se configuran las autenticaciones de los usuarios por nombre de usuario (username).
//	**/
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication()
//				.usersByUsernameQuery("select username, password, active from users where username=?")
//				.authoritiesByUsernameQuery("select u.username, r.role from users u inner join user_role ur on(u.user_id=ur.user_id) inner join roles r on(ur.role_id=r.role_id) where u.username=?")
//				.dataSource(dataSource)
//				.passwordEncoder(bCryptPasswordEncoder);
//	}
//
//	/**
//	  * Se configuran las autorizaciones para las vistas según rol de usuario.
//	**/
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.
//			authorizeRequests()
//				.antMatchers("/", "/login", "/registration", "/api").permitAll()
//				.antMatchers("/usersList").hasAuthority("ADMIN").anyRequest().authenticated()
//				.and().csrf().disable().formLogin().loginPage("/login").failureUrl("/login?error=true")
//				.defaultSuccessUrl("/home")
//				.usernameParameter("username")
//				.passwordParameter("password")
//				.and().exceptionHandling().accessDeniedPage("/access-denied");
//	}
//}