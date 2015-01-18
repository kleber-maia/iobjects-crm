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
 * Representa o processo de Demonstra��o da aplica��o.
 */
public class Demo extends iobjects.process.Process implements DemoService {

  /**
   * Construtor padr�o.
   */
  public Demo() {
  }
  
  /**
   * Requisita o acesso a demonstra��o para o visitante identificado por 'name' 
   * e 'email' e retorna a mensagem que ser� apresentada ao usu�rio.
   * @param name String Nome do visitante.
   * @param email String Email do visitante.
   * @return 
   * @throws Exception Em caso de exce��o na tentativa de realizar a opera��o.
   */
  public String requestDemo(String name, String email) throws Exception {
    // obt�m a inst�ncia de usu�rio
    Usuario usuario = (Usuario)getFacade().getEntity(Usuario.CLASS_NAME);
    // utiliza a conex�o Teste
    usuario.setConnectionName("Teste");
    // localiza o usu�rio Convidado
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
   * @param empresaInfo Empresa remetente da mensagem que ser� enviada.
   * @param tituloInfo T�tulo referente a cobran�a que ser� enviada.
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
    // salva em arquivo tempor�rio
    FileTools.saveTextFile(messageFile.getParent() + "/temp.html", content.toString(), "ISO-8859-1");
    // cria e configura nosso Smtp
    MailServiceFile.Smtp props = getFacade().mailService().getSmtpProperties();
    if (!props.getActive()) {
      throw new ExtendedException(getClass().getName(), "execute", "O servi�o de envio de e-mail est� inativo.");
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
