package com.serverless.bookstore.security.modules;

import java.util.ArrayList;
import java.util.List;

public class PolicyDocument {
	private String Version;
	private List<Statement> statement = new ArrayList<>();


	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public List<Statement> getStatement() {
		return statement;
	}

	public void setStatement(List<Statement> statement) {
		this.statement = statement;
	}
}

