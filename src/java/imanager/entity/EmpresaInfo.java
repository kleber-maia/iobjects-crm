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

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informa��es contidas pela entidade Empresa.
 */
public class EmpresaInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private String nome = "";  
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
  private String telefone = "";  
  private String email = "";  
  private int estoque = 0;
  private int consolidadora = 0;  
  private String servidorSmtp = "";  
  private int portaSmtp = 0;  
  private int requerSslSmtp = 0;
  private String usuarioEmail = "";  
  private String senhaEmail = "";  
  private int copiaEmail = 0;  
  private int contatoContadorId = 0;
  private String contadorCRC = "";
  private int arquivo = 0;  
  private int usuarioInclusaoId = 0;
  private Timestamp dataInclusao = DateTools.ZERO_DATE;  
  private int usuarioAlteracaoId = 0;
  private Timestamp dataHoraAlteracao = DateTools.ZERO_DATE;
  private int serieDocumento = 0;
  private int numeroDocumentoFiscal = 0;
  private int codigoMunicipio = 0;
  private int identificacaoAmbiente = 0;
  private int regimeTributario = 0;
  private int utilizaPaf = 0;
  private String cnae = "";
  
  /**
   * Construtor padr�o.
   */
  public EmpresaInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param nome String Nome.
   * @param razaoSocial String Raz�o Social.
   * @param cnpj String CNPJ.
   * @param inscricaoEstadual String Inscri��o Estadual.
   * @param inscricaoMunicipal String Inscri��o Municipal.
   * @param endereco String Endere�o.
   * @param enderecoNumero String Endere�o N�mero.
   * @param enderecoComplemento String Endere�o Complemento.
   * @param enderecoBairro String Endere�o Bairro.
   * @param cidade String Cidade.
   * @param uf String UF.
   * @param cep String CEP.
   * @param telefone String Telefone.
   * @param email String Email.
   * @param estoque int Estoque.
   * @param consolidadora int Consolidadora.
   * @param servidorSmtp String Servidor SMTP.
   * @param portaSmtp int Porta SMTP.
   * @param requerSslSmtp int Request SSL SMTP.
   * @param usuarioEmail String Usu�rio Email.
   * @param senhaEmail String Senha Email.
   * @param copiaEmail int C�pia Email.
   * @param contatoContadorId int Contado Contador Id.
   * @param contadorCRC String Contador CRC.
   * @param arquivo int Arquivo.
   * @param usuarioInclusaoId int Usu�rio Inclus�o.
   * @param dataInclusao Timestamp Data Hora Inclus�o.
   * @param usuarioAlteracaoId int Usu�rio Altera��o.
   * @param dataHoraAlteracao Timestamp Data Hora Altera��o.
   * @param serieDocumento int S�rie Documento.
   * @param numeroDocumentoFiscal int N�mero Documento Fiscal.
   * @param codigoMunicipio int C�digo Munic�pio.
   * @param identificacaoAmbiente int Identifica��o Ambiente.
   * @param regimeTributario int Regime Tribut�rio
   */
  public EmpresaInfo(
           int empresaId,
           String nome,
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
           String telefone,
           String email,
           int estoque,
           int consolidadora,
           String servidorSmtp,
           int portaSmtp,
           int requerSslSmtp,
           String usuarioEmail,
           String senhaEmail,
           int copiaEmail,
           int contatoContadorId,
           String contadorCRC,
           int arquivo,
           int usuarioInclusaoId,
           Timestamp dataInclusao,
           int usuarioAlteracaoId,
           Timestamp dataHoraAlteracao,
           int serieDocumento,
           int numeroDocumentoFiscal,
           int codigoMunicipio,
           int identificacaoAmbiente,
           int regimeTributario
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.nome = nome;
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
    this.telefone = telefone;
    this.email = email;
    this.estoque = estoque;
    this.consolidadora = consolidadora;
    this.servidorSmtp = servidorSmtp;
    this.portaSmtp = portaSmtp;
    this.requerSslSmtp = requerSslSmtp;
    this.usuarioEmail = usuarioEmail;
    this.senhaEmail = senhaEmail;
    this.copiaEmail = copiaEmail;
    this.contatoContadorId = contatoContadorId;
    this.contadorCRC = contadorCRC;
    this.arquivo = arquivo;
    this.usuarioInclusaoId = usuarioInclusaoId;
    this.dataInclusao = dataInclusao;
    this.usuarioAlteracaoId = usuarioAlteracaoId;
    this.dataHoraAlteracao = dataHoraAlteracao;
    this.serieDocumento = serieDocumento;
    this.numeroDocumentoFiscal = numeroDocumentoFiscal;
    this.codigoMunicipio = codigoMunicipio;
    this.identificacaoAmbiente = identificacaoAmbiente;
    this.regimeTributario = regimeTributario;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public String getNome() {
    return nome;
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
  
  public String getTelefone() {
    return telefone;
  }
  
  public String getEmail() {
    return email;
  }
  
  public int getEstoque() {
    return estoque;
  }

  public int getConsolidadora() {
    return consolidadora;
  }
  
  public String getServidorSmtp() {
    return servidorSmtp;
  }
  
  public int getPortaSmtp() {
    return portaSmtp;
  }
  
  public int getRequerSslSmtp() {
    return requerSslSmtp;
  }
  
  public String getUsuarioEmail() {
    return usuarioEmail;
  }
  
  public String getSenhaEmail() {
    return senhaEmail;
  }
  
  public int getCopiaEmail() {
    return copiaEmail;
  }
  
  public int getContatoContadorId() {
    return contatoContadorId;
  }
  
  public String getContadorCRC() {
    return contadorCRC;
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
  
  public int getSerieDocumento() {
    return serieDocumento;
  }
  
  public int getNumeroDocumentoFiscal() {
    return numeroDocumentoFiscal;
  }
  
  public int getCodigoMunicipio() {
    return codigoMunicipio;
  }
  
  public int getIdentificacaoAmbiente() {
    return identificacaoAmbiente;
  }
  
  public int getRegimeTributario() {
    return regimeTributario;
  }

  public int getUtilizaPaf() {
    return utilizaPaf;
  }  
  
  public String getCnae() {
    return cnae;
  }    
  
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
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
  
  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setEstoque(int estoque) {
    this.estoque = estoque;
  }

  public void setConsolidadora(int consolidadora) {
    this.consolidadora = consolidadora;
  }
  
  public void setServidorSmtp(String servidorSmtp) {
    this.servidorSmtp = servidorSmtp;
  }
  
  public void setPortaSmtp(int portaSmtp) {
    this.portaSmtp = portaSmtp;
  }
  
  public void setRequerSslSmtp(int requestSslSmtp) {
    this.requerSslSmtp = requestSslSmtp;
  }
  
  public void setUsuarioEmail(String usuarioEmail) {
    this.usuarioEmail = usuarioEmail;
  }
  
  public void setSenhaEmail(String senhaEmail) {
    this.senhaEmail = senhaEmail;
  }
  
  public void setCopiaEmail(int copiaEmail) {
    this.copiaEmail = copiaEmail;
  }
  
  public void setContatoContadorId(int contatoContadorId) {
    this.contatoContadorId = contatoContadorId;
  }
  
  public void setContadorCRC(String contadorCRC) {
    this.contadorCRC = contadorCRC;
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
  
  public void setSerieDocumento(int serieDocumento) {
    this.serieDocumento = serieDocumento;
  }
  
  public void setNumeroDocumentoFiscal(int numeroDocumentoFiscal) {
    this.numeroDocumentoFiscal = numeroDocumentoFiscal;
  }
  
  public void setCodigoMunicipio(int codigoMunicipio) {
    this.codigoMunicipio = codigoMunicipio;
  }
  
  public void setIdentificacaoAmbiente(int identificacaoAmbiente) {
    this.identificacaoAmbiente = identificacaoAmbiente;
  }
  
  public void setRegimeTributario(int regimeTributario) {
    this.regimeTributario = regimeTributario;
  }

  public void setUtilizaPaf(int utilizaPaf) {
    this.utilizaPaf = utilizaPaf;
  }
  
  public void setCnae(String cnae) {
    this.cnae = cnae;
  }      
  
}
