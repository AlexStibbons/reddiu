package vp.spring.rcs.web.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import vp.spring.rcs.model.Project;



public class PageDto {
	
	
	private int size; 
	
	private int index; 
	
	private List<ProjectDto> content = new ArrayList<>(); 
	
	private boolean hasNext;
	
	private boolean hasPrevious;
	
	private long numberOfItems;
	
	private int totalPages;


	
	public PageDto() {
		super();
	}

	public PageDto(Page<Project> page) {
		this.size = page.getSize();
		this.index = page.getNumber();
		this.content = page.getContent().stream()
						.map(ProjectDto::new)
						.collect(Collectors.toList());
		this.hasNext = page.hasNext();
		this.hasPrevious = page.hasPrevious();
		this.numberOfItems = page.getTotalElements();
		this.totalPages = page.getTotalPages();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<ProjectDto> getContent() {
		return content;
	}

	public void setContent(List<ProjectDto> content) {
		this.content = content;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public long getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(long numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	


}
