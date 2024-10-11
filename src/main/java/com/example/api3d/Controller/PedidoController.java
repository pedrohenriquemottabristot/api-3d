package com.example.api3d.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.api3d.Domain.Pedido;
import com.example.api3d.Form.PedidoForm;
import com.example.api3d.Repository.PedidoRepository;
import com.example.api3d.Service.PedidoService;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


@Controller
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/pedido")
    public String index(Model model, @RequestParam("display") Optional<String> display){
        String finalDisplay = display.orElse("true");

        List<Pedido> pedidos = pedidoRepository.findByAtivo(Boolean.valueOf(finalDisplay));

        model.addAttribute("pedidos", pedidos);

        return "pedido/listar";
    }

    @GetMapping("/pedido/create")
    public String create(Model model) {
        PedidoForm pedidoForm = new PedidoForm();
        
        List<Pedido> listaPedidos = pedidoRepository.findAll();
        pedidoForm.setListPedidos(listaPedidos);

        model.addAttribute("pedidoForm", pedidoForm);

        return "pedido/create";
    }
    
    @PostMapping("/pedido/create")
    public String create(@Valid PedidoForm pedidoForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        List<Pedido> listaPedidos = pedidoRepository.findAll();
        pedidoForm.setListPedidos(listaPedidos);

        model.addAttribute("pedidoForm", pedidoForm);
        
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/pedido/create";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Salvo com sucesso!");
        pedidoService.create(pedidoForm);
        
        return "redirect:/pedido";
    }

    @GetMapping("/pedido/update/{id}")
    public String update(@PathVariable Long id, Model model){
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        PedidoForm pedidoForm = new PedidoForm(pedido.orElseThrow());

        List<Pedido> listaPedidos = pedidoRepository.findAll();
        pedidoForm.setListPedidos(listaPedidos);

        model.addAttribute("pedidoForm", pedidoForm);
        model.addAttribute("id", pedido.orElseThrow().getId());

        return "/pedido/update";
    }

    @GetMapping("/pedido/visualizar/{id}")
    public String visualizar(@PathVariable Long id, Model model){
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        PedidoForm pedidoForm = new PedidoForm(pedido.get());

        List<Pedido> listaPedidos = pedidoRepository.findAll();
        pedidoForm.setListPedidos(listaPedidos);

        model.addAttribute("pedidoForm", pedidoForm);
        model.addAttribute("id", pedido.get().getId());

        return "/pedido/visualizar";
    }
    
    @PostMapping("/pedido/update/{id}")
    public String update(@PathVariable Long id, @Valid PedidoForm pedidoForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        List<Pedido> listaPedidos = pedidoRepository.findAll();
        pedidoForm.setListPedidos(listaPedidos);

        model.addAttribute("pedidoForm", pedidoForm);

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/pedido/update";
        }

        pedidoService.update(pedidoForm, id);      

        redirectAttributes.addFlashAttribute("successMessage", "Alterado com sucesso!");
        return "redirect:/pedido";
    }
    
    @GetMapping("/pedido/remover/{id}")
    public String remover(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Pedido> pedido = this.pedidoRepository.findById(id);
        Pedido pedidoModel = pedido.get();

        if (pedidoModel.isAtivo()) {
            pedidoModel.setAtivo(false);    
            redirectAttributes.addFlashAttribute("successMessage", 
            "Excluído com sucesso!");
        } else {
            pedidoModel.setAtivo(true);
            redirectAttributes.addFlashAttribute("successMessage", 
            "Recuperado com sucesso!");
        }
        
        this.pedidoRepository.save(pedidoModel);
        
        return "redirect:/pedido";        
    }
}
