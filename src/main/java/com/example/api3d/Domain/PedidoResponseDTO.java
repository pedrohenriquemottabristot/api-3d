package com.example.api3d.Domain;

import java.util.UUID;

public record PedidoResponseDTO(
    UUID id,
    int andares,

    String cor1,
    String cor2,
    String cor3,

    String desenho1,
    String desenho2,
    String desenho3
) {

}
