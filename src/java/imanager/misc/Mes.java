package imanager.misc;

/**
 * Representa a lista de valores para ser utilizada nos campos e parâmetros
 * de mês. Atenção: Janeiro tem índice 0 e Dezembro tem índice 11.
 */
public class Mes {

  /**
   * Constante que representa o valor Todos.
   */
  static public final int TODOS = 12;
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_FIELD = {"JANEIRO",
                                                        "FEVEREIRO",
                                                        "MARÇO",
                                                        "ABRIL",
                                                        "MAIO",
                                                        "JUNHO",
                                                        "JULHO",
                                                        "AGOSTO",
                                                        "SETEMBRO",
                                                        "OUTUBRO",
                                                        "NOVEMBRO",
                                                        "DEZEMBRO"};
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_PARAM = {"JANEIRO",
                                                        "FEVEREIRO",
                                                        "MARÇO",
                                                        "ABRIL",
                                                        "MAIO",
                                                        "JUNHO",
                                                        "JULHO",
                                                        "AGOSTO",
                                                        "SETEMBRO",
                                                        "OUTUBRO",
                                                        "NOVEMBRO",
                                                        "DEZEMBRO",
                                                        "TODOS"};

  private Mes() {
  }

}
