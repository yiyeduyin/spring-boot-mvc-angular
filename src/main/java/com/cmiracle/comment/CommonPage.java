package com.cmiracle.comment;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;

public class CommonPage<T> implements Serializable, Iterable<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 80926525668946115L;

	private int number;
	private int numberOfElements;
	private int size;
	private long totalElements;
	private int totalPages;
	private boolean hasContent;
	private boolean hasNext;
	private boolean hasPrevious;
	private boolean first;
	private boolean last;
	private List<T> content;

	public CommonPage(Page<T> page) {
		this.content = page.getContent();
		this.number = page.getNumber();
		this.numberOfElements = page.getNumberOfElements();
		this.size = page.getSize();
		this.totalElements = page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.hasContent = page.hasContent();
		this.hasNext = page.hasNext();
		this.hasPrevious = page.hasPrevious();
		this.first = page.isFirst();
		this.last = page.isLast();
	}

	public CommonPage(List<T> content, int number, int numberOfElements, int size, long totalElements, int totalPages,
			boolean hasContent, boolean hasNext, boolean hasPrevious, boolean first, boolean last) {
		this.content = content;
		this.number = number;
		this.numberOfElements = numberOfElements;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.hasContent = hasContent;
		this.hasNext = hasNext;
		this.hasPrevious = hasPrevious;
		this.first = first;
		this.last = last;
	}

	public CommonPage() {

	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public List<T> getContent() {
		return content;
	}

	public int getNumber() {
		return number;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public int getSize() {
		return size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public boolean hasContent() {
		return hasContent;
	}

	public boolean hasNext() {
		return hasNext;
	}

	public boolean hasPrevious() {
		return hasPrevious;
	}

	public boolean isFirst() {
		return first;
	}

	public boolean isLast() {
		return last;
	}

	public Iterator<T> iterator() {
		return content.iterator();
	}

	public Object getSort() {
		return new Sort();
	}

	class Sort {

	}
}
