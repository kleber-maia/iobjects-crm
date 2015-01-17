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
