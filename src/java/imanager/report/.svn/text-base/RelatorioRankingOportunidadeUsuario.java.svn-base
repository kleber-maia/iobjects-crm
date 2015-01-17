   
package imanager.report;

import java.sql.*;
import java.util.*;

import imanager.misc.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.report.*;
import iobjects.util.*;

/**
 * Representa o relatório de RelatorioRankingOportunidadeUsuario.
 */
public class RelatorioRankingOportunidadeUsuario extends Report {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.report.RelatorioRankingOportunidadeUsuario";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION               = new Action("relatorioRankingOportunidadeUsuario", "Oportunidade por Usuário", "Emite o relatório de Ranking Oportunidade por Usuário.", HELP, "report/relatoriorankingoportunidadeusuario.jsp", "CRM", "Oportunidade", Action.CATEGORY_REPORT, false, true);
  static public final Action ACTION_RELATORIO     = new Action("relatorioRankingOportunidadeUsuarioRelatorio", ACTION.getCaption(), ACTION.getDescription(), HELP, "report/relatoriorankingoportunidadeusuariorelatorio.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_REPORT, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_MAKE_REPORT = ACTION.addCommand(new Command(Command.COMMAND_MAKE_REPORT, "Gerar", "Gera e exibe o relatório com os parâmetros informados."));
  static public final Command COMMAND_PRINT       = ACTION.addCommand(new Command(Command.COMMAND_PRINT, "Imprimir", "Envia o relatório exibido."));
  static public final Command COMMAND_COPY        = ACTION.addCommand(new Command(Command.COMMAND_COPY, "Copiar", "Copia todo o conteúdo do relatório exibido."));
  // nossos parâmetros de usuário
  public final Param USER_PARAM_CAMPANHA_ID     = new Param("userParamCampanhaId", "Campanha", "Selecione a Campanha.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_DEPARTAMENTO_ID = new Param("userParamDepartamentoId", "Departamento", "Selecione o Departamento.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_FASE_ID         = new Param("userParamFaseId", "Fase", "Selecione a Fase.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_STATUS          = new Param("userParamStatsId", "Status", "Selecione o Status.", StatusOportunidade.TODOS + "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_DATA_INICIAL    = new Param("userParamDataInicial", "Data Incial", "Informe a Data Inicial.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar a Data Inicial.");
  public final Param USER_PARAM_DATA_FINAL      = new Param("userParamDataFinal", "Data Final", "Informe a Data Final.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar a Data Final.");
  public final Param USER_PARAM_CONSOLIDADO     = new Param("userParamConsolidado", "Consolidado", "Selecione se os dados serão consolidados entre todas empresas.", "");

  {
   // lookups
   USER_PARAM_CONSOLIDADO.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
   USER_PARAM_STATUS.setLookupList(StatusOportunidade.LOOKUP_LIST_FOR_PARAM);
   // valores padrão
   USER_PARAM_DATA_INICIAL.setValue("01/" + NumberTools.completeZero(DateTools.getActualMonth(), 2) + "/" + DateTools.getActualYear());
   USER_PARAM_DATA_FINAL.setValue(DateTools.formatDate(DateTools.getActualDate()));
   //*
   USER_PARAM_DATA_FINAL.setSpecialConstraint(true, USER_PARAM_DATA_INICIAL);
  }

  /**
   * Construtor padrão.
   */
  public RelatorioRankingOportunidadeUsuario() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_RELATORIO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_CAMPANHA_ID);
    userParamList().add(USER_PARAM_DEPARTAMENTO_ID);
    userParamList().add(USER_PARAM_FASE_ID);
    userParamList().add(USER_PARAM_STATUS);
    userParamList().add(USER_PARAM_DATA_INICIAL);
    userParamList().add(USER_PARAM_DATA_FINAL);
    userParamList().add(USER_PARAM_CONSOLIDADO);
  }

  /**
   * Retorna o ResultSet de RelatorioRankingOportunidadeUsuario.
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @param empresaId int Empresa ID.
   * @param campanhaId int Campanha ID ou 0.
   * @param departamentoId int Departamento ID ou 0.
   * @param faseId int Fase ID ou 0.
   * @param status int Status ou StatusOportunidade.TODOS para exibir todos.
   * @param dataInicial Timestamp Data Inicial.
   * @param dataFinal Timestamp Data Final.
   * @param consolidado NaoSim.SIM para que a análise exibida seja consolidada.
   * @return ResultSet Retorna o ResultSet de RelatorioRankingOportunidadeUsuario.
   *                   <b>Importante: a rotina que executar este método deve ser
   *                   responsável por fechar o ResultSet retornardo e o seu
   *                   Statement.</b>
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetRelatorioRankingOportunidadeUsuario(
                       int empresaId,
                       int campanhaId,
                       int departamentoId,
                       int faseId,
                       int status,
                       Timestamp dataInicial,
                       Timestamp dataFinal,
                       int consolidado
                    ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT securityservice_usuario.va_nome as nome, count(crm_oportunidade.in_usuario_id) as total_oportunidade "
                 + "FROM crm_oportunidade "
                 + "INNER JOIN securityservice_usuario ON (securityservice_usuario.in_usuario_id = crm_oportunidade.in_usuario_id) "
                 + "WHERE ((crm_oportunidade.in_empresa_id = ?) OR (" + (consolidado == NaoSim.SIM) + ")) "
                 + " AND ((crm_oportunidade.in_campanha_id = ?) OR (" + campanhaId + " = 0 )) "
                 + " AND ((crm_oportunidade.in_departamento_id = ?) OR (" + departamentoId + " = 0 )) "
                 + " AND ((crm_oportunidade.in_fase_id = ?) OR (" + faseId + " = 0 )) "
                 + " AND ((crm_oportunidade.sm_status = ?)  OR  (" + (status == StatusOportunidade.TODOS) + ")) "
                 + " AND (crm_oportunidade.da_inclusao >= ? "
                 + " AND crm_oportunidade.da_inclusao <= ?) "
                 + "GROUP BY securityservice_usuario.va_nome "
                 + "ORDER BY total_oportunidade DESC, securityservice_usuario.va_nome ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setInt(2, campanhaId);
      preparedStatement.setInt(3, departamentoId);
      preparedStatement.setInt(4, faseId);
      preparedStatement.setInt(5, status);
      preparedStatement.setTimestamp(6, dataInicial);
      preparedStatement.setTimestamp(7, dataFinal);
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
   * Retorna o ResultSet de Usuarios.
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @param empresaId int Empresa ID.
   * @param campanhaId int Campanha ID.
   * @param departamentoId int Departamento ID ou 0.
   * @param faseId int Fase ID ou 0.
   * @param status int Status ou StatusOportunidade.TODOS para exibir todos.
   * @param dataInicial Timestamp Data Inicial.
   * @param dataFinal Timestamp Data Final.
   * @param consolidado NaoSim.SIM para que a análise exibida seja consolidada
   * @return ResultSet Retorna o ResultSet de Usuarioss.
   *                   <b>Importante: a rotina que executar este método deve ser
   *                   responsável por fechar o ResultSet retornardo e o seu
   *                   Statement.</b>
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetUsuarios(
                       int empresaId,
                       int campanhaId,
                       int departamentoId,
                       int faseId,
                       int status,
                       Timestamp dataInicial,
                       Timestamp dataFinal,
                       int consolidado
                    ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT crm_campanha.va_nome as nome_campanha, crm_oportunidade.in_oportunidade_id, global_departamento.va_nome as nome_departamento, securityservice_usuario.va_nome as nome_usuario, relacionamento_contato.va_nome as nome_cliente, crm_fase.va_nome as nome_fase, crm_oportunidade.da_acompanhamento, crm_oportunidade.sm_percentual_sucesso as sucesso, crm_oportunidade.sm_status as status, crm_oportunidade.do_valor as valor, crm_oportunidade.in_empresa_id as empresa_id "
                 + "FROM crm_oportunidade "
                 + "INNER JOIN securityservice_usuario ON (securityservice_usuario.in_usuario_id = crm_oportunidade.in_usuario_id) "
                 + "INNER JOIN global_departamento ON (global_departamento.in_empresa_id = crm_oportunidade.in_empresa_id AND global_departamento.in_departamento_id = crm_oportunidade.in_departamento_id) "
                 + "INNER JOIN crm_fase ON (crm_fase.in_fase_id = crm_oportunidade.in_fase_id) "
                 + "INNER JOIN crm_campanha ON (crm_campanha.in_campanha_id = crm_oportunidade.in_campanha_id AND crm_campanha.sm_arquivo = " + NaoSim.NAO + ") "
                 + "INNER JOIN relacionamento_contato ON (relacionamento_contato.in_contato_id = crm_oportunidade.in_cliente_id) "
                 + "WHERE ((crm_oportunidade.in_empresa_id = ?) OR (" + (consolidado == NaoSim.SIM) + ")) "
                 + " AND ((crm_oportunidade.in_campanha_id = ?) OR (" + campanhaId + " = 0 )) "
                 + " AND ((crm_oportunidade.in_departamento_id = ?) OR (" + departamentoId + " = 0 )) "
                 + " AND ((crm_oportunidade.in_fase_id = ?) OR (" + faseId + " = 0 )) "
                 + " AND ((crm_oportunidade.sm_status = ?)  OR  (" + (status == StatusOportunidade.TODOS) + ")) "
                 + " AND (crm_oportunidade.da_inclusao >= ? "
                 + " AND crm_oportunidade.da_inclusao <= ?) "
                 + "ORDER BY nome_usuario, crm_oportunidade.in_oportunidade_id ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setInt(2, campanhaId);
      preparedStatement.setInt(3, departamentoId);
      preparedStatement.setInt(4, faseId);
      preparedStatement.setInt(5, status);
      preparedStatement.setTimestamp(6, dataInicial);
      preparedStatement.setTimestamp(7, dataFinal);
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
