   
package imanager.process;

import java.sql.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.process.*;
import iobjects.util.*;

import imanager.entity.*;

/**
 * Representa o processo de Alteração Carteira.
 */
public class AlteracaoCarteira extends iobjects.process.Process {

  public class Info {
    boolean success = false;
  }

  // identificação da classe
  static public final String CLASS_NAME = "imanager.process.AlteracaoCarteira";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("alteracaoCarteira", "Alterar Carteira", "Realiza o processo de Alteração de Carteira.", HELP, "process/alteracaocarteira.jsp", "Contato", "Auxiliares", Action.CATEGORY_PROCESS, false, true);
  // nossos comandos
  static public final Command COMMAND_EXECUTE = ACTION.addCommand(new Command(Command.COMMAND_EXECUTE, "Executar", "Executa o processo de " + ACTION.getCaption() + " com os parâmetros informados."));
  static public final Command COMMAND_ALTERAR = ACTION.addCommand(new Command("alterar", "Alterar Carteira", "Altera a Carteira dos Contatos selecionados."));
  // nossas etapas do assistente
  static public final ProcessStep PROCESS_STEP_USUARIO   = new ProcessStep("wizardUsuario", "Usuário", "Selecione o Usuário que será associado à carteira dos Contatos selecionados.");
  static public final ProcessStep PROCESS_STEP_RESULTADO = new ProcessStep("wizardResultado", "Resultado", "Resultado da execução do processo.");
  // nossos parâmetros de controle
  public final Param CONTROL_PARAM_CONTATO_ID        = new Param("cid", "");
  public final Param CONTROL_PARAM_CONTATO_INFO_LIST = new Param("controlParamContatoInfoList", "");
  // nossos parâmetros de usuário
  public final Param USER_PARAM_USUARIO_CARTEIRA = new Param("userParamUsuarioCarteira", "Usuário", "Selecione o novo Usuário da Carteira.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "value != 0", "Obrigatório selecionar o Usuário.");

  /**
   * Construtor padrão.
   */
  public AlteracaoCarteira() {
    // nossas ações
    actionList().add(ACTION);
    // nossas etapas do assistente
    processStepList().add(PROCESS_STEP_USUARIO);
    processStepList().add(PROCESS_STEP_RESULTADO);
    // nossos parâmetros de usuário
    userParamList().add(CONTROL_PARAM_CONTATO_ID);
    userParamList().add(CONTROL_PARAM_CONTATO_INFO_LIST);
    userParamList().add(USER_PARAM_USUARIO_CARTEIRA);
  }

  /**
   * Libera recursos da lista de contatos.
   */
  public void clearContatoInfoList() {
    CONTROL_PARAM_CONTATO_INFO_LIST.setObject(null);
  }

  /**
   * Retorna o ContatoInfo[] alvo do processo de alteração.
   * @return Retorna o ContatoInfo[] alvo do processo de alteração.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ContatoInfo[] getContatoInfoList() throws Exception {
    // se já temos a lista...retorna
    if (CONTROL_PARAM_CONTATO_INFO_LIST.getObject() != null)
      return (ContatoInfo[])CONTROL_PARAM_CONTATO_INFO_LIST.getObject();
    // inicia transação
    getFacade().beginTransaction();
    try {
      // nossa instância de Contato
      Contato contato = (Contato)getFacade().getEntity(Contato.CLASS_NAME);
      // nosso filtro
      StringBuffer where = new StringBuffer();
      // loop nas chaves informadas
      String[] contatoIdList = CONTROL_PARAM_CONTATO_ID.getArrayValue();
      for (int i=0; i<contatoIdList.length; i++)
        where.append((i > 0 ? " OR " : "") + "(" + Contato.FIELD_CONTATO_ID.getFieldName(contato.getTableName()) + "=" + contatoIdList[i] + ")");
      // prepara a consulta
      PreparedStatement statement = contato.prepareSelect(new String[]{Contato.FIELD_NOME.getFieldName()}, where.toString());
      ContatoInfo[] result = (ContatoInfo[])contato.select(statement);
      // guarda
      CONTROL_PARAM_CONTATO_INFO_LIST.setObject(result);
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
   * Executa o processo e retorna um Info contendo o resultado.
   * @return Info Executa o processo e retorna um Info contendo o resultado.
   * @throws Exception Em caso de exceção na tentativa de execução do processo.
   */
  public Info execute() throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // nossa instância de Contato
      Contato contato = (Contato)getFacade().getEntity(Contato.CLASS_NAME);
      // lista de contatos selecionados
      ContatoInfo[] contatoInfoList = getContatoInfoList();
      // loop nos contatos selecionados
      for (int i=0; i<contatoInfoList.length; i++) {
        // contato da vez
        ContatoInfo contatoInfo = contatoInfoList[i];
        // altera a carteira
        contatoInfo.setUsuarioCarteiraId(USER_PARAM_USUARIO_CARTEIRA.getIntValue());
        contatoInfo.setDataHoraCarteira(new Timestamp(System.currentTimeMillis()));
        // salva
        contato.update(contatoInfo);
        // atualiza
        contatoInfoList[i] = (ContatoInfo)contato.refresh(contatoInfo);
      } // for
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      Info result = new Info();
      result.success = true;
      return result;
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

}
