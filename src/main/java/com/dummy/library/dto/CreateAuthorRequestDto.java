package com.dummy.library.dto;

import com.dummy.library.model.Author;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorRequestDto {

  @NotBlank
  @Parameter(
      description = "Author's full name", // for sake of simplicity, we only require the name
      required = true)
  private String name;

  public Author toModel() {
    return new Author(null, name);
  }
}
