package com.example.cms.utility;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class ResponeStructure<T>{
	
	private int statuscode;
	private String message;
	private T body;
	
	public ResponeStructure<T> setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}
	
	public ResponeStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public ResponeStructure<T> setBody(T body) {
		this.body = body;
		return this;
	}
	

}
