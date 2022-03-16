package com.pizzaiolo.application.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Comment;
import com.pizzaiolo.domains.entities.Pizza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Value;

@Value
@ApiModel(value = "Pizzas editable", description = "Version editable de las pizzas.")
public class CommentEditDTO {
	
	@ApiModelProperty(value = "Identificador del comentario.")
	@JsonProperty("idComentario")
	private int idComment;
	@ApiModelProperty(value = "Fecha de creación del comentario.")
	@JsonProperty("fecha")
	private Date date;
	@ApiModelProperty(value = "Identificador del usuario que genera el comentario.")
	@JsonProperty("idUsuario")
	private String idUser;
	@ApiModelProperty(value = "Puntuación que se añade al comentario.")
	@JsonProperty("rating")
	private int rating;
	@ApiModelProperty(value = "Texto del comentario.")
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
				source.getText()				
				);
	}
	
}
