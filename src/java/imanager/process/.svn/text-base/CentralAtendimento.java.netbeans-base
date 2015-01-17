   
package imanager.process;

import java.util.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.process.*;
import iobjects.util.*;

import imanager.entity.*;
/**
 * Representa o processo de CentralAtendimento.
 */
public class CentralAtendimento extends iobjects.process.Process {

  public class Info {
    boolean success = false;
  }

  // nossos faqs
  FAQ FAQ_TUTORIAL_CENTRAL_ATENDIMENTO = new FAQ("tutorialCentralAtendimento", " Passo a Passo ", "Central de Atendimento: utilizando a Central de Atendimento.", "", "tutorial_central_atendimento.html", "tutorial_central_atendimento.zip");

  // identificação da classe
  static public final String CLASS_NAME = "imanager.process.CentralAtendimento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("centralatendimento", "Central de Atendimento", "Exibe a Central de Atendimento.", HELP, "process/centralatendimento.jsp", "CRM", "", Action.CATEGORY_PROCESS, false, true);
  // nossos comandos
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE, "Finalizar Atendimento", "Finaliza o Atendimento salvando-o."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Novo Atendimento",   "Insere um novo registro."));

  static {
    ACTION.setShowType(Action.SHOW_TYPE_EMBEDED);
  }

  /**
   * Construtor padrão.
   */
  public CentralAtendimento() {
    // nossos faqs
    faqList().add(FAQ_TUTORIAL_CENTRAL_ATENDIMENTO);
    // nossas ações
    actionList().add(ACTION);
    
  }
}
