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
        CustomPersistentToken newToken = new CustomPersistentToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
        rememberMeTokenRepository.save(newToken);
	//getJpaTemplate().update(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());                
    }

    @Override
    @Transactional
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        CustomPersistentToken token = rememberMeTokenRepository.findBySeries(series);
        token.setTokenValue(tokenValue);
        token.setLastUsed(lastUsed);
        
    /*    getJpaTemplate().update(updateTokenSql, tokenValue, lastUsed, series); */
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
		try {
                    CustomPersistentToken token = rememberMeTokenRepository.findBySeries(series);
                    return token;
			return getJpaTemplate().queryForObject(tokensBySeriesSql,
					new RowMapper<PersistentRememberMeToken>() {
						public PersistentRememberMeToken mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return new PersistentRememberMeToken(rs.getString(1), rs
									.getString(2), rs.getString(3), rs.getTimestamp(4));
						}
					}, seriesId);
		}
		catch (EmptyResultDataAccessException zeroResults) {
			if (logger.isDebugEnabled()) {
				logger.debug("Querying token for series '" + seriesId
						+ "' returned no results.", zeroResults);
			}
		}
		catch (IncorrectResultSizeDataAccessException moreThanOne) {
			logger.error("Querying token for series '" + seriesId
					+ "' returned more than one value. Series" + " should be unique");
		}
		catch (DataAccessException e) {
			logger.error("Failed to load token for series " + seriesId, e);
		}

		return null;
	}

    @Override
    @Transactional    
    public void removeUserTokens(String username) {
       // List<CustomPersistentToken> token = rememberMeTokenRepository.findByUsername(username);
        rememberMeTokenRepository.delete(username);
   /*     getJpaTemplate().update(removeUserTokensSql, username); */
    }
}
