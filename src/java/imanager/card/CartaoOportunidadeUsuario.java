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
 * Representa o cart�o de Oportunidade Usu�rio.
 */
public class CartaoOportunidadeUsuario extends Card {
 
  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.card.CartaoOportunidadeUsuario";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION = new Action("cartaoOportunidadeUsuario", "Oportunidades por Usu�rio", "Exibe as Oportunidade por Usu�rio.", HELP, "card/cartaooportunidadeusuario.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padr�o.
   */
  public CartaoOportunidadeUsuario() {
    // nossas a��es
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo as Oportunidade por Usu�rio.
   * <ol>
   *   <li>securityservice_usuario.va_nome
   *   <li>quantidade de oportunidades
   *   <li>crm_oportunidade.in_usuario_id
   * </ol>
   * <b>Importante: a rotina que executar este m�todo deve ser respons�vel por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Usu�rios e as
   *         quantidades de Tarefas no m�s.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetOportunidadeUsuario(int empresaId) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT securityservice_usuario.in_usuario_id,"
                 + "securityservice_usuario.va_nome, "
                 + "crm_oportunidade.in_oportunidade_id, "
                 + "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "crm_oportunidade.da_acompanhamento, "
                 + "crm_oportunidade.va_descricao "
                 + "FROM crm_oportunidade "
                 + "INNER JOIN securityservice_usuario ON (crm_oportunidade.in_usuario_id = securityservice_usuario.in_usuario_id) "
                 + "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_oportunidade.in_cliente_id) "              
                 + "WHERE in_empresa_id = ? "
                 + "AND crm_oportunidade.sm_status = " + StatusOportunidade.EM_ANDAMENTO + " "
                 + "group by securityservice_usuario.in_usuario_id, securityservice_usuario.va_nome, crm_oportunidade.in_oportunidade_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, crm_oportunidade.da_acompanhamento, crm_oportunidade.va_descricao  "
                 + "ORDER BY securityservice_usuario.va_nome, securityservice_usuario.in_usuario_id, crm_oportunidade.in_oportunidade_id";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os par�metros
      preparedStatement.setInt(1, empresaId);
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
