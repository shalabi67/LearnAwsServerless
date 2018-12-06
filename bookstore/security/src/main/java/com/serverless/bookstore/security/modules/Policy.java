package com.serverless.bookstore.security.modules;

public class Policy {
	private String principalId;
	private PolicyDocument policyDocument;
	private Context context;
	private String usageIdentifierKey;

	public Policy(String principalId, PolicyDocument policyDocument) {
		this.principalId = principalId;
		this.policyDocument = policyDocument;
	}

	public Policy() {
	}

	public String getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	public PolicyDocument getPolicyDocument() {
		return policyDocument;
	}

	public void setPolicyDocument(PolicyDocument policyDocument) {
		this.policyDocument = policyDocument;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getUsageIdentifierKey() {
		return usageIdentifierKey;
	}

	public void setUsageIdentifierKey(String usageIdentifierKey) {
		this.usageIdentifierKey = usageIdentifierKey;
	}
}