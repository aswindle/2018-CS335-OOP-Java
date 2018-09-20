
public class Book {
	private String title;
	private String writer;
	private int pageCount;
	private int pubYear;

	public Book(String name, String author, int pages, int year) {
		this.title = name;
		this.writer = author;
		this.pageCount = pages;
		this.pubYear = year;
	}

	// getters and setters omitted

	public String toString() {
		return String.format("Title: %s\nWriter: %s\nYear: %d\nPages: %d", title, writer, pubYear, pageCount);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPubYear() {
		return pubYear;
	}

	public void setPubYear(int pubYear) {
		this.pubYear = pubYear;
	}

}
