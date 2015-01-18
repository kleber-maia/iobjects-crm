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
 * Representa o relatório de Ranking Atendimento Assunto.
 */
public class RelatorioRankingAtendimentoAssunto extends Report {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.report.RelatorioRankingAtendimentoAssunto";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION               = new Action("relatorioRankingAtendimentoAssunto", "Atendimento por Assunto", "Emite o relatório de Ranking Atendimento por Assunto.", HELP, "report/relatoriorankingatendimentoassunto.jsp", "CRM", "Atendimento", Action.CATEGORY_REPORT, false, true);
  static public final Action ACTION_RELATORIO     = new Action("relatorioRankingAtendimentoAssuntoRelatorio", ACTION.getCaption(), ACTION.getDescription(), HELP, "report/relatoriorankingatendimentoassuntorelatorio.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_REPORT, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_MAKE_REPORT = ACTION.addCommand(new Command(Command.COMMAND_MAKE_REPORT, "Gerar", "Gera e exibe o relatório com os parâmetros informados."));
  static public final Command COMMAND_PRINT       = ACTION.addCommand(new Command(Command.COMMAND_PRINT, "Imprimir", "Envia o relatório exibido."));
  static public final Command COMMAND_COPY        = ACTION.addCommand(new Command(Command.COMMAND_COPY, "Copiar", "Copia todo o conteúdo do relatório exibido."));
  // nossos parâmetros de usuário
  public final Param USER_PARAM_CAMPANHA_ID  = new Param("userParamCampanhaId", "Campanha", "Selecione a Campanha.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_USUARIO_ID   = new Param("userParamUsuarioId", "Usuário", "Selecione o Usuário.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_DATA_INICIAL = new Param("userParamDataInicial", "Data Incial", "Informe a Data Inicial.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar a Data Inicial.");
  public final Param USER_PARAM_DATA_FINAL   = new Param("userParamDataFinal", "Data Final", "Informe a Data Final.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar a Data Final.");
  public final Param USER_PARAM_CONSOLIDADO  = new Param("userParamConsolidado", "Consolidado", "Selecione se os dados serão consolidados entre todas empresas.", "");

  {
   // lookup
   USER_PARAM_CONSOLIDADO.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
   // valores padrão
   USER_PARAM_DATA_INICIAL.setValue("01/" + NumberTools.completeZero(DateTools.getActualMonth(), 2) + "/" + DateTools.getActualYear());
   USER_PARAM_DATA_FINAL.setValue(DateTools.formatDate(DateTools.getActualDate()));
   //*
   USER_PARAM_DATA_FINAL.setSpecialConstraint(true, USER_PARAM_DATA_INICIAL);
  }

  /**
   * Construtor padrão.
   */
  public RelatorioRankingAtendimentoAssunto() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_RELATORIO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_CAMPANHA_ID);
    userParamList().add(USER_PARAM_USUARIO_ID);
    userParamList().add(USER_PARAM_DATA_INICIAL);
    userParamList().add(USER_PARAM_DATA_FINAL);
    userParamList().add(USER_PARAM_CONSOLIDADO);
  }

  /**
   * Retorna o ResultSet de Ranking Atendimento Assunto.
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @param empresaId int Empresa ID.
   * @param campanhaId int Campanha ID ou 0.
   * @param usuarioId int Usuário ID ou 0.
   * @param dataInicial Timestamp Data Inicial.
   * @param dataFinal Timestamp Data Final.
   * @param consolidado NaoSim.SIM para que a análise exibida seja consolidada.
   * @return ResultSet Retorna o ResultSet de Ranking Atendimento Assunto.
   *                   <b>Importante: a rotina que executar este método deve ser
   *                   responsável por fechar o ResultSet retornardo e o seu
   *                   Statement.</b>
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetRankingAtendimentoAssunto(
                       int empresaId,
                       int campanhaId,
                       int usuarioId,
                       Timestamp dataInicial,
                       Timestamp dataFinal,
                       int consolidado
                    ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT crm_assunto.va_nome as nome, count(crm_atendimento.in_assunto_id) as total_assunto "
                 + "FROM crm_atendimento "
                 + "INNER JOIN crm_assunto ON (crm_assunto.in_assunto_id = crm_atendimento.in_assunto_id) "
                 + "WHERE ((crm_atendimento.in_empresa_id = ?) OR (" + (consolidado == NaoSim.SIM) + ")) "
                 + " AND ((crm_atendimento.in_campanha_id = ?) OR (" + campanhaId + " = 0 )) "
                 + " AND ((crm_atendimento.in_usuario_inclusao_id = ?) OR (" + usuarioId + " = 0 )) "
                 + " AND (crm_atendimento.dt_inicio >= ? "
                 + " AND crm_atendimento.dt_inicio <= ?) "
                 + "GROUP BY crm_assunto.va_nome "
                 + "ORDER BY total_assunto DESC, nome ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setInt(2, campanhaId);
      preparedStatement.setInt(3, usuarioId);
      preparedStatement.setTimestamp(4, dataInicial);
      preparedStatement.setTimestamp(5, new Timestamp(DateTools.getCalculatedDays(dataFinal, 1).getTime()));
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

  /**
   * Retorna o ResultSet de Assuntos.
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @param empresaId int Empresa ID.
   * @param campanhaId int Campanha ID.
   * @param usuarioId int Usuário ID.
   * @param dataInicial Timestamp Data Inicial.
   * @param dataFinal Timestamp Data Final.
   * @param consolidado NaoSim.SIM para que a análise exibida seja consolidada
   * @return ResultSet Retorna o ResultSet de Assuntos.
   *                   <b>Importante: a rotina que executar este método deve ser
   *                   responsável por fechar o ResultSet retornardo e o seu
   *                   Statement.</b>
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetAssuntos(
                       int empresaId,
                       int campanhaId,
                       int usuarioId,
                       Timestamp dataInicial,
                       Timestamp dataFinal,
                       int consolidado
                    ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT securityservice_usuario.va_nome as nome_usuario, crm_atendimento.in_atendimento_id, global_departamento.va_nome as nome_departamento, crm_assunto.va_nome as nome_assunto, crm_meio.va_nome as nome_meio, crm_campanha.va_nome as nome_campanha, relacionamento_contato.va_nome as nome_cliente, crm_atendimento.dt_inicio, crm_atendimento.dt_termino, crm_atendimento.in_empresa_id, crm_atendimento.va_descricao "
                 + "FROM crm_atendimento "
                 + "INNER JOIN securityservice_usuario ON (securityservice_usuario.in_usuario_id = crm_atendimento.in_usuario_inclusao_id) "
                 + "INNER JOIN global_departamento ON (global_departamento.in_empresa_id = crm_atendimento.in_empresa_id AND global_departamento.in_departamento_id = crm_atendimento.in_departamento_id) "
                 + "INNER JOIN crm_assunto ON (crm_assunto.in_assunto_id = crm_atendimento.in_assunto_id) "
                 + "INNER JOIN crm_meio ON (crm_meio.in_meio_id = crm_atendimento.in_meio_id) "
                 + "INNER JOIN crm_campanha ON (crm_campanha.in_campanha_id = crm_atendimento.in_campanha_id) "
                 + "INNER JOIN relacionamento_contato ON (relacionamento_contato.in_contato_id = crm_atendimento.in_cliente_id) "
                 + "WHERE ((crm_atendimento.in_empresa_id = ?) OR (" + (consolidado == NaoSim.SIM) + ")) "
                 + " AND ((crm_atendimento.in_campanha_id = ?) OR (" + campanhaId + " = 0 )) "
                 + " AND ((crm_atendimento.in_usuario_inclusao_id = ?) OR (" + usuarioId + " = 0 )) "
                 + " AND (crm_atendimento.dt_inicio >= ? "
                 + " AND crm_atendimento.dt_inicio <= ?) "
                 + "ORDER BY nome_assunto, crm_atendimento.in_atendimento_id ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setInt(2, campanhaId);
      preparedStatement.setInt(3, usuarioId);
      preparedStatement.setTimestamp(4, dataInicial);
      preparedStatement.setTimestamp(5, new Timestamp(DateTools.getCalculatedDays(dataFinal, 1).getTime()));
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
