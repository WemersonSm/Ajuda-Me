package com.senai.ajudame.conf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
	
	@Value("${ads04.security.urlCheckToken}")
	private String urlCheckToken;
	
	@Value("${ads04.security.clientId}")
	private String clientId;
	
	@Value("${ads04.security.clientSecret}")
	private String clientSecret;


	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**").permitAll()
        		.antMatchers(HttpMethod.POST, "/login**").permitAll()
        		.antMatchers(HttpMethod.POST, "/login/**").permitAll()
        		.antMatchers(HttpMethod.POST, "/pessoafisica/criar/**").permitAll()
        		.antMatchers(HttpMethod.POST, "/pessoajuridica/criar/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.GET, "/email**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.GET, "/email/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                ;
    }
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
       resources.resourceId("todo-services").tokenServices(tokenService());
	}
	
	
	@Primary
	@Bean
	public RemoteTokenServices tokenService() {
	    RemoteTokenServices tokenService = new RemoteTokenServices();
	    tokenService.setCheckTokenEndpointUrl(urlCheckToken);
	    tokenService.setClientId(clientId);
	    tokenService.setClientSecret(clientSecret);
	    tokenService.setAccessTokenConverter(getAccessTokenConverter());
	    return tokenService;
	}

	private Map<String, Object> getDetailsMap(Map<String, ?> map){
		Map<String, Object> ret = new HashMap<>();
		ret.put("tenant", map.get("tenant"));
		return ret;
	}

	private AccessTokenConverter getAccessTokenConverter() {
		return new DefaultAccessTokenConverter() {
			@Override
			public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
				OAuth2Authentication oauth = super.extractAuthentication(map);
				oauth.setDetails(getDetailsMap(map));
				return oauth;
			}
		};
	}	
	
	
}
