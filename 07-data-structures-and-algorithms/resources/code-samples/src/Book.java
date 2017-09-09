
public class Book {	
	private String title;
	private String author;
	private int year;

	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	public String getTitle()  { return title; }
	public String getAuthor() { return author; }
	public int    getYear()   { return year; }
	
	public void setAuthor(String author) { this.author = author; }
	public void setTitle(String title)   { this.title = title; }
	public void setYear(int year)        { this.year = year; }
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
	}
	
	private static int hashCode(String s) { // not using String.hashCode() for demonstration purposes
		int prime = 31;
		int primePower = 1;
		int hashCode = 1;
		
		for(int i = 0; i < s.length(); i++) {
			hashCode += s.charAt(i) * primePower;
			primePower *= prime;
		}
		
		return hashCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : hashCode(author));
		result = prime * result + ((title == null) ? 0 : hashCode(title));
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
}
