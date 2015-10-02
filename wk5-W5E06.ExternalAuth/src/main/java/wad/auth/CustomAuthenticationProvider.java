package wad.auth;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.json.JSONObject;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final String AUTH_URI = "http://authebin.herokuapp.com/authenticate";

    private final RestTemplate restTemplate;

    public CustomAuthenticationProvider() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        
        String username = a.getName();
        String password = a.getCredentials().toString();
       
        JSONObject request = new JSONObject();
        request.put("username", username);
        request.put("password", password);
        
        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        // send request and parse result
        ResponseEntity<String> loginResponse = restTemplate
            .exchange(AUTH_URI, HttpMethod.POST, entity, String.class);
        
        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("USER")); // or ROLE_USER ???
            return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);                        
        } else  {       
            throw new AuthenticationException("Unable to auth against third party systems") {};
        }
   //     return null;
    }
  
    
    @Override
    public boolean supports(Class<?> type) {
        return true;
    }

}
