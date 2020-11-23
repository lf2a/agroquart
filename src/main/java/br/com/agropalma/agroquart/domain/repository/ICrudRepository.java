package br.com.agropalma.agroquart.domain.repository;

import java.util.List;

/**
 * <h1>ICrudRepository.java</h1>
 * Interface que fornece métodos para um CRUD.
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 20/11/2020
 */
public interface ICrudRepository<T, I> {

    /**
     * Recebe o ID de tipo I e irá buscar o objeto no banco de dados e irá retorna-lo.
     *
     * @param id O id do objeto a ser buscado.
     * @return Null ou o objeto de tipo T retornado do banco de dados.
     */
    T buscarPorId(I id);

    /**
     * Irá buscar todos os objetos de tipo T do banco de dados.
     *
     * @return Uma lista com todos os objetos ou uma lista vazia.
     */
    List<T> buscarTodos();

    /**
     * Irá receber um objeto de tipo T para salva-lo ou atualiza-lo no banco de dados.
     *
     * @param obj O objeto a ser salvo ou atualizado.
     */
    void salvarOuAtualizar(T obj);

    /**
     * Irá excluir o objeto do banco de dados com base no ID de tipo I fornecido.
     *
     * @param id O ID do objeto a ser excluido do banco de dados.
     */
    int excluir(I id);
}
