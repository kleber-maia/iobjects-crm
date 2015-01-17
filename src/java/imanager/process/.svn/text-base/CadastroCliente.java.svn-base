   
package imanager.process;

import java.util.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.process.*;
import iobjects.util.*;

import imanager.entity.*;
import imanager.misc.*;

/**
 * Representa o processo de Cadastro Cliente.
 */
public class CadastroCliente extends iobjects.process.Process {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.process.CadastroCliente";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cadastroCliente", "Cadastrar Cliente", "Realiza o processo de Cadastrar Cliente.", HELP, "process/cadastrocliente.jsp", "Contato", "Auxiliares", Action.CATEGORY_PROCESS, false, true);
  // nossos comandos
  static public final Command COMMAND_EXECUTE   = ACTION.addCommand(new Command(Command.COMMAND_EXECUTE, "Executar", "Executa o processo de " + ACTION.getCaption() + " com os parâmetros informados."));
  static public final Command COMMAND_CADASTRAR = ACTION.addCommand(new Command("cadastrar", "Cadastrar Cliente", "Cadastra rapidamente um novo Cliente."));
  // nossas etapas do assistente
  static public final ProcessStep PROCESS_STEP_DADOS     = new ProcessStep("wizardStepDados", "Dados", "Informe os dados do cliente que será cadastrado.");
  static public final ProcessStep PROCESS_STEP_RESULTADO = new ProcessStep("wizardStepResultado", "Resultado", "O cliente foi cadastrado com sucesso.");
  // nossos parâmetros de usuário
  public final Param USER_PARAM_NOME                 = PROCESS_STEP_DADOS.addParam(new Param("userParamNome", "Nome", "Informe o Nome.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE, "", "value != ''", "Obrigatório informar o Nome."));
  public final Param USER_PARAM_GRUPO_CONTATO_ID     = PROCESS_STEP_DADOS.addParam(new Param("userParamGrupoContatoId", "Grupo", "Selecione o Grupo.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "value != ''", "Obrigatório selecione o Grupo."));
  public final Param USER_PARAM_RG                   = PROCESS_STEP_DADOS.addParam(new Param("userParamRg", "RG", "Informe o RG.", "", 11, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_CPF                  = PROCESS_STEP_DADOS.addParam(new Param("userParamCpf", "CPF", "Informe o CPF.", "", 11, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE, "000.000.000-00", "", ""));
  public final Param USER_PARAM_DATA_NASCIMENTO      = PROCESS_STEP_DADOS.addParam(new Param("userParamDataNascimento", "Data de Nascimento", "Informe a Data de Nascimento.", "", 8, Param.ALIGN_LEFT, Param.FORMAT_DATE));
  public final Param USER_PARAM_SEXO                 = PROCESS_STEP_DADOS.addParam(new Param("userParamSexo", "Sexo", "Selecione o Sexo.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER));
  public final Param USER_PARAM_ENDERECO             = PROCESS_STEP_DADOS.addParam(new Param("userParamEndereco", "Endereço", "Informe o Endereço.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_ENDERECO_NUMERO      = PROCESS_STEP_DADOS.addParam(new Param("userParamEnderecoNumero", "Número", "Informe o Número.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_ENDERECO_COMPLEMENTO = PROCESS_STEP_DADOS.addParam(new Param("userParamEnderecoComplemento", "Complemento", "Informe o Complemento.", "", 50, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_ENDERECO_BAIRRO      = PROCESS_STEP_DADOS.addParam(new Param("userParamEnderecoBairro", "Bairro", "Informe o Bairro.", "", 50, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_MUNICIPIO            = PROCESS_STEP_DADOS.addParam(new Param("userParamMunicipio", "Município", "Informe o Município.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_CODIGO_MUNICIPIO     = PROCESS_STEP_DADOS.addParam(new Param("userParamCodigoMunicipio", "Cód Município", "Informe o Código do Município.", "", 7, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER));
  public final Param USER_PARAM_UF                   = PROCESS_STEP_DADOS.addParam(new Param("userParamUf", "UF", "Informe a UF.", "", 2, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_CEP                  = PROCESS_STEP_DADOS.addParam(new Param("userParamCep", "CEP", "Informe o CEP.", "", 8, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE, "00000-000", "", ""));
  public final Param USER_PARAM_TELEFONE_RESIDENCIAL = PROCESS_STEP_DADOS.addParam(new Param("userParamTelefoneResidencial", "Residencial", "Informe o Telefone Residencial.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE, "(00) 0000-0000", "", ""));
  public final Param USER_PARAM_TELEFONE_CELULAR     = PROCESS_STEP_DADOS.addParam(new Param("userParamTelefoneCelular", "Celular", "Informe o Telefone Celular.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE, "(00) 0000-0000", "", ""));
  public final Param USER_PARAM_TELEFONE_TRABALHO    = PROCESS_STEP_DADOS.addParam(new Param("userParamTelefoneTrabalho", "Trabalho", "Informe o Telefone do Trabalho.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE, "(00) 0000-0000", "", ""));
  public final Param USER_PARAM_EMAIL                = PROCESS_STEP_DADOS.addParam(new Param("userParamEmail", "E-Mail", "Informe o E-Mail.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_ANOTACOES            = PROCESS_STEP_DADOS.addParam(new Param("userParamAnotacoes", "Anotações", "Informe as Anotações.", "", 2147483647, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_TIPO_PESSOA          = PROCESS_STEP_DADOS.addParam(new Param("userParamTipoPessoa", "Tipo Pessoa", "Selecione o Tipo de Pessoa.", "tipoPessoa", 5, Param.ALIGN_LEFT, Param.FORMAT_INTEGER));
  public final Param USER_PARAM_RAZAO_SOCIAL         = PROCESS_STEP_DADOS.addParam(new Param("userParamRazaoSocial", "Razão Social", "Informe a Razão Social", "razaoSocial", 250, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_CNPJ                 = PROCESS_STEP_DADOS.addParam(new Param("userParamCnpj", "CNPJ", "Informe o CNPJ", "cnpj", 14, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE, "00.000.000/0000-00", "", ""));
  public final Param USER_PARAM_INSCRICAO_ESTADUAL   = PROCESS_STEP_DADOS.addParam(new Param("userParamInscricaoEstadual", "Inscrição Estadual", "Informe a Inscrição Estadual", "inscricaoEstadual", 14, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));
  public final Param USER_PARAM_INSCRICAO_MUNICIPAL  = PROCESS_STEP_DADOS.addParam(new Param("userParamInscricaoMunicipal", "Inscrição Municipal", "Informe a Inscrição Municipal", "inscricaoMunicipal", 14, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE));

  {
    USER_PARAM_SEXO.setLookupList(Sexo.LOOKUP_LIST_FOR_FIELD);
    USER_PARAM_TIPO_PESSOA.setLookupList(TipoPessoa.LOOKUP_LIST_FOR_FIELD);
  }

  /**
   * Construtor padrão.
   */
  public CadastroCliente() {
    // nossas ações
    actionList().add(ACTION);
    // nossas etapas do assistente
    processStepList().add(PROCESS_STEP_DADOS);
    processStepList().add(PROCESS_STEP_RESULTADO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_NOME);
    userParamList().add(USER_PARAM_GRUPO_CONTATO_ID);
    userParamList().add(USER_PARAM_RG);
    userParamList().add(USER_PARAM_CPF);
    userParamList().add(USER_PARAM_DATA_NASCIMENTO);
    userParamList().add(USER_PARAM_SEXO);
    userParamList().add(USER_PARAM_ENDERECO);
    userParamList().add(USER_PARAM_ENDERECO_NUMERO);
    userParamList().add(USER_PARAM_ENDERECO_COMPLEMENTO);
    userParamList().add(USER_PARAM_ENDERECO_BAIRRO);
    userParamList().add(USER_PARAM_MUNICIPIO);
    userParamList().add(USER_PARAM_CODIGO_MUNICIPIO);
    userParamList().add(USER_PARAM_UF);
    userParamList().add(USER_PARAM_CEP);
    userParamList().add(USER_PARAM_TELEFONE_RESIDENCIAL);
    userParamList().add(USER_PARAM_TELEFONE_CELULAR);
    userParamList().add(USER_PARAM_TELEFONE_TRABALHO);
    userParamList().add(USER_PARAM_EMAIL);
    userParamList().add(USER_PARAM_ANOTACOES);
    userParamList().add(USER_PARAM_TIPO_PESSOA);
    userParamList().add(USER_PARAM_RAZAO_SOCIAL);
    userParamList().add(USER_PARAM_CNPJ);
    userParamList().add(USER_PARAM_INSCRICAO_ESTADUAL);
    userParamList().add(USER_PARAM_INSCRICAO_MUNICIPAL);
  }

  /**
   * Executa o processo com os parâmetros de usuário e retorna um ContatoInfo
   * contendo o resultado.
   * @return ContatoInfo Executa o processo com os parâmetros de usuário e
   *         retorna um ContatoInfo contendo o resultado.
   * @throws Exception Em caso de exceção na tentativa de execução do processo.
   */
  public ContatoInfo execute() throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // nossa instância de Contato
      Contato contato = (Contato)getFacade().getEntity(Contato.CLASS_NAME);
      // novo contato
      ContatoInfo contatoInfo = new ContatoInfo();
      contatoInfo.setNome(USER_PARAM_NOME.getValue());
      contatoInfo.setGrupoContatoId(USER_PARAM_GRUPO_CONTATO_ID.getIntValue());
      contatoInfo.setRg(USER_PARAM_RG.getValue());
      contatoInfo.setCpf(USER_PARAM_CPF.getValue());
      contatoInfo.setDataNascimento(USER_PARAM_DATA_NASCIMENTO.getDateValue());
      contatoInfo.setSexo(USER_PARAM_SEXO.getIntValue());
      contatoInfo.setEndereco(USER_PARAM_ENDERECO.getValue());
      contatoInfo.setEnderecoNumero(USER_PARAM_ENDERECO_NUMERO.getValue());
      contatoInfo.setEnderecoComplemento(USER_PARAM_ENDERECO_COMPLEMENTO.getValue());
      contatoInfo.setEnderecoBairro(USER_PARAM_ENDERECO_BAIRRO.getValue());
      contatoInfo.setCidade(USER_PARAM_MUNICIPIO.getValue());
      contatoInfo.setCodigoMunicipio(USER_PARAM_CODIGO_MUNICIPIO.getIntValue());
      contatoInfo.setUf(USER_PARAM_UF.getValue());
      contatoInfo.setCep(USER_PARAM_CEP.getValue());
      contatoInfo.setTelefoneResidencial(USER_PARAM_TELEFONE_RESIDENCIAL.getValue());
      contatoInfo.setTelefoneCelular(USER_PARAM_TELEFONE_CELULAR.getValue());
      contatoInfo.setTelefoneTrabalho(USER_PARAM_TELEFONE_TRABALHO.getValue());
      contatoInfo.setEmail(USER_PARAM_EMAIL.getValue());
      contatoInfo.setAnotacoes(USER_PARAM_ANOTACOES.getValue());
      contatoInfo.setTipoPessoa(USER_PARAM_TIPO_PESSOA.getIntValue());
      contatoInfo.setRazaoSocial(USER_PARAM_RAZAO_SOCIAL.getValue());
      contatoInfo.setCnpj(USER_PARAM_CNPJ.getValue());
      contatoInfo.setInscricaoEstadual(USER_PARAM_INSCRICAO_ESTADUAL.getValue());
      contatoInfo.setInscricaoMunicipal(USER_PARAM_INSCRICAO_MUNICIPAL.getValue());
      // valores padrão
      contatoInfo.setCliente(NaoSim.SIM);
      // insere
      contato.insert(contatoInfo);
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      return contatoInfo;
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

}
