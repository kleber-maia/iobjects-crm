
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
 * Representa o processo de Mala Direta.
 */
public class MalaDireta extends iobjects.process.Process {

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

  /**
   * Representa o resultado da execução do processo de Mala Direta.
   */
  public class MalaDiretaInfo {
    private String   enderecos   = "";
    private String   etiquetas   = "";
    private String[] gruposEmail = {};
    public MalaDiretaInfo(String   enderecos,
                          String   etiquetas,
                          String[] gruposEmail) {
      this.enderecos = enderecos;
      this.etiquetas = etiquetas;
      this.gruposEmail = gruposEmail;
    }
    public String   getEnderecos() { return enderecos; }
    public String   getEtiquetas() { return etiquetas; }
    public String[] getGruposEmail() {return gruposEmail; }
  }

  // identificação da classe
  static public final String CLASS_NAME = "imanager.process.MalaDireta";
  // nossa ajuda extra
  static public final String HELP = "<p>A criação da mala direta é realizada através dos endereços "
                                  + "e e-mails dos Contatos selecionados.</p>"
                                  + "<p><b>Dica: utilize o comando Mala Direta "
                                  + "existente em vários cadastros e relatórios "
                                  + "do sistema.</b></p>"
                                  + "<p><b>IMPORTANTE - </b>A correta impressão das etiquetas depende de algumas configurações de impressão:</p>"
                                  + "<ul><li>No Internet Explorer, clique em Arquivo -> Configurar Página.</li>"
                                  + "<li>No Campo 'Tamanho da pagína' selecione o tamanho da página que você irá imprimir.</li>"
                                  + "<li>Nos Campos 'Margens' preecha todos com 0.</li>"
                                  + "<li>No Campo 'Cabeçalhos e Rodapés' selecione todos como '-Vazio-'.</li>"
                                  + "<li>Clique em OK.</li></ul>"
                                  + "<li>Quando clicar em Imprimir clique em 'Preferências'.</li><ul><li>Na Aba Papel/Qualidade selecione o tamanho da página que você deseja imprimir.</li></ul></li></ul>";
  // nossas ações
  static public final Action ACTION           = new Action("malaDireta", "Mala Direta", "Realiza a criação de mala direta para envio de e-mails e confecção de etiquetas para Contatos.", HELP, "process/maladireta.jsp", "Contato", "Auxiliares", Action.CATEGORY_PROCESS, false, true);
  static public final Action ACTION_ENDERECOS = new Action("malaDiretaEnderecos", "", "", "", "process/maladiretaenderecos.jsp", "", "", Action.CATEGORY_NONE, false, false);
  // nossos comandos
  static public final Command COMMAND_ADD_CONTATO    = new Command("addContato", "", "");
  static public final Command COMMAND_DELETE_CONTATO = new Command("deleteContato", "", "");
  static public final Command COMMAND_USE_PRODUCER   = ACTION.addCommand(new Command("useProducer", "Mala Direta", "Cria uma mala direta com base nos contatos que estão sendo exibidos."));
  static public final Command COMMAND_EXECUTE        = ACTION.addCommand(new Command(Command.COMMAND_EXECUTE, "Executar", "Executa o processo de " + ACTION.getCaption() + " com os parâmetros informados."));
  // nossas etapas do assistente
  static public final ProcessStep PROCESS_STEP_CONTATOS = new ProcessStep("wizardStepContatos", "Contatos", "Verifique os Contatos que deseja manter para criação da mala direta.");
  static public final ProcessStep PROCESS_STEP_FINAL    = new ProcessStep("wizardStepFinal", "Mala Direta", "A Mala Direta foi criada com sucesso. Utilize os grupos para envio de e-mail e a lista de endereços para criação de etiquetas.");
  // nossos parâmetros de usuário
  public Param USER_PARAM_EMPRESA_ID    = new Param("userParamEmpresaId", "Empresa Id", "", "", 4, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public Param USER_PARAM_CONTATO_ID    = new Param("userParamContatoId", "");
  public Param USER_PARAM_DELETE_INDEX  = new Param("userParamDeleteIndex", "");
  public Param USER_PARAM_ENDERECO_LIST = new Param("userParamEnderecoList", "");

  private MalaDiretaInfo malaDiretaInfo = null;

  /**
   * Construtor padrão.
   */
  public MalaDireta() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_ENDERECOS);
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
   * 'contatoId' para criação da Mala Direta.
   * @param contatoId int Contato Id
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de dados.
   */
  public void addEndereco(int contatoId) throws Exception {
    addEndereco(new int[]{contatoId});
  }

  /**
   * Adiciona os endereços associados aos Contatos representados por
   * 'contatoIdList' para criação da Mala Direta.
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
        // põe na lista
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
      if (((EnderecoInfo)vector.elementAt(i)).getId() == contatoId) {
        vector.remove(i);
        return;
      } // if
    } // for
  }

  /**
   * Retorna a representação HTML do hyperlink para chamada do processo de Mala
   * Direta utilizando o comando de reutilização de dados (COMMAND_USE_PRODUCER).
   * @param facade Facade Fachada.
   * @param disabled boolean True se o hyperlink deve estar desabilitado.
   * @return String Retorna a representação HTML do hyperlink para chamada do
   *                processo de Mala Direta utilizando o comando de reutilização
   *                de dados (COMMAND_USE_PRODUCER).
   */
  static public String commandUseProducerHyperlink(Facade  facade,
                                                   boolean disabled) {
    return Hyperlink.script(facade,
                            ACTION.getName(),
                            ACTION.getCaption(),
                            COMMAND_USE_PRODUCER.getDescription(),
                            ImageList.IMAGE_MAIL,
                            ACTION.url(COMMAND_USE_PRODUCER),
                            "",
                            "",
                            disabled || !facade.getLoggedUser().hasAccessRight(ACTION, COMMAND_USE_PRODUCER));
  }

  /**
   * Retorna um MalaDiretaInfo contendo a Mala Direta gerada a partir dos
   * Contatos selecionados.
   * @return Retorna um MalaDiretaInfo contendo a Mala Direta gerada a partir dos
   *         Contatos selecionados.
   * @throws Exception
   */
  public MalaDiretaInfo execute() throws Exception {
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
        throw new ExtendedException(getClass().getName(), "execute", "Nenhum Contato selecionado para a mala direta.");

      // lista de endereços
      StringBuffer enderecos = new StringBuffer();
      StringBuffer etiquetas = new StringBuffer();
      // grupos de e-mail
      Vector       vectorGrupos = new Vector();
      StringBuffer grupo        = new StringBuffer();
      int          count        = 0;
      // cabeçalho
      enderecos.append("Nome;Endereço;Numero;Complemento;Bairro;Cidade;UF;CEP;E-Mail\r\n");
      // loop nos endereços
      for (int i=0; i<vector.size(); i++) {
        // endereço da vez
        EnderecoInfo endereco = (EnderecoInfo)vector.elementAt(i);
        // adiciona o endereço
        enderecos.append(endereco.getNome().replace(';', ':') + ";"
                       + endereco.getEndereco().replace(';', ':') + ";"
                       + endereco.getNumero().replace(';', ':') + ";"
                       + endereco.getComplemento().replace(';', ':') + ";"
                       + endereco.getBairro().replace(';', ':') + ";"
                       + endereco.getCidade().replace(';', ':') + ";"
                       + endereco.getUf().replace(';', ':') + ";"
                       + endereco.getCep().replace(';', ':') + ";"
                       + endereco.getEmail()
                       + "\r\n");
        // adiciona etiquetas
        etiquetas.append("<b>" + endereco.getNome() + "</b><br />" +
                       endereco.getEndereco() + ", " + endereco.getNumero() + 
                      (endereco.getComplemento().trim().isEmpty() ? "" : endereco.getComplemento() + ",") + 
                       endereco.getBairro() + " - " + endereco.getCidade() + " - " + endereco.getUf().toUpperCase() + 
                       " - " + endereco.getCep() + "\r\n");
        // adiciona o e-mail no grupo atual
        String[] eMails = endereco.getEmail().split(";");
        for (int w=0; w<eMails.length; w++) {
          if (eMails[w].equals(""))
            continue;
          //grupo.append("\"" + endereco.getNome() + "\" &lt;" + eMails[w] + "&gt;; ");
          grupo.append(eMails[w] + ";");
          count++;
          // se alcançamos o máximo por grupo...inicia um novo
          if (count == 100) {
            vectorGrupos.add(grupo.toString());
            grupo = new StringBuffer();
            count = 0;
          } // if
        } // for
      } // for
      // adiciona o último grupo
      if (grupo.length() > 0)
        vectorGrupos.add(grupo.toString());
      // nosso resultado
      String[] gruposEmail = new String[vectorGrupos.size()];
      vectorGrupos.copyInto(gruposEmail);
      MalaDiretaInfo result = new MalaDiretaInfo(enderecos.toString(), etiquetas.toString(), gruposEmail);
      // guarda
      setMalaDiretaInfo(result);
      // apaga nossa lista temporária de endereços
      //vector.clear();
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
   * Retorna um vector contendo os endereços.
   * @return Retorna um vector contendo os endereços.
   */
  public Vector<EnderecoInfo> getEnderecoList() {
    return (Vector<EnderecoInfo>)USER_PARAM_ENDERECO_LIST.getObject();
  }

  /**
   * Retorna o MalaDiretaInfo obtido a partir da última execução do processo.
   * @return Retorna o MalaDiretaInfo obtido a partir da última execução do processo.
   */
  public MalaDiretaInfo getMalaDiretaInfo() {
    return malaDiretaInfo;
  }

  /**
   * Guarda o MalaDiretaInfo obtido a partir da execução do processo.
   * @param malaDiretaInfo MalaDiretaInfo obtido a partir da execução do processo.
   */
  public void setMalaDiretaInfo(MalaDiretaInfo malaDiretaInfo) {
    this.malaDiretaInfo = malaDiretaInfo;
  }

}
