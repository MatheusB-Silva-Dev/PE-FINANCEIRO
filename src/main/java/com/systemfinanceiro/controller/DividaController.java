package com.systemfinanceiro.controller;

import com.systemfinanceiro.model.Divida;
import com.systemfinanceiro.service.DividaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marca a classe como controller rest, todos os metodos retornam JSON
@RequestMapping("/dividas") // Definimos a url base para as requisicoes https
public class DividaController {
    private final DividaService dividaService; //injetando a service

    // construtor recebe a service e faz a injecao de dependencia via construtor
    public DividaController(DividaService dividaService) {
        this.dividaService = dividaService;
    }

    // metodo post cria objeto
    @PostMapping
    public Divida adicionarDivida(@RequestBody Divida divida) {// Anotacao @RequestBody indica que o spring espera um JSON, converte em objeto divida
        return dividaService.adicionarDivida(divida); // chama a service para add e retorna o objeto criado
    }

    // metodo para consulta/listar
    @GetMapping
    public List<Divida> listaDividas() { // retorna a lista de dividas, o spring controla toda a lista
        return dividaService.listaDividas(); // chama a service
    }

    // metodo de remover, recebe o ID na url atraves da requisicao
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerDivida(@PathVariable Long id) { // recebe o id via url
        dividaService.removerDivida(id); // chama a service para remover e retorna somente o status http sem corpo void
        return ResponseEntity.noContent().build(); //retorna 204 indicando sucesso sem corpo na resposta

    }

    //metodo put que atualiza atraves do id passado pela requisição
    @PutMapping("/{id}")
    //aqui não e void retorna o objeto Divida
    //recebe o id e o dividaAtualizada que vem do corpo da requisição e o spring converte
    public ResponseEntity<Divida> atualizarDivida (@PathVariable Long id, @RequestBody Divida dividaAtualizada) {
        //dividaSalva serve para receber o objeto atualizado
        Divida dividaSalva = dividaService.atualizarDivida(id, dividaAtualizada); // chamando a service passando o id e o JSON com os atributos para atualizar
        return ResponseEntity.ok(dividaSalva); //corpo da requisiçao com a atualização, retorna status HTTP 200 junto com o objeto atualizado no corpo
    }

    @GetMapping("/valor-total")
    public double valorTotal() {
        return dividaService.calculoValorTotal();
    }
}

//@RequestBody -- Recebe JSON da requisição e converte em objeto Divida automaticamente
//@PathVariable -- Captura valor do ID da URL e converte automaticamente para Long

