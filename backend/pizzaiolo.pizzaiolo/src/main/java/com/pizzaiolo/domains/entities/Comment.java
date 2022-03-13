package com.pizzaiolo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.core.entities.EntityBase;

import java.util.Date;
import java.util.Objects;


/**
 * The persistent class for the Comment database table.
 * 
 */
@Entity
@Table(name="comment")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment extends EntityBase<Comment> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idComment")
	@JsonProperty("id")
	private int idComment;

	@Column(name="date")
	@Generated(value = GenerationTime.ALWAYS)
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date date;
	
	@NotBlank
	@Length(max = 150)
	@JsonProperty("usuario")
	private String idUser;
	
	@NotNull
	@Column(name="rating")
	@JsonProperty("rating")
	private int rating;

	@Lob
	private String text;

	//bi-directional many-to-one association to Pizza
	
	@ManyToOne
	@JoinColumn(name="idPizza")
	private Pizza pizza;

	public Comment() {
	}
	
	public Comment(int idComment) {
		super();
		this.idComment = idComment;
	}
	
	public Comment(int idComment, String idUser) {
		super();
		this.idComment = idComment;
		this.idUser = idUser;
	}
	
	public Comment(int idComment, String idUser, String text) {
		super();
		this.idComment = idComment;
		this.idUser = idUser;
		this.text = text;
	}
	
	public Comment(int idComment, Date date, String idUser, int rating, String text) {
		super();
		this.idComment = idComment;
		this.date = date;
		this.idUser = idUser;
		this.rating = rating;
		this.text = text;
		
	}

	public int getIdComment() {
		return this.idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, idComment, idUser, pizza, rating, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(date, other.date) && idComment == other.idComment && Objects.equals(idUser, other.idUser)
				&& Objects.equals(pizza, other.pizza) && rating == other.rating && Objects.equals(text, other.text);
	}

	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", date=" + date + ", idUser=" + idUser + ", rating=" + rating
				+ ", text=" + text + ", pizza=" + pizza + "]";
	}
	

}