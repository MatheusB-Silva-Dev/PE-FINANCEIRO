package com.systemfinanceiro.service;

import com.systemfinanceiro.model.Transacao;
import com.systemfinanceiro.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//anotação que indica que essa classe e um Service do Spring
@Service
public class TransacaoService {
    //declarando a dependência do Repository
    private final TransacaoRepository transacaoRepository;

    //construtor que injeta a dependencia
    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    //metodo que adiciona transacoes, metodo save vem do JPArepository
    public Transacao adicionarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    //lista todas as transacoes, metodo findAll vem do JPArepository
    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }

    //remove transacao, metodo deletById vem do JPArepository
    public void removerTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

    public Transacao atualizarTransacao(Long id, Transacao transacaoAtualizada) {
        //busca a transacao existente pelo id
        //findById retorna um Optional que pode ter ou não o valor
        return transacaoRepository.findById(id)
                //se tiver valor entra no map
                .map(transacao -> { //divida -> objeto antigo que veio do banco
                    //atualizamos cada campo com os valores que vieram na requisição
                    //primeiro pega o valor do objeto novo (get), depois seta no objeto antigo (set)
                    //atualiza os campos necessários
                    transacao.setDescricao(transacaoAtualizada.getDescricao());
                    transacao.setValor(transacaoAtualizada.getValor());
                    transacao.setData(transacaoAtualizada.getData());

                    //salva e retorna o objeto atualizado
                    return transacaoRepository.save(transacao);
                })
                //caso o Optional esteja vazio, não encontrou a transacao pelo ID, ja pula para essa parte e dispara a excecao
                .orElseThrow(() -> new RuntimeException("Transação não encontrada com ID: " + id));
    }

    public double calcularValorTotal() {
        double total = 0;
        for(Transacao t : listarTodas()) {
            total += t.getValor();
        }
        return total;
    }
}
