package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {

		List<Product> categories = productRepository.findAll();

//		List<ProductDTO> productDTOs = new ArrayList<>();
//		
//		for (Product product : categories) {
//			productDTOs.add(new ProductDTO(product));
//		}

		List<ProductDTO> productDTOs = categories.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		return productDTOs;
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {

		Optional<Product> optionalProduct = productRepository.findById(id);
		Product product = optionalProduct.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));

		return new ProductDTO(product, product.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO productDTO) {
		Product product = new Product();
		copyDtoToEntity(productDTO, product);
		product = productRepository.save(product);
		return new ProductDTO(product);
	}

	

	@Transactional
	public ProductDTO update(Long id, ProductDTO productDTO) {
		try {
			Product product = productRepository.getOne(id);
			copyDtoToEntity(productDTO, product);
			productRepository.save(product);
			return new ProductDTO(product);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} 

	}

	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Integrity violation ");
		}		
	}

//	public Page<ProductDTO> findAllPagedOld(PageRequest pageRequest) {
//		Page<Product> categories = productRepository.findAll(pageRequest);
//
//		Page<ProductDTO> productDTOs = categories.map(x -> new ProductDTO(x));
//		return productDTOs;
//	
//	}
	
	public Page<ProductDTO> findAllPaged(Pageable pageable) {
		Page<Product> categories = productRepository.findAll(pageable);

		Page<ProductDTO> productDTOs = categories.map(x -> new ProductDTO(x));
		return productDTOs;
	
	}
	
	private void copyDtoToEntity(ProductDTO productDTO, Product product) {
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setDate(productDTO.getDate()); 
		product.setImgUrl(productDTO.getImgUrl()); 
		product.setPrice(productDTO.getPrice()); 
		
		
		
		product.getCategories().clear();
		for (CategoryDTO categoryDTO : productDTO.getCategories()) {
			Category category = categoryRepository.getOne(categoryDTO.getId());
			product.getCategories().add(category);
		}
		
	}
}
