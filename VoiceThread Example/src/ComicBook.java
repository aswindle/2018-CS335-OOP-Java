
public class ComicBook extends Book {
	// ComicBook's extra fields that are in addition to Book's
	private String artist;
	private int issue;

	public ComicBook(String name, String author, String artist, 
			int pages, int year, int issue) {
		
		// super() MUST be the first call in the constructor
		super(name, author, pages, year);

		// set the new fields of the derived class afterwards
		this.artist = artist;
		this.issue = issue;
	}
	
	// getters and setters omitted

//	public String toString(){
//		return super.toString() + 
//				String.format("\nArtist: %s\nIssue: %d", artist, issue);
//	}
	
	public String toString(){
		return String.format("Title: %s #%d\nWriter: %s\nArtist: %s\nYear: %d\nPages: %d", 
				getTitle(), issue, getWriter(), this.artist,
				super.getPubYear(), this.getPageCount());
	}
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getIssue() {
		return issue;
	}

	public void setIssue(int issue) {
		this.issue = issue;
	}
	
	

}
