package com.pizzaiolo.application.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Comment;

import lombok.Value;

@Value
public class CommentEditDTO {
	
	@JsonProperty("idComentario")
	private int idComment;
	@JsonProperty("fecha")
	private Date date;
	@JsonProperty("idUsuario")
	private String idUser;
	@JsonProperty("rating")
	private int rating;
	@JsonProperty("comentario")
	private String text;

	public static CommentEditDTO from(Comment source) {
		return new CommentEditDTO(
				source.getIdComment(), 
				source.getDate(), 
				source.getIdUser(), 
				source.getRating(), 
				source.getText());
	}

	public static Comment  from( CommentEditDTO source) {
		return new Comment(
				source.getIdComment(), 
				source.getDate(), 
				source.getIdUser(), 
				source.getRating(), 
				source.getText());
	}
	
}