package com.erbf.bugsLife.oauth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	ADMIN("ROLE_ADMIN", "ADMIN"),
	USER("ROLE_USER","USER"),
	MANAGER("ROLE_MANAGER","MANAGER");
	
	private final String key;
	private final String name;
	
	
}
