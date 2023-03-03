package com.koreaIT.java.BAM.controller;

import com.koreaIT.java.BAM.dto.Member;

public abstract class Controller {
	
	public static Member foundmember;
	
	public abstract void run(String cmd, String methodname);
	
	public static boolean islogined() {
		return foundmember != null;
	}
	
	public abstract void makeTestData();
	
}
