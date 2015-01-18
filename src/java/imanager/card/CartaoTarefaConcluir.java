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

import imanager.entity.*;
import imanager.misc.*;
import iobjects.entity.Paginate;

/**
 * Representa o cartão de Tarefa Concluir.
 */
public class CartaoTarefaConcluir extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoTarefaConcluir";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoTarefaConcluir", "Tarefas a Concluir", "Exibe as Tarefas do Usuário com prazo se aproximando.", HELP, "card/cartaotarefaconcluir.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);
 
  /**
   * Construtor padrão.
   */
  public CartaoTarefaConcluir() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna um TarefaInfo[] referente às Tarefas a concluir identificadas
   * pela Empresa e Usuário.
   * @return Retorna um TarefaInfo[] referente às Tarefas a concluir
   *         identificadas pela Empresa e Usuário.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public TarefaInfo[] getTarefaConcluir(int empresaId, int usuarioId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // nossa instância de Tarefa
      Tarefa tarefa = (Tarefa)getFacade().getEntity(Tarefa.CLASS_NAME);
      // consulta
      TarefaInfo[] result = tarefa.selectByFilter(empresaId,
                                                  0,
                                                  0,
                                                  0,
                                                  StatusTarefa.TODOS,
                                                  0,
                                                  usuarioId,
                                                  new Timestamp(DateTools.getActualDate().getTime()),
                                                  new Timestamp(DateTools.getCalculatedDays(30).getTime()),
                                                  NaoSim.SIM,
                                                  null);
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      return result;
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Retorna o ResultSet contendo o Usuário a quem foi
   * encaminhada a tarefa, o ID da tarefa, o prazo, o status e o cliente.
   * <ol>
   *   <li>relacionamento_contato.va_nome
   *   <li>crm_tarefa.in_tarefa_id
   *   <li>crm_tarefa.da_prazo
   *   <li>crm_tarefa.sm_status
   *   <li>securityservice_usuario.va_nome
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo as Saídas para entrega.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */

  public ResultSet getResultSetTarefaConcluir(int empresaId, int usuarioId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "select relacionamento_contato.va_nome, in_tarefa_id, crm_tarefa.da_prazo, crm_tarefa.sm_status, securityservice_usuario.va_nome, crm_tarefa.va_descricao "
                 + "from crm_tarefa "
                 + "inner join relacionamento_contato on (relacionamento_contato.in_contato_id = crm_tarefa.in_cliente_id) "
                 + "inner join securityservice_usuario on (securityservice_usuario.in_usuario_id = crm_tarefa.in_usuario_id) "
                 + "where crm_tarefa.in_empresa_id = ? "
                 + " and crm_tarefa.sm_status <> " + StatusTarefa.CONCLUIDA + " "
                 + " and crm_tarefa.in_usuario_inclusao_id = ? "
                 + " and crm_tarefa.in_usuario_inclusao_id <> crm_tarefa.in_usuario_id ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setInt(2, usuarioId);
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
