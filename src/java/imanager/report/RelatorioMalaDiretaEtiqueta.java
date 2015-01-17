   
package imanager.report;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.report.*;
import iobjects.util.*;

/**
 * Representa o relatório de Mala Direta Etiqueta.
 */
public class RelatorioMalaDiretaEtiqueta extends Report {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.report.RelatorioMalaDiretaEtiqueta";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION               = new Action("malaDiretaEtiqueta", "Mala Direta Etiqueta", "Emite o relatório de Mala Direta Etiqueta.", HELP, "report/relatoriomaladiretaetiqueta.jsp", "Contato", "Auxiliares", Action.CATEGORY_REPORT, false, false);
  static public final Action ACTION_RELATORIO     = new Action("malaDiretaEtiquetaRelatorio", ACTION.getCaption(), ACTION.getDescription(), HELP, "report/relatoriomaladiretaetiquetarelatorio.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_REPORT, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_MAKE_REPORT = ACTION.addCommand(new Command(Command.COMMAND_MAKE_REPORT, "Gerar", "Gera e exibe o relatório com os parâmetros informados."));
  static public final Command COMMAND_ETIQUETA    = ACTION.addCommand(new Command("etiqueta", "Etiquetas", "Gera e exibe as etiquetas."));  
  static public final Command COMMAND_PRINT       = ACTION.addCommand(new Command(Command.COMMAND_PRINT, "Imprimir", "Envia o relatório exibido."));
  static public final Command COMMAND_COPY        = ACTION.addCommand(new Command(Command.COMMAND_COPY, "Copiar", "Copia todo o conteúdo do relatório exibido."));
  static public final Command COMMAND_GERAR_PDF   = ACTION.addCommand(new Command("gerarPdf", "Gerar Etiquetas", "Gera um arquivo PDF com os endereços selecionados."));
  // nossos parâmetros de usuário
  public final Param USER_PARAM_ETIQUETAS            = new Param("uPe", "Modelos pré-configurados", "Selecionar qual o modelo.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "", "");
  public final Param USER_PARAM_QTD_LINHAS           = new Param("uPQL", "Quantidade de Linhas", "Informe a Quantidade de Linhas por Pagína.", "1", 9, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER, "", "value != '0'", "Obrigatório informar a Quantidade de Linhas.");
  public final Param USER_PARAM_QTD_COLUNAS          = new Param("uPQC", "Quantidade de Colunas", "Informe a Quantidade de Colunas por Pagína.", "1", 9, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER, "", "value != '0'", "Obrigatório informar a Quantidade de Colunas.");
  public final Param USER_PARAM_ETIQUETAS_REGISTRO   = new Param("uPER", "Etiquetas por Registro", "Informe a Quantidade de etiquetas por registro.", "1", 9, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER, "", "value != '0'", "Obrigatório informar a Quantidade de Etiquetas por Registro.");
  public final Param USER_PARAM_LINHA_INICIO         = new Param("uPLI", "Linha Início", "Informe a Linha de Início.", "1", 9, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER, "", "value != '0'", "Obrigatório informar a linha de Inicio.");
  public final Param USER_PARAM_TAMANHO_PAGINA       = new Param("uPTP", "Tamanho Pagína", "Selecionar o Tamanho da Pagina.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "value != ''", "Obrigatório Selecionar o Tamanho da Pagína.");
  public final Param USER_PARAM_MARGEM_SUPERIOR      = new Param("uPMS", "Margem Superior/Inferior (mm)", "Informe a Margem Superior/Inferior em Milímetros.", "0,00", 9, Param.ALIGN_RIGHT, Param.FORMAT_NONE, "", "value != ''", "Obrigatório informar a Margem Superior/Inferior.");
  public final Param USER_PARAM_MARGEM_LATERAL       = new Param("uPML", "Margem Lateral (mm)", "Informe a Margem Lateral em Milímetros.", "0,00", 9, Param.ALIGN_RIGHT, Param.FORMAT_NONE, "", "value != ''", "Obrigatório informar a Margem Lateral.");
  public final Param USER_PARAM_ESPACO_HORIZONTAL    = new Param("uPEH", "Espaço Horizontal (mm)", "Informe o espaço horizontal em Milímetros.", "0,00", 9, Param.ALIGN_RIGHT, Param.FORMAT_NONE, "", "value != ''", "Obrigatório informar o espaço horizontal.");  
  public final Param USER_PARAM_ESPACO_VERTICAL      = new Param("uPEV", "Espaço Vertical (mm)", "Informe o espaço vertical em Milímetros.", "0,00", 9, Param.ALIGN_RIGHT, Param.FORMAT_NONE, "", "value != ''", "Obrigatório informar o espaço vertical.");    
  public final Param USER_PARAM_TAMANHO_FONTE        = new Param("uPTF", "Tamanho Fonte", "Selecione o Tamanho da Fonte.", "1", 9, Param.ALIGN_RIGHT, Param.FORMAT_NONE, "", "value != ''", "Obrigatório selecionar o Tamanho da Fonte.");    
  // nossos parâmetros de controle
  public final Param CONTROL_PARAM_ENDERECO_LIST     = new Param("cPEnderecoList", "");
   
  {
    USER_PARAM_TAMANHO_PAGINA.setLookupList(TAMANHO_PAGINA);
    USER_PARAM_TAMANHO_FONTE.setLookupList(TAMANHO_FONTE);
    USER_PARAM_ETIQUETAS.setLookupList(ETIQUETAS);
  }
  
  public static String[] TAMANHO_PAGINA = new String[]{"A4 (210mm x 297mm)", "Carta (216mm x 279mm)"};
  public static String[] TAMANHO_FONTE = new String[]{"Pequeno", "Normal", "Grande"};
  public static String[] ETIQUETAS      = new String[]{"(NENHUM)", "6080-6180-6280-62580 PIMACO", "6081-6181-6281-62581 PIMACO","6082-6182-6282-62582 PIMACO", "6083-6183-6283 PIMACO", "6089 PIMACO", "6093 PIMACO", "A4054-A4054R-A4254-A4354 PIMACO", "A4056-A4056R-A4256-A4356 PIMACO", "A4060-A4260-A4360 PIMACO", "A4062-A4262-A4362 PIMACO", "A4063-A4063R-A4263-A4363 PIMACO"};
  
  public static int TAMANHO_PAGINA_A4 = 0;
  public static int TAMANHO_PAGINA_CARTA = 1;
  
  public static int TAMANHO_FONTE_PEQUENA = 0;
  public static int TAMANHO_FONTE_NORMAL = 1;
  public static int TAMANHO_FONTE_GRANDE = 2;
  
  public static int ETIQUETAS_NENHUM = 0;
  public static int ETIQUETAS_10_3   = 1;
  public static int ETIQUETAS_10_2   = 2;
  public static int ETIQUETAS_7_2    = 3;
  public static int ETIQUETAS_5_2    = 4;
  public static int ETIQUETAS_15_4   = 5;
  public static int ETIQUETAS_6_4    = 6;
  public static int ETIQUETAS_A_11_2 = 7;
  public static int ETIQUETAS_A_11_3 = 8;
  public static int ETIQUETAS_A_7_3  = 9;
  public static int ETIQUETAS_A_8_2  = 10;
  public static int ETIQUETAS_A_7_2  = 11;
  
  /**
   * Construtor padrão.
   */
  public RelatorioMalaDiretaEtiqueta() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_RELATORIO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_ETIQUETAS);
    userParamList().add(USER_PARAM_QTD_LINHAS);
    userParamList().add(USER_PARAM_QTD_COLUNAS);
    userParamList().add(USER_PARAM_ETIQUETAS_REGISTRO);
    userParamList().add(USER_PARAM_LINHA_INICIO);
    userParamList().add(USER_PARAM_TAMANHO_PAGINA);
    userParamList().add(USER_PARAM_TAMANHO_FONTE);
    userParamList().add(USER_PARAM_MARGEM_SUPERIOR);
    userParamList().add(USER_PARAM_MARGEM_LATERAL);
    userParamList().add(USER_PARAM_ESPACO_HORIZONTAL);
    userParamList().add(USER_PARAM_ESPACO_VERTICAL);
    userParamList().add(CONTROL_PARAM_ENDERECO_LIST);
  }

  /**
   * Retorna o ResultSet de Mala Direta Etiqueta.
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet de Mala Direta Etiqueta.
   *                   <b>Importante: a rotina que executar este método deve ser
   *                   responsável por fechar o ResultSet retornardo e o seu
   *                   Statement.</b>
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetRelatorioMalaDiretaEtiqueta() throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT va_nome, da_nascimento, va_endereco, va_endereco_numero, va_endereco_complemento, va_endereco_bairro, va_cidade, ch_uf, ch_cep " +
                   "FROM relacionamento_contato WHERE sm_cliente = 1 AND sm_arquivo = 0";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
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
