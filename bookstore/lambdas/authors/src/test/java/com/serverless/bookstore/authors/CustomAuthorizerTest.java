package com.serverless.bookstore.authors;

import com.serverless.bookstore.security.builders.PolicyDocumentSerializer;
import com.serverless.bookstore.security.modules.AuthorizationRequest;
import com.serverless.bookstore.security.modules.Policy;
import com.serverless.bookstore.security.modules.Statement;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CustomAuthorizerTest {

	@Test
	public void testAllowPolicy() {
		AuthorizationRequest authorizationRequest = new AuthorizationRequest();
		authorizationRequest.setMethodArn("testMethodArn");
		authorizationRequest.setAuthorizationToken(Statement.ALLOW);

		CustomAuthorizer customAuthorizer = new CustomAuthorizer();
		Policy policy = customAuthorizer.handleRequest(authorizationRequest, null);
		Assert.assertTrue(getEffect(policy).equals(Statement.ALLOW) );
	}

	@Test
	public void testDenyPolicy() {
		AuthorizationRequest authorizationRequest = new AuthorizationRequest();
		authorizationRequest.setMethodArn("testMethodArn");
		authorizationRequest.setAuthorizationToken(Statement.DENY);

		CustomAuthorizer customAuthorizer = new CustomAuthorizer();
		Policy policy = customAuthorizer.handleRequest(authorizationRequest, null);
		Assert.assertTrue(getEffect(policy).equals(Statement.DENY) );
	}

	private String getEffect(Policy policy) {
		Map<String, Object>[] statements = (Map<String, Object>[])policy.getPolicyDocument().get(PolicyDocumentSerializer.STATEMENT);
		return (String)statements[0].get(PolicyDocumentSerializer.EFFECT);
	}
}
