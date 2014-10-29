package com.example.homework.base;

public abstract class BaseApiInterface {
	
	protected BaseApiListener apiListener;
	
	public void setApiListener(BaseApiListener listener){
		apiListener = listener;
	}
	
	
	
}
