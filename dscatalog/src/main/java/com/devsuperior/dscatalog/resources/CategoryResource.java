package com.devsuperior.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
//		List<Category> categories = new ArrayList<>();
//		categories.add(new Category(1L, "Books"));
//		categories.add(new Category(2L, "Electronics"));
//		
		List<CategoryDTO> categoryDTOs = categoryService.findAll();
		return ResponseEntity.ok().body(categoryDTOs);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
//		List<Category> categories = new ArrayList<>();
//		categories.add(new Category(1L, "Books"));
//		categories.add(new Category(2L, "Electronics"));
//		
		CategoryDTO categoryDTO = categoryService.findById(id);
		return ResponseEntity.ok().body(categoryDTO);
	}
}
