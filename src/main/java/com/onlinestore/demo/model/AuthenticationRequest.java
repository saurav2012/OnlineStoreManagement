package com.onlinestore.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -6986746375915710855L;

    @NotNull
    private String username;
    @NotNull
    private String password;
}
