package com.serverless.bookstore.security.modules;

public class Statement {
	private String Action;
	private String Effect;
	private String Resource;

	public Statement(String action, String effect, String resource) {
		Action = action;
		Effect = effect;
		Resource = resource;
	}

	public Statement() {
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public String getEffect() {
		return Effect;
	}

	public void setEffect(String effect) {
		Effect = effect;
	}

	public String getResource() {
		return Resource;
	}

	public void setResource(String resource) {
		Resource = resource;
	}
}