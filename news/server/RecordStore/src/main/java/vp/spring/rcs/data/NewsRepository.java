package vp.spring.rcs.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.spring.rcs.model.News;

@Component
public interface NewsRepository extends JpaRepository<News, Long> {

	public Page<News> findByTitleContainsIgnoreCase(String title, Pageable page);
	public Page<News> findByCategoryId(long id, Pageable page);
	
	public Page<News> findByTitleContainsIgnoreCaseAndCategoryId(String title, Long id, Pageable page);
	
}
