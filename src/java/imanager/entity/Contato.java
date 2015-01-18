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

import securityservice.entity.*;

import dne.entity.*;

import imanager.misc.*;

/**
 * Representa a entidade Contato no banco de dados da aplicação.
 */
public class Contato extends Entity {

  public class CepInfo {
    public String cep             = "";
    public String uf              = "";
    public String codigoUf        = "";
    public String municipio       = "";
    public String codigoMunicipio = "";
    public String bairro          = "";
    public String endereco        = "";
  }
  
  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.Contato";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("contato", "Contato", "Mantém o cadastro dos [Clientes], [Vendedores], [Fornecedores] e demais Contatos.", HELP, "entity/contato.jsp", "Contato", "", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("contatoCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/contatocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_CHANGE_NOME = ACTION.addCommand(new Command("changeNome",           "Alterar nome", "Permite alterar o nome do Contato."));
  static public final Command COMMAND_EDIT        = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",       "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT      = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",      "Insere um novo registro."));
  static public final Command COMMAND_DELETE      = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",      "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE        = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",       "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH      = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar",    "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_CONTATO_ID = new EntityField("in_contato_id", "Contato ID", "", "contatoId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_LEFT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Nome.");
  static public final EntityField FIELD_GRUPO_CONTATO_ID = new EntityField("in_grupo_contato_id", "Grupo Contato ID", "", "grupoContatoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigatório selecionar o Grupo de Contato.");
  static public final EntityField FIELD_TIPO_PESSOA = new EntityField("sm_tipo_pessoa", "Tipo Pessoa", "Selecione o Tipo de Pessoa.", "tipoPessoa", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_RG = new EntityField("ch_rg", "RG", "Informe o RG.", "rg", Types.VARCHAR, 11, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_CPF = new EntityField("ch_cpf", "CPF", "Informe o CPF.", "cpf", Types.CHAR, 11, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "000.000.000-00", false);
  static public final EntityField FIELD_DATA_NASCIMENTO = new EntityField("da_nascimento", "Data Nascimento", "Informe a Data de Nascimento.", "dataNascimento", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_SEXO = new EntityField("sm_sexo", "Sexo", "Selecione o Sexo.", "sexo", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_RAZAO_SOCIAL = new EntityField("va_razao_social", "Razão Social", "Informe a Razão Social.", "razaoSocial", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_CNPJ = new EntityField("ch_cnpj", "CNPJ", "Informe o CNPJ.", "cnpj", Types.CHAR, 14, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "00.000.000/0000-00", false);
  static public final EntityField FIELD_INSCRICAO_ESTADUAL = new EntityField("va_inscricao_estadual", "Inscrição Estadual", "Informe a Inscrição Estadual.", "inscricaoEstadual", Types.VARCHAR, 14, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_INSCRICAO_MUNICIPAL = new EntityField("va_inscricao_municipal", "Inscrição Municipal", "Informe a Inscrição Municipal.", "inscricaoMunicipal", Types.VARCHAR, 14, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ENDERECO = new EntityField("va_endereco", "Endereço", "Informe o Endereço.", "endereco", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ENDERECO_NUMERO = new EntityField("va_endereco_numero", "Número", "Informe o Número.", "enderecoNumero", Types.VARCHAR, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ENDERECO_COMPLEMENTO = new EntityField("va_endereco_complemento", "Complemento", "Informe o Complemento.", "enderecoComplemento", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ENDERECO_BAIRRO = new EntityField("va_endereco_bairro", "Bairro", "Informe o Bairro.", "enderecoBairro", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_CIDADE = new EntityField("va_cidade", "Município", "Informe o Município.", "cidade", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_UF = new EntityField("ch_uf", "UF", "Informe a UF.", "uf", Types.CHAR, 2, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_CEP = new EntityField("ch_cep", "CEP", "Informe o CEP.", "cep", Types.CHAR, 8, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "00000-000", false);
  static public final EntityField FIELD_TELEFONE_RESIDENCIAL = new EntityField("ch_telefone_residencial", "Residencial", "Informe o Telefone Residencial.", "telefoneResidencial", Types.CHAR, 11, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "(00) 0000-00000", false);
  static public final EntityField FIELD_TELEFONE_CELULAR = new EntityField("ch_telefone_celular", "Celular", "Informe o Telefone Celular.", "telefoneCelular", Types.CHAR, 11, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "(00) 0000-00000", false);
  static public final EntityField FIELD_TELEFONE_TRABALHO = new EntityField("ch_telefone_trabalho", "Trabalho", "Informe o Telefone do Trabalho.", "telefoneTrabalho", Types.CHAR, 11, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "(00) 0000-00000", false);
  static public final EntityField FIELD_EMAIL = new EntityField("va_email", "E-mail", "Informe os E-mail's separados por ';'.", "email", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_CLIENTE = new EntityField("sm_cliente", "Cliente", "Selecione se o contato é Cliente.", "cliente", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_FORNECEDOR = new EntityField("sm_fornecedor", "Fornecedor", "Selecione se o contato é Fornecedor.", "fornecedor", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_FUNCIONARIO = new EntityField("sm_funcionario", "Funcionário", "Selecione se o contato é Funcionário.", "funcionario", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_VENDEDOR = new EntityField("sm_vendedor", "Vendedor", "Selecione se o contato é Vendedor.", "vendedor", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_TRANSPORTADOR = new EntityField("sm_transportador", "Transportador", "Selecione se o contato é Transportador.", "transportador", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_PERCENTUAL_COMISSAO = new EntityField("do_percentual_comissao", "Comissão Vendas (%)", "Informe o Percentual sobre as Comissões apuradas nas vendas, exemplo: 80,5%, 100%, 110%.", "percentualComissao", Types.DOUBLE, 6, 2, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_DOUBLE, "", false);
  static public final EntityField FIELD_PERCENTUAL_INDICACAO = new EntityField("do_percentual_indicacao", "Indicação Vendas (%)", "Informe o Percentual sobre as Comissões apuradas nas vendas, exemplo: 80,5%, 100%, 110%.", "percentualIndicacao", Types.DOUBLE, 6, 2, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_DOUBLE, "", false);
  static public final EntityField FIELD_ANOTACOES = new EntityField("va_anotacoes", "Anotações", "Informe as Anotações.", "anotacoes", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_ARQUIVO = new EntityField("sm_arquivo", "Arquivo Morto", "Selecione se o Contato está Arquivado.", "arquivo", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_USUARIO_INCLUSAO_ID = new EntityField("in_usuario_inclusao_id", "Usuário de Inclusão", "", "usuarioInclusaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_INCLUSAO = new EntityField("da_inclusao", "Data de Inclusão", "", "dataInclusao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_USUARIO_ALTERACAO_ID = new EntityField("in_usuario_alteracao_id", "Usuário de Alteração", "", "usuarioAlteracaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_HORA_ALTERACAO = new EntityField("dt_alteracao", "Data/Hora de Alteração", "", "dataHoraAlteracao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE_TIME, "", false);
  static public final EntityField FIELD_USUARIO_CARTEIRA_ID = new EntityField("in_usuario_carteira_id", "Carteira", "", "usuarioCarteiraId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_HORA_CARTEIRA = new EntityField("dt_carteira", "Data/Hora de Carteira", "", "dataHoraCarteira", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE_TIME, "", false);
  static public final EntityField FIELD_CODIGO_MUNICIPIO = new EntityField("in_codigo_municipio", "Cód Município", "Informe o Código do Município.", "codigoMunicipio", Types.INTEGER, 7, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_PERCENTUAL_COMISSAO_SERVICO = new EntityField("do_percentual_comissao_servico", "Comissão Serviço (%)", "Informe o Percentual sobre as Comissões apuradas nos serviços, exemplo: 80,5%, 100%, 110%.", "percentualComissaoServico", Types.DOUBLE, 5, 2, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_DOUBLE, "", false);
  static public final EntityField FIELD_PERCENTUAL_INDICACAO_SERVICO = new EntityField("do_percentual_indicacao_servico", "Indicação Serviço (%)", "Informe o Percentual sobre as Comissões apuradas nos serviços, exemplo: 80,5%, 100%, 110%.", "percentualIndicacaoServico", Types.DOUBLE, 5, 2, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_DOUBLE, "", false);
  static public final EntityField FIELD_USUARIO_FACEBOOK = new EntityField("va_usuario_facebook", "Nome Usuário", "Informe o Nome do Usuário do Facebook.", "usuarioFacebook", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  static public final EntityField FIELD_CRO = new EntityField("va_cro", "CRO", "Informe o CRO.", "cro", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);

  // nossos lookups
  static public final EntityLookup LOOKUP_GRUPO_CONTATO    = new EntityLookup("lookupGrupoContato", "Grupo de Contato", "Selecione o Grupo de Contato.", GrupoContato.CLASS_NAME, FIELD_GRUPO_CONTATO_ID, GrupoContato.FIELD_NOME, GrupoContato.FIELD_NOME);
  static public final EntityLookup LOOKUP_USUARIO_CARTEIRA = new EntityLookup("lookupUsuarioCarteira", "Carteira", "", Usuario.CLASS_NAME, FIELD_USUARIO_CARTEIRA_ID, Usuario.FIELD_NOME, Usuario.FIELD_NOME);
  // nossos parâmetros de usuário
  public final Param USER_PARAM_NOME             = new Param("userParamNome", "Nome", "Informe o Nome.", "", 30, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE);
  public final Param USER_PARAM_GRUPO_CONTATO_ID = new Param("userParamGrupoContatoId", "Grupo de Contato", "Selecione o Grupo de Contato.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_TIPO_PESSOA      = new Param("userParamTipoPessoa", "Tipo de Pessoa", "Selecione o Tipo de Pessoa.", TipoPessoa.TODOS + "", 5, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_PERSONALIDADE    = new Param("userParamPersonalidade", "Personalidade", "Selecione a Personalidade.", Personalidade.TODOS + "", 5, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_TELEFONE         = new Param("userParamTelefone", "Telefone", "Informe o Telefone Residêncial ou Celular ou Telefone do Trabalho.", "", 11, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE);
  public final Param USER_PARAM_EMAIL            = new Param("userParamEmail", "E-Mail", "Informe o E-Mail.", "", 90, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE);
  public final Param USER_PARAM_USUARIO_CARTEIRA = new Param("userParamUsuarioCarteira", "Carteira", "Selecione o Usuário.", "", 5, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_MES_NASCIMENTO   = new Param("userParamNascimento", "Mês de Aniversário", "Selecione o Mês de Aniversário.", Mes.TODOS + "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_MES_CADASTRO     = new Param("userParamCadastro", "Mês de Cadastro", "Selecione o Mês de Cadastro.", Mes.TODOS + "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_SEXO             = new Param("userParamSexo", "Sexo", "Selecione o Sexo.", Sexo.TODOS + "", 5, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_CPF              = new Param("userParamCpf", "CPF", "Informe o CPF.", "", 11, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE, "000.000.000-00", "", "");
  public final Param USER_PARAM_CNPJ             = new Param("userParamCnpj", "CNPJ", "Informe o CNPJ.", "", 14, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE, "00.000.000/0000-00", "", "");
  public final Param USER_PARAM_BAIRRO           = new Param("userParamBairro", "Bairro", "Informe o Bairro.", "", 50, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE);
  public final Param USER_PARAM_CIDADE           = new Param("userParamCidade", "Cidade", "Informe a Cidade.", "", 50, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE);
  public final Param USER_PARAM_UF               = new Param("userParamUF", "UF", "Informe a UF.", "", 2, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE);
  public final Param USER_PARAM_ARQUIVO          = new Param("userParamArquivo", "Arquivo Morto", "Selecione se mostra os Arquivados.", NaoSim.NAO + "", 5, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);

  static {
    FIELD_TIPO_PESSOA.setLookupList(TipoPessoa.LOOKUP_LIST_FOR_FIELD);
    FIELD_SEXO.setLookupList(Sexo.LOOKUP_LIST_FOR_FIELD);
    FIELD_CLIENTE.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_FORNECEDOR.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_FUNCIONARIO.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_VENDEDOR.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_TRANSPORTADOR.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_ARQUIVO.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
  }

  {
    USER_PARAM_TIPO_PESSOA.setLookupList(TipoPessoa.LOOKUP_LIST_FOR_PARAM);
    USER_PARAM_PERSONALIDADE.setLookupList(Personalidade.LOOKUP_LIST_FOR_PARAM);
    USER_PARAM_MES_NASCIMENTO.setLookupList(Mes.LOOKUP_LIST_FOR_PARAM);
    USER_PARAM_MES_CADASTRO.setLookupList(Mes.LOOKUP_LIST_FOR_PARAM);
    USER_PARAM_SEXO.setLookupList(Sexo.LOOKUP_LIST_FOR_PARAM);
    USER_PARAM_ARQUIVO.setLookupList(NaoSim.LOOKUP_LIST_FOR_PARAM);
    // *
    USER_PARAM_NOME.setSpecialConstraint(true, true);
    USER_PARAM_TELEFONE.setSpecialConstraint(true, true);
    USER_PARAM_EMAIL.setSpecialConstraint(true, true);
    USER_PARAM_CPF.setSpecialConstraint(true, true);
    USER_PARAM_CNPJ.setSpecialConstraint(true, true);
    USER_PARAM_BAIRRO.setSpecialConstraint(true, true);
    USER_PARAM_CIDADE.setSpecialConstraint(true, true);
  }

  /**
   * Construtor padrão.
   */
  public Contato() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("relacionamento_contato");
    // nossos campos  
    fieldList().add(FIELD_CONTATO_ID);  
    fieldList().add(FIELD_NOME);  
    fieldList().add(FIELD_GRUPO_CONTATO_ID);  
    fieldList().add(FIELD_TIPO_PESSOA);  
    fieldList().add(FIELD_RG);  
    fieldList().add(FIELD_CPF);  
    fieldList().add(FIELD_DATA_NASCIMENTO);  
    fieldList().add(FIELD_SEXO);  
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
    fieldList().add(FIELD_TELEFONE_RESIDENCIAL);
    fieldList().add(FIELD_TELEFONE_CELULAR);
    fieldList().add(FIELD_TELEFONE_TRABALHO);
    fieldList().add(FIELD_EMAIL);  
    fieldList().add(FIELD_CLIENTE);  
    fieldList().add(FIELD_FORNECEDOR);  
    fieldList().add(FIELD_FUNCIONARIO);  
    fieldList().add(FIELD_VENDEDOR);  
    fieldList().add(FIELD_TRANSPORTADOR);  
    fieldList().add(FIELD_PERCENTUAL_COMISSAO);  
    fieldList().add(FIELD_PERCENTUAL_INDICACAO);  
    fieldList().add(FIELD_ANOTACOES);  
    fieldList().add(FIELD_ARQUIVO);  
    fieldList().add(FIELD_USUARIO_INCLUSAO_ID);
    fieldList().add(FIELD_DATA_INCLUSAO);  
    fieldList().add(FIELD_USUARIO_ALTERACAO_ID);
    fieldList().add(FIELD_DATA_HORA_ALTERACAO);
    fieldList().add(FIELD_USUARIO_CARTEIRA_ID);
    fieldList().add(FIELD_DATA_HORA_CARTEIRA);
    fieldList().add(FIELD_CODIGO_MUNICIPIO);
    fieldList().add(FIELD_PERCENTUAL_COMISSAO_SERVICO);
    fieldList().add(FIELD_PERCENTUAL_INDICACAO_SERVICO);
    fieldList().add(FIELD_USUARIO_FACEBOOK);
    fieldList().add(FIELD_CRO);
    // nossos lookups
    lookupList().add(LOOKUP_GRUPO_CONTATO);
    lookupList().add(LOOKUP_USUARIO_CARTEIRA);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_NOME);
    userParamList().add(USER_PARAM_GRUPO_CONTATO_ID);
    userParamList().add(USER_PARAM_TIPO_PESSOA);
    userParamList().add(USER_PARAM_PERSONALIDADE);
    userParamList().add(USER_PARAM_TELEFONE);
    userParamList().add(USER_PARAM_EMAIL);
    userParamList().add(USER_PARAM_USUARIO_CARTEIRA);
    userParamList().add(USER_PARAM_MES_NASCIMENTO);
    userParamList().add(USER_PARAM_MES_CADASTRO);
    userParamList().add(USER_PARAM_SEXO);
    userParamList().add(USER_PARAM_CPF);
    userParamList().add(USER_PARAM_CNPJ);
    userParamList().add(USER_PARAM_BAIRRO);
    userParamList().add(USER_PARAM_CIDADE);
    userParamList().add(USER_PARAM_UF);
    userParamList().add(USER_PARAM_ARQUIVO);
  }

  /**
   * Retorna um CepInfo contendo o logradouro referente ao 'cep' informado, caso
   * seja encontrado.
   * <b>O Código do Município e o Código da UF são obtidos através de consulta 
   * posterior a base de Municípios.</b>
   * @param cep Cep referente ao logradouro que se deseja obter os dados.
   * @return
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public CepInfo consultaCep(String cep) throws Exception {
    // nossos objetos
    Logradouro logradouro = (Logradouro)getFacade().getEntity(Logradouro.CLASS_NAME);
    Municipio  municipio  = (Municipio)getFacade().getEntity(Municipio.CLASS_NAME);
    // nosso resultado
    CepInfo result = new CepInfo();
    
    LogradouroInfo logradouroInfo = null;
    try {
      // localiza o CEP no DNE
      logradouroInfo = logradouro.selectByPrimaryKey(cep);
      // preenche nosso resultado
      result.uf        = logradouroInfo.getUf();
      result.municipio = logradouroInfo.getMunicipio();
      result.bairro    = logradouroInfo.getBairro();
      result.endereco  = logradouroInfo.getEndereco();
      result.cep       = logradouroInfo.getCep();
    }
    catch (RecordNotFoundException e) {
      // retorna vazio
      return result;
    } // try-catch
    // procura pelo Município no DNE
    MunicipioInfo[] municipioInfoList = municipio.selectByFilter(result.municipio.toUpperCase(), result.uf, null);
    // se encontramos o Município...usa seus códigos
    if (municipioInfoList.length == 1) {
      result.codigoMunicipio = municipioInfoList[0].getCodigoMunicipio();
      result.codigoUf = municipioInfoList[0].getCodigoUf();
    }
    /*
    // se não encontramos...procura na própria base de Contato
    else {
      // consulta nosso cadastro
      ResultSet resultSet = SqlTools.prepareSelect(getConnection(),
                                                   getTableName(),
                                                   new String[]{FIELD_CODIGO_MUNICIPIO.getFieldName()},
                                                   new String[]{},
                                                   FIELD_CIDADE.getFieldName() + " = '" + result.municipio.toUpperCase() + "' AND " + FIELD_CODIGO_MUNICIPIO.getFieldName() + "> 0",
                                                   1,
                                                   0).executeQuery();
      if (resultSet.next())
        result.codigoMunicipio = resultSet.getString(1);
      // libera recursos
      resultSet.getStatement().close();
      resultSet.close();
    } // if
    */
    // retorna
    return result;
  }
  
  /**
   * Exclui o(a) Contato informado(a) por 'contatoInfo'.
   * @param contatoInfo ContatoInfo referente a(o) Contato
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(ContatoInfo contatoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // apaga as metas
      ContatoMeta contatoMeta = (ContatoMeta)getFacade().getEntity(ContatoMeta.CLASS_NAME);
      ContatoMetaInfo[] contatoMetaInfoList = contatoMeta.selectByFilter(0, contatoInfo.getContatoId());
      for (int i=0; i<contatoMetaInfoList.length; i++)
        contatoMeta.delete(contatoMetaInfoList[i]);
      // exclui o registro
      super.delete(contatoInfo);
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
   * Insere o(a) Contato identificado(a) por 'contatoInfo'.
   * @param contatoInfo ContatoInfo contendo as informações do(a) Contato que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(ContatoInfo contatoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(contatoInfo);
      // valor da seqüência
      contatoInfo.setContatoId(getNextSequence(FIELD_CONTATO_ID));
      // usuários
      contatoInfo.setUsuarioInclusaoId(getFacade().getLoggedUser().getId());
      contatoInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      contatoInfo.setUsuarioCarteiraId(getFacade().getLoggedUser().getId());
      contatoInfo.setDataInclusao(new Timestamp(DateTools.getActualDate().getTime())); // apenas a data
      contatoInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      contatoInfo.setDataHoraCarteira(new Timestamp(System.currentTimeMillis()));
      // insere o registro
      super.insert(contatoInfo);
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
   * Retorna um ContatoInfo referente a(o) Contato
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param contatoId Contato ID.
   * @return Retorna um ContatoInfo referente a(o) Contato
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ContatoInfo selectByCpfCnpj(String cpf,
                                     String cnpj) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_CPF.getFieldName(getTableName()) + " <> '' AND " + FIELD_CPF.getFieldName(getTableName()) + " = ?) OR " +
                                                  "(" + FIELD_CNPJ.getFieldName(getTableName()) + " <> '' AND " + FIELD_CNPJ.getFieldName(getTableName()) + " = ?)"
                                                 );
      statement.setString(1, cpf);
      statement.setString(2, cnpj);
      ContatoInfo[] result = (ContatoInfo[])select(statement);
      // retorna
      if (result.length == 0)
        throw new RecordNotFoundException(getClass().getName(), "selectByCpfCnpj", "Nenhum registro encontrado.");
      else if (result.length > 1)
        throw new ManyRecordsFoundException(getClass().getName(), "selectByCpfCnpj", "Mais de um registro encontrado.");
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
   * Retorna um ContatoInfo referente a(o) Contato
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param contatoId Contato ID.
   * @return Retorna um ContatoInfo referente a(o) Contato
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ContatoInfo selectByPrimaryKey(
              int contatoId
         ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_CONTATO_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, contatoId);
      ContatoInfo[] result = (ContatoInfo[])select(statement);
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
   * Retorna um ContatoInfo[] contendo a lista de Contato
   * indicados(as) pelos parâmetros de pesquisa.
   * @param nome Nome ou vazio.
   * @param grupoContatoId Grupo Contato ID ou 0.
   * @param tipoPessoa Tipo Pessoa ou TipoPessoa.TODOS.
   * @param personalidade Personalidade ou Personalidade.TODOS.
   * @param telefone Telefone ou vazio.
   * @param email Email ou vazio.
   * @param usuarioCarteiraId Usuário Carteira ID ou 0.
   * @param mesNascimento Mês Nascimento ou Mes.TODOS.
   * @param mesCadastro Mês Cadastro ou Mes.TODOS.
   * @param sexo Sexo ou Sexo.TODOS.
   * @param cpf CPF ou vazio.
   * @param cnpj CNPJ ou vazio.
   * @param bairro Bairro ou vazio.
   * @param cidade Cidade ou vazio.
   * @param cidade UF ou vazio.
   * @param arquivo Arquivo ou NaoSim.Todos.
   * @return Retorna um ContatoInfo[] contendo a lista de Contato
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ContatoInfo[] selectByFilter(String    nome,
                                      int       grupoContatoId,
                                      int       tipoPessoa,
                                      int       personalidade,
                                      String    telefone,
                                      String    email,
                                      int       usuarioCarteiraId,
                                      int       mesNascimento,
                                      int       mesCadastro,
                                      int       sexo,
                                      String    cpf,
                                      String    cnpj,
                                      String    bairro,
                                      String    cidade,
                                      String    uf,
                                      int       arquivo) throws Exception {
    // se não informou pelo menos um valor...dispara
    if (nome.equals("") &&
       (grupoContatoId == 0) &&
       (tipoPessoa == TipoPessoa.TODOS) &&
       (personalidade == Personalidade.TODOS) &&
       (telefone.equals("")) &&
       (email.equals("")) &&
       (usuarioCarteiraId == 0) &&
       (mesNascimento == Mes.TODOS) &&
       (mesCadastro == Mes.TODOS) &&
       (sexo == Sexo.TODOS) &&
       cpf.equals("") &&
       cnpj.equals("") &&
       bairro.equals("") &&
       cidade.equals("") &&
       uf.equals(""))
      throw new ExtendedException(CLASS_NAME, "selectByFilter", "Obrigatório informar pelo menos um dos parâmetros.");
    // inicia transação
    getFacade().beginTransaction();
    try {
      // nossa instância de GrupoContato
      GrupoContato grupoContato = (GrupoContato)getFacade().getEntity(GrupoContato.CLASS_NAME);
      // obtém o GrupoContato
      GrupoContatoInfo grupoContatoInfo = (grupoContatoId > 0 ? grupoContato.selectByPrimaryKey(grupoContatoId) : null);
      // prepara a consulta
      String[] orderFieldNames = {FIELD_NOME.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "((lookupGrupoContato.va_nome LIKE ?) OR (" + grupoContatoId + " = 0)) AND " +
                                                  "((" + FIELD_TIPO_PESSOA.getFieldName(getTableName()) + " = ?) OR (" + tipoPessoa + " = " + TipoPessoa.TODOS + ")) AND " +
                                                  "(" +
                                                     "(" + personalidade + " = " + Personalidade.TODOS + ") OR " +
                                                     "((" + FIELD_CLIENTE.getFieldName(getTableName()) + " = " + NaoSim.SIM + ") AND (" + personalidade + " = " + Personalidade.CLIENTE + ")) OR " +
                                                     "((" + FIELD_FORNECEDOR.getFieldName(getTableName()) + " = " + NaoSim.SIM + ") AND (" + personalidade + " = " + Personalidade.FORNECEDOR + ")) OR " +
                                                     "((" + FIELD_FUNCIONARIO.getFieldName(getTableName()) + " = " + NaoSim.SIM + ") AND (" + personalidade + " = " + Personalidade.FUNCIONARIO + ")) OR " +
                                                     "((" + FIELD_VENDEDOR.getFieldName(getTableName()) + " = " + NaoSim.SIM + ") AND (" + personalidade + " = " + Personalidade.VENDEDOR + ")) OR " +
                                                     "((" + FIELD_TRANSPORTADOR.getFieldName(getTableName()) + " = " + NaoSim.SIM + ") AND (" + personalidade + " = " + Personalidade.TRANSPORTADOR + ")) " +
                                                  ") AND " +
                                                  "(" + 
                                                     "(" + FIELD_TELEFONE_RESIDENCIAL.getFieldName(getTableName()) + " LIKE ?) OR " +
                                                     "(" + FIELD_TELEFONE_CELULAR.getFieldName(getTableName()) + " LIKE ?) OR " +
                                                     "(" + FIELD_TELEFONE_TRABALHO.getFieldName(getTableName()) + " LIKE ?) " +
                                                  ") AND " +
                                                  "(" + FIELD_EMAIL.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "((" + FIELD_USUARIO_CARTEIRA_ID.getFieldName(getTableName()) + " = ?) OR (" + usuarioCarteiraId + " = 0)) AND " +
                                                  "(((EXTRACT(MONTH FROM " + FIELD_DATA_NASCIMENTO.getFieldName(getTableName()) + ") = ?) AND (" + FIELD_DATA_NASCIMENTO.getFieldName(getTableName()) + " <> ?)) OR (" + mesNascimento + " = " + Mes.TODOS + ")) AND " +
                                                  "(((EXTRACT(MONTH FROM " + FIELD_DATA_INCLUSAO.getFieldName(getTableName()) + ") = ?) AND (" + FIELD_DATA_INCLUSAO.getFieldName(getTableName()) + " <> ?)) OR (" + mesCadastro + " = " + Mes.TODOS + ")) AND " +
                                                  "((" + FIELD_SEXO.getFieldName(getTableName()) + " = ?) OR (" + sexo + " = " + Sexo.TODOS+ ")) AND " +
                                                  "(" + FIELD_CPF.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "(" + FIELD_CNPJ.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "(" + FIELD_ENDERECO_BAIRRO.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "(" + FIELD_CIDADE.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "(" + FIELD_UF.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "((" + FIELD_ARQUIVO.getFieldName(getTableName()) + " = ?) OR (" + arquivo + " = " + NaoSim.TODOS + "))"
                                                );
      statement.setString(1, nome + "%");
      statement.setString(2, (grupoContatoId > 0 ? grupoContatoInfo.getNome() + "%" : ""));
      statement.setInt(3, tipoPessoa);
      statement.setString(4, "%" + telefone + "%");
      statement.setString(5, "%" + telefone + "%");
      statement.setString(6, "%" + telefone + "%");
      statement.setString(7, "%" + email + "%");
      statement.setInt(8, usuarioCarteiraId);
      statement.setInt(9, mesNascimento+1);           // as constantes de mês começam com 0
      statement.setTimestamp(10, DateTools.ZERO_DATE); // despreza as datas de aniversário não informadas
      statement.setInt(11, mesCadastro+1);             // as constantes de mês começam com 0
      statement.setTimestamp(12, DateTools.ZERO_DATE); // despreza as datas de cadastro não informadas
      statement.setInt(13, sexo);
      statement.setString(14, cpf + "%");
      statement.setString(15, cnpj + "%");
      statement.setString(16, bairro + "%");
      statement.setString(17, cidade + "%");
      statement.setString(18, uf + "%");
      statement.setInt(19, arquivo);
      // nosso resultado
      ContatoInfo[] result = (ContatoInfo[])select(statement);
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
   * Atualiza o(a) Contato identificado(a) por 'contatoInfo'.
   * @param contatoInfo ContatoInfo contendo as informações do(a) Contato que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(ContatoInfo contatoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(contatoInfo);
      // usuários
      contatoInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      contatoInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // atualiza o registro
      super.update(contatoInfo);
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
   * Valida o(a) Contato identificado(a) por 'contatoInfo'.
   * @param contatoInfo ContatoInfo contendo as informações do(a) Contato que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(ContatoInfo contatoInfo) throws Exception {
    // se o nome está em branco...exceção
    if (contatoInfo.getNome().trim().isEmpty())
      throw new ExtendedException(CLASS_NAME, "validate", "Obrigatório informar o Nome.");
    // se é pessoa física...
    if (contatoInfo.getTipoPessoa() == TipoPessoa.FISICA) {
      // apaga os campos de pessoa jurídica
      contatoInfo.setRazaoSocial("");
      contatoInfo.setCnpj("");
      contatoInfo.setInscricaoEstadual("");
      contatoInfo.setInscricaoMunicipal("");
      // temos CPF?
      String cpf = StringTools.removeNotNumbers(contatoInfo.getCpf()).trim();
      if (!cpf.equals("")) {
        // se não é válido...exceção
        if (!DigitVerifier.isCpfValid(contatoInfo.getCpf()))
          throw new ExtendedException(CLASS_NAME, "validate", "O CPF informado é inválido.");
        // se está em duplicidade...exceção
        ContatoInfo[] listaCpf = selectByFilter("", 0, TipoPessoa.FISICA, Personalidade.TODOS, "", "", 0, Mes.TODOS, Mes.TODOS, Sexo.TODOS, contatoInfo.getCpf(), "", "", "", "", NaoSim.TODOS);
        for (int i=0; i<listaCpf.length; i++)
          if (listaCpf[i].getCpf().equals(contatoInfo.getCpf()) && (listaCpf[i].getContatoId() != contatoInfo.getContatoId()))
            throw new ExtendedException(CLASS_NAME, "validate", "O CPF informado já está cadastrado: " + listaCpf[i].getNome() + ".");
      } // if
    }
    // se é pessoa jurídica...
    else {
      // apaga os campos pessoa física
      contatoInfo.setCpf("");
      contatoInfo.setRg("");
      contatoInfo.setDataNascimento(DateTools.ZERO_DATE);
      // temos CNPJ?
      String cnpj = StringTools.removeNotNumbers(contatoInfo.getCnpj()).trim();
      if (!cnpj.equals("")) {
        // se não é válido...exceção
        if (!DigitVerifier.isCnpjValid(contatoInfo.getCnpj()))
          throw new ExtendedException(CLASS_NAME, "validate", "O CNPJ informado é inválido.");
        // se está em duplicidade...exceção
        ContatoInfo[] listaCnpj = selectByFilter("", 0, TipoPessoa.JURIDICA, Personalidade.TODOS, "", "", 0, Mes.TODOS, Mes.TODOS, Sexo.TODOS, "", contatoInfo.getCnpj(), "", "", "", NaoSim.TODOS);
        for (int i=0; i<listaCnpj.length; i++)
          if (listaCnpj[i].getCnpj().equals(contatoInfo.getCnpj()) && (listaCnpj[i].getContatoId() != contatoInfo.getContatoId()))
            throw new ExtendedException(CLASS_NAME, "validate", "O CNPJ informado já está cadastrado: " + listaCnpj[i].getNome() + ".");
      } // if
    } // if

    // se não é vendedor...
    if (contatoInfo.getVendedor() != NaoSim.SIM) {
      contatoInfo.setPercentualComissao(0);
    } // if
    // se não é cliente...
    if (contatoInfo.getCliente() != NaoSim.SIM) {
      contatoInfo.setPercentualIndicacao(0);
    } // if
  }

}
