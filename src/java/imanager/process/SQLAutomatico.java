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


import java.io.*;
import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.process.*;
import iobjects.schedule.*;
import iobjects.util.*;
import iobjects.util.mail.*;

import imanager.entity.*;
import imanager.misc.*;
import iobjects.xml.mail.MailServiceFile;
import java.text.SimpleDateFormat;

/**
 * Representa o processo de SQL Autom�tico.
 * 
 * Este processo vasculha os scripts SQL e os executa no banco de dados da
 * conex�o configurada para o processo.
 * 
 * Os scripts devem estar em ~upload/sqlautomatico ou em 
 * ~upload/sqlautomatico/[connectionName], onde: [connectionName] � o nome da 
 * conex�o configurada no Schedulle do sistema.
 * Exemplo 1: ~upload/cobrancaautomatica/teste.sql.
 * Exemplo 2: ~upload/cobrancaautomatica/softgroup/teste.sql.
 * 
 * Todos os arquivos com extens�o .sql ser�o executados como scripts. Os scripts 
 * localizados no diret�rio raiz ser�o executados em todas as conex�es. Os 
 * scripts localizados no subdiret�rio com o nome da conex�o ser�o executados 
 * somente nesta conex�o.
 */
public class SQLAutomatico extends iobjects.process.Process implements Scheduleable {

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.process.SQLAutomatica";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION = new Action("sqlAutomatico", "SQL Autom�tico", "Realiza o processo de SQL Autom�tico.", HELP, "process/sqlautomatico.jsp", "Global", "", Action.CATEGORY_PROCESS, false, false);
  // nossos comandos
  static public final Command COMMAND_EXECUTE = ACTION.addCommand(new Command(Command.COMMAND_EXECUTE, "Executar", "Executa o processo de " + ACTION.getCaption() + " com os par�metros informados."));
  // nossas etapas do assistente
  static public final ProcessStep PROCESS_STEP_ETAPA1 = new ProcessStep("wizardStepEtapa1", "Etapa 1", "Informe os valores dos par�metros da etapa 1.");
  static public final ProcessStep PROCESS_STEP_ETAPA2 = new ProcessStep("wizardStepEtapa2", "Etapa 2", "Informe os valores dos par�metros da etapa 2.");
  static public final ProcessStep PROCESS_STEP_ETAPA3 = new ProcessStep("wizardStepEtapa3", "Etapa 3", "Resultado da execu��o do processo.");
  // nossos par�metros de usu�rio
  public final Param USER_PARAM_PARAMETRO1 = PROCESS_STEP_ETAPA1.addParam(new Param("userParamParametro1", "Par�metro 1", "Informe o(a) Par�metro 1.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_NONE, "", "value != ''", "Obrigat�rio informar Par�metro 1."));
  public final Param USER_PARAM_PARAMETRO2 = PROCESS_STEP_ETAPA2.addParam(new Param("userParamParametro2", "Par�metro 2", "Informe o(a) Par�metro 2.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_NONE, "", "value != ''", "Obrigat�rio informar Par�metro 2."));

  private StringBuffer report = new StringBuffer();
  
  /**
   * Construtor padr�o.
   */
  public SQLAutomatico() {
    // nossas a��es
    actionList().add(ACTION);
    // nossas etapas do assistente
    processStepList().add(PROCESS_STEP_ETAPA1);
    processStepList().add(PROCESS_STEP_ETAPA2);
    processStepList().add(PROCESS_STEP_ETAPA3);
    // nossos par�metros de usu�rio
    userParamList().add(USER_PARAM_PARAMETRO1);
    userParamList().add(USER_PARAM_PARAMETRO2);
  }

  /**
   * Executa o processo e retorna um Info contendo o resultado.
   * @param parametro1 String Par�metro 1.
   * @param parametro2 String Par�metro 2.
   * @return Info Executa o processo e retorna um Info contendo o resultado.
   * @throws Exception Em caso de exce��o na tentativa de execu��o do processo.
   */
  public void execute() throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // limpa nosso relat�rio
      report = new StringBuffer();
      // localiza os arquivos de scripts
      File[] rootFileList = FileTools.getFiles(getFacade().uploadLocalPath() + "sqlautomatico",
                                               new String[]{".sql"},
                                               false,
                                               FileTools.ORDER_BY_NAME);
      File[] connectionFileList = FileTools.getFiles(getFacade().uploadLocalPath() + "sqlautomatico/" + getConnectionName(),
                                                     new String[]{".sql"},
                                                     false,
                                                     FileTools.ORDER_BY_NAME);
      // une as listas
      File[] scriptFileList = new File[rootFileList.length + connectionFileList.length];
      System.arraycopy(rootFileList, 0, scriptFileList, 0, rootFileList.length);
      System.arraycopy(connectionFileList, 0, scriptFileList, rootFileList.length, connectionFileList.length);      
      // loop nos arquivos
      for (int i=0; i<scriptFileList.length; i++) {
        // arquivo da vez
        File scriptFile = scriptFileList[i];
        // p�e no relat�rio
        report.append("\r\n-> Script " + scriptFile.getName() + "...");
        // carrega o script
        String script = FileTools.loadTextFile(scriptFile.getPath(), "");
        // executa
        SqlTools.execute(getConnection(), script);
      } // for
      // salva tudo
      getFacade().commitTransaction();
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }
  
  /**
   * Executa o processo a partir do agendamento.
   * @return
   * @throws Exception 
   */
  public RunStatus runScheduledTask() throws Exception {
    // executa
    execute();
    // retorna OK se n�o tivemos exce��es
    return new RunStatus(report.indexOf("Exce��o") < 0, report.toString());
  }
  
}
