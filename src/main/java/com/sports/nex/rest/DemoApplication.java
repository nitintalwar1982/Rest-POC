package com.sports.nex.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

//@ApplicationPath("/rest")
public class DemoApplication extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(SportsNexService.class);
		return classes;
	}
}
