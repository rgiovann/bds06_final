package com.devsuperior.movieflix.dto;

public class MovieGenreDTO {
	
	private Long id;
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;
	private GenreDTO genre;
	private String synopsis;
	
	public MovieGenreDTO(){
	}

	public MovieGenreDTO(Long id, String title, String subTitle, Integer year, String imgUrl, GenreDTO genre, String synopsis) {

		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.year = year;
		this.imgUrl = imgUrl;
		this.genre = genre;
		this.synopsis = synopsis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}
	
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MovieGenreDTO other = (MovieGenreDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}