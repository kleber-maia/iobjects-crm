package imanager.process;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;

import imanager.entity.*;

/**
 * Utilitário para reaproveitamento de dados em consultas que envolvam
 * Contatos.
 */
public class ContatoProducer {

  static public final String PARAM_PRODUCER_NAME = "ContatoProducer";

  private Vector vector = new Vector();

  private ContatoProducer() {
  }

  /**
   * Adiciona o Contato indicado por 'contatoId' na lista para criação
   * da Mala Direta.
   * @param contatoId int Contato Id.
   */
  public void add(int contatoId) {
    // adiciona na lista
    vector.add(new Integer(contatoId));
  }

  /**
   * Adiciona a coleção de Contatos contidos em 'entityInfoList' na lista
   * para criação da Mala Direta.
   * @param entityInfoList EntityInfo[] contendo os EntityInfo´s cujos dados
   *                       serão reaproveitados. <b>Obs: uma das propriedades
   *                       dos EntityInfo´s deve ter o nome igual a
   *                       Contato.FIELD_CONTATO_ID.getFieldAlias()</b>.
   * @throws Exception Em caso de exceção na tentativa de obter a propriedade
   *                   'contatoId' dos EntityInfo´s.
   */
  public void addCollection(EntityInfo[] entityInfoList) throws Exception {
    addCollection(entityInfoList, Contato.FIELD_CONTATO_ID.getFieldAlias());
  }

  /**
   * Limpa a lista e adiciona a coleção de Contatos contidos em 'entityInfoList'.
   * @param entityInfoList EntityInfo[] contendo os EntityInfo´s cujos dados
   *                       serão reaproveitados.
   * @param propertyName Nome da propriedade nos EntityInfo´s que representa o
   *                     Contato Id.
   * @throws Exception Em caso de exceção na tentativa de obter a propriedade
   *                   'contatoId' dos EntityInfo´s.
   */
  public void addCollection(EntityInfo[] entityInfoList,
                            String       propertyName) throws Exception {
    // limpa a lista
    clear();
    // loop na lista
    for (int i=0; i<entityInfoList.length; i++) {
      // Info da vez
      EntityInfo entityInfo = entityInfoList[i];
      // adiciona o Contato
      add(((Integer)entityInfo.getPropertyValue(propertyName)).intValue());
    } // for
  }

  /**
   * Limpa a lista e adiciona a coleção de Contatos contidos em 'resultSet'.
   * @param resultSet ResultSet Um dos campos deve ser Contato Id.
   * @param fieldName String Nome do campo que representa o Contato Id.
   * @throws Exception Em caso de exceção na tentativa de acesso ao ResultSet.
   */
  public void addCollection(ResultSet resultSet,
                            String    fieldName) throws Exception {
    // limpa a lista
    clear();
    // loop na lista
    while (resultSet.next()) {
      // adiciona o Contato
      add(resultSet.getInt(fieldName));
    } // while
  }

  /**
   * Limpa a lista de Contatos.
   */
  public void clear() {
    vector.clear();
  }

  /**
   * Retorna um int[] contendo os Contato Id´s adicionados no Producer.
   * @return int[] Retorna um int[] contendo os Contato Id´s
   *         adicionados no Producer.
   */
  public int[] getContatoIdList() {
    // nosso resultado
    int[] result = new int[vector.size()];
    // copia
    for (int i=0; i<result.length; i++)
      result[i] = ((Integer)vector.elementAt(i)).intValue();
    // retorna
    return result;
  }

  /**
   * Retorna a instância de ContatoProducer para ser usado em 'facade'.
   * @param facade Facade.
   * @return Retorna a instância de ContatoProducer para ser usado em 'facade'.
   */
  static public ContatoProducer getInstance(Facade facade) {
    // procura Producer na Facade
    Param param = facade.userParamList().get(PARAM_PRODUCER_NAME);
    // se ainda não existe...cria
    if (param == null) {
      ContatoProducer result = new ContatoProducer();
      param = new Param(PARAM_PRODUCER_NAME, result);
      facade.userParamList().add(param);
      return result;
    }
    // se existe...retorna
    else
      return (ContatoProducer)param.getObject();
  }

}
