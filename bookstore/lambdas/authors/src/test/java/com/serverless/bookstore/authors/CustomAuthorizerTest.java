package com.serverless.bookstore.authors;

import com.serverless.bookstore.security.modules.AuthorizationRequest;
import com.serverless.bookstore.security.modules.Policy;
import org.junit.Test;

public class CustomAuthorizerTest {

	@Test
	public void testAllowPolicy() {
		AuthorizationRequest authorizationRequest = new AuthorizationRequest();
		authorizationRequest.setMethodArn("testMethodArn");
		authorizationRequest.setAuthorizationToken("allow");

		CustomAuthorizer customAuthorizer = new CustomAuthorizer();
		Policy policy = customAuthorizer.handleRequest(authorizationRequest, null);
	}
}
