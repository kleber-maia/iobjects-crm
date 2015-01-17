   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.entity.*;
import imanager.misc.*;
import iobjects.entity.Paginate; 

/**
 * Representa o cartão de Atendimento Recente.
 */
public class CartaoAtendimentoRecente extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAtendimentoRecente";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoAtendimentoRecente", "Atendimentos Recentes", "Exibe os Atendimentos Recentes do Usuário.", HELP, "card/cartaoatendimentorecente.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padrão.
   */
  public CartaoAtendimentoRecente() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna um AtendimentoInfo[] referente aos Atendimentos Recentes identificados
   * pela Empresa e Usuário.
   * @return Retorna um AtendimentoInfo[] referente aos Atendimentos Recentes
   *         identificados pela Empresa e Usuário.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AtendimentoInfo[] getAtendimentoRecente(int empresaId, int usuarioId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // nossa instância de Atendimento
      Atendimento atendimento = (Atendimento)getFacade().getEntity(Atendimento.CLASS_NAME);
      // prepara a consulta
      String[] orderFieldNames = {Atendimento.FIELD_ATENDIMENTO_ID.getFieldName(atendimento.getTableName())};
      PreparedStatement statement = atendimento.prepareSelect(
                                                              orderFieldNames,
                                                              "(" + Atendimento.FIELD_EMPRESA_ID.getFieldName(atendimento.getTableName()) + " = ?) AND " +
                                                              "(" + Atendimento.FIELD_USUARIO_INCLUSAO_ID.getFieldName(atendimento.getTableName()) + " = ?) ",
                                                              new String[0],
                                                              new Paginate(20, 0)
                                                            );
      statement.setInt(1, empresaId);
      statement.setInt(2, usuarioId);
      // nosso resultado
      AtendimentoInfo[] result = (AtendimentoInfo[])atendimento.select(statement);
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

}
