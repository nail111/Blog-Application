package com.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Comment model information")
public class CommentDto {

    @ApiModelProperty(value = "Comment id")
    private Long id;

    @NotEmpty(message = "Name should not be null or empty")
    @ApiModelProperty(value = "Comment name")
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    @ApiModelProperty(value = "Comment email")
    private String email;

    @NotEmpty
    @Size(min = 2, message = "Comment body should have at least 2 characters")
    @ApiModelProperty(value = "Comment body")
    private String body;
}
