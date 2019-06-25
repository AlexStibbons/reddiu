package vp.spring.rcs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Comment;
import vp.spring.rcs.model.News;
import vp.spring.rcs.service.CategoryService;
import vp.spring.rcs.service.CommentService;
import vp.spring.rcs.service.NewsService;
import vp.spring.rcs.web.dto.CommentDto;
import vp.spring.rcs.web.dto.NewsDto;
import vp.spring.rcs.web.dto.PageNewsDto;

@RestController
public class NewsController {

	@Autowired
	NewsService newsService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("api/news")
	public ResponseEntity<PageNewsDto> findAll(Pageable page) {
		PageNewsDto dto = new PageNewsDto(newsService.findAll(page));
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	// @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("api/news/{id}")
	public ResponseEntity<NewsDto> findById(@PathVariable long id) {
		News found = newsService.findById(id);
		
		if (found != null) {
			return new ResponseEntity<>(new NewsDto(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("api/news/search") // frontend?
	public ResponseEntity<PageNewsDto> filter(@RequestParam String title, 
											@RequestParam(required = false) Long categoryId, 
											Pageable page) {
		if (categoryId != null) {
			PageNewsDto dto = new PageNewsDto(newsService.findByTitleAndCategory(title, categoryId, page));
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			PageNewsDto dot = new PageNewsDto(newsService.findByTitle(title, page));
			return new ResponseEntity<>(dot, HttpStatus.OK);
		}

	}
	
	
	@DeleteMapping("api/news/{id}")
	public ResponseEntity<Void> deleteNews(@PathVariable long id) {
		
		// delete all comments connected to the news
		commentService.findAll().stream()
			.filter(c -> c.getNews().getId() == id)
			.forEach(c -> commentService.deleteComment(c));
		
		// delete news itself
		newsService.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("api/news/{id}")
	public ResponseEntity<NewsDto> edit(@PathVariable long id, @RequestBody NewsDto newsDto){
		
		News found = newsService.findById(id);
		found.setId(id);
		found.setTitle(newsDto.getTitle());
		found.setDescription(newsDto.getDescription());
		found.setText(newsDto.getText());
		found.setCategory(categoryService.findById(newsDto.getCategory().getId()));
		
		found = newsService.save(found);
		
		return new ResponseEntity<>(new NewsDto(found), HttpStatus.OK);
	}
	
	@PostMapping("api/news/new")
	public ResponseEntity<NewsDto> create(@RequestBody NewsDto newsDto){
		News news = new News();
		news.setTitle(newsDto.getTitle());
		news.setDescription(newsDto.getDescription());
		news.setText(newsDto.getText());
		news.setCategory(categoryService.findById(newsDto.getCategory().getId()));
		
		news = newsService.save(news);
		
		return new ResponseEntity<>(new NewsDto(news), HttpStatus.CREATED);
	}
	
	@PostMapping("api/news/{id}/comment")
	public ResponseEntity<NewsDto> comment(@PathVariable long id, @RequestBody CommentDto commentDto){
		
		News found = newsService.findById(id);
		Comment comment = new Comment();
		
		comment.setUsername(commentDto.getUsername());
		comment.setText(commentDto.getText());
		comment.setNews(found);
		comment = commentService.save(comment);
		
		if (commentDto.getParentCommentId() != 0) {
			comment.setParentComment(commentService.findById(commentDto.getParentCommentId()));
			comment = commentService.save(comment);
		}

		
		return new ResponseEntity<>(new NewsDto(found), HttpStatus.OK);
	}
	 
}
