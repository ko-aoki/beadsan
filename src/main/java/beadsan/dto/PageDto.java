package beadsan.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageDto<T> {

//	public int getTotalPages() {
//		return totalPages;
//	}
//
//	public void setTotalPages(int totalPages) {
//		this.totalPages = totalPages;
//	}
//
//	public long getTotalElements() {
//		return totalElements;
//	}
//
//	public void setTotalElements(long totalElements) {
//		this.totalElements = totalElements;
//	}
//
//	public int getNumberOfElements() {
//		return numberOfElements;
//	}
//
//	public void setNumberOfElements(int numberOfElements) {
//		this.numberOfElements = numberOfElements;
//	}
//
//	public int getSize() {
//		return size;
//	}
//
//	public void setSize(int size) {
//		this.size = size;
//	}
//
//	public List<T> getContent() {
//		return content;
//	}
//
//	public void setContent(List<T> content) {
//		this.content = content;
//	}
//
	private int totalPages;
	
	private long totalElements;
	
	private int numberOfElements;

	private int size;

	private List<T> content;
}

