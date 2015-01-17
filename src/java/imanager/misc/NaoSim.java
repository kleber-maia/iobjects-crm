package imanager.misc;

/**
 * Representa a lista de valores para ser utilizada nos campos e parâmetros
 * do tipo Não e Sim.
 */
public class NaoSim {

  /**
   * Constante que representa o valor Não.
   */
  static public final int NAO = 0;
  /**
   * Constante que representa o valor Sim.
   */
  static public final int SIM = 1;
  /**
   * Constante que representa o valor Todos.
   */
  static public final int TODOS = 2;
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_FIELD = {"NÃO",
                                                        "SIM"};
  /**
   * Lista de valores de pesquisa.
   */
  static public final String[] LOOKUP_LIST_FOR_PARAM = {"NÃO",
                                                        "SIM",
                                                        "TODOS"};

  private NaoSim() {
  }

}
