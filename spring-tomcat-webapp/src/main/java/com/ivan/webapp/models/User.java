package com.ivan.webapp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
  private static final long serialVersionUID = -8536177304729545356L;

  private String id;
  private String fullName;
}
