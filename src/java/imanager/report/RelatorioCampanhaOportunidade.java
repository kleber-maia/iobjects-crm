   
package imanager.report;

import java.sql.*;
import java.util.*;

import imanager.misc.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.report.*;
import iobjects.util.*;

/**
 * Representa o relatório de Campanha Vendas.
 */
public class RelatorioCampanhaOportunidade extends Report {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.report.RelatorioCampanhaOportunidade";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION               = new Action("campanhaOportunidade", "Campanha Oportunidade", "Emite o relatório de Campanha Oportunidade.", HELP, "report/relatoriocampanhaoportunidade.jsp", "CRM", "", Action.CATEGORY_REPORT, false, false);
  static public final Action ACTION_RELATORIO     = new Action("campanhaOportunidadeRelatorio", ACTION.getCaption(), ACTION.getDescription(), HELP, "report/relatoriocampanhaoportunidaderelatorio.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_REPORT, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_MAKE_REPORT = ACTION.addCommand(new Command(Command.COMMAND_MAKE_REPORT, "Gerar", "Gera e exibe o relatório com os parâmetros informados."));
  static public final Command COMMAND_PRINT       = ACTION.addCommand(new Command(Command.COMMAND_PRINT, "Imprimir", "Envia o relatório exibido."));
  static public final Command COMMAND_COPY        = ACTION.addCommand(new Command(Command.COMMAND_COPY, "Copiar", "Copia todo o conteúdo do relatório exibido."));
  // nossos parâmetros de usuário
  public final Param USER_PARAM_DATA_INICIAL   = new Param("userParamDataInicial", "Data Inicial", "Informe a Data Inicial.", DateTools.formatDate(DateTools.getCalculatedMonths(DateTools.getActualDate(), -1)), 250, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar Parâmetro 1.");
  public final Param USER_PARAM_DATA_FINAL     = new Param("userParamDataFinal", "Data Final", "Informe a Data Final.", DateTools.formatDate(DateTools.getActualDate()), 250, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar Parâmetro 2.");
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
  public RelatorioCampanhaOportunidade() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_RELATORIO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_DATA_INICIAL);
    userParamList().add(USER_PARAM_DATA_FINAL);
    userParamList().add(USER_PARAM_CONSOLIDADO);
  }

  /**
   * Retorna o ResultSet de Campanha Vendas.
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @param parametro1 String Parâmetro 1.
   * @param parametro2 String Parâmetro 2.
   * @return ResultSet Retorna o ResultSet de Campanha Vendas.
   *                   <b>Importante: a rotina que executar este método deve ser
   *                   responsável por fechar o ResultSet retornardo e o seu
   *                   Statement.</b>
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetRelatorioCampanhaOportunidade(int       empresaId, 
                                                       Timestamp dataInicial,
                                                       Timestamp dataFinal,
                                                       int       consolidado
                                                       ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      // SQL
      String sql = "SELECT SUM(crm_oportunidade.do_valor) as valor, COUNT(crm_oportunidade.in_oportunidade_id) as quantidade, CAST(crm_oportunidade.da_inclusao as DATE) as data, crm_campanha.va_nome as campanha "
                 + " FROM crm_oportunidade "
                 + " LEFT JOIN crm_campanha ON (crm_oportunidade.da_inclusao >= crm_campanha.da_inicio AND crm_oportunidade.da_inclusao <= crm_campanha.da_termino) "
                 + " WHERE  "
                 + " (crm_oportunidade.in_empresa_id = ? OR ? = " + NaoSim.SIM + ") AND "
                 + " (crm_oportunidade.da_inclusao >= ? AND crm_oportunidade.da_inclusao < ?) "
                 + " GROUP BY data, campanha "
                 + " ORDER BY data";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setInt(2, consolidado);
      preparedStatement.setTimestamp(3, dataInicial);
      preparedStatement.setTimestamp(4, new Timestamp(DateTools.getCalculatedDays(dataFinal, 1).getTime())); 
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
