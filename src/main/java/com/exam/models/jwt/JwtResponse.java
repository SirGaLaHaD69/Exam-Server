package com.exam.models.jwt;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter@AllArgsConstructor
public class JwtResponse implements Serializable {
    String token;
}
