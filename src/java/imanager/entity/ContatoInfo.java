    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*; 
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Contato.
 */
public class ContatoInfo extends EntityInfo {
  
  private int contatoId = 0;  
  private String nome = "";  
  private int grupoContatoId = 0;  
  private int tipoPessoa = 0;  
  private String rg = "";  
  private String cpf = "";  
  private Timestamp dataNascimento = DateTools.ZERO_DATE;  
  private int sexo = 0;  
  private String razaoSocial = "";  
  private String cnpj = "";  
  private String inscricaoEstadual = "";  
  private String inscricaoMunicipal = "";  
  private String endereco = "";  
  private String enderecoNumero = "";
  private String enderecoComplemento = "";
  private String enderecoBairro = "";
  private String cidade = "";  
  private String uf = "";  
  private String cep = "";  
  private String telefoneResidencial = "";
  private String telefoneCelular = "";
  private String telefoneTrabalho = "";
  private String email = "";  
  private int cliente = 0;  
  private int fornecedor = 0;  
  private int funcionario = 0;  
  private int vendedor = 0;  
  private int transportador = 0;  
  private double percentualComissao = 0.0D;  
  private double percentualIndicacao = 0.0D;  
  private String anotacoes = "";  
  private int arquivo = 0;  
  private int usuarioInclusaoId = 0;
  private Timestamp dataInclusao = DateTools.ZERO_DATE;  
  private int usuarioAlteracaoId = 0;
  private Timestamp dataHoraAlteracao = DateTools.ZERO_DATE;  
  private int usuarioCarteiraId = 0;
  private Timestamp dataHoraCarteira = DateTools.ZERO_DATE;
  private int codigoMunicipio = 0;
  private double percentualComissaoServico = 0.0D;
  private double percentualIndicacaoServico = 0.0D;
  private String usuarioFacebook = "";
  private String cro = "";

  /**
   * Construtor padrão.
   */
  public ContatoInfo() {
  }

  /**
   * Construtor estendido.
   * @param contatoId int Contato ID.
   * @param nome String Nome.
   * @param grupoContatoId int Grupo Contato ID.
   * @param tipoPessoa int Tipo Pessoa.
   * @param rg String RG.
   * @param cpf String CPF.
   * @param dataNascimento Timestamp Data Nascimento.
   * @param sexo int Sexo.
   * @param razaoSocial String Razão Social.
   * @param cnpj String CNPJ.
   * @param inscricaoEstadual String Inscrição Estadual.
   * @param inscricaoMunicipal String Inscrição Municipal.
   * @param endereco String Endereço.
   * @param enderecoNumero String Endereço Número.
   * @param enderecoComplemento String Endereço Complemento.
   * @param enderecoBairro String Endereço Bairro.
   * @param cidade String Cidade.
   * @param uf String UF.
   * @param cep String CEP.
   * @param telefoneResidencial String Telefone Residencial.
   * @param telefoneCelular String Telefone Celular.
   * @param telefoneTrabalho String Telefone Trabalho.
   * @param email String Email.
   * @param cliente int Cliente.
   * @param fornecedor int Fornecedor.
   * @param funcionario int Funcionário.
   * @param vendedor int Vendedor.
   * @param transportador int Transportador.
   * @param percentualComissao int Percentual Comissão.
   * @param percentualIndicacao int Percentual Indicação.
   * @param anotacoes String Anotações.
   * @param arquivo int Arquivo.
   * @param usuarioInclusaoId int Usuário Inclusão.
   * @param dataInclusao Timestamp Data Hora Inclusão.
   * @param usuarioAlteracaoId int Usuário Alteração.
   * @param dataHoraAlteracao Timestamp Data Hora Alteração.
   * @param usuarioCarteiraId int Usuário Carteira.
   * @param dataHoraCarteira Timestamp Data Hora Carteira.
   * @param codigoMunicipio int Código Municipio.
   * @param percentualComissaoServico double Percentual Comissão Servico;
   * @param percentualIndicaçãoServico double Percentual Indicação Servico;
   * @param usuarioFacebook String Usuário do Facebook;
   * @param cro String CRO.
   */
  public ContatoInfo(
           int contatoId,
           String nome,
           int grupoContatoId,
           int tipoPessoa,
           String rg,
           String cpf,
           Timestamp dataNascimento,
           int sexo,
           String razaoSocial,
           String cnpj,
           String inscricaoEstadual,
           String inscricaoMunicipal,
           String endereco,
           String enderecoNumero,
           String enderecoComplemento,
           String enderecoBairro,
           String cidade,
           String uf,
           String cep,
           String telefoneResidencial,
           String telefoneCelular,
           String telefoneTrabalho,
           String email,
           int cliente,
           int fornecedor,
           int funcionario,
           int vendedor,
           int transportador,
           double percentualComissao,
           double percentualIndicacao,
           String anotacoes,
           int arquivo,
           int usuarioInclusaoId,
           Timestamp dataInclusao,
           int usuarioAlteracaoId,
           Timestamp dataHoraAlteracao,
           int usuarioCarteiraId,
           Timestamp dataHoraCarteira,
           int codigoMunicipio,
           double percentualComissaoServico,
           double percentualIndicacaoServico,
           String usuarioFacebook,
           String cro
         ) {
    // guarda nossos dados
    this.contatoId = contatoId;
    this.nome = nome;
    this.grupoContatoId = grupoContatoId;
    this.tipoPessoa = tipoPessoa;
    this.rg = rg;
    this.cpf = cpf;
    this.dataNascimento = dataNascimento;
    this.sexo = sexo;
    this.razaoSocial = razaoSocial;
    this.cnpj = cnpj;
    this.inscricaoEstadual = inscricaoEstadual;
    this.inscricaoMunicipal = inscricaoMunicipal;
    this.endereco = endereco;
    this.enderecoNumero = enderecoNumero;
    this.enderecoComplemento = enderecoComplemento;
    this.enderecoBairro = enderecoBairro;
    this.cidade = cidade;
    this.uf = uf;
    this.cep = cep;
    this.telefoneResidencial = telefoneResidencial;
    this.telefoneCelular = telefoneCelular;
    this.telefoneTrabalho = telefoneTrabalho;
    this.email = email;
    this.cliente = cliente;
    this.fornecedor = fornecedor;
    this.funcionario = funcionario;
    this.vendedor = vendedor;
    this.transportador = transportador;
    this.percentualComissao = percentualComissao;
    this.percentualIndicacao = percentualIndicacao;
    this.anotacoes = anotacoes;
    this.arquivo = arquivo;
    this.usuarioInclusaoId = usuarioInclusaoId;
    this.dataInclusao = dataInclusao;
    this.usuarioAlteracaoId = usuarioAlteracaoId;
    this.dataHoraAlteracao = dataHoraAlteracao;
    this.usuarioCarteiraId = usuarioCarteiraId;
    this.dataHoraCarteira = dataHoraCarteira;
    this.codigoMunicipio = codigoMunicipio;
    this.percentualComissaoServico = percentualComissaoServico;
    this.percentualIndicacaoServico = percentualIndicacaoServico;
    this.usuarioFacebook = usuarioFacebook;
    this.cro = cro;
  }
  
  public int getContatoId() {
    return contatoId;
  }
  
  public String getNome() {
    return nome;
  }
  
  public int getGrupoContatoId() {
    return grupoContatoId;
  }
  
  public int getTipoPessoa() {
    return tipoPessoa;
  }
  
  public String getRg() {
    return rg;
  }
  
  public String getCpf() {
    return cpf;
  }
  
  public Timestamp getDataNascimento() {
    return dataNascimento;
  }
  
  public int getSexo() {
    return sexo;
  }
  
  public String getRazaoSocial() {
    return razaoSocial;
  }
  
  public String getCnpj() {
    return cnpj;
  }
  
  public String getInscricaoEstadual() {
    return inscricaoEstadual;
  }
  
  public String getInscricaoMunicipal() {
    return inscricaoMunicipal;
  }
  
  public String getEndereco() {
    return endereco;
  }
  
  public String getEnderecoNumero() {
    return enderecoNumero;
  }

  public String getEnderecoComplemento() {
    return enderecoComplemento;
  }

  public String getEnderecoBairro() {
    return enderecoBairro;
  }

  public String getCidade() {
    return cidade;
  }
  
  public String getUf() {
    return uf;
  }
  
  public String getCep() {
    return cep;
  }
  
  public String getTelefoneResidencial() {
    return telefoneResidencial;
  }
  
  public String getTelefoneCelular() {
    return telefoneCelular;
  }

  public String getTelefoneTrabalho() {
    return telefoneTrabalho;
  }

  public String getEmail() {
    return email;
  }
  
  public int getCliente() {
    return cliente;
  }
  
  public int getFornecedor() {
    return fornecedor;
  }
  
  public int getFuncionario() {
    return funcionario;
  }
  
  public int getVendedor() {
    return vendedor;
  }
  
  public int getTransportador() {
    return transportador;
  }
  
  public double getPercentualComissao() {
    return percentualComissao;
  }
  
  public double getPercentualIndicacao() {
    return percentualIndicacao;
  }
  
  public String getAnotacoes() {
    return anotacoes;
  }
  
  public int getArquivo() {
    return arquivo;
  }
  
  public int getUsuarioInclusaoId() {
    return usuarioInclusaoId;
  }
  
  public Timestamp getDataInclusao() {
    return dataInclusao;
  }
  
  public int getUsuarioAlteracaoId() {
    return usuarioAlteracaoId;
  }
  
  public Timestamp getDataHoraAlteracao() {
    return dataHoraAlteracao;
  }
     
  public int getUsuarioCarteiraId() {
    return usuarioCarteiraId;
  }

  public Timestamp getDataHoraCarteira() {
    return dataHoraCarteira;
  }
  
  public int getCodigoMunicipio() {
    return codigoMunicipio;
  }
  
  public double getPercentualComissaoServico(){
    return percentualComissaoServico;
  }
  
  public double getPercentualIndicacaoServico(){
    return percentualIndicacaoServico;
  }
  
  public String getUsuarioFacebook() {
    return usuarioFacebook;
  }
  
  public String getCro() {
    return cro;
  }
  
  public void setContatoId(int contatoId) {
    this.contatoId = contatoId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public void setGrupoContatoId(int grupoContatoId) {
    this.grupoContatoId = grupoContatoId;
  }
  
  public void setTipoPessoa(int tipoPessoa) {
    this.tipoPessoa = tipoPessoa;
  }
  
  public void setRg(String rg) {
    this.rg = rg;
  }
  
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  
  public void setDataNascimento(Timestamp dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
  
  public void setSexo(int sexo) {
    this.sexo = sexo;
  }
  
  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }
  
  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }
  
  public void setInscricaoEstadual(String inscricaoEstadual) {
    this.inscricaoEstadual = inscricaoEstadual;
  }
  
  public void setInscricaoMunicipal(String inscricaoMunicipal) {
    this.inscricaoMunicipal = inscricaoMunicipal;
  }
  
  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }
  
  public void setEnderecoNumero(String enderecoNumero) {
    this.enderecoNumero = enderecoNumero;
  }

  public void setEnderecoComplemento(String enderecoComplemento) {
    this.enderecoComplemento = enderecoComplemento;
  }

  public void setEnderecoBairro(String enderecoBairro) {
    this.enderecoBairro = enderecoBairro;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }
  
  public void setUf(String uf) {
    this.uf = uf;
  }
  
  public void setCep(String cep) {
    this.cep = cep;
  }
  
  public void setTelefoneResidencial(String telefoneResidencial) {
    this.telefoneResidencial = telefoneResidencial;
  }
  
  public void setTelefoneCelular(String telefoneCelular) {
    this.telefoneCelular = telefoneCelular;
  }

  public void setTelefoneTrabalho(String telefoneTrabalho) {
    this.telefoneTrabalho = telefoneTrabalho;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setCliente(int cliente) {
    this.cliente = cliente;
  }
  
  public void setFornecedor(int fornecedor) {
    this.fornecedor = fornecedor;
  }
  
  public void setFuncionario(int funcionario) {
    this.funcionario = funcionario;
  }
  
  public void setVendedor(int vendedor) {
    this.vendedor = vendedor;
  }
  
  public void setTransportador(int transportador) {
    this.transportador = transportador;
  }
  
  public void setPercentualComissao(double percentualComissao) {
    this.percentualComissao = percentualComissao;
  }
  
  public void setPercentualIndicacao(double percentualIndicacao) {
    this.percentualIndicacao = percentualIndicacao;
  }
  
  public void setAnotacoes(String anotacoes) {
    this.anotacoes = anotacoes;
  }
  
  public void setArquivo(int arquivo) {
    this.arquivo = arquivo;
  }
  
  public void setUsuarioInclusaoId(int usuarioInclusaoId) {
    this.usuarioInclusaoId = usuarioInclusaoId;
  }
  
  public void setDataInclusao(Timestamp dataInclusao) {
    this.dataInclusao = dataInclusao;
  }
  
  public void setUsuarioAlteracaoId(int usuarioAlteracaoId) {
    this.usuarioAlteracaoId = usuarioAlteracaoId;
  }
  
  public void setDataHoraAlteracao(Timestamp dataHoraAlteracao) {
    this.dataHoraAlteracao = dataHoraAlteracao;
  }
  
  public void setUsuarioCarteiraId(int usuarioCarteiraId) {
    this.usuarioCarteiraId = usuarioCarteiraId;
  }

  public void setDataHoraCarteira(Timestamp dataHoraCarteira) {
    this.dataHoraCarteira = dataHoraCarteira;
  }

  public void setCodigoMunicipio(int codigoMunicipio) {
    this.codigoMunicipio = codigoMunicipio;
  }
  
  public void setPercentualComissaoServico(double percentualComissaoServico){
    this.percentualComissaoServico = percentualComissaoServico;
  }
  
  public void setPercentualIndicacaoServico(double percentualIndicacaoServico){
    this.percentualIndicacaoServico = percentualIndicacaoServico;
  }
  
  public void setUsuarioFacebook(String usuarioFacebook) {
    this.usuarioFacebook = usuarioFacebook;
  }
  
  public void setCro(String cro) {
    this.cro = cro;
  }
  
}
