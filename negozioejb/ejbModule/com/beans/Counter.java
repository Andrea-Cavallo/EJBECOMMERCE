package com.beans;

import java.io.Serializable;

import javax.ejb.Remote;
import javax.ejb.Singleton;

@Singleton
@Remote
public class Counter implements Serializable, CounterRemote {

	
	private static final long serialVersionUID = 1L;
	private int count = 1;

	public Counter() {
	}

	@Override
	public void inc() {
		count++;
		System.out.println(count);
	}

	@Override
	public int inc2() {
		count++;
		return count;
	}

}
