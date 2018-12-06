package com.serverless.bookstore.security.builders;

import com.serverless.bookstore.datalyer.builders.IBuilder;
import com.serverless.bookstore.security.modules.PolicyDocument;
import com.serverless.bookstore.security.modules.Statement;

public class PolicyStatementBuilder implements IBuilder<PolicyDocument> {
	private PolicyDocument policyDocument = new PolicyDocument();

	@Override public PolicyDocument build() {
		return policyDocument;
	}

	public PolicyStatementBuilder addStatement(String action, String effect, String resource) {
		policyDocument.getStatement().add(new Statement(action, effect, resource));
		return this;
	}


	public PolicyStatementBuilder version(String version) {
		policyDocument.setVersion(version);

		return this;
	}


}
