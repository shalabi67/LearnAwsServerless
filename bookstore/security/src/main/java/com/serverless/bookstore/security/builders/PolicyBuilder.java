package com.serverless.bookstore.security.builders;

import com.serverless.bookstore.datalyer.builders.IBuilder;
import com.serverless.bookstore.security.modules.Context;
import com.serverless.bookstore.security.modules.Policy;
import com.serverless.bookstore.security.modules.PolicyDocument;

public class PolicyBuilder implements IBuilder<Policy> {
	private Policy policy = new Policy();
	public PolicyBuilder(String principalId, PolicyDocument policyDocument) {
		policy.setPolicyDocument(policyDocument);
		policy.setPrincipalId(principalId);
	}
	public PolicyBuilder context(String stringKey, String numberKey, String booleanKey) {
		policy.setContext(new Context(stringKey, numberKey, booleanKey));

		return this;
	}
	public PolicyBuilder usageIdentifierKey(String usageIdentifierKey) {
		policy.setUsageIdentifierKey(usageIdentifierKey);

		return this;
	}

	@Override public Policy build() {
		return policy;
	}
}
