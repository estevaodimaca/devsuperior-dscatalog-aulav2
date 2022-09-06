package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {

		List<Category> categories = categoryRepository.findAll();

//		List<CategoryDTO> categoryDTOs = new ArrayList<>();
//		
//		for (Category category : categories) {
//			categoryDTOs.add(new CategoryDTO(category));
//		}

		List<CategoryDTO> categoryDTOs = categories.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		return categoryDTOs;
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {

		Optional<Category> optionalCategory = categoryRepository.findById(id);
		Category category = optionalCategory.orElseThrow(() -> new EntityNotFoundException("Entity not Found"));

//		List<CategoryDTO> categoryDTOs = new ArrayList<>();
//		
//		for (Category category : categories) {
//			categoryDTOs.add(new CategoryDTO(category));
//		}

		return new CategoryDTO(category);
	}
}
