package imanager.misc;

/**
 * Representa a lista de valores para ser utilizada nos campos e parâmetros
 * do bloqueio.
 */
public class TipoBloqueio {

  /**
   * Constante que representa o valor Física.
   */
  static public final int TOTAL = 0;
  /**
   * Constante que representa o valor Jurídica.
   */
  static public final int EMPRESA = 1;
  /**
   * Constante que representa o valor Todos.
   */
  static public final int AGENDA = 2;
  /**
   * Constante que representa o valor Todos.
   */
  static public final int TODOS = 3;
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_FIELD = {"TOTAL",
                                                        "EMPRESA",
                                                        "AGENDA"};
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_PARAM = {"TOTAL",
                                                        "EMPRESA",
                                                        "AGENDA",
                                                        "TODOS"};

  private TipoBloqueio() {
  }

}
