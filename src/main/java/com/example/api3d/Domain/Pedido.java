package com.example.api3d.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "pedido")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue
    private Long id;

    int andares;

    String cor1;
    String cor2;
    String cor3;

    String desenho1;
    String desenho2;
    String desenho3;
    public boolean isAtivo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAtivo'");
    }
    public void setAtivo(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAtivo'");
    }

  
    
}