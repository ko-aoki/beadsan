package beadsan.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDto<T> {

	private int totalPages;
	
	private long totalElements;
	
	private int numberOfElements;

	private int size;

	private List<T> content;
}

