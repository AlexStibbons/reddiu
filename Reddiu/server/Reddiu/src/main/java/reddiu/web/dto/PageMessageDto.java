package reddiu.web.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import reddiu.model.Message;


public class PageMessageDto {
	
	private int size;
	
	private int index;
	
	private List<MessageDto> content = new ArrayList<>();
	
	private boolean hasNext;
	
	private boolean hasPrevious;
	
	private long numberOfMessages;
	
	private int totalPages;

	public PageMessageDto() {
		super();
	}
	
	public PageMessageDto(Page<Message> page) {
		this.size = page.getSize();
		this.index = page.getNumber();
		this.content = page.getContent().stream()
					.map(MessageDto::new)
					.collect(Collectors.toList());
		this.hasNext = page.hasNext();
		this.hasPrevious = page.hasPrevious();
		this.numberOfMessages = page.getTotalElements();
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

	public List<MessageDto> getContent() {
		return content;
	}

	public void setContent(List<MessageDto> content) {
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

	public long getNumberOfMessages() {
		return numberOfMessages;
	}

	public void setNumberOfMessages(long numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	

}
