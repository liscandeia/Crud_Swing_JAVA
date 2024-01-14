package crud_swing.dao;

import crud_swing.domain.Cliente;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * My Awesome Project
 * <p>
 * Description: This project does something amazing and solves a specific problem.
 *
 * @author Lis de Lima
 * @version 1.0
 * @project maos_na_maca
 * @since 09/01/2024 - 00:18
 * <p>
 * Copyright (c) 2023 Lis de Lima. All rights reserved.
 */
public class ClienteSetDAO implements IClienteDAO {
    private Set<Cliente> set;

    public ClienteSetDAO() {
        this.set = new HashSet<>();
    }


    @Override
    public boolean cadastrar(Cliente cliente) {
        return this.set.add(cliente);
    }

    @Override
    public void excluir(Long cpf) {
       Cliente excluindoCliente = null;
       //buscando todos os clientes
       for (Cliente cliente : this.set){
           if(cliente.getCpf().equals(cpf)){
               excluindoCliente = cliente;
               break;
            }
       }
       if (excluindoCliente != null){
           this.set.remove(excluindoCliente);
       }

    }

    @Override
    public void alterar(Cliente cliente) {
        if (this.set.contains(cliente)){
            for (Cliente clientesCadastrados : this.set){
                if(clientesCadastrados.equals(cliente)){
                    clientesCadastrados.setNome(cliente.getNome());
                    clientesCadastrados.setCidade(cliente.getCidade());
                    clientesCadastrados.setTel(cliente.getTel());
                    clientesCadastrados.setEnd(cliente.getEnd());
                    clientesCadastrados.setCidade(cliente.getCidade());
                    clientesCadastrados.setEstado(cliente.getEstado());
                    clientesCadastrados.setNumero(cliente.getNumero());
                }
            }

        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        for(Cliente clienteConsultado : this.set){
            if(clienteConsultado.getCpf().equals(cpf)){
                return clienteConsultado;
            }
        }
        return null;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.set;
    }
}
