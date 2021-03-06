package com.bq.corbel.lib.token.verifier;

import com.bq.corbel.lib.token.exception.TokenVerificationException;
import com.bq.corbel.lib.token.reader.TokenReader;
import com.bq.corbel.lib.token.repository.OneTimeAccessTokenRepository;

/**
 * @author Alberto J. Rubio
 * 
 */
public class OneTimeAccessTokenVerifier implements TokenVerifier {

	private final OneTimeAccessTokenRepository oneTimeAccessTokenRepository;

	public OneTimeAccessTokenVerifier(OneTimeAccessTokenRepository oneTimeAccessTokenRepository) {
		this.oneTimeAccessTokenRepository = oneTimeAccessTokenRepository;
	}

	@Override
	public void verify(TokenReader reader) throws TokenVerificationException {
		if (reader.getInfo().isOneUseToken()) {
			if (oneTimeAccessTokenRepository.findAndRemove(reader.getToken()) == null) {
				throw new TokenVerificationException.TokenNotFound();
			}
		}
	}
}
