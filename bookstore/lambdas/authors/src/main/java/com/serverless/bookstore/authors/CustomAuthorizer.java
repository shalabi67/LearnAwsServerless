package com.serverless.bookstore.authors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.bookstore.security.builders.PolicyBuilder;
import com.serverless.bookstore.security.modules.AuthorizationRequest;
import com.serverless.bookstore.security.modules.Policy;
import com.serverless.bookstore.security.builders.PolicyStatementBuilder;
import com.serverless.bookstore.security.modules.Statement;

public class CustomAuthorizer implements RequestHandler<AuthorizationRequest, Policy> {

	@Override public Policy handleRequest(AuthorizationRequest authorizationRequest, Context context) {
		return createPolicy(authorizationRequest);
	}

	private Policy createPolicy(AuthorizationRequest authorizationRequest) {
		PolicyStatementBuilder policyStatementBuilder = new PolicyStatementBuilder();
		policyStatementBuilder.version("2012-10-17");
		String effect = Statement.DENY;
		if(authorizationRequest.getAuthorizationToken().equalsIgnoreCase(Statement.ALLOW)) {
			effect = Statement.ALLOW;
		}
		policyStatementBuilder.addStatement("execute-api:Invoke", effect, authorizationRequest.getMethodArn());
		PolicyBuilder policyBuilder = new PolicyBuilder("user-id::123", policyStatementBuilder.build());
		return policyBuilder.build();
	}
}
