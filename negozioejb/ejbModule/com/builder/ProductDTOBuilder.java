package com.builder;

import com.dto.ProductDTO;
import com.model.Product;

public class ProductDTOBuilder {

	public static Product dtoToModel(ProductDTO pdto) {
		Product p = new Product();
		p.setId(pdto.getId());
		p.setName(pdto.getName());
		p.setDescription(pdto.getDescription());
		p.setPrezzo(pdto.getPrice());
		return p;
	}

	public static ProductDTO modelToDto(Product p) {
		ProductDTO pdto = new ProductDTO();
		pdto.setId(p.getId());
		pdto.setName(p.getName());
		pdto.setDescription(p.getDescription());
		pdto.setPrezzo(p.getPrice());

		return pdto;

	}

}
