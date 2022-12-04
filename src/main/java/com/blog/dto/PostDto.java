package com.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Post model information")
public class PostDto {

    @ApiModelProperty(value = "Post id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    @ApiModelProperty(value = "Post title")
    private String title;

    @NotEmpty
    @Size(min = 3, message = "Post description should have at least 3 characters")
    @ApiModelProperty(value = "Post description")
    private String description;

    @NotEmpty
    @ApiModelProperty(value = "Post content")
    private String content;

    @ApiModelProperty(value = "Post comments")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<CommentDto> comments;
}
