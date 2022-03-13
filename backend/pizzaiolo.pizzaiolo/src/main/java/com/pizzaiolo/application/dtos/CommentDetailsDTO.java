package com.pizzaiolo.application.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Comment;

import lombok.Value;

@Value
public class CommentDetailsDTO {
	
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

	public static CommentDetailsDTO from(Comment source) {
		return new CommentDetailsDTO(
				source.getIdComment(), 
				source.getDate(), 
				source.getIdUser(), 
				source.getRating(), 
				source.getText());
	}
	
	public static Comment  from( CommentDetailsDTO source) {
		return new Comment(
				source.getIdComment(), 
				source.getDate(), 
				source.getIdUser(), 
				source.getRating(), 
				source.getText());
	}
	
}
