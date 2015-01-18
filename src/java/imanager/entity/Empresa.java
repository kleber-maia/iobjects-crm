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
     
package imanager.entity;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;
import iobjects.util.*;
import iobjects.util.mail.*;

import imanager.misc.*;

import securityservice.entity.*;

/** 
 * Representa a entidade Empresa no banco de dados da aplicação.
 */
public class Empresa extends Entity {

  // nossos faqs
  FAQ FAQ_TUTORIAL_INTERFACE = new FAQ("tutorialInterface", " Passo a Passo ", "Interface do usuário: dicas e funções.", "", "tutorial_interface.html", "");

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.Empresa";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("empresa", "Empresa", "Mantém o cadastro das empresas gerenciadas pelo sistema.", HELP, "entity/empresa.jsp", "Global", "", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("empresaCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/empresacadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa ID", "", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_LEFT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 30, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Nome.");
  static public final EntityField FIELD_RAZAO_SOCIAL = new EntityField("va_razao_social", "Razão Social", "Informe a Razão Social.", "razaoSocial", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_CNPJ = new EntityField("ch_cnpj", "CNPJ", "Informe o CNPJ.", "cnpj", Types.CHAR, 14, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "00.000.000/0000-00", true, "", "value != ''", "Obrigatório informar o CNPJ.");
  static public final EntityField FIELD_INSCRICAO_ESTADUAL = new EntityField("va_inscricao_estadual", "Inscrição Estadual", "Informe a Inscrição Estadual.", "inscricaoEstadual", Types.VARCHAR, 30, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar a Inscrição Estadual.");
  static public final EntityField FIELD_INSCRICAO_MUNICIPAL = new EntityField("va_inscricao_municipal", "Inscrição Municipal", "Informe a Inscrição Municipal.", "inscricaoMunicipal", Types.VARCHAR, 30, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ENDERECO = new EntityField("va_endereco", "Endereço", "Informe o Endereço.", "endereco", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ENDERECO_NUMERO = new EntityField("va_endereco_numero", "Número", "Informe o Número.", "enderecoNumero", Types.VARCHAR, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ENDERECO_COMPLEMENTO = new EntityField("va_endereco_complemento", "Complemento", "Informe o Complemento.", "enderecoComplemento", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ENDERECO_BAIRRO = new EntityField("va_endereco_bairro", "Bairro", "Informe o Bairro.", "enderecoBairro", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_CIDADE = new EntityField("va_cidade", "Cidade", "Informe a Cidade.", "cidade", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_UF = new EntityField("ch_uf", "UF", "Informe a UF.", "uf", Types.CHAR, 2, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_CEP = new EntityField("ch_cep", "CEP", "Informe o CEP.", "cep", Types.CHAR, 8, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_TELEFONE = new EntityField("va_telefone", "Telefone", "Informe o Telefone.", "telefone", Types.VARCHAR, 11, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "(00)0000-00000", false);
  static public final EntityField FIELD_EMAIL = new EntityField("va_email", "E-mail", "Informe os E-mail's separados por ';'.", "email", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ESTOQUE = new EntityField("sm_estoque", "Estoque", "Selecione se a Empresa funciona como um Estoque.", "estoque", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_CONSOLIDADORA = new EntityField("sm_consolidadora", "Consolidadora", "Selecione se a Empresa é Consolidadora.", "consolidadora", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_SERVIDOR_SMTP = new EntityField("va_servidor_smtp", "Servidor", "Informe o Servidor SMTP.", "servidorSmtp", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  static public final EntityField FIELD_PORTA_SMTP = new EntityField("in_porta_smtp", "Porta", "Informe a Porta SMTP.", "portaSmtp", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_REQUER_SSL_SMTP = new EntityField("sm_requer_ssl_smtp", "Requer SSL", "Selecione se requer conexão SSL.", "requerSslSmtp", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_USUARIO_EMAIL = new EntityField("va_usuario_email", "Usuário", "Informe o Usuário de Email.", "usuarioEmail", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  static public final EntityField FIELD_SENHA_EMAIL = new EntityField("va_senha_email", "Senha", "Informe a Senha de Email.", "senhaEmail", Types.VARCHAR, 30, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  static public final EntityField FIELD_COPIA_EMAIL = new EntityField("sm_copia_email", "Receber Cópia", "Selecione se deseja receber Cópias dos e-mails enviados.", "copiaEmail", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_CONTATO_CONTADOR_ID = new EntityField("in_contato_contador_id", "Contador ID", "", "ContatoContadorId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != '0'", "Obrigatório selecionar o Contador.");
  static public final EntityField FIELD_CONTADOR_CRC = new EntityField("va_contador_crc", "CRC", "Informe o CRC do Contador.", "contadorCRC", Types.VARCHAR, 30, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o CRC do Contador.");
  static public final EntityField FIELD_ARQUIVO = new EntityField("sm_arquivo", "Arquivo Morto", "Selecione se a Empresa está Arquivada.", "arquivo", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_USUARIO_INCLUSAO_ID = new EntityField("in_usuario_inclusao_id", "Usuário de Inclusão", "", "usuarioInclusaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_INCLUSAO = new EntityField("da_inclusao", "Data de Inclusão", "", "dataInclusao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_USUARIO_ALTERACAO_ID = new EntityField("in_usuario_alteracao_id", "Usuário de Alteração", "", "usuarioAlteracaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_HORA_ALTERACAO = new EntityField("dt_alteracao", "Data/Hora de Alteração", "", "dataHoraAlteracao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE_TIME, "", false);
  static public final EntityField FIELD_SERIE_DOCUMENTO = new EntityField("in_serie_documento", "Série", "Informe a Série atual da Nota Fiscal ou 0 para série única.", "serieDocumento", Types.INTEGER, 3, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_NUMERO_DOCUMENTO_FISCAL = new EntityField("in_numero_documento_fiscal", "Número", "Informe o Número da última Nota Fiscal emitida.", "numeroDocumentoFiscal", Types.INTEGER, 9, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_CODIGO_MUNICIPIO = new EntityField("in_codigo_municipio", "Cód Município", "Informe o Codigo do Município.", "codigoMunicipio", Types.INTEGER, 7, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_IDENTIFICACAO_AMBIENTE = new EntityField("sm_identificacao_ambiente", "Ident. Ambiente", "Selecione a Identificação do Ambiente.", "identificacaoAmbiente", Types.SMALLINT, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_REGIME_TRIBUTARIO = new EntityField("sm_regime_tributario", "Regime Tributário", "Selecione o Regime Tributário.", "regimeTributario", Types.SMALLINT, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_UTILIZA_PAF = new EntityField("sm_utiliza_paf", "PAF", "Selecione se Utiliza PAF.", "utilizaPaf", Types.SMALLINT, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_CNAE = new EntityField("va_cnae", "CNAE", "Informe o CNAE.", "cnae", Types.VARCHAR, 20, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  // nossos lookups
  static public final EntityLookup LOOKUP_CONTADOR = new EntityLookup("lookupContador", "Contador", "Selecione o Contador.", Contato.CLASS_NAME, new EntityField[]{FIELD_CONTATO_CONTADOR_ID}, new EntityField[]{Contato.FIELD_NOME}, new EntityField[]{Contato.FIELD_NOME}, Contato.FIELD_FORNECEDOR.getFieldName() + "=" + NaoSim.SIM, true, true);
  
  /**
   * Opções para Identificação Ambiente
   */
  static public final String[] IDENTIFICACAO_AMBIENTE = {"PRODUÇÃO",
                                                         "HOMOLOGAÇÃO"};
  
  /**
   * Lista de opçoes do regime tributário.
   */
  static public final String[] REGIME_TRIBUTARIO = {"SIMPLES NACIONAL",
                                                    "SIMPLES NACIONAL - EXCESSO DE SUBLIMITE DE RECEITA BRUTA",                                          
                                                    "TRIBUTAÇÃO NORMAL"};
  
  static {
    FIELD_ESTOQUE.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_CONSOLIDADORA.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_REQUER_SSL_SMTP.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_COPIA_EMAIL.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_ARQUIVO.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_IDENTIFICACAO_AMBIENTE.setLookupList(IDENTIFICACAO_AMBIENTE);
    FIELD_REGIME_TRIBUTARIO.setLookupList(REGIME_TRIBUTARIO);    
    FIELD_UTILIZA_PAF.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
  }

  /**
   * Construtor padrão.
   */
  public Empresa() {
    // nossos faqs
    faqList().add(FAQ_TUTORIAL_INTERFACE);
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("global_empresa");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_NOME);  
    fieldList().add(FIELD_RAZAO_SOCIAL);  
    fieldList().add(FIELD_CNPJ);  
    fieldList().add(FIELD_INSCRICAO_ESTADUAL);  
    fieldList().add(FIELD_INSCRICAO_MUNICIPAL);  
    fieldList().add(FIELD_ENDERECO);  
    fieldList().add(FIELD_ENDERECO_NUMERO);
    fieldList().add(FIELD_ENDERECO_COMPLEMENTO);
    fieldList().add(FIELD_ENDERECO_BAIRRO);
    fieldList().add(FIELD_CIDADE);  
    fieldList().add(FIELD_UF);  
    fieldList().add(FIELD_CEP);  
    fieldList().add(FIELD_TELEFONE);  
    fieldList().add(FIELD_EMAIL);  
    fieldList().add(FIELD_ESTOQUE);
    fieldList().add(FIELD_CONSOLIDADORA);  
    fieldList().add(FIELD_SERVIDOR_SMTP);  
    fieldList().add(FIELD_PORTA_SMTP);  
    fieldList().add(FIELD_REQUER_SSL_SMTP);
    fieldList().add(FIELD_USUARIO_EMAIL);  
    fieldList().add(FIELD_SENHA_EMAIL);  
    fieldList().add(FIELD_COPIA_EMAIL);  
    fieldList().add(FIELD_CONTATO_CONTADOR_ID);
    fieldList().add(FIELD_CONTADOR_CRC);
    fieldList().add(FIELD_ARQUIVO);
    fieldList().add(FIELD_USUARIO_INCLUSAO_ID);
    fieldList().add(FIELD_DATA_INCLUSAO);  
    fieldList().add(FIELD_USUARIO_ALTERACAO_ID);
    fieldList().add(FIELD_DATA_HORA_ALTERACAO);
    fieldList().add(FIELD_SERIE_DOCUMENTO);
    fieldList().add(FIELD_NUMERO_DOCUMENTO_FISCAL);
    fieldList().add(FIELD_CODIGO_MUNICIPIO);
    fieldList().add(FIELD_IDENTIFICACAO_AMBIENTE);
    fieldList().add(FIELD_REGIME_TRIBUTARIO);
    fieldList().add(FIELD_UTILIZA_PAF);
    fieldList().add(FIELD_CNAE);
    // nossos lookups
    lookupList().add(LOOKUP_CONTADOR);
  }

  /**
   * Exclui o(a) Empresa informado(a) por 'empresaInfo'.
   * @param empresaInfo EmpresaInfo referente a(o) Empresa
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(EmpresaInfo empresaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(empresaInfo);
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
   * Returna o próximo número do campo Documento Fiscal para ser utilizado.
   * @param empresaId Empresa ID.
   * @return
   * @throws Exception 
   */
  public int getNextNumeroDocumentoFiscal(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // atualiza o registro
      SqlTools.execute(getConnection(),
                       "UPDATE " + getTableName() + " " +
                       "SET " + FIELD_NUMERO_DOCUMENTO_FISCAL.getFieldName() + " = (" + FIELD_NUMERO_DOCUMENTO_FISCAL.getFieldName() + " + 1) " +
                       "WHERE (" + FIELD_EMPRESA_ID.getFieldName() + "=" + empresaId + ")");
      // atualiza o registro
      ResultSet resultSet = SqlTools.executeQuery(getConnection(),
                                                  "SELECT " + FIELD_NUMERO_DOCUMENTO_FISCAL.getFieldName() + " FROM " + getTableName() + " WHERE " + FIELD_EMPRESA_ID.getFieldName() + "=" + empresaId);
      // nosso resultado
      resultSet.next();
      int result = resultSet.getInt(1);
      // libera recursos
      resultSet.close();
      // salva transação
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
   * Insere o(a) Empresa identificado(a) por 'empresaInfo'.
   * @param empresaInfo EmpresaInfo contendo as informações do(a) Empresa que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(EmpresaInfo empresaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(empresaInfo);
      // valor da seqüência
      empresaInfo.setEmpresaId(getNextSequence(FIELD_EMPRESA_ID));
      // usuários
      empresaInfo.setUsuarioInclusaoId(getFacade().getLoggedUser().getId());
      empresaInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      empresaInfo.setDataInclusao(new Timestamp(DateTools.getActualDate().getTime())); // apenas data
      empresaInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // insere o registro
      super.insert(empresaInfo);

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
   * Retorna true se a empresa identificada por 'empresaId' for Consolidadora.
   * @param empresaId Empresa ID.
   * @return Retorna true se a empresa identificada por 'empresaId' for Consolidadora.
   * @throws Exception
   */
  public boolean isConsolidadora(int empresaId) throws Exception {
    return selectByPrimaryKey(empresaId).getConsolidadora() == NaoSim.SIM;
  }

  /**
   * Retorna true se a empresa identificada por 'empresaId' for Estoque.
   * @param empresaId Empresa ID.
   * @return Retorna true se a empresa identificada por 'empresaId' for Estoque.
   * @throws Exception
   */
  public boolean isEstoque(int empresaId) throws Exception {
    return selectByPrimaryKey(empresaId).getEstoque() == NaoSim.SIM;
  }

  /**
   * Retorna um EmpresaInfo referente a(o) Empresa
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param empresaId Empresa ID.
   * @return Retorna um EmpresaInfo referente a(o) Empresa
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public EmpresaInfo selectByPrimaryKey(
              int empresaId
         ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      EmpresaInfo[] result = (EmpresaInfo[])select(statement);
      // retorna
      if (result.length == 0)
        throw new RecordNotFoundException(getClass().getName(), "selectByPrimaryKey", "Nenhum registro encontrado.");
      else if (result.length > 1)
        throw new ManyRecordsFoundException(getClass().getName(), "selectByPrimaryKey", "Mais de um registro encontrado.");
      else {
        // salva tudo
        getFacade().commitTransaction();
        return result[0];
      } // if
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }
  
  /**
   * Retorna um EmpresaInfo referente a(o) Empresa
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param empresaId Empresa ID.
   * @return Retorna um EmpresaInfo referente a(o) Empresa
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public EmpresaInfo selectByCnpj(
              String cnpj
         ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_CNPJ.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setString(1, cnpj);
      EmpresaInfo[] result = (EmpresaInfo[])select(statement);
      // retorna
      if (result.length == 0)
        throw new RecordNotFoundException(getClass().getName(), "selectByCnpj", "Nenhum registro encontrado.");
      else if (result.length > 1)
        throw new ManyRecordsFoundException(getClass().getName(), "selectByCnpj", "Mais de um registro encontrado.");
      else {
        // salva tudo
        getFacade().commitTransaction();
        return result[0];
      } // if
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }  

  /**
   * Retorna um EmpresaInfo[] contendo a lista de Empresa
   * indicados(as) pelos parâmetros de pesquisa.
   * @param nome Nome.
   * @param arquivo Constante de NaoSim.
   * @return Retorna um EmpresaInfo[] contendo a lista de Empresa
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public EmpresaInfo[] selectByFilter(String nome,
                                      String cnpj,
                                      int    arquivo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_NOME.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "(" + FIELD_CNPJ.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "((" + FIELD_ARQUIVO.getFieldName(getTableName()) + " = ?) OR (" + arquivo + " = " + NaoSim.TODOS + "))"
                                                );
      statement.setString(1, nome + "%");
      statement.setString(2, cnpj + "%");
      statement.setInt(3, arquivo);
      // nosso resultado
      EmpresaInfo[] result = (EmpresaInfo[])select(statement);
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
   * Envia uma mensagem de e-mail da empresa identificada por 'fromEmpresaId' 
   * para o usuário identificado por 'toUsuarioId'.
   * @param fromEmpresaId
   * @param toUsuarioId
   * @param cc
   * @param bcc
   * @param subject
   * @param content
   * @return
   * @throws Exception 
   */
  public boolean sendMail(int      fromEmpresaId,
                          int      toUsuarioId,
                          String[] cc,
                          String[] bcc,
                          String   subject,
                          String   content) throws Exception {
    // nossa instância de usuário
    Usuario usuario = (Usuario)getFacade().getEntity(Usuario.CLASS_NAME);
    // localiza o usuário
    UsuarioInfo usuarioInfo = usuario.selectByUsuarioId(toUsuarioId);
    // se o usuário não tem e-mail...dispara
    if (usuarioInfo.getEmail().isEmpty() || !usuarioInfo.getEmail().contains("@") || usuarioInfo.getEmail().indexOf(".") < 0)
      return false;
    // envia e retorna
    return sendMail(fromEmpresaId, usuarioInfo.getEmail().split(";"), cc, bcc, subject, content);
  }
  
  /**
   * Envia uma mensagem de e-mail da empresa identificada por 'fromEmpresaId'
   * para o e-mail cadastrado na própria empresa.
   * @param cc
   * @param bcc
   * @param subject
   * @param content
   * @return
   * @throws Exception 
   */
  public boolean sendMail(int      fromEmpresaId,
                          String[] cc,
                          String[] bcc,
                          String   subject,
                          String   content) throws Exception {
    return sendMail(fromEmpresaId, new String[]{}, cc, bcc, subject, content);
  }
  
  /**
   * Envia uma mensagem de e-mail da empresa identificada por 'fromEmpresaId'
   * para um destinatário qualquer.
   * @param to
   * @param cc
   * @param bcc
   * @param subject
   * @param content
   * @return
   * @throws Exception 
   */
  public boolean sendMail(int      fromEmpresaId,
                          String[] to,
                          String[] cc,
                          String[] bcc,
                          String   subject,
                          String   content) throws Exception {
    // localiza a empresa
    EmpresaInfo empresaInfo = selectByPrimaryKey(fromEmpresaId);
    // se não temos as configurações SMTP...retorna falso
    if (empresaInfo.getServidorSmtp().isEmpty() || empresaInfo.getPortaSmtp() == 0 || empresaInfo.getUsuarioEmail().isEmpty() || empresaInfo.getSenhaEmail().isEmpty())
      return false;
    // se não temos destinatário...envia para a própria empresa
    if (to.length == 0)
      to = empresaInfo.getEmail().split(";");
    // se devemos enviar uma cópia para a empresa
    else if (empresaInfo.getCopiaEmail() == NaoSim.SIM)
      cc = StringTools.arrayConcat(cc, empresaInfo.getEmail());
    // põe os endereços em minúsculo
    for (int i=0; i<to.length; i++)
      to[i] = to[i].toLowerCase();
    for (int i=0; i<cc.length; i++)
      cc[i] = cc[i].toLowerCase();
    for (int i=0; i<bcc.length; i++)
      bcc[i] = bcc[i].toLowerCase();
    // nosso SMTP
    Smtp smtp = new Smtp(empresaInfo.getServidorSmtp(), empresaInfo.getPortaSmtp(), empresaInfo.getUsuarioEmail(), empresaInfo.getSenhaEmail(), empresaInfo.getRequerSslSmtp() == NaoSim.SIM);
    // nossa mensagem
    Message message = new Message(new String[]{empresaInfo.getUsuarioEmail()},
                                  new String[]{},
                                  to,
                                  cc,
                                  bcc,
                                  getFacade().applicationInformation().getName() + " - " + subject,
                                  content);
    // envia
    smtp.send(message);
    // retorna OK
    return true;
  }
  
  /**
   * Atualiza o(a) Empresa identificado(a) por 'empresaInfo'.
   * @param empresaInfo EmpresaInfo contendo as informações do(a) Empresa que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(EmpresaInfo empresaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(empresaInfo);
      // usuário
      empresaInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      empresaInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // atualiza o registro
      super.update(empresaInfo);
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
   * Valida o(a) Empresa identificado(a) por 'empresaInfo'.
   * @param empresaInfo EmpresaInfo contendo as informações do(a) Empresa que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(EmpresaInfo empresaInfo) throws Exception {
    // valida o CNPJ
    if (!DigitVerifier.isCnpjValid(empresaInfo.getCnpj()))
      throw new ExtendedException(CLASS_NAME, "validate", "O CNPJ informado é inválido.");
  }

}
