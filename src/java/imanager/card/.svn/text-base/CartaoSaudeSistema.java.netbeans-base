   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cartão da Saúde do Sistema.
 */
public class CartaoSaudeSistema extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoSaudeSistema";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoSaudeSistema", "Saúde do Sistema", "Exibe os Contatos com aniversário se aproximando.", HELP, "card/cartaosaudesistema.jsp", "Global", "", Action.CATEGORY_CARD, false, false);

  /**
   * Construtor padrão.
   */
  public CartaoSaudeSistema() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna um Double contendo a porcentagem de registros sem data
   * de nascimento.
   * <ol>
   *   <li>relacionamento_contato.da_nascimento
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return double Retorna um double contendo a porcentagem de registros sem
   * data de nascimento.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public boolean getPorcentagemDataNascimento() throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    // nosso resultSet
    ResultSet resultSet = null;
    try {
      // SQL
      String sql = " SELECT ((SELECT COUNT(da_nascimento) FROM relacionamento_contato WHERE da_nascimento = '" + DateTools.ZERO_DATE + "' AND sm_cliente = " + Personalidade.CLIENTE + " )*100)/count(da_nascimento) as resultado"
                 + " FROM relacionamento_contato "
                 + " WHERE sm_cliente = " + Personalidade.CLIENTE + " " ;
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // executa
      preparedStatement.execute();
      // nosso resultSet
      resultSet = preparedStatement.getResultSet();
      resultSet.next();
      // nosso resultado
      boolean result = false;
      // verifica se o resultado é maior que 5%
      if (resultSet.getDouble("resultado") > 5)
        result = true;
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
