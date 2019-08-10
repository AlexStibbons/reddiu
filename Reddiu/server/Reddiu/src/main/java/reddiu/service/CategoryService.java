package reddiu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reddiu.data.CategoryRepository;
import reddiu.model.Category;
import reddiu.web.dto.CategoryDto;

@Component
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(long id) {
		return categoryRepository.findById(id).get();
	}
	


}
