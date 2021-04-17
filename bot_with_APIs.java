package bot;

import java.awt.List;

import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.sun.source.tree.Tree;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.CoreQuote;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.util.TypesafeMap;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.swing.JScrollPane;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.Random;
import java.util.prefs.*;


public class bot_with_APIs extends JFrame{
	
	protected static JTextArea textarea = new JTextArea();
	protected static JTextField chatbox = new JTextField();
	JScrollPane scroll = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String [] possibleanswer = {"I'm not sure how I can help you with that.", "Would you like to talk to a real agent? type real agent to do so.","This path is still under construction!", "please provide feedback on what the other options for help may be!", "I'm still learning. Once I grow up I can help you with this"};
	
	public static String gtext;
	public static String combinedtext;
	public static boolean french = false;
	private static int balance = 0;

	 
	public bot_with_APIs()  {
		JFrame frame = new JFrame ();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLayout(null);
		frame.setSize(1600, 1600);
		frame.setTitle("Chat");
		frame.add(textarea);
		frame.add(scroll);
		frame.add(chatbox);
		//for text area
		textarea.setSize(1500, 1400);
		textarea.setLocation(2, 2);
		textarea.add(scroll);
		
		// for text field
		chatbox.setSize(340, 30);
		chatbox.setLocation(1550,0);
		
		
		
		
		bot("Welcome to the chat");
		bot("The text box to enter your text is on the right -->");
		bot("Please select your prefered language (english/french)");
		
		chatbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 gtext = chatbox.getText().toLowerCase();
				combinedtext = combinedtext + ". " + gtext;
				
				
				textarea.append("you ->" + gtext + "\n");
				
				chatbox.setText("");
				
				//French
				if(gtext.contains("salut") || gtext.contains("french")) {
					french = true;
					bot("Bonjour, Bienvenue au service client de T22 Eats! Êtes-vous déjà membre?");
				}
				else if(gtext.contains("oui")) {
					bot("Très bien Bienvenue dans le chat. Pour vous aider, choisissez une option dans ce menu existant." + "\n");
					issuesmenu();
				}else if (gtext.contains("non")) {
					bot("oups, nous ne pouvons autoriser que les clients enregistrés. Inscrivez-vous et revenez.");
					bot("Êtes-vous inscrit maintenant?");
				}else if (gtext.contains("equilibre")||gtext.contains("compte")) {
					try {
						bot("Le solde actuel de votre compte est: " + readBalance() + "$");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				
				
				
				
				
				else if (gtext.contains("1f")) {
					bot("Entrez le numéro de commande avec l'aliment manquant: par exemple #af 45846");
			    }else if(gtext.contains("#af")) {
			    	bot("Merci! Pourriez-vous s'il vous plaît nous dire quels articles votre commande a manqué?");
			    }else if (gtext.contains("frites")||gtext.contains("boire")||gtext.contains("burgar")||gtext.contains("poulette")) {
			    	bot("Nous nous excusons pour le dérangement!");
			    	bot("Souhaitez-vous un remboursement ou parler à un vrai agent?");
			    }else if(gtext.contains("remboursement")) {
			    	bot("Entendu. Nous vous rembourserons via des crédits intégrés dans une heure");
			    	bot("J'espère que cela résout le problème");
			    	bot("passe une bonne journée. tapez menu pour voir à nouveau le menu");
			    	try {
			    		balance += 10;
						saveBalance(balance);
					} catch (IOException e) {
						e.printStackTrace();
					}
			    }else if(gtext.contains("parlez") || gtext.contains("agente")) {
			    	bot("Entendu. appelez le 4511284 pour obtenir l'aide que je ne peux pas fournir.");
			    	bot("passe une bonne journée. tapez menu pour voir à nouveau le menu");
			    }
				
				
				
				
				
				
				else if (gtext.contains("2f")) {
					bot("Entrez le numéro de commande avec la livraison tardive: par exemple #bf 45846");
				}
		        else if(gtext.contains("#bf")) {
		    	  bot("Merci! Pourriez-vous s'il vous plaît nous dire quel est le retard de la livraison");
		        }
		        else if(gtext.contains("trente")||gtext.contains("dix") ||gtext.contains("quinze")) {
		        	bot("Je suis désolé mais ce n'est pas assez tard pour justifier un remboursement. Voudriez-vous parler à un vrai agent?");
		        	bot("Si oui, tapez un agent réel");
		        }else if (gtext.contains("une heure")||gtext.contains("plus d'une heure") ||gtext.contains("sur deux heures")) {
		        	bot("Nous nous excusons pour le problème. Souhaitez-vous un remboursement ou une conversation avec un vrai agent?");
		        }
				
				
				
				else if (gtext.contains("3f")) {
					bot("Entrez le numéro de commande avec la mauvaise qualité des aliments: par exemple #cf 45846");
				}else if(gtext.contains("#cf")) {
			    	  bot("Merci! Pourriez-vous s'il vous plaît nous dire en quoi la nourriture n'était pas à la hauteur des normes");
				}else if(gtext.contains("du froid") || (gtext.contains("pas aux normes"))) {
					bot("Nous sommes désolés! cela a dû gâcher l'expérience. Nous pouvons offrir une compensation partielle.");
					bot("Quels articles avaient la faible qualité");
				}else if (gtext.contains("pizzaaa")||gtext.contains("envelopper")||gtext.contains("bifteck")||gtext.contains("cafe")) {
					bot("Entendu. Nous fournirons des crédits d'application pour compenser les articles. Merci de nous en avoir informé.");
					bot("tapez menu pour vérifier le menu afin de résoudre un autre problème. Merci");
					try {
						balance += 10;
						saveBalance(balance);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (gtext.contains("menuf")) {
					issuesmenuf();
				}
				
				
				
				else if (gtext.contains("4f")) {
					bot("Entrez le numéro de commande avec la mauvaise qualité des aliments: par exemple #df 45846");
				}else if(gtext.contains("#df")) {
			    	  bot("Merci! Pourriez-vous s'il vous plaît nous dire quel était le problème avec le pilote");
				}else if(gtext.contains("impolie") || gtext.contains("irrespectueuse") || gtext.contains("crier")) {
					bot("C'est terrible. Nous allons régler cela en parlant au chauffeur.");
					bot("Nous fournirons des crédits d'application pour compenser l'expérience. Nos excuses");
					bot("Si vous n'êtes pas satisfait, saisissez un agent réel pour parler à une personne réelle et en discuter davantage.");
					try {
						balance += 5;
						saveBalance(balance);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(combinedtext);
				}
				
				
				else if (gtext.contains("5f")) {
					bot("Merci d'avoir pris le temps de nous faire part de vos commentaires");
					bot("Quel est le problème avec l'application?");
				}else if(gtext.contains("sensible")) {
					bot("WOah! C'est une douleur. essayez de réinstaller l'application");
				}else if(gtext.contains("commande passée") || gtext.contains("articles dans le panier")) {
					bot("Entendu. Cela pourrait être dû au fait que le restaurant est fermé. si le problème persiste, essayez de le réinstaller.");
				}else if(gtext.contains("l'argent facturé est inexact")) {
					bot("C'est un yikes. avez-vous déjà passé la commande?");
				}else if(gtext.contains("nourriture placée")) {
					bot("Oh non. Souhaitez-vous rembourser la commande ou parler à un vrai agent?");
				}else if(gtext.contains("pas ")) {
					bot("Bonne épargne. nos serveurs sont actuellement en panne. Veuillez réessayer plus tard.");
					bot("tapez menu pour revenir au menu principal!");
				}
				
				else if (gtext.contains("6f")) {
					bot("Vérifiez avec un vrai agent en tapant un vrai agent pour obtenir des correctifs.");
					
						 
				}else if(gtext.contains("0f")) {
					bot("au revoir");
					try {
					      FileWriter myWriter = new FileWriter("filename.txt");
					      myWriter.write(combinedtext);
					      myWriter.close();
					      System.out.println("Successfully wrote to the file.");
					    } catch (IOException e) {
					      System.out.println("An error occurred.");
					      e.printStackTrace();
					    }
				}
				else if(gtext.contains("merci")) {
					bot("Je vous en prie! Je suis heureux d'aider de toutes les manières possibles");
				}
				
				
				//English
				else if(gtext.contains("hi") || gtext.contains("english")) {
					french = false;
					bot("Hello, Welcome to T22 Eats Customer Support! Are you an existing member?");
				}
				else if(gtext.contains("yes")) {
					bot("Alright Welcome to the chat. To help you, choose an option from this existing menu." + "\n");
					issuesmenu();
				}else if (gtext.contains("no")) {
					bot("oops, we can only allow registered clients. Get registered and then come back.");
					bot("Are you registered now?");
				}else if (gtext.contains("balance")||gtext.contains("account")||gtext.contains("credits")) {
					try {
						bot("Your current account balance is: " + readBalance() + "$");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				
				
				
				
				
				else if (gtext.contains("1")) {
					bot("Enter the order number with the missing food: eg #a 45846");
			    }else if(gtext.contains("#a")) {
			    	bot("Thank you! Could you please tell us which items your order missed?");
			    }else if (gtext.contains("fries")||gtext.contains("drink")||gtext.contains("burger")||gtext.contains("chicken")) {
			    	bot("We apologize for the inconvenience!");
			    	bot("Would you like a refund or talk to a real agent?");
			    }else if(gtext.contains("refund")) {
			    	bot("Understood. We will provide a refund via in app credits in an hour");
			    	bot("I hope this solves the problem");
			    	bot("have a great day. type menu to see the menu again");
			    	try {
			    		balance += 10;
						saveBalance(balance);
					} catch (IOException e) {
						e.printStackTrace();
					}
			    }else if(gtext.contains("talk") || gtext.contains("agent")) {
			    	bot("Understood. call 4511284 to get the help that I can't provide.");
			    	bot("have a great day. type menu to see the menu again");
			    }
				
				
				
				
				
				
				else if (gtext.contains("2")) {
					bot("Enter the order number with the late delivery: eg #b 45846");
				}
		        else if(gtext.contains("#b")) {
		    	  bot("Thank you! Could you please tell us how late the delivery was");
		        }
		        else if(gtext.contains("thirty")||gtext.contains("ten") ||gtext.contains("fifteen")) {
		        	bot("I am sorry but this isn't late enough to warrant a refund. would you like to talk a real agent?");
		        	bot("If yes type real agent");
		        }else if (gtext.contains("an hour")||gtext.contains("over an hour") ||gtext.contains("over two hours")) {
		        	bot("We apologise for the issue. would you like a refund or a conversation with a real agent?");
		        }
				
				
				
				else if (gtext.contains("3")) {
					bot("Enter the order number with the poor food quality: eg #c 45846");
				}else if(gtext.contains("#c")) {
			    	  bot("Thank you! Could you please tell us how the food wasn't up to the standards");
				}else if(gtext.contains("cold") || (gtext.contains("not up to the standards"))) {
					bot("We apologise! this must've ruined the experience. We can offer a partial compensation.");
					bot("What items had the low quality");
				}else if (gtext.contains("pizza")||gtext.contains("wrap")||gtext.contains("steak")||gtext.contains("coffee")) {
					bot("Understood. We will provide in app credits to make up for the items. Thank you for letting us know.");
					bot("type menu to check the menu to address another issue. Thank you");
					try {
						balance += 10;
						saveBalance(balance);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				
				
				else if (gtext.contains("4")) {
					bot("Enter the order number with the poor food quality: eg #d 45846");
				}else if(gtext.contains("#d")) {
			    	  bot("Thank you! Could you please tell us what the issue was with the driver");
				}else if(gtext.contains("rude") || gtext.contains("disrespectful") || gtext.contains("shout")) {
					bot("That's awful. We'll get that sorted by talkking to the driver.");
					bot("We'll provide in app credits to make up for the experience. Our apologies");
					bot("If you are unsatisfied, type real agent to talk to a real person and futther discuss this.");
					try {
						balance += 5;
						saveBalance(balance);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(combinedtext);
				}
				
				
				else if (gtext.contains("5")) {
					bot("Thank you for taking the time to provide feedback");
					bot("What is wrong with the app?");
				}else if(gtext.contains("responsive")) {
					bot("WOah! THat's a pain. please try reinstalling the app");
				}else if(gtext.contains("order being placed") || gtext.contains("items in cart")) {
					bot("Understood. This could be as tthe restraunt is closed. if the problem persists, try reinstalling it.");
				}else if(gtext.contains("money being charged is inaccurate")) {
					bot("That's a yikes. have you placed the order already?");
				}else if(gtext.contains("placed food")) {
					bot("Oh no. would you like to refund the order or talk to a real agent?");
				}else if(gtext.contains("haven't ")) {
					bot("Good save. our servers are currently down. please try again later.");
					bot("type menu to return to the main menu!");
				}
				
				else if (gtext.contains("6")) {
					bot("Check up with a real agent by typing real agent to get stuff fixed that idk how to");
					
						 
				}else if(gtext.contains("0")) {
					bot("bye bye");
					try {
					      FileWriter myWriter = new FileWriter("filename.txt");
					      myWriter.write(combinedtext);
					      myWriter.close();
					      System.out.println("Successfully wrote to the file.");
					    } catch (IOException e) {
					      System.out.println("An error occurred.");
					      e.printStackTrace();
					    }
				}
				else if(gtext.contains("menu")) {
					issuesmenu();
				}else if(gtext.contains("thank you")) {
					bot("You are welcome! I am happy to assist in any way I can");
				}
				else if(gtext.length()==0) {
					bot("Please type something. We can't understand./Veuillez taper quelque chose. On ne peut pas comprendre.");
				}
				else {
					Random rand = new Random();
					int upperbound = 5;
					int int_rand = rand.nextInt(upperbound);
					bot(possibleanswer[int_rand]);
				}
				
					
				
				
				}
		}
				
				
			
			
		);
		
		
	}
	private static void bot(String s) {
		textarea.append("Bot ->" + s + " \n");
	}
	public static void issuesmenuf () {
			bot("1f: nourriture manquante");
			bot("2f: livraison tardive");
			bot("3f: qualité de la nourriture");
			bot("4f: livreur");
			bot("5f: l'application ne fonctionne pas");
			bot("6f: autre");
			bot("0f: sortir");
	}
	public static void issuesmenu () {
		
			bot("1: missing food");
			bot("2: late delivery");
			bot("3: quality of food");
			bot("4: delivery driver");
			bot("5: the app isn't working");
			bot("6: other");
			bot("0: exit");
	}
	
	public void saveBalance(int bal) throws IOException {
		  String newbalstring = String.valueOf(bal);
	      FileWriter myWriter = new FileWriter("balance.txt");
	      myWriter.write(newbalstring);
	      myWriter.close();
	    }

	public String readBalance() throws FileNotFoundException {
		  File myObj = new File("balance.txt");
	      Scanner myReader = new Scanner(myObj);
	      String bal = myReader.next();
	      myReader.close();
	      return bal;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new bot_with_APIs();
		 String data = null;
		try {
		      File myObj = new File("filename.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		Properties props = new Properties();
	    // set the list of annotators to run
	    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
	    // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
	    props.setProperty("coref.algorithm", "neural");
	    // build pipeline
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    // create a document object
	    CoreDocument doc = new CoreDocument(data);
	    // annnotate the document
	    pipeline.annotate(doc);
	    // examples
	
	
	System.out.println("---");
    System.out.println("entities found");
    for (CoreEntityMention em : doc.entityMentions())
      System.out.println("\tdetected entity: \t"+em.text()+"\t"+em.entityType());
    System.out.println("---");
    System.out.println("tokens and ner tags");
    String tokensAndNERTags = doc.tokens().stream().map(token -> "("+token.word()+","+token.ner()+")").collect(
        Collectors.joining(" "));
    System.out.println(tokensAndNERTags);
	/*
	    CoreDocument document = pipeline.processToCoreDocument(data);
	    // display tokens
	    for (CoreLabel tok : document.tokens()) {
	      System.out.println(String.format("%s\t%s", tok.word(), tok.tag()));
	    }
	 */
	    
		
		
		
	}

}
