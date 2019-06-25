package vp.spring.rcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.NewsRepository;
import vp.spring.rcs.model.News;

@Component
public class NewsService {

	@Autowired
	NewsRepository newsRepository;
	
	public Page<News> findAll(Pageable page) {
		return newsRepository.findAll(page);
	}
	
	public void deleteById(long id) {
		newsRepository.deleteById(id);
	}
	
	public News findById(long id) {
		return newsRepository.findById(id).get();
	}
	
	public News save(News news) {
		return newsRepository.save(news);
	}
	
	public Page<News> findByTitle(String title, Pageable page) {
		return newsRepository.findByTitleContainsIgnoreCase(title, page);
	}
	
	public Page<News> findByCategory(long id, Pageable page) {
		return newsRepository.findByCategoryId(id, page);
	}
	
	public Page<News> findByTitleAndCategory(String title, Long id, Pageable page) {
		return newsRepository.findByTitleContainsIgnoreCaseAndCategoryId(title, id, page);
	}
	
}
