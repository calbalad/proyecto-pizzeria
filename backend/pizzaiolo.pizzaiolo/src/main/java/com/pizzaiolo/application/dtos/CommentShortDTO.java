package com.pizzaiolo.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Comment;

import lombok.Value;

@Value
public class CommentShortDTO {
	
	@JsonProperty("idComentario")
	private int idComment;
	@JsonProperty("idUsuario")
	private String idUser;
	@JsonProperty("comentario")
	private String text;

	public static CommentShortDTO from(Comment source) {
		return new CommentShortDTO(source.getIdComment(), source.getIdUser(), source.getText());
	}

	
}
