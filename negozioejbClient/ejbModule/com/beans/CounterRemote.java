package com.beans;

import javax.ejb.Remote;

import com.beans.interfaces.ICounter;


@Remote
public interface CounterRemote  extends ICounter {



}
