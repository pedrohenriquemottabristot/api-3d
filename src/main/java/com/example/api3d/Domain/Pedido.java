package com.example.api3d.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "endereco")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue
    UUID id;

    int andares;

    String cor1;
    String cor2;
    String cor3;

    String desenho1;
    String desenho2;
    String desenho3;

  
    
}