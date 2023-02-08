package com.devsuperior.movieflix.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {

	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtSecret);
		return tokenConverter;
	}

	@Bean
	JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

// ****	
// not needed anymore, used to debug internal InternalAuthorizationServiceException (missing @Autowired)
// ****	
//    @Bean
//    WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
//        return new DefaultWebResponseExceptionTranslator() {
//            @Override
//            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
//                // This is the line that prints the stack trace to the log. You can customise this to format the trace etc if you like
//             	e.printStackTrace();
//             	
//                // Carry on handling the exception
//                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
//                HttpHeaders headers = new HttpHeaders();
//                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
//                OAuth2Exception excBody = responseEntity.getBody();
//                return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
//            }
//        };
//    }
}
