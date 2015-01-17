    
package imanager.process;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.process.*;
import iobjects.security.*;
import iobjects.util.*;

import securityservice.entity.*;

import imanager.entity.*;
import iobjects.util.mail.HTMLMessage;
import iobjects.util.mail.Smtp;
import iobjects.xml.mail.MailServiceFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Representa o processo de Demonstração da aplicação.
 */
public class Demo extends iobjects.process.Process implements DemoService {

  /**
   * Construtor padrão.
   */
  public Demo() {
  }
  
  /**
   * Requisita o acesso a demonstração para o visitante identificado por 'name' 
   * e 'email' e retorna a mensagem que será apresentada ao usuário.
   * @param name String Nome do visitante.
   * @param email String Email do visitante.
   * @return 
   * @throws Exception Em caso de exceção na tentativa de realizar a operação.
   */
  public String requestDemo(String name, String email) throws Exception {
    // obtém a instância de usuário
    Usuario usuario = (Usuario)getFacade().getEntity(Usuario.CLASS_NAME);
    // utiliza a conexão Teste
    usuario.setConnectionName("Teste");
    // localiza o usuário Convidado
    UsuarioInfo usuarioInfo = usuario.selectByNomeOrEmail("Convidado", "convidado");
    // envia o e-mail com login e senha de acesso
    sendMessage(new File(getFacade().uploadLocalPath() + "demo/demo.html"), "Softgroup", "falecom@softgroup.com.br", name, email, usuarioInfo.getNome(), usuarioInfo.getSenha());
    // armazena o nome e e-mail para ser sincronizado
    File               fileContatos = new File(getFacade().uploadLocalPath() + "demo/contatos.csv");
    FileOutputStream   outputStream = new FileOutputStream(fileContatos, true);
    OutputStreamWriter writer       = new OutputStreamWriter(outputStream, "ISO-8859-1");
    writer.append(DateTools.formatDateTime(DateTools.getActualDateTime()) + ";" + name.replaceAll(";", " ") + ";" + email.replaceAll(";", " ") + "\r\n");
    writer.close();
    outputStream.close();
    // retorna
    return "Um login e senha foram enviados para o seu e-mail.";
  }

  /**
   * Envia a mensagem contida em 'messageFile' para o contato contido em
   * 'tituloInfo'.
   * @param messageFile Arquivo contendo a mensagem a ser enviada.
   * @param empresaInfo Empresa remetente da mensagem que será enviada.
   * @param tituloInfo Título referente a cobrança que será enviada.
   * @throws Exception 
   */
  private void sendMessage(File        messageFile,
                           String      fromNome,
                           String      fromEmail,
                           String      toNome,
                           String      toEmail,
                           String      demoUsername,
                           String      demoPassword) throws Exception {
    // carrega o arquivo de mensagem
    StringBuffer content = new StringBuffer(FileTools.loadTextFile(messageFile.getPath(), "ISO-8859-1"));
    // procura pelo assunto
    String subject = "";
    // se encontramos o assunto...guarda
    int index = content.indexOf("<title>");
    if (index > 0) {
      subject = content.substring(index + 7, content.indexOf("</title>", index));
    } // if
    // substitui as macros
    int begin = content.indexOf("[NOME]");
    int end   = content.indexOf("]", begin);
    content.replace(begin, end+1, StringTools.toCaptalCase(toNome, true));
    // *
    begin = content.indexOf("[USERNAME]");
    end   = content.indexOf("]", begin);
    content.replace(begin, end+1, demoUsername);
    // *
    begin = content.indexOf("[PASSWORD]");
    end   = content.indexOf("]", begin);
    content.replace(begin, end+1, demoPassword);
    // salva em arquivo temporário
    FileTools.saveTextFile(messageFile.getParent() + "/temp.html", content.toString(), "ISO-8859-1");
    // cria e configura nosso Smtp
    MailServiceFile.Smtp props = getFacade().mailService().getSmtpProperties();
    if (!props.getActive()) {
      throw new ExtendedException(getClass().getName(), "execute", "O serviço de envio de e-mail está inativo.");
    } // if
    Smtp smtp = new Smtp(props.getHostName(), props.getPort(), props.getUserName(), props.getPassword(), props.getSSL());
    // cria uma mensagem HTML
    HTMLMessage htmlMessage = new HTMLMessage(new String[]{"\"" + StringTools.toCaptalCase(fromNome, true) + "\"<" + fromEmail + ">"},
                                              new String[]{},
                                              new String[]{toEmail},
                                              new String[]{fromEmail},
                                              new String[]{},
                                              subject,
                                              messageFile.getParent() + "/temp.html");
    // envia
    smtp.send(htmlMessage);
  }
  
}
