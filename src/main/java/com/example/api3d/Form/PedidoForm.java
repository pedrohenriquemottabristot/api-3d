package com.example.api3d.Form;


import com.example.api3d.Domain.Pedido;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.NotBlank; 
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PedidoForm {

    @NotNull(message = "Preencha o campo andares.")
    private int andares;

    @NotBlank(message = "Preencha o campo cor1.")
    private String cor1;

    @NotBlank(message = "Preencha o campo cor2.")
    private String cor2;

    @NotBlank(message = "Preencha o campo cor3.")
    private String cor3;

    private String desenho1;
    private String desenho2;
    private String desenho3;

    public PedidoForm(Pedido pedido) {
        this.andares = pedido.getAndares();
        this.cor1 = pedido.getCor1();
        this.cor2 = pedido.getCor2();
        this.cor3 = pedido.getCor3();
        this.desenho1 = pedido.getDesenho1();
        this.desenho2 = pedido.getDesenho2();
        this.desenho3 = pedido.getDesenho3();
    }

    public void setListPedidos(List<Pedido> listaPedidos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setListPedidos'");
    }
}
