package com.pizzaiolo.application.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Comment;
import com.pizzaiolo.domains.entities.Pizza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import lombok.Value;

@Value
@ApiModel(value = "Pizzas detalladas", description = "Version detallada de las pizzas.")
public class CommentDetailsDTO {
	
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
	@ApiModelProperty(value = "Identificador de la pizza sobre la que se genera el comentario.")
	@JsonProperty("idPizza")
	private int idPizza;

	public static CommentDetailsDTO from(Comment source) {
		return new CommentDetailsDTO(
				source.getIdComment(), 
				source.getDate(), 
				source.getIdUser(), 
				source.getRating(), 
				source.getText(), 
				source.getPizza().getIdPizza());
	}
	
	public static Comment  from( CommentDetailsDTO source) {
		return new Comment(
				source.getIdComment(), 
				source.getDate(), 
				source.getIdUser(), 
				source.getRating(), 
				source.getText(),
				new Pizza (source.getIdPizza())
				);
	}
	
}
