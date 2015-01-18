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
 * Representa o processo de SQL Automático.
 * 
 * Este processo vasculha os scripts SQL e os executa no banco de dados da
 * conexão configurada para o processo.
 * 
 * Os scripts devem estar em ~upload/sqlautomatico ou em 
 * ~upload/sqlautomatico/[connectionName], onde: [connectionName] é o nome da 
 * conexão configurada no Schedulle do sistema.
 * Exemplo 1: ~upload/cobrancaautomatica/teste.sql.
 * Exemplo 2: ~upload/cobrancaautomatica/softgroup/teste.sql.
 * 
 * Todos os arquivos com extensão .sql serão executados como scripts. Os scripts 
 * localizados no diretório raiz serão executados em todas as conexões. Os 
 * scripts localizados no subdiretório com o nome da conexão serão executados 
 * somente nesta conexão.
 */
public class SQLAutomatico extends iobjects.process.Process implements Scheduleable {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.process.SQLAutomatica";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("sqlAutomatico", "SQL Automático", "Realiza o processo de SQL Automático.", HELP, "process/sqlautomatico.jsp", "Global", "", Action.CATEGORY_PROCESS, false, false);
  // nossos comandos
  static public final Command COMMAND_EXECUTE = ACTION.addCommand(new Command(Command.COMMAND_EXECUTE, "Executar", "Executa o processo de " + ACTION.getCaption() + " com os parâmetros informados."));
  // nossas etapas do assistente
  static public final ProcessStep PROCESS_STEP_ETAPA1 = new ProcessStep("wizardStepEtapa1", "Etapa 1", "Informe os valores dos parâmetros da etapa 1.");
  static public final ProcessStep PROCESS_STEP_ETAPA2 = new ProcessStep("wizardStepEtapa2", "Etapa 2", "Informe os valores dos parâmetros da etapa 2.");
  static public final ProcessStep PROCESS_STEP_ETAPA3 = new ProcessStep("wizardStepEtapa3", "Etapa 3", "Resultado da execução do processo.");
  // nossos parâmetros de usuário
  public final Param USER_PARAM_PARAMETRO1 = PROCESS_STEP_ETAPA1.addParam(new Param("userParamParametro1", "Parâmetro 1", "Informe o(a) Parâmetro 1.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_NONE, "", "value != ''", "Obrigatório informar Parâmetro 1."));
  public final Param USER_PARAM_PARAMETRO2 = PROCESS_STEP_ETAPA2.addParam(new Param("userParamParametro2", "Parâmetro 2", "Informe o(a) Parâmetro 2.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_NONE, "", "value != ''", "Obrigatório informar Parâmetro 2."));

  private StringBuffer report = new StringBuffer();
  
  /**
   * Construtor padrão.
   */
  public SQLAutomatico() {
    // nossas ações
    actionList().add(ACTION);
    // nossas etapas do assistente
    processStepList().add(PROCESS_STEP_ETAPA1);
    processStepList().add(PROCESS_STEP_ETAPA2);
    processStepList().add(PROCESS_STEP_ETAPA3);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_PARAMETRO1);
    userParamList().add(USER_PARAM_PARAMETRO2);
  }

  /**
   * Executa o processo e retorna um Info contendo o resultado.
   * @param parametro1 String Parâmetro 1.
   * @param parametro2 String Parâmetro 2.
   * @return Info Executa o processo e retorna um Info contendo o resultado.
   * @throws Exception Em caso de exceção na tentativa de execução do processo.
   */
  public void execute() throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // limpa nosso relatório
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
        // põe no relatório
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
    // retorna OK se não tivemos exceções
    return new RunStatus(report.indexOf("Exceção") < 0, report.toString());
  }
  
}
