package com.template.test;

import java.util.prefs.Preferences;

import org.junit.Test;

public class RegisterUtilTest {

	@Test
	public void test(){
		System.out.println(Preferences.systemRoot().name());
		System.out.println(Preferences.systemRoot().node("jshp").absolutePath());
		System.out.println(Preferences.systemRoot().node("jshp"));
	}
}
