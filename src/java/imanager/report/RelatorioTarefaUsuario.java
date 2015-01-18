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
   
package imanager.report;

import java.sql.*;
import java.util.*;

import imanager.misc.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.report.*;
import iobjects.util.*;

/**
 * Representa o relatório de Tarefa Usuário.
 */
public class RelatorioTarefaUsuario extends Report {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.report.RelatorioTarefaUsuario";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION               = new Action("relatorioTarefaUsuario", "Tarefa por Usuário", "Emite o relatório de Tarefa por Usuário.", HELP, "report/relatoriotarefausuario.jsp", "CRM", "Tarefa", Action.CATEGORY_REPORT, false, true);
  static public final Action ACTION_RELATORIO     = new Action("relatorioTarefaUsuarioRelatorio", ACTION.getCaption(), ACTION.getDescription(), HELP, "report/relatoriotarefausuariorelatorio.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_REPORT, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_MAKE_REPORT = ACTION.addCommand(new Command(Command.COMMAND_MAKE_REPORT, "Gerar", "Gera e exibe o relatório com os parâmetros informados."));
  static public final Command COMMAND_PRINT       = ACTION.addCommand(new Command(Command.COMMAND_PRINT, "Imprimir", "Envia o relatório exibido."));
  static public final Command COMMAND_COPY        = ACTION.addCommand(new Command(Command.COMMAND_COPY, "Copiar", "Copia todo o conteúdo do relatório exibido."));
  // nossos parâmetros de usuário
  public final Param USER_PARAM_DATA_INICIAL = new Param("userParamDataInicial", "Data Inicial", "Informe a Data Inicial.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar a Data Inicial.");
  public final Param USER_PARAM_DATA_FINAL   = new Param("userParamDataFinal", "Data Final", "Informe a Data Final.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar a Data Final.");
  public final Param USER_PARAM_STATUS       = new Param("userParamStatus", "Status", "Selecione o Status.", StatusTarefa.TODOS + "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_ASSUNTO      = new Param("userParamAssunto", "Assunto", "Selecione o Assunto.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_DEPARTAMENTO = new Param("userParamDepartamento", "Departamento", "Selecione o Departamento.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_PRORROGADO   = new Param("userParamProrrogado", "Prorrogado", "Selecione se está Prorrogado.", NaoSim.TODOS + "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_CONSOLIDADO  = new Param("userParamConsolidado", "Consolidado", "Selecione se os dados serão consolidados entre todas empresas.", "");

  {
   // valores padrão
   USER_PARAM_DATA_INICIAL.setValue("01/" + NumberTools.completeZero(DateTools.getActualMonth(), 2) + "/" + DateTools.getActualYear());
   USER_PARAM_DATA_FINAL.setValue(DateTools.formatDate(DateTools.getActualDate()));
   // nossos lookups
   USER_PARAM_STATUS.setLookupList(StatusTarefa.LOOKUP_LIST_FOR_PARAM);
   USER_PARAM_PRORROGADO.setLookupList(NaoSim.LOOKUP_LIST_FOR_PARAM);
   USER_PARAM_CONSOLIDADO.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
  }

  /**
   * Construtor padrão.
   */
  public RelatorioTarefaUsuario() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_RELATORIO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_DATA_INICIAL);
    userParamList().add(USER_PARAM_DATA_FINAL);
    userParamList().add(USER_PARAM_STATUS);
    userParamList().add(USER_PARAM_ASSUNTO);
    userParamList().add(USER_PARAM_DEPARTAMENTO);
    userParamList().add(USER_PARAM_PRORROGADO);
    userParamList().add(USER_PARAM_CONSOLIDADO);
  }

  /**
   * Retorna o ResultSet de Tarefa Usuário.
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @param empresaId Empresa ID.
   * @param dataInicial Data Inicial.
   * @param dataFinal Data Final.
   * @param status Status NaoSim.TODOS para exibir todos.
   * @param assunto Assunto ou 0.
   * @param departamento Departamento ou 0.
   * @param prorrogado Prorrogado NaoSim.TODOS para exibir todos.
   * @param consolidado NaoSim.SIM para que o ranking exibido seja consolidado
   *                    entre todas as empresas.
   * @return ResultSet Retorna o ResultSet de Tarefa Usuário.
   *                   <b>Importante: a rotina que executar este método deve ser
   *                   responsável por fechar o ResultSet retornardo e o seu
   *                   Statement.</b>
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetTarefaUsuario(
                       int empresaId,
                       Timestamp dataInicial,
                       Timestamp dataFinal,
                       int status,
                       int assunto,
                       int departamento,
                       int prorrogado,
                       int consolidado
                    ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT crm_tarefa.in_tarefa_id as tarefa_id, global_departamento.va_nome as nome_departamento, crm_assunto.va_nome as nome_assunto,  securityservice_usuario.va_nome as nome_usuario, relacionamento_contato.va_nome as nome_cliente, crm_tarefa.da_prazo as prazo, crm_tarefa.sm_status as status, crm_tarefa.sm_prorrogacao as prorrogado, crm_tarefa.in_empresa_id as empresa_id "
                 + "FROM crm_tarefa "
                 + "INNER JOIN global_departamento ON (global_departamento.in_empresa_id = crm_tarefa.in_empresa_id AND global_departamento.in_departamento_id = crm_tarefa.in_departamento_id) "
                 + "INNER JOIN crm_assunto ON (crm_assunto.in_assunto_id = crm_tarefa.in_assunto_id) "
                 + "INNER JOIN securityservice_usuario ON (securityservice_usuario.in_usuario_id = crm_tarefa.in_usuario_id) "
                 + "INNER JOIN relacionamento_contato ON (relacionamento_contato.in_contato_id = crm_tarefa.in_cliente_id) "
                 + "WHERE ((crm_tarefa.in_empresa_id = ?) OR (" + (consolidado == NaoSim.SIM) + "))"
                 + " AND (crm_tarefa.da_inclusao >= ? AND crm_tarefa.da_inclusao <= ?) "
                 + " AND ((crm_tarefa.sm_status = ?) OR (" + (status == StatusTarefa.TODOS) + "))"
                 + " AND ((crm_tarefa.in_assunto_id = ?) OR (" + assunto + " = 0 )) "
                 + " AND ((crm_tarefa.in_departamento_id = ?) OR (" + departamento + " = 0 )) "
                 + " AND (((crm_tarefa.sm_prorrogacao = 0) AND (" + (prorrogado == NaoSim.NAO) + ")) OR ((crm_tarefa.sm_prorrogacao >= 1) AND (" + (prorrogado == NaoSim.SIM) + ")) OR ((crm_tarefa.sm_prorrogacao >= 0) AND (" + (prorrogado == NaoSim.TODOS) + ")))"
                 + "ORDER BY nome_usuario, tarefa_id ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setTimestamp(2, dataInicial);
      preparedStatement.setTimestamp(3, dataFinal);
      preparedStatement.setInt(4, status);
      preparedStatement.setInt(5, assunto);
      preparedStatement.setInt(6, departamento);
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
