package reddiu.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reddiu.model.Category;
import reddiu.service.CategoryService;
import reddiu.web.dto.CategoryDto;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("api/categories")
	public ResponseEntity<List<CategoryDto>> findAll(){
		
		List<CategoryDto> dtos = categoryService.findAll().stream()
								.map(CategoryDto::new)
								.collect(Collectors.toList());
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("api/categories/{id}")
	public ResponseEntity<CategoryDto> findById(@PathVariable long id){
		Category found = categoryService.findById(id);
		
		if (found != null) {
			return new ResponseEntity<>(new CategoryDto(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
