package com.systemfinanceiro.controller;

import com.systemfinanceiro.model.Transacao;
import com.systemfinanceiro.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // marca a classe como controller de uma api rest, fazendo com que todos os metodos retornem json
@RequestMapping("/transacoes") // definimos a url para requisicao http
public class TransacaoController {
    private final TransacaoService transacaoService; // Injetando bean TransacaoService

    public TransacaoController(TransacaoService transacaoService) { //Injetando atraves do construtor
        this.transacaoService = transacaoService;
    }

    @PostMapping //Metodo Post/adicionar cria objeto
    public Transacao adicionarTransacao(@RequestBody Transacao transacao) { // Anotacao @RequestBody indica que o spring espera um JSON, converte em objeto transcao
        return transacaoService.adicionarTransacao(transacao); //chama a service para adicionar
    }

    @GetMapping //Metodo Get para listar todas as transacoes
    public List<Transacao> listaTransacao() { // Passa o tipo de retorno List
       return transacaoService.listarTodas(); // chama a service para listar os objetos
    }

    @DeleteMapping ("/{id}") //Metodo delete atraves do id da transacao o spring controla toda a lista
    public ResponseEntity<Void> removerTransacao(@PathVariable Long id) { //@PathVariable: Captura valor do ID da URL e converte automaticamente para Long
        transacaoService.removerTransacao(id); //chama a service para remover o objeto passando o id
        return ResponseEntity.noContent().build(); //retorna 204 indicando sucesso sem corpo na resposta

    }

    //metodo put que atualiza atraves do id passado pela requisição
    @PutMapping("/{id}")
    //aqui não e void retorna o objeto Divida
    //recebe o id e o transacaoAtualizada que vem do corpo da requisição e o spring converte
    public ResponseEntity<Transacao> atualizarTransacao(@PathVariable Long id, @RequestBody Transacao transacaoAtualizada) {
        Transacao transacaoSalva = transacaoService.atualizarTransacao(id, transacaoAtualizada);//chamando a service passando o id e o JSON com os atributos para atualizar
        return ResponseEntity.ok(transacaoSalva);//corpo da requisiçao com a atualização, retorna status HTTP 200 junto com o objeto atualizado no corpo
    }

    @GetMapping("/valor-total")
    public double valorTotal() {
        return transacaoService.calcularValorTotal();
    }

}


//@RequestBody -- Recebe JSON da requisição e converte em objeto Transacao automaticamente
//@PathVariable -- Captura valor do ID da URL e converte automaticamente para Long