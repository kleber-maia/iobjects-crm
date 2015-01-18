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
   
package imanager.process;

import java.sql.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.process.*;
import iobjects.util.*;

import imanager.entity.*;

/**
 * Representa o processo de Altera��o Carteira.
 */
public class AlteracaoCarteira extends iobjects.process.Process {

  public class Info {
    boolean success = false;
  }

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.process.AlteracaoCarteira";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION = new Action("alteracaoCarteira", "Alterar Carteira", "Realiza o processo de Altera��o de Carteira.", HELP, "process/alteracaocarteira.jsp", "Contato", "Auxiliares", Action.CATEGORY_PROCESS, false, true);
  // nossos comandos
  static public final Command COMMAND_EXECUTE = ACTION.addCommand(new Command(Command.COMMAND_EXECUTE, "Executar", "Executa o processo de " + ACTION.getCaption() + " com os par�metros informados."));
  static public final Command COMMAND_ALTERAR = ACTION.addCommand(new Command("alterar", "Alterar Carteira", "Altera a Carteira dos Contatos selecionados."));
  // nossas etapas do assistente
  static public final ProcessStep PROCESS_STEP_USUARIO   = new ProcessStep("wizardUsuario", "Usu�rio", "Selecione o Usu�rio que ser� associado � carteira dos Contatos selecionados.");
  static public final ProcessStep PROCESS_STEP_RESULTADO = new ProcessStep("wizardResultado", "Resultado", "Resultado da execu��o do processo.");
  // nossos par�metros de controle
  public final Param CONTROL_PARAM_CONTATO_ID        = new Param("cid", "");
  public final Param CONTROL_PARAM_CONTATO_INFO_LIST = new Param("controlParamContatoInfoList", "");
  // nossos par�metros de usu�rio
  public final Param USER_PARAM_USUARIO_CARTEIRA = new Param("userParamUsuarioCarteira", "Usu�rio", "Selecione o novo Usu�rio da Carteira.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "value != 0", "Obrigat�rio selecionar o Usu�rio.");

  /**
   * Construtor padr�o.
   */
  public AlteracaoCarteira() {
    // nossas a��es
    actionList().add(ACTION);
    // nossas etapas do assistente
    processStepList().add(PROCESS_STEP_USUARIO);
    processStepList().add(PROCESS_STEP_RESULTADO);
    // nossos par�metros de usu�rio
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
   * Retorna o ContatoInfo[] alvo do processo de altera��o.
   * @return Retorna o ContatoInfo[] alvo do processo de altera��o.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public ContatoInfo[] getContatoInfoList() throws Exception {
    // se j� temos a lista...retorna
    if (CONTROL_PARAM_CONTATO_INFO_LIST.getObject() != null)
      return (ContatoInfo[])CONTROL_PARAM_CONTATO_INFO_LIST.getObject();
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // nossa inst�ncia de Contato
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
   * @throws Exception Em caso de exce��o na tentativa de execu��o do processo.
   */
  public Info execute() throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // nossa inst�ncia de Contato
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
