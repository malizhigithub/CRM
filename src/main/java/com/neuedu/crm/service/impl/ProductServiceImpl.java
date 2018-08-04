package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.ProductMapper;
import com.neuedu.crm.pojo.Product;
import com.neuedu.crm.pojo.ProductExample;
import com.neuedu.crm.service.IProductService;

/**
 * 创建wujunyou，修改malizhi
 * @author malihzi
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public long countByProductExample(ProductExample productExample) {
		return productMapper.countByExample(productExample);
	}

	@Override
	public boolean deleteByProductExample(ProductExample productExample) {
		return productMapper.deleteByExample(productExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return productMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertProduct(Product product) {
		return productMapper.insert(product) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(Product product) {
		return productMapper.insertSelective(product) > 0 ? true : false;
	}

	@Override
	public List<Product> selectByProductExample(ProductExample productExample) {
		return productMapper.selectByExample(productExample);
	}

	@Override
	public Product selectProductByPrimaryKey(Integer id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByProductExampleSelective(Product product, ProductExample productExample) {
		return productMapper.updateByExampleSelective(product, productExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByProductExample(Product product, ProductExample productExample) {
		return productMapper.updateByExample(product, productExample) > 0 ? true : false;
	}

	@Override
	public boolean updateProductByPrimaryKeySelective(Product product) {
		return productMapper.updateByPrimaryKeySelective(product) > 0 ? true : false;
	}

	@Override
	public boolean updateProductByPrimaryKey(Product product) {
		return productMapper.updateByPrimaryKey(product) > 0 ? true : false ;
	}
}
