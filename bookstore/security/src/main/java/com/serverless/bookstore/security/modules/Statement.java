package com.serverless.bookstore.security.modules;

public class Statement {
	public static String ALLOW = "allow";
	public static String DENY = "deny";

	private String action;
	private String effect;
	private String resource;

	public Statement(String action, String effect, String resource) {
		this.action = action;
		this.effect = effect;
		this.resource = resource;
	}

	public Statement() {
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
}