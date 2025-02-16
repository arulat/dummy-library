package com.dummy.library.service;

import com.dummy.library.model.AuthorDetails;
import org.springframework.lang.NonNull;

public interface AuthorDetailsService {
  AuthorDetails findDetailsById(@NonNull Long id);
}
