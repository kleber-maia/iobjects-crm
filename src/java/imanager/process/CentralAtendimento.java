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
