package com.bankonetconseiller;

public enum Steps {
	STEP1("Nom du client:"), STEP2("Prenom du client:"), STEP3("Login du client:");
	
	private String value;
	
	private Steps(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
