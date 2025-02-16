package com.dummy.library.dto;

import com.dummy.library.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuthorRequestDto {
  private String name;

  public Author toModel(Long id) {
    return new Author(id, name);
  }
}
