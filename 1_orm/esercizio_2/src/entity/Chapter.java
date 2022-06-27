package entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Chapter{
	
	@ManyToOne
    @JoinColumn(name = "BOOK_ISBN")
    @MapsId("isbn")
	private Book book;
	
	@Id
	@Column(name = "CHAPTER_NUM", nullable = false)
	private Integer chapterNum;
	
	@Column(name = "TITLE", nullable = false, length = 100)
	private String title;

	public Chapter() {	}
	public Chapter(Integer chapterNum, String title) {
		this.chapterNum = chapterNum;
		this.title = title;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	@Override
	public String toString() {
		return "Chapter [chapterNum=" + chapterNum + ", title=" + title + "]";
	}
}
