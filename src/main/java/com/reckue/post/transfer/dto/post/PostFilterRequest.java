package com.reckue.post.transfer.dto.post;

import com.reckue.post.model.type.PostStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@ApiModel(value = "PostFilterRequest", description = "Фильтр поиска постов")
public class PostFilterRequest {

	@ApiModelProperty("Нужны ли ноды в выдаче (true по умолчанию)")
	Boolean nodesRequired;

	@ApiModelProperty("Строка поиска (заголовок)")
	String searchTitle;

	@ApiModelProperty("Источник")
	String source;

	@ApiModelProperty("Id автора")
	String authorId;

	@ApiModelProperty("Теги")
	List<String> tags;

	@ApiModelProperty("Статус поста")
	List<PostStatusType> status;

	@ApiModelProperty("Пост создан после")
	LocalDateTime createdAfter;

	@ApiModelProperty("Постан создан до")
	LocalDateTime createdBefore;
}
