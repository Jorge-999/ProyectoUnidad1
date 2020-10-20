/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;
import jade.core.Agent;
import behaviours.RequestPerformer;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import gui.BookBuyGui;
import gui.BookSellerGui;

/**
 *
 * @author usuario
 */
public class agenteComprador extends Agent
{
  private String bookTitle;
  private AID[] sellerAgents;
  private int ticker_timer = 10000;
  private agenteComprador this_agent = this;
  private BookBuyGui gui=new BookBuyGui(this);
  String title;
  String args;
  
  
  protected void setup() {
     gui.showGui();
     pausa(20000);
    System.out.println("Agente Comprador " + getAID().getName() + " Esta listo");
     args = gui.title.trim();
    
    if(args != null) {
      bookTitle = args;
      System.out.println("Libro: " + bookTitle);
     
      addBehaviour(new TickerBehaviour(this, ticker_timer) {
        protected void onTick() {
          System.out.println("Tratando de comprar " + bookTitle);
          
          DFAgentDescription template = new DFAgentDescription();
          ServiceDescription sd = new ServiceDescription();
          sd.setType("book-selling");
          template.addServices(sd);
          
          try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            System.out.println("Encontré los siguientes agentes vendedores:");
            sellerAgents = new AID[result.length];
            for(int i = 0; i < result.length; i++) {
              sellerAgents[i] = result[i].getName();
              System.out.println(sellerAgents[i].getName());
            }
            
          }catch(FIPAException fe) {
            fe.printStackTrace();
          }
          
          myAgent.addBehaviour(new RequestPerformer(this_agent));
        }
      });
    } else {
      System.out.println("No se ha especificado ningún título de libro de destino ");
      doDelete();
    }
  }
  
  protected void takeDown() {
     gui.dispose();
    System.out.println("Agente comprador " + getAID().getName() + " terminado");
  }
  
  public AID[] getSellerAgents() {
    return sellerAgents;
  }
  
  public String getBookTitle() {
    return bookTitle;
  }
  public static void pausa(int t){
      try{
          Thread.sleep(t);
      }
      catch(InterruptedException e){
          
      }
    
    
    
}
}

    

    
    
    

