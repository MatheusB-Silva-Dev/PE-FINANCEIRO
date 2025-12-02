package com.systemfinanceiro.service;

import com.systemfinanceiro.model.Divida;
import com.systemfinanceiro.repository.DividaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//anotação que indica que essa classe e um Service do Spring
//o Spring vai gerenciar essa classe como um Bean, injetando ela quando necessario
@Service
public class DividaService {
    //declarando a dependência do Repository
    //a interface que vai fazer a comunicação com o banco de dados
    private final DividaRepository dividaRepository;

    //construtor que injeta a dependencia
    public DividaService(DividaRepository dividaRepository) {
        this.dividaRepository = dividaRepository;
    }

    //metodo que lista todas as dividas, chama o metodo findAll() que o JpaRepository ja oferece
    public List<Divida> listaDividas() {
        return dividaRepository.findAll();
    }

    //metodo que adiciona uma nova divida, chama save() do JpaRepository, o ID e gerado
    //automaticamente pelo banco
    public Divida adicionarDivida(Divida divida) {
        return dividaRepository.save(divida);
    }

    //remove uma divida pelo ID, chama o deleteById() do JpaRepository
    public void removerDivida(Long id) {
        dividaRepository.deleteById(id);
    }

    //metodo para atualizar uma dívida existente
    //recebendo o ID da dívida e o objeto com os novos valores (dividaAtualizada)
    public Divida atualizarDivida(Long id, Divida dividaAtualizada) {
        //busca a dívida pelo ID
        //findById retorna um Optional que pode ter ou não o valor
        return dividaRepository.findById(id)
                //se tiver valor entra no map
                .map(divida -> { // para cada divida encontrada execute esse bloco
                    //divida -> objeto antigo que veio do banco
                    //atualizamos cada campo com os valores que vieram na requisição
                    //primeiro pega o valor do objeto novo (get), depois seta no objeto antigo (set)
            divida.setValor(dividaAtualizada.getValor());
            divida.setDescricao(dividaAtualizada.getDescricao());
            divida.setDataVencimento(dividaAtualizada.getDataVencimento());

            //salva a divida atualizada no banco como o objeto já tem ID, o JPA entende que é um UPDATE
            return dividaRepository.save(divida);
        })
                //caso o Optional esteja vazio, não encontrou a dívida pelo ID, ja pula para essa parte e dispara a excecao
                .orElseThrow(() -> new RuntimeException("Divida não encontrada com o ID: " + id));
    }

    public double calculoValorTotal() {
        double total = 0;
        for (Divida d : listaDividas()) {
            total += d.getValor();
        }
        return total;
    }
}
