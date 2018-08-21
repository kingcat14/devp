package com.yunkang.saas.security.local.config;


import com.yunkang.saas.platform.config.PlatformConfig;
import com.yunkang.saas.security.local.business.authorize.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gonghongrui on 2017/1/10.
 */
//@Configuration
@EnableWebSecurity
//用于@PreAuthorize的生效,基于方法的权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
//覆盖默认的spring security提供的配置
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableJpaRepositories(basePackages = {"com.yunkang.saas.security"})
@EntityScan({"com.yunkang.saas.security"})
@Import({PlatformConfig.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private SecurityUserService securityUserService;


	@Value("${security.basic.enabled:true}")
	private boolean securityEnable;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		String[] antMatchers = {"/security/login/authenticate", "/login.html", "/ext_/**"
				,"/**/*.html","/**.html"
				,"/assets/**","/fonts/**","/maps/**","/scripts/**","/styles/**","/**/*.js"
				, "/favicon.ico", "/**/*.jpg", "/**/*.png", "/**/*.gif"
				,"/**/*.css"
				,"/ext_*/**/*"
		};

		if(!securityEnable){
			String[] testMatchers = {"/security/login/authenticate", "/security/login/getResource", "/security/login", "/login.html", "/ext_/**"
					, "/**.html","/assets/**","/fonts/**","/maps/**","/scripts/**","/styles/**","/**/*.js","/**/*.html"
					, "/favicon.ico", "/**/*.jpg", "/**/*.png", "/**/*.gif"
					,"/**/*.css"
					,"/ext_*/**/*"
					,"/**/*"
			};
			antMatchers = testMatchers;
		}
//		http.formLogin().loginPage("/login").permitAll()
//				.and().authorizeRequests().antMatchers("/health", "/css/**").anonymous()
//				.and().authorizeRequests().anyRequest().authenticated();

		http
				.formLogin().loginPage("/login").permitAll()
			//禁用CSRF保护
			.and().csrf().disable().authorizeRequests()
			//任何访问都必须授权

			//配置那些路径可以不用权限访问
//			.mvcMatchers("/login","*.html","assets/*").permitAll()
				.antMatchers(antMatchers).permitAll()
				.anyRequest().fullyAuthenticated()
				.and().formLogin()
			//登陆成功后的处理，因为是API的形式所以不用跳转页面
			.successHandler(new RestAuthenticationSuccessHandler())
			//登陆失败后的处理
			.failureHandler(new SimpleUrlAuthenticationFailureHandler())
			.and()
			//登出后的处理
			.logout().logoutSuccessHandler(new RestLogoutSuccessHandler())
			.and()
			//认证不通过后的处理
			.exceptionHandling().accessDeniedHandler(new RestAccessDeniedHandler())
			.authenticationEntryPoint(new RestAuthenticationEntryPoint())
		.and().headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityUserService())
				.passwordEncoder(passwordEncoder());
	}


	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//密码加密
		return new StandardPasswordEncoder();

	}

	@Bean
	SecurityUserService securityUserService() {
		return new SecurityUserService();
	}


	/**
	 * 登陆成功后的处理
	 */
	public static class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

		@Override
		public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authentication)
				throws ServletException, IOException {

			clearAuthenticationAttributes(request);
		}
	}

	/**
	 * 登出成功后的处理
	 */
	public static class RestLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

		@Override
		public void onLogoutSuccess(HttpServletRequest request,
                                    HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			//Do nothing!
		}
	}

	/**
	 * 登出成功后的处理
	 */
	public static class RestAccessDeniedHandler implements AccessDeniedHandler {


		@Override
		public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
			//Do nothing!
		}
	}

	/**
	 * 权限不通过的处理
	 */
	public static class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

		@Override
		public void commence(HttpServletRequest request,
		                     HttpServletResponse response,
		                     AuthenticationException authException) throws IOException {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"Authentication Failed: " + authException.getMessage());
		}
	}
}
