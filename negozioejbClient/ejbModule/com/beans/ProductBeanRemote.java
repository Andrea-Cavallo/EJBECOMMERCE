package com.beans;

import javax.ejb.Remote;

import com.beans.interfaces.IProduct;

@Remote
public interface ProductBeanRemote extends IProduct {

}
