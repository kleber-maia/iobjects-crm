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
import iobjects.ui.*;
import iobjects.ui.param.*;
import iobjects.util.*;

import imanager.misc.*;
import imanager.entity.*;

/**
 * Representa o processo de Traça Rota.
 */
public class TracaRota extends iobjects.process.Process implements Comparator {

  public int compare(Object o1, Object o2) {
    EnderecoInfo e1 = (EnderecoInfo)o1;
    EnderecoInfo e2 = (EnderecoInfo)o2;
    return e1.getCep().compareTo(e2.getCep());
  }

  public class EnderecoInfo {
    private int    id       = 0;
    private String nome     = "";
    private String cpf      = "";
    private String cnpj     = "";
    private String email    = "";
    private String telefoneResidencial = "";
    private String telefoneCelular     = "";
    private String telefoneTrabalho    = "";
    private String endereco = "";
    private String numero   = "";
    private String complemento = "";
    private String bairro = "";
    private String cidade   = "";
    private String uf       = "";
    private String cep      = "";
    public EnderecoInfo(int    id,
                        String nome,
                        String cpf,
                        String cnpj,
                        String endereco,
                        String numero,
                        String complemento,
                        String bairro,
                        String cidade,
                        String uf,
                        String cep,
                        String telefoneResidencial,
                        String telefoneCelular,
                        String telefoneTrabalho,
                        String email) {
      this.id                  = id;
      this.nome                = nome;
      this.cpf                 = cpf;
      this.cnpj                = cnpj;
      this.email               = email;
      this.telefoneResidencial = telefoneResidencial;
      this.telefoneCelular     = telefoneCelular;
      this.telefoneTrabalho    = telefoneTrabalho;
      this.endereco            = endereco;
      this.numero              = numero;
      this.complemento         = complemento;
      this.bairro              = bairro;
      this.cidade              = cidade;
      this.uf                  = uf;
      this.cep                 = cep;
    }
    public int    getId      () { return id      ; }
    public String getNome    () { return nome    ; }
    public String getCpf     () { return cpf     ; }
    public String getCnpj    () { return cnpj    ; }
    public String getEmail   () { return email   ; }
    public String getTelefoneResidencial() { return telefoneResidencial; }
    public String getTelefoneCelular()     { return telefoneCelular; }
    public String getTelefoneTrabalho()    { return telefoneTrabalho; }
    public String getEndereco()    { return endereco; }
    public String getNumero  ()    { return numero  ; }
    public String getComplemento() { return complemento; }
    public String getBairro  () { return bairro  ; }
    public String getCidade  () { return cidade  ; }
    public String getUf      () { return uf      ; }
    public String getCep     () { return cep     ; }
  }

  // identificação da classe
  static public final String CLASS_NAME = "imanager.process.TracaRota";
  // nossa ajuda extra
  static public final String HELP = "<p>O traçado de rota é realizado através dos endereços "
                                  + "dos Contatos selecionados.</p>"
                                  + "<p><b>Dica: utilize o comando Traçar Rota "
                                  + "existente em vários cadastros e relatórios "
                                  + "do sistema.</b></p>";
  // nossas ações
  static public final Action ACTION = new Action("tracaRota", "Traçar Rota", "Realiza o traçado de rota para endereços de Contatos.", HELP, "process/tracarota.jsp", "Contato", "Auxiliares", Action.CATEGORY_PROCESS, false, true);
  // nossos comandos
  static public final Command COMMAND_ADD_CONTATO    = new Command("addContato", "", "");
  static public final Command COMMAND_DELETE_CONTATO = new Command("deleteContato", "", "");
  static public final Command COMMAND_USE_PRODUCER   = ACTION.addCommand(new Command("useProducer", "Traçar Rota", "Traça uma rota com base nos contatos que estão sendo exibidos."));
  static public final Command COMMAND_EXECUTE        = ACTION.addCommand(new Command(Command.COMMAND_EXECUTE, "Executar", "Executa o processo de " + ACTION.getCaption() + " com os parâmetros informados."));
  // nossas etapas do assistente
  static public final ProcessStep PROCESS_STEP_CONTATOS = new ProcessStep("wizardStepContatos", "Contatos", "Verifique os Contatos que deseja manter para o traçado da rota. Os que não possuem endereço serão ignorados.");
  static public final ProcessStep PROCESS_STEP_FINAL    = new ProcessStep("wizardStepFinal", "Rota", "O traçado da rota foi criado com sucesso.");
  // nossos parâmetros de usuário
  public Param USER_PARAM_EMPRESA_ID    = new Param("userParamEmpresaId", "Empresa Id", "", "", 4, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public Param USER_PARAM_CONTATO_ID    = new Param("userParamContatoId", "");
  public Param USER_PARAM_DELETE_INDEX  = new Param("userParamDeleteIndex", "");
  public Param USER_PARAM_ENDERECO_LIST = new Param("userParamEnderecoList", "");

  /**
   * Construtor padrão.
   */
  public TracaRota() {
    // nossas ações
    actionList().add(ACTION);
    // nossas etapas do assistente
    processStepList().add(PROCESS_STEP_CONTATOS);
    processStepList().add(PROCESS_STEP_FINAL);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_EMPRESA_ID);
    userParamList().add(USER_PARAM_CONTATO_ID);
    userParamList().add(USER_PARAM_DELETE_INDEX);
    userParamList().add(USER_PARAM_ENDERECO_LIST);
  }

  /**
   * Adiciona os endereços associados ao Contato representado por
   * 'contatoId' para criação da Rota.
   * @param contatoId int Contato Id
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de dados.
   */
  public void addEndereco(int contatoId) throws Exception {
    addEndereco(new int[]{contatoId});
  }

  /**
   * Adiciona os endereços associados aos Contatos representados por
   * 'contatoIdList' para criação da Rota.
   * @param contatoIdList int[] Lista de Contato Id´s para adicionar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de dados.
   */
  public void addEndereco(int[] contatoIdList) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // se não temos Id´s...dispara
      if (contatoIdList.length == 0)
        return;
      // nossa instância de Contato
      Contato contato = (Contato)getFacade().getEntity(Contato.CLASS_NAME);
      // nosso Where
      StringBuffer where = new StringBuffer();
      for (int i=0; i<contatoIdList.length; i++) {
        if (where.length() > 0)
          where.append(" OR ");
        where.append("(" + Contato.FIELD_CONTATO_ID.getFieldName(contato.getTableName()) + " = " + contatoIdList[i] + ")");
      } // for
      // nossa consulta
      String sql = "SELECT "
        /* 1 */  +    Contato.FIELD_CONTATO_ID.getFieldName(contato.getTableName()) + ", "
        /* 2 */  +    Contato.FIELD_NOME.getFieldName(contato.getTableName()) + ", "
        /* 3 */  +    Contato.FIELD_CPF.getFieldName(contato.getTableName()) + ", "
        /* 4 */  +    Contato.FIELD_CNPJ.getFieldName(contato.getTableName()) + ", "
        /* 5 */  +    Contato.FIELD_ENDERECO.getFieldName(contato.getTableName()) + ", "
        /* 6 */  +    Contato.FIELD_ENDERECO_NUMERO.getFieldName(contato.getTableName()) + ", "
        /* 7 */  +    Contato.FIELD_ENDERECO_COMPLEMENTO.getFieldName(contato.getTableName()) + ", "
        /* 8 */  +    Contato.FIELD_ENDERECO_BAIRRO.getFieldName(contato.getTableName()) + ", "
        /* 9 */  +    Contato.FIELD_CIDADE.getFieldName(contato.getTableName()) + ", "
        /* 10 */  +   Contato.FIELD_UF.getFieldName(contato.getTableName()) + ", "
        /* 11 */  +   Contato.FIELD_CEP.getFieldName(contato.getTableName()) + ", "
        /* 12 */  +   Contato.FIELD_TELEFONE_RESIDENCIAL.getFieldName(contato.getTableName()) + ", "
        /* 13 */  +   Contato.FIELD_TELEFONE_CELULAR.getFieldName(contato.getTableName()) + ", "
        /* 14 */  +   Contato.FIELD_TELEFONE_TRABALHO.getFieldName(contato.getTableName()) + ", "
        /* 15 */ +    Contato.FIELD_EMAIL.getFieldName(contato.getTableName()) + " "
                 + "FROM "
                 +   contato.getTableName() + " "
                 + "WHERE "
                 +   "(" + where.toString() + ") "
                 + "ORDER BY "
                 +   Contato.FIELD_NOME.getFieldName(contato.getTableName());
      // executa a consulta
      ResultSet resultSet = SqlTools.executeQuery(contato.getConnection(), sql);
      // nossa lista de endereços
      if (USER_PARAM_ENDERECO_LIST.getObject() == null)
        USER_PARAM_ENDERECO_LIST.setObject(new Vector());
      Vector vector = (Vector)USER_PARAM_ENDERECO_LIST.getObject();
      // loop nos endereços obtidos
      while (resultSet.next()) {
        // novo endereço
        EnderecoInfo enderecoInfo = new EnderecoInfo(resultSet.getInt(1),
                                                     resultSet.getString(2),
                                                     resultSet.getString(3),
                                                     resultSet.getString(4),
                                                     resultSet.getString(5),
                                                     resultSet.getString(6),
                                                     resultSet.getString(7),
                                                     resultSet.getString(8),
                                                     resultSet.getString(9),
                                                     resultSet.getString(10),
                                                     resultSet.getString(11),
                                                     resultSet.getString(12),
                                                     resultSet.getString(13),
                                                     resultSet.getString(14),
                                                     resultSet.getString(15));
        vector.add(enderecoInfo);
      } // while
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
   * Adiciona os endereços associados aos Contatos representados pelo
   * ContatoProducer atualmente publicado.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de dados.
   */
  public void addEnderecoFromProducer() throws Exception {
    // Producer que se encontra na Facade
    ContatoProducer contatoProducer = ContatoProducer.getInstance(getFacade());
    // adiciona os Endereços dos Contatos selecionados
    addEndereco(contatoProducer.getContatoIdList());
  }

  /**
   * Remove o endereço associado a 'contatoId'.
   * @param contatoId Contato ID referente ao endereço que se deseja remover.
   */
  public void deleteEndereco(int contatoId) {
    // nossa lista de endereços
    if (USER_PARAM_ENDERECO_LIST.getObject() == null)
      USER_PARAM_ENDERECO_LIST.setObject(new Vector());
    Vector vector = (Vector)USER_PARAM_ENDERECO_LIST.getObject();
    // apaga o endereço referente ao índice informado
    for (int i=0; i<vector.size(); i++) {
      EnderecoInfo enderecoInfo = (EnderecoInfo)vector.elementAt(i);
      if (enderecoInfo.getId() == contatoId) {
        vector.remove(i);
        return;
      } // if
    } // for
  }

  /**
   * Retorna uma String contendo a Rota gerada a partir dos Contatos selecionados.
   * @return Retorna uma String contendo a Rota gerada a partir dos Contatos
   *         selecionados.
   * @throws Exception
   */
  public String execute(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // lista de endereços
      Vector vector = null;
      if (USER_PARAM_ENDERECO_LIST.getObject() == null)
        vector = new Vector();
      else
        vector = (Vector)USER_PARAM_ENDERECO_LIST.getObject();
      // se não temos nenhum endereço...exceção
      if (vector.size() == 0)
        throw new ExtendedException(getClass().getName(), "execute", "Nenhum Contato selecionado para o traçado de rota.");

      // ordena pelo CEP
      EnderecoInfo[] enderecoInfoList = new EnderecoInfo[vector.size()];
      vector.copyInto(enderecoInfoList);
      Arrays.sort(enderecoInfoList, this);
      vector.clear();

      // nosso resultado
      StringBuffer result = new StringBuffer();
      // parâmetros para serem utilizados
      String[] paramNames = {"&saddr=", "&daddr=", "+to:"};
      int      paramIndex = 0;
      int      paramMax   = 2;

      // nossa instância de Empresa
      Empresa empresa = (Empresa)getFacade().getEntity(Empresa.CLASS_NAME);
      // localiza a empresa
      EmpresaInfo empresaInfo = empresa.selectByPrimaryKey(empresaId);
      // se temos endereço...
      if (!empresaInfo.getCep().equals("") && !empresaInfo.getEndereco().equals("") && !empresaInfo.getEnderecoNumero().equals("") && !empresaInfo.getEnderecoBairro().equals("") && !empresaInfo.getCidade().equals("") && !empresaInfo.getUf().equals(""))
        result.append(paramNames[(paramIndex < paramMax ? paramIndex++ : paramIndex)] + empresaInfo.getEndereco() + "," + empresaInfo.getEnderecoNumero() + "," + empresaInfo.getEnderecoBairro() + "," + empresaInfo.getCidade() + "," + empresaInfo.getUf() + "," + StringTools.formatCustomMask(empresaInfo.getCep(), "00000-000"));

      // loop nos endereços
      for (int i=0; i<enderecoInfoList.length; i++) {
        // endereço da vez
        EnderecoInfo enderecoInfo = enderecoInfoList[i];
        // se temos endereço...
        if (!enderecoInfo.getCep().equals("") && !enderecoInfo.getEndereco().equals("") && !enderecoInfo.getNumero().equals("") && !enderecoInfo.getBairro().equals("") && !enderecoInfo.getCidade().equals("") && !enderecoInfo.getUf().equals(""))
          result.append(paramNames[(paramIndex < paramMax ? paramIndex++ : paramIndex)] + enderecoInfo.getEndereco() + "," + enderecoInfo.getNumero() + "," + enderecoInfo.getBairro() + "," + enderecoInfo.getCidade() + "," + empresaInfo.getUf() + "," + StringTools.formatCustomMask(enderecoInfo.getCep(), "00000-000"));
      } // for
      // finaliza a URL
      result.insert(0, "http://maps.google.com/maps?f=d&source=s_d");

      // salva tudo
      getFacade().commitTransaction();
      // retorna substituindo ' ' por '+'
      return result.toString().replaceAll(" ", "+");
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

}
