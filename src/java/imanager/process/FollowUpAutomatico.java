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

import java.util.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.process.*;
import iobjects.schedule.Scheduleable;
import iobjects.util.*;
import iobjects.util.mail.HTMLMessage;
import iobjects.util.mail.MailService;
import iobjects.util.mail.Message;
import iobjects.util.mail.Smtp;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Representa o processo de FollowUpAutomatico.
 */
public class FollowUpAutomatico extends iobjects.process.Process implements Scheduleable  {

  public class Info {
    boolean success = false;
  }

  // identificação da classe
  static public final String CLASS_NAME = "imanager.process.EnvioAutomatico";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("envioautomatico", "EnvioAutomatico", "Realiza o processo de EnvioAutomatico.", HELP, "process/envioautomatico.jsp", "Imanager", "", Action.CATEGORY_PROCESS, false, false);
  // nossos comandos
  static public final Command COMMAND_EXECUTE = ACTION.addCommand(new Command(Command.COMMAND_EXECUTE, "Executar", "Executa o processo de " + ACTION.getCaption() + " com os parâmetros informados."));

  /**
   * Construtor padrão.
   */
  public FollowUpAutomatico() {
    // nossas ações
    actionList().add(ACTION);
    // nossas etapas do assistente
  }

  /**
   * Procura por Oportunidades que estão com a data de inclusão menor ou igual 
   * a quantidade do campo dias perdida da campanha e Procura por CampanhaAutomatizadas
   * para o envio de emails dos clientes atraves das oportunidades.
   * @return procura por Oportunidades que estão com a data de inclusão menor ou igual 
   * a quantidade do campo dias perdida da campanha e Procura por CampanhaAutomatizadas
   * para o envio de emails dos clientes atraves das oportunidades..
   * @throws Exception Em caso de exceção na tentativa de execução do processo.
   */
  public RunStatus runScheduledTask() throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // nosso statement
      PreparedStatement statement = SqlTools.prepare(getConnection(), "SELECT relacionamento_contato.va_nome as nome, \n" +
                                                                               "relacionamento_contato.va_email as email,\n" +
                                                                               "crm_campanha_automatizada.va_nome_remetente as nome_remetente,\n" +
                                                                               "crm_campanha_automatizada.va_email_remetente as email_remetente,\n" +
                                                                               "crm_campanha_automatizada.va_assunto as assunto,\n" +
                                                                               "crm_campanha_automatizada.va_mensagem as mensagem\n" +
                                                                               "FROM crm_oportunidade\n" +
                                                                               "INNER JOIN relacionamento_contato ON (crm_oportunidade.in_cliente_id = relacionamento_contato.in_contato_id)\n" +
                                                                               "INNER JOIN crm_campanha_automatizada ON (crm_oportunidade.in_campanha_id = crm_campanha_automatizada.in_campanha_id)\n" +
                                                                               "WHERE CAST(CURRENT_DATE - crm_oportunidade.da_inclusao AS VARCHAR) = CAST(crm_campanha_automatizada.sm_dias_envio AS varchar) || ' day' || (CASE WHEN crm_campanha_automatizada.sm_dias_envio = 1 THEN '' ELSE 's' END);");
      // nosso resultSet
      ResultSet resultSet = statement.executeQuery();
      // count
      int count = 0;
      // loop
      while(resultSet.next()) {
        if (!getFacade().mailService().getSmtpActive())
          throw new Exception("SMTP inativo.");
        // variaveis
        String nomeCliente = resultSet.getString("nome");
        String emailCliente = resultSet.getString("email").toLowerCase();
        String nomeRemetente = resultSet.getString("nome_remetente");
        String emailRemetente = resultSet.getString("email_remetente").toLowerCase();
        String assunto = resultSet.getString("assunto");
        String mensagem = resultSet.getString("mensagem");
        // nosso SMTP
        Smtp smtp = new Smtp(getFacade().mailService().getSmtpProperties().getHostName(), getFacade().mailService().getSmtpProperties().getPort(), getFacade().mailService().getSmtpProperties().getUserName(), getFacade().mailService().getSmtpProperties().getPassword(), getFacade().mailService().getSmtpProperties().getSSL());
        // salva arquivo temporario
        FileTools.saveTextFile(getFacade().tempLocalPath() + "EnvioAutomatico.html", mensagem, "");
        // envia o email
        smtp.send(new HTMLMessage("[" + nomeRemetente + "]" + emailRemetente, "[" + nomeCliente + "]" + emailCliente, assunto, getFacade().tempLocalPath() + "EnvioAutomatico.html")); 
        // incrementa
        count++;
      } // while
      getFacade().commitTransaction();
      // retorna
      return new RunStatus(true, "Fo" + (count > 1 ? "ram" : "i") + " enviado" + (count > 1 ? "s" : "") + " " + count + " email" + (count > 1 ? "s" : "") + ".");
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

}
