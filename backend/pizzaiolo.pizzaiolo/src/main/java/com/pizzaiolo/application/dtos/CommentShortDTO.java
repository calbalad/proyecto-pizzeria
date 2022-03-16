package com.pizzaiolo.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaiolo.domains.entities.Comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
@ApiModel(value = "Pizzas editable", description = "Version editable de las pizzas.")
public class CommentShortDTO {
	
	@ApiModelProperty(value = "Identificador del comentario.")
	@JsonProperty("idComentario")
	private int idComment;
	@ApiModelProperty(value = "Identificador del usuario que genera el comentario.")
	@JsonProperty("idUsuario")
	private String idUser;
	@ApiModelProperty(value = "Texto del comentario.")
	@JsonProperty("comentario")
	private String text;

	public static CommentShortDTO from(Comment source) {
		return new CommentShortDTO(source.getIdComment(), source.getIdUser(), source.getText());
	}

	
}
