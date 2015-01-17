package imanager.misc;

/**
 * Representa a lista de valores para ser utilizada nos campos e par�metros
 * do tipo personalidade.
 */
public class Personalidade {

  /**
   * Constante que representa o valor Cliente.
   */
  static public final int CLIENTE = 0;
  /**
   * Constante que representa o valor Fornecedor.
   */
  static public final int FORNECEDOR = 1;
  /**
   * Constante que representa o valor Funcion�rio.
   */
  static public final int FUNCIONARIO = 2;
  /**
   * Constante que representa o valor Vendedor.
   */
  static public final int VENDEDOR = 3;
  /**
   * Constante que representa o valor Transportador.
   */
  static public final int TRANSPORTADOR = 4;
  /**
   * Constante que representa o valor Todos.
   */
  static public final int TODOS = 5;
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_PARAM = {"CLIENTE",
                                                        "FORNECEDOR",
                                                        "FUNCION�RIO",
                                                        "VENDEDOR",
                                                        "TRANSPORTADOR",
                                                        "TODOS"};

  private Personalidade() {
  }

}
