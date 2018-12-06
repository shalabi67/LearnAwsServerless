package com.serverless.bookstore.security.modules;

public class AuthorizationRequest {
	private String type;
	private String authorizationToken;
	private String methodArn;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthorizationToken() {
		return authorizationToken;
	}

	public void setAuthorizationToken(String authorizationToken) {
		this.authorizationToken = authorizationToken;
	}

	public String getMethodArn() {
		return methodArn;
	}

	public void setMethodArn(String methodArn) {
		this.methodArn = methodArn;
	}
}
