package appSemTela;

import crud_swing.dao.ClienteSetDAO;
import crud_swing.dao.IClienteDAO;
import crud_swing.domain.Cliente;

import javax.swing.*;

/**
 * My Awesome Project
 * <p>
 * Description: This project does something amazing and solves a specific problem.
 *
 * @author Lis de Lima
 * @version 1.0
 * @project Default (Template) Project
 * @since 07/01/2024 - 18:31
 * <p>
 * Copyright (c) 2023 Lis de Lima. All rights reserved.
 */
public class App {
    private static IClienteDAO iClienteDAO; //guardamos a interface a assinatura
    public static void main(String[] args) {
        iClienteDAO = new ClienteSetDAO(); //aqui inicializamos ela idependente da manipulação dos dados por isso é importante a interface
        String opcao = JOptionPane.showInputDialog(null,"Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração, 5 para sair", "Cadastro", JOptionPane.QUESTION_MESSAGE
        );
        while (!isOpcaoValida(opcao)){
            if ("".equals(opcao)){
                sair();
            }
            //retribuindo a var de entrada para que só entre nas outras telas as opções válidas
            opcao = JOptionPane.showInputDialog(null,"Escolha uma opção válida!!! Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração, 5 para sair", "Cadastro", JOptionPane.WARNING_MESSAGE);
        }
        while (isOpcaoValida(opcao)){
            if (isOpcaoSair(opcao)) {
                System.exit(0);
            } else if (isOpcaoConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,"Escreva o cpf do cliente que deseja consultar:", "Opção 2", JOptionPane.PLAIN_MESSAGE);
                consultar(dados);
            } else if (isOpcaoExcluir(opcao)) {
                String dados = JOptionPane.showInputDialog(null,"Escreva o cpf do cliente que deseja excluir: ", "Opção", JOptionPane.PLAIN_MESSAGE);
                excluir(dados);
            } else if (isOpcaoAlterar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,"Escreva os novos dados separados por vírgula (o cpf é um campo protegido e não pode ser alterado): nome, cpf, telefone, endereço, numero, cidade, estado ", "Opção", JOptionPane.PLAIN_MESSAGE);
                alterar(dados);
            } else if (isOpcaoCadastrar(opcao)){
                String dados = JOptionPane.showInputDialog(null,"Escreva os dados separados por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "Opção 1", JOptionPane.PLAIN_MESSAGE);
                cadastrar(dados);
            }
            //passando o retorno do while para que entre no looping de escolher a opção
        opcao = JOptionPane.showInputDialog(null,"Escolha uma opção válida!!! Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração, 5 para sair", "Cadastro", JOptionPane.WARNING_MESSAGE);


        }
    }

    private static void alterar(String dados) {
            String[] dadosSeparados = dados.split(",") ;
            if (dadosSeparados.length == 7 ){
                Long cpf = Long.parseLong(dadosSeparados[1].trim()); //tivemos que transformar o que não era string
                Long tel = Long.parseLong(dadosSeparados[2].trim());
                Integer numero = Integer.parseInt(dadosSeparados[4].trim());
                Cliente cliente = new Cliente(dadosSeparados[0],cpf,tel,dadosSeparados[3],numero,dadosSeparados[5],dadosSeparados[6]);
                Cliente isCliente = iClienteDAO.consultar(cpf);
                if (isCliente != null){
                    JOptionPane.showInputDialog(null,"Cliente alterado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    iClienteDAO.alterar(cliente);
                }
                else {
                    JOptionPane.showInputDialog(null, "Cliente não encontrado, cpf inválido!", "ERRO", JOptionPane.WARNING_MESSAGE);
                }
            }else if (dadosSeparados.length > 7){
                dados = JOptionPane.showInputDialog(null,"Você preencheu informações a mais. Preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
                dadosSeparados = dados.split(",") ;
            }else{
                dados = JOptionPane.showInputDialog(null,"Para alterar o cadastro é necessário preencher todos os campos. Preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
                dadosSeparados = dados.split(",") ;
            }

        }

    private static void excluir(String dados) {
        while (!isNumerico(dados)){
            if ("".equals(dados)){
                sair();
            }
            dados = JOptionPane.showInputDialog(null,"CPF inválido. Valor não numérico, preencha o cpf novamente.", "ERRO", JOptionPane.WARNING_MESSAGE);
        }
        Long cpfClienteLong = Long.valueOf(dados);
        Cliente isCliente = iClienteDAO.consultar(cpfClienteLong);
        if (isCliente != null) {
            iClienteDAO.excluir(cpfClienteLong);
            JOptionPane.showInputDialog(null,"Cliente excluido com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showInputDialog(null,"CPF inválido. Cliente não encontrado", "ERRO", JOptionPane.WARNING_MESSAGE);

        }


    }

    private static void consultar(String dados) {
        while (!isNumerico(dados)){
            if ("".equals(dados)){
                sair();
            }
            dados = JOptionPane.showInputDialog(null,"CPF inválido. Valor não numérico, preencha o cpf novamente.", "ERRO", JOptionPane.WARNING_MESSAGE);
        }
        Cliente isCliente = iClienteDAO.consultar(Long.valueOf(dados));
        if (isCliente != null){
            String consulta_Cliente = "Cliente encontrado: " + isCliente.toString() ;
            JOptionPane.showInputDialog(null,consulta_Cliente, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showInputDialog(null,"CPF inválido. Cliente não encontrado", "ERRO", JOptionPane.WARNING_MESSAGE);
        }

    }

    private static void cadastrar(String dados) {
        //validar pra preecher nulo no que nao preencheu
        String[] dadosSeparados = dados.split(",") ;
        while (dadosSeparados.length < 2){
            dados = JOptionPane.showInputDialog(null,"CPF e nome obrigatório preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
            dadosSeparados = dados.split(",") ;
        }
        while (dadosSeparados.length >7){
            dados = JOptionPane.showInputDialog(null,"Você preencheu informações a mais. Preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
            dadosSeparados = dados.split(",") ;
        }
        if(dadosSeparados.length == 7){
            while (!isNumerico(dadosSeparados[1] )){
                if ("".equals(dados)){
                    sair();
                }
                dados = JOptionPane.showInputDialog(null,"CPF inválido. Valor não numérico, preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
                dadosSeparados = dados.split(",") ;
            }
            while (!isNumerico(dadosSeparados[2] )){
                if ("".equals(dados)){
                    sair();
                }
                dados = JOptionPane.showInputDialog(null,"Telefone inválido. Valor não numérico, preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
                dadosSeparados = dados.split(",") ;
            }
            while (!isNumerico(dadosSeparados[4] )){
                if ("".equals(dados)){
                    sair();
                }
                dados = JOptionPane.showInputDialog(null,"Número inválido. Valor não numérico, preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
                dadosSeparados = dados.split(",") ;
            }
            Long cpf = Long.parseLong(dadosSeparados[1].trim()); //tivemos que transformar o que não era string
            Long tel = Long.parseLong(dadosSeparados[2].trim());
            Integer numero = Integer.parseInt(dadosSeparados[4].trim());
            // Create Cliente object
            Cliente cliente = new Cliente(dadosSeparados[0],cpf,tel,dadosSeparados[3],numero,dadosSeparados[5],dadosSeparados[6]);
            Boolean isCliente = iClienteDAO.cadastrar(cliente); //retorna true se deu certo a operação e guardou o objt no db
            if (isCliente){
                JOptionPane.showInputDialog(null,"Cadastro realizado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            } else JOptionPane.showInputDialog(null,"Cliente já cadastrado", "ERRO", JOptionPane.WARNING_MESSAGE);
            System.out.println(cliente.getNome() + cliente.getNumero());

        }else if(dadosSeparados.length >=2 && dadosSeparados.length < 7){
            String[] novoArray = new String[7];
            System.arraycopy(dadosSeparados, 0, novoArray, 0, dadosSeparados.length);
            for (int i = dadosSeparados.length; i < novoArray.length; i++) {
                novoArray[i] = null;
            }
            dadosSeparados = novoArray;
            while (!isNumerico(dadosSeparados[1] )){
                if ("".equals(dados)){
                    sair();
                }
                dados = JOptionPane.showInputDialog(null,"CPF inválido. Valor não numérico, preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
                dadosSeparados = dados.split(",") ;
            }
            if (dadosSeparados[2] != null ){
                while (!isNumerico(dadosSeparados[2] )){
                    if ("".equals(dados)){
                        sair();
                    }
                    dados = JOptionPane.showInputDialog(null,"Telefone inválido. Valor não numérico, preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
                    dadosSeparados = dados.split(",") ;
                }}
            if (dadosSeparados[4] != null ){
                while (!isNumerico(dadosSeparados[4]) ){
                    if ("".equals(dados)){
                        sair();
                    }
                    dados = JOptionPane.showInputDialog(null,"Número inválido. Valor não numérico, preencha todos os dados novamente separdos por vírgula: nome, cpf, telefone, endereço, numero, cidade, estado", "ERRO", JOptionPane.WARNING_MESSAGE);
                    dadosSeparados = dados.split(",") ;
                }
            }

            Long cpf = Long.parseLong(dadosSeparados[1].trim()); //tivemos que transformar o que não era string
            Long tel;
            Integer numero;
            if (dadosSeparados[2] != null){
                 tel = Long.parseLong(dadosSeparados[2].trim());
            }else{
                 tel = null;
            }
            if (dadosSeparados[4] != null){
                 numero = Integer.parseInt(dadosSeparados[4].trim());
            }else{
                 numero = null;
            }
            // Create Cliente object
            Cliente cliente = new Cliente(dadosSeparados[0],cpf,tel,dadosSeparados[3],numero,dadosSeparados[5],dadosSeparados[6]);
            Boolean isCliente = iClienteDAO.cadastrar(cliente); //retorna true se deu certo a operação e guardou o objt no db
            if (isCliente){
                JOptionPane.showInputDialog(null,"Cadastro realizado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            } else JOptionPane.showInputDialog(null,"Cliente já cadastrado", "ERRO", JOptionPane.WARNING_MESSAGE);
            //System.out.println(cliente.getNome() + cliente.getCpf() + cliente.getTel() +cliente.getEnd()+ cliente.getNumero() + cliente.getCidade() + cliente.getEstado());
        }

    }
    private static Boolean isOpcaoValida (String opcao){
        if ("1".equals(opcao)|| "2".equals(opcao) || "3".equals(opcao)|| "4".equals(opcao) || "5".equals(opcao)){
            return true;
        }
        return false;
    }
    private static void sair (){
        JOptionPane.showInputDialog(null,"Saindo", "Sair", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
    public static Boolean isOpcaoCadastrar(String opcao){
        if ("1".equals(opcao)){
            return true;
        }
        return false;
    }
    private static Boolean isOpcaoConsultar(String opcao){
        if ("2".equals(opcao)){
            return true;
        }
        return false;
    }
    private static Boolean isOpcaoExcluir(String opcao){
        if ("3".equals(opcao)){
            return true;
        }
        return false;
    }
    private static Boolean isOpcaoAlterar(String opcao){
        if ("4".equals(opcao)){
            return true;
        }
        return false;
    }
    private static Boolean isOpcaoSair(String opcao){
        if ("5".equals(opcao)){
            return true;
        }
        return false;
    }
    private static Boolean isNumerico(String opcao) {
            if (opcao.matches("\\d+") ){
                return true;
            }
            return false;
        }
}