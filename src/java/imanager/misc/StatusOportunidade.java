package imanager.misc;

/**
 * Representa a lista de valores para ser utilizada nos campos e par�metros
 * do tipo Status de Tarefa.
 */
public class StatusOportunidade {

  /**
   * Constante que representa o valor Nova.
   */
  static public final int EM_ANDAMENTO = 0;
  /**
   * Constante que representa o valor Ganha.
   */
  static public final int GANHA = 1;
  /**
   * Constante que representa o valor Perdida.
   */
  static public final int PERDIDA = 2;
  /**
   * Constante que representa o valor Todos.
   */
  static public final int TODOS = 3;
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_FIELD = {"EM ANDAMENTO",
                                                        "GANHA",
                                                        "PERDIDA"};
  /**
   * Lista de cores.
   */
  static public final String[] COLOR_LIST_FOR_FIELD = {"blue",
                                                       "green",
                                                       "red"};
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_PARAM = {"EM ANDAMENTO",
                                                        "GANHA",
                                                        "PERDIDA",
                                                        "TODOS"};

  private StatusOportunidade() {
  }

}