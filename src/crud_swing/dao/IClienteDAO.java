package crud_swing.dao;

import crud_swing.domain.Cliente;

import java.util.Collection;

/**
 * My Awesome Project
 * <p>
 * Description: This project does something amazing and solves a specific problem.
 *
 * @author Lis de Lima
 * @version 1.0
 * @project maos_na_maca
 * @since 07/01/2024 - 18:38
 * <p>
 * Copyright (c) 2023 Lis de Lima. All rights reserved.
 */
public interface IClienteDAO {
    public boolean cadastrar(Cliente cliente);
    public void excluir(Long cpf);
    public void alterar(Cliente cliente);
    public Cliente consultar (Long cpf);
    public Collection<Cliente> buscarTodos();
}
