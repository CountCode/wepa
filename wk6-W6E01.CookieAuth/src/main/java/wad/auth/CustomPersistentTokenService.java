package wad.auth;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import wad.domain.CustomPersistentToken;
import wad.repository.RememberMeTokenRepository;

@Service
public class CustomPersistentTokenService implements PersistentTokenRepository {
    
@Autowired
private RememberMeTokenRepository rememberMeTokenRepository;
    
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        List<CustomPersistentToken> tokens =rememberMeTokenRepository.findBySeries(token.getSeries());
        if (tokens.isEmpty()) {
            CustomPersistentToken newToken = new CustomPersistentToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
            rememberMeTokenRepository.save(newToken);
        } else {
            removeUserTokens(token.getUsername());
        }
    }

    @Override
    @Transactional
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        List<CustomPersistentToken> tokens =rememberMeTokenRepository.findBySeries(series);
        //check that the List is not empty and has only one token
        CustomPersistentToken token = tokens.remove(0);
        token.setTokenValue(tokenValue);
        token.setLastUsed(lastUsed);

        // rememberMeTokenRepository.sabe(token)  I think the transactinal does the same?
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
                    List<CustomPersistentToken> tokens = rememberMeTokenRepository.findBySeries(series);
                    if (tokens.isEmpty() || tokens.size()>1) {
                        tokens.removeAll(tokens);
                        return null;
                    }
                    CustomPersistentToken token = tokens.remove(0);
                    PersistentRememberMeToken pRMToken = new PersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getLastUsed());
                    return pRMToken;	
	}

    @Override
    @Transactional    
    public void removeUserTokens(String username) {

        List<CustomPersistentToken> token = rememberMeTokenRepository.findByUsername(username);
        rememberMeTokenRepository.delete(token);
    }
}
