package com.marcelo.wsoauth2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.marcelo.wsoauth2.CustomUserDetailsService;

import springfox.documentation.annotations.ApiIgnore;


@Configuration
@EnableAuthorizationServer
@ApiIgnore
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	private TokenStore tokenStore = new InMemoryTokenStore();
	
	private String CLIENTE = "cliente";
	
	private String CLIENTE_SECRET = "123";
	
	private static final String RESOURCE_ID = "restservice";
			
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		endpoints.tokenStore(this.tokenStore)
				.authenticationManager(this.authenticationManager)
				.userDetailsService(customUserDetailService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients ) throws Exception{
		clients.inMemory()
				.withClient(CLIENTE)
				.secret(new BCryptPasswordEncoder().encode(CLIENTE_SECRET))
				.authorizedGrantTypes("password", "authorization_code", "refresh_token")
				.scopes("bar", "read", "write")
				.resourceIds(RESOURCE_ID)
				.accessTokenValiditySeconds(500)
				.refreshTokenValiditySeconds(60*60*24);
	}
	
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenService = new DefaultTokenServices();
		tokenService.setSupportRefreshToken(true);
		tokenService.setAccessTokenValiditySeconds(0);
		tokenService.setTokenStore(this.tokenStore);
		
		return tokenService;
	}

}
