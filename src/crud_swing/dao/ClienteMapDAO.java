
//lembrar de trocar para o correto
package crud_swing.dao;

import crud_swing.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * My Awesome Project
 * <p>
 * Description: This project does something amazing and solves a specific problem.
 *
 * @author Lis de Lima
 * @version 1.0
 * @project maos_na_maca
 * @since 07/01/2024 - 18:44
 * <p>
 * Copyright (c) 2023 Lis de Lima. All rights reserved.
 */
public class ClienteMapDAO implements IClienteDAO {
    private Map<Long, Cliente> map;

    public ClienteMapDAO() {
        this.map = new HashMap<>();
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        if (this.map.containsKey(cliente.getCpf())){
            return false;
        }
        this.map.put(cliente.getCpf(), cliente);
         return true;

    }

    @Override
    public void excluir(Long cpf) {
        Cliente clienteCadastrado = this.map.get(cpf); //guardando o cliente que encontramos com o cpf
        if(clienteCadastrado != null){
            this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado); //passamos que quando o cpf existir, removemos o cliente todo
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = this.map.get(cliente.getCpf()); //guardando o cliente que encontramos com o cpf
        if (clienteCadastrado != null){
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setTel(cliente.getTel());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEstado(cliente.getEstado());
            clienteCadastrado.setNumero(cliente.getNumero());
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    } //se existir o cpf digitado q passamos por parametro retorna o objt cliente se n retorna null

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    } //retorna uma coleção do map
}
