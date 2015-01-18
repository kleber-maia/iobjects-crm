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
   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cart�o de Contato Aniversariante.
 */
public class CartaoContatoAniversariante extends Card {

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.card.CartaoContatoAniversariante";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION = new Action("cartaoContatoAniversariante", "Aniversariantes", "Exibe os Contatos com anivers�rio se aproximando.", HELP, "card/cartaocontatoaniversariante.jsp", "Contato", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padr�o.
   */
  public CartaoContatoAniversariante() {
    // nossas a��es
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os Contatos que realizam anivers�rio ou 
   * anivers�rio de cadastro nos pr�ximos 3 dias.
   * <ol>
   *   <li>relacionamento_contato.in_contato_id
   *   <li>relacionamento_contato.va_nome
   *   <li>relacionamento_contato.ch_telefone_residencial
   *   <li>relacionamento_contato.ch_telefone_celular
   *   <li>relacionamento_contato.ch_telefone_trabalho
   *   <li>relacionamento_contato.da_nascimento
   *   <li>relacionamento_contato.da_inclusao
   *   <li>aniversario
   *   <li>usuario_carteira
   * </ol>
   * <b>Importante: a rotina que executar este m�todo deve ser respons�vel por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Contatos que realizam
   *         anivers�rio ou anivers�rio de cadastro nos pr�ximos 7 dias.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetCartaoContatoAniversariante() throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT in_contato_id, relacionamento_contato.va_nome, ch_telefone_residencial, ch_telefone_celular, ch_telefone_trabalho, da_nascimento, TO_CHAR(da_nascimento, 'MMDD') as aniversario, securityservice_usuario.va_nome as usuario_carteira, relacionamento_grupo_contato.va_nome as grupo "
                 + "FROM relacionamento_contato "
                 + "INNER JOIN securityservice_usuario ON (securityservice_usuario.in_usuario_id = relacionamento_contato.in_usuario_carteira_id) "
                 + "INNER JOIN relacionamento_grupo_contato ON (relacionamento_contato.in_grupo_contato_id = relacionamento_grupo_contato.in_grupo_contato_id) "
                 + "WHERE ((TO_CHAR(da_nascimento, 'MMDD') >= ?) AND (TO_CHAR(da_nascimento, 'MMDD') <= ?)) "
                 +   "AND (sm_tipo_pessoa = " + TipoPessoa.FISICA + ")"
                 +   "AND (sm_arquivo = " + NaoSim.NAO + ")"
                 + "ORDER BY usuario_carteira, aniversario, relacionamento_contato.va_nome ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // datas consideradas
      String[] today    = DateTools.splitDate(DateTools.getActualDate());
      String[] nextDays = DateTools.splitDate(DateTools.getCalculatedDays(2));
      // preenche os par�metros
      preparedStatement.setString(1, today[1] + today[0]);
      preparedStatement.setString(2, nextDays[1] + nextDays[0]);
      // executa
      preparedStatement.execute();
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      return preparedStatement.getResultSet();
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

}
