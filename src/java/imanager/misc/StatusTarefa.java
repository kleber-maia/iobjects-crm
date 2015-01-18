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
package imanager.misc;

/**
 * Representa a lista de valores para ser utilizada nos campos e parâmetros
 * do tipo Status de Tarefa.
 */
public class StatusTarefa {

  /**
   * Constante que representa o valor Nova.
   */
  static public final int NOVA = 0;
  /**
   * Constante que representa o valor Em Andamento.
   */
  static public final int EM_ANDAMENTO = 1;
  /**
   * Constante que representa o valor Pendente com Cliente.
   */
  static public final int PENDENTE_CLIENTE = 2;
  /**
   * Constante que representa o valor Pendente com Cliente.
   */
  static public final int PENDENTE_FORNECEDOR = 3;
  /**
   * Constante que representa o valor Concluída.
   */
  static public final int CONCLUIDA = 4;
  /**
   * Constante que representa o valor Todos.
   */
  static public final int TODOS = 5;
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_FIELD = {"NOVA",
                                                        "EM ANDAMENTO",
                                                        "PENDENTE COM CLIENTE",
                                                        "PENDENTE COM FORNECEDOR",
                                                        "CONCLUÍDA"};
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_PARAM = {"NOVA",
                                                        "EM ANDAMENTO",
                                                        "PENDENTE COM CLIENTE",
                                                        "PENDENTE COM FORNECEDOR",
                                                        "CONCLUÍDA",
                                                        "TODOS"};

  private StatusTarefa() {
  }

}
