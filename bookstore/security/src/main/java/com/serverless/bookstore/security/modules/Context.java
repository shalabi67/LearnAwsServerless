package com.serverless.bookstore.security.modules;

public class Context {
	private String stringKey;
	private String numberKey;
	private String booleanKey;

	public Context(String stringKey, String numberKey, String booleanKey) {
		this.stringKey = stringKey;
		this.numberKey = numberKey;
		this.booleanKey = booleanKey;
	}
	public Context() {

	}

	public String getStringKey() {
		return stringKey;
	}

	public void setStringKey(String stringKey) {
		this.stringKey = stringKey;
	}

	public String getNumberKey() {
		return numberKey;
	}

	public void setNumberKey(String numberKey) {
		this.numberKey = numberKey;
	}

	public String getBooleanKey() {
		return booleanKey;
	}

	public void setBooleanKey(String booleanKey) {
		this.booleanKey = booleanKey;
	}
}
