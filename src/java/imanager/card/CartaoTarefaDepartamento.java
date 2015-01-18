/*
The MIT License (MIT)

Copyright (c) 2008 Kleber Maia de Andrade

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/   
   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cartão de Tarefa Departamento.
 */
public class CartaoTarefaDepartamento extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoTarefaDepartamento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoTarefaDepartamento", "Tarefas por Departamento", "Exibe o total de Tarefas por Departamento.", HELP, "card/cartaotarefadepartamento.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /** 
   * Construtor padrão.
   */
  public CartaoTarefaDepartamento() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os Departamentos e as quantidades de
   * Tarefas no mês.
   * <ol>
   *   <li>global_departamento.in_departamento_id
   *   <li>global_departamento.va_nome
   *   <li>quantidade de tarefas
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Departamentos e as
   *         quantidades de Tarefas no mês.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetTarefaDepartamento(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "select global_departamento.in_departamento_id, "
                 + "global_departamento.va_nome, "
                 + "crm_tarefa.in_tarefa_id, "
                 + "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "crm_tarefa.da_prazo, "
                 + "usuario.in_usuario_id, "
                 + "usuario.va_nome, "
                 + "crm_tarefa.va_descricao "              
                 + "from crm_tarefa "
                 + "inner join global_departamento on (global_departamento.in_departamento_id = crm_tarefa.in_departamento_id AND global_departamento.in_empresa_id = crm_tarefa.in_empresa_id) "
                 + "inner join securityservice_usuario as usuario on (usuario.in_usuario_id = crm_tarefa.in_usuario_id) "
                 + "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_tarefa.in_cliente_id) "
                 + "where (crm_tarefa.in_empresa_id = ?) "
                 + " and (to_char(crm_tarefa.da_inclusao, 'mm/yyyy') <= ?) "
                 + " and crm_tarefa.sm_status <> " + StatusTarefa.CONCLUIDA + " "
                 + "group by global_departamento.in_departamento_id, global_departamento.va_nome, crm_tarefa.in_tarefa_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, crm_tarefa.da_prazo, usuario.in_usuario_id, usuario.va_nome, crm_tarefa.va_descricao "
                 + "order by global_departamento.va_nome, global_departamento.in_departamento_id ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setString(2, DateTools.formatMonthYear(DateTools.getActualDate()));
      // executa
      preparedStatement.execute();
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      return preparedStatement.getResultSet();
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

}
