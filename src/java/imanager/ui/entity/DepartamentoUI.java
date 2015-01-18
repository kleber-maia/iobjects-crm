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
package imanager.ui.entity;

import iobjects.*;
import iobjects.entity.*;
import iobjects.ui.entity.*;

import imanager.entity.*;

/**
 * Utilit�rio para cria��o de controles para a classe Departamento.
 */
public class DepartamentoUI { 

  /**
   * Retorna a representa��o HTML de um LookupList contendo valores da
   * entidade Departamento.
   * @param facade Fachada.
   * @param param Param cujas propriedades ir�o gerar o controle de sele��o.
   * @param empresaId Empresa ID.
   * @param width int Largura do controle de sele��o na p�gina ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formata��o do ParamFormSelect.
   * @param onChangeScript C�digo JavaScript para ser executado quando o usu�rio
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representa��o HTML de um LookupList contendo valores
   *         da entidade Departamento.
   * @throws java.lang.Exception
   */
  static public String lookupListForParam(Facade facade,
                                          int    empresaId,
                                          Param  param,
                                          int    width,
                                          String style,
                                          String onChangeScript) throws Exception {
    // retorna
    return LookupList.script(facade,
                             Departamento.CLASS_NAME,
                             "global_departamento",
                             new String[]{Departamento.FIELD_NOME.getFieldName()},
                             new String[]{Departamento.FIELD_DEPARTAMENTO_ID.getFieldName()},
                             new String[]{param.getIntValue() + ""},
                             new String[]{Departamento.FIELD_NOME.getFieldName()},
                             Departamento.FIELD_EMPRESA_ID.getFieldName() + "=" + empresaId,
                             LookupList.SELECT_TYPE_SINGLE,
                             param.getName(),
                             param.getScriptConstraint(),
                             param.getScriptConstraintErrorMessage(),
                             "width:" + (width > 0 ? width + "px;" : "100%;") + style,
                             onChangeScript,
                             true);
  }

  /**
   * Retorna a representa��o JavaScript de um LookupList contendo valores da
   * entidade Departamento.
   * @param facade Fachada.
   * @param entityLookup EntityLookup cujas propriedades ir�o gerar o controle de sele��o.
   * @param entityInfo EntityInfo contendo os valores do registro que est� sendo
   *                   editado/inserido.
   * @param empresaId Empreda ID.
   * @param width int Largura do controle de sele��o na p�gina ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formata��o do ParamFormSelect.
   * @param onChangeScript C�digo JavaScript para ser executado quando o usu�rio
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representa��o JavaScript de um LookupList contendo
   *         valores da entidade Departamento.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de dados.
   */
  static public String lookupListForLookup(Facade       facade,
                                           EntityLookup entityLookup,
                                           EntityInfo   entityInfo,
                                           int          empresaId,
                                           int          width,
                                           String       style,
                                           String       onChangeScript) throws Exception {
    // obt�m Departamento ID
    Integer departamentoId = (Integer)entityInfo.getPropertyValue(entityLookup.getKeyFields()[1].getFieldAlias());
    // retorna
    return LookupList.script(facade,
                             Departamento.CLASS_NAME,
                             "global_departamento",
                             new String[]{Departamento.FIELD_NOME.getFieldName()},
                             new String[]{Departamento.FIELD_DEPARTAMENTO_ID.getFieldName()},
                             new String[]{departamentoId + ""},
                             new String[]{Departamento.FIELD_NOME.getFieldName()},
                             "(" + Departamento.FIELD_EMPRESA_ID.getFieldName() + " = " + empresaId + ")",
                             LookupList.SELECT_TYPE_SINGLE,
                             entityLookup.getKeyFields()[1].getFieldAlias(),
                             entityLookup.getKeyFields()[1].getScriptConstraint(),
                             entityLookup.getKeyFields()[1].getScriptConstraintErrorMessage(),
                             (width > 0 ? "width:" + width + "px;" : "width:100%;") + style,
                             onChangeScript,
                             true);
  }

  private DepartamentoUI() {
  }

}
