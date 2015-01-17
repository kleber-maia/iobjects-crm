package imanager.ui.entity;

import iobjects.*;
import iobjects.entity.*;
import iobjects.ui.*;
import iobjects.ui.entity.*;

import imanager.entity.*;
import imanager.misc.*;

/**
 * Utilit�rio para cria��o de controles para a classe Contato.
 */
public class ContatoUI {

  /**
   * Retorna a representa��o JavaScript de um LookupSearch contendo valores da
   * entidade Contato.
   * @param facade Fachada.
   * @param param Param cujas propriedades ir�o gerar o controle de sele��o.
   * @param cliente Nao ou Sim para que os Clientes sejam retornados.
   * @param fornecedor Nao ou Sim para que os Fornecedores sejam retornados.
   * @param funcionario Nao ou Sim para que os Funcion�rios sejam retornados.
   * @param vendedor Nao ou Sim para que os Vendedores sejam retornados.
   * @param transportador Nao ou Sim para que os Transportadores sejam retornados.
   * @param width int Largura do controle de sele��o na p�gina ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formata��o do ExternalLookup.
   * @param onChangeScript C�digo JavaScript para ser executado quando o usu�rio
   *                       alterar o valor do elemento HTML.
   * @param readOnly True para apenas leitura.
   * @return Retorna a representa��o JavaScript de um LookupSearch contendo valores da
   * entidade Contato.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de dados.
   */
  static public String lookupSearchForParam(Facade  facade,
                                            Param   param,
                                            int     cliente,
                                            int     fornecedor,
                                            int     funcionario,
                                            int     vendedor,
                                            int     transportador,
                                            int     width,
                                            String  style,
                                            String  onChangeScript,
                                            boolean readOnly) throws Exception {
    // retorna
    return LookupSearch.script(facade,
                               param.getName(),
                               Contato.CLASS_NAME,
                               new EntityField[]{Contato.FIELD_NOME, Contato.FIELD_RAZAO_SOCIAL, Contato.FIELD_CPF, Contato.FIELD_CNPJ},
                               Contato.FIELD_NOME,
                               "",
                               "(" + Contato.FIELD_ARQUIVO.getFieldName() + " = " + NaoSim.NAO + ") AND " +
                               "((" + Contato.FIELD_CLIENTE.getFieldName() + " = " + cliente + ") OR (" + cliente + " = " + NaoSim.TODOS + ")) AND " +
                               "((" + Contato.FIELD_FORNECEDOR.getFieldName() + " = " + fornecedor + ") OR (" + fornecedor + " = " + NaoSim.TODOS + ")) AND " +
                               "((" + Contato.FIELD_FUNCIONARIO.getFieldName() + " = " + funcionario + ") OR (" + funcionario + " = " + NaoSim.TODOS + ")) AND " +
                               "((" + Contato.FIELD_VENDEDOR.getFieldName() + " = " + vendedor + ") OR (" + vendedor + " = " + NaoSim.TODOS + ")) AND " +
                               "((" + Contato.FIELD_TRANSPORTADOR.getFieldName() + " = " + transportador + ") OR (" + transportador + " = " + NaoSim.TODOS + "))",
                               Contato.FIELD_CONTATO_ID,
                               param.getValue(),
                               param.getScriptConstraint(),
                               param.getScriptConstraintErrorMessage(),
                               width,
                               style,
                               onChangeScript,
                               readOnly);
  }

  /**
   * Retorna a representa��o JavaScript de um LookupSearch contendo valores da
   * entidade Contato.
   * @param facade Fachada.
   * @param entityLookup EntityLookup cujas propriedades ir�o gerar o controle de sele��o.
   * @param entityInfo EntityInfo contendo os valores do registro que est� sendo
   *                   editado/inserido.
   * @param cliente Nao ou Sim para que os Clientes sejam retornados.
   * @param fornecedor Nao ou Sim para que os Fornecedores sejam retornados.
   * @param funcionario Nao ou Sim para que os Funcion�rios sejam retornados.
   * @param vendedor Nao ou Sim para que os Vendedores sejam retornados.
   * @param transportador Nao ou Sim para que os Transportadores sejam retornados.
   * @param width int Largura do controle de sele��o na p�gina ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formata��o do ExternalLookup.
   * @param onChangeScript C�digo JavaScript para ser executado quando o usu�rio
   *                       alterar o valor do elemento HTML.
   * @param readOnly True para apenas leitura.
   * @return Retorna a representa��o JavaScript de um LookupSearch contendo valores da
   * entidade Contato.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de dados.
   */
  static public String lookupSearchForLookup(Facade       facade,
                                             EntityLookup entityLookup,
                                             EntityInfo   entityInfo,
                                             int          cliente,
                                             int          fornecedor,
                                             int          funcionario,
                                             int          vendedor,
                                             int          transportador,
                                             int          width,
                                             String       style,
                                             String       onChangeScript,
                                             boolean      readOnly) throws Exception {
    // obt�m Contato ID
    Integer contatoId = (Integer)entityInfo.getPropertyValue(entityLookup.getKeyFields()[0].getFieldAlias());
    // retorna
    return LookupSearch.script(facade,
                               entityLookup.getKeyFields()[0].getFieldAlias(),
                               Contato.CLASS_NAME,
                               new EntityField[]{Contato.FIELD_NOME, Contato.FIELD_RAZAO_SOCIAL, Contato.FIELD_CPF, Contato.FIELD_CNPJ},
                               Contato.FIELD_NOME,
                               "",
                               "(" + Contato.FIELD_ARQUIVO.getFieldName() + " = " + NaoSim.NAO + ") AND " +
                               "((" + Contato.FIELD_CLIENTE.getFieldName() + " = " + cliente + ") OR (" + cliente + " = " + NaoSim.TODOS + ")) AND " +
                               "((" + Contato.FIELD_FORNECEDOR.getFieldName() + " = " + fornecedor + ") OR (" + fornecedor + " = " + NaoSim.TODOS + ")) AND " +
                               "((" + Contato.FIELD_FUNCIONARIO.getFieldName() + " = " + funcionario + ") OR (" + funcionario + " = " + NaoSim.TODOS + ")) AND " +
                               "((" + Contato.FIELD_VENDEDOR.getFieldName() + " = " + vendedor + ") OR (" + vendedor + " = " + NaoSim.TODOS + ")) AND " +
                               "((" + Contato.FIELD_TRANSPORTADOR.getFieldName() + " = " + transportador + ") OR (" + transportador + " = " + NaoSim.TODOS + "))",
                               Contato.FIELD_CONTATO_ID,
                               (contatoId > 0 ? contatoId + "" : ""),
                               entityLookup.getKeyFields()[0].getScriptConstraint(),
                               entityLookup.getKeyFields()[0].getScriptConstraintErrorMessage(),
                               width,
                               style,
                               onChangeScript,
                               readOnly);
  }

  private ContatoUI() {
  }

}
