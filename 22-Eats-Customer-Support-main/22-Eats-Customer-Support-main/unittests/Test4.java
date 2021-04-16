package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bot.bot;

public class Test4 extends bot{
	@Test
	public void test_menuItem4() throws InterruptedException{
		bot b4 = new bot();
		String[] userinputs = {"hi", "yes", "4", "#d", "they were rude"};
		for(String i:userinputs) {
			b4.chatbox.setText(i);
			b4.chatbox.postActionEvent();
			Thread.sleep(1000);
		}
		String result = b4.textarea.getText();
		String expected = "Bot ->Welcome to the chat\n" +
				"Bot ->The text box to enter your text is on the right -->\n" + 
				"Bot ->Welcome to the chat\n" +
				"Bot ->The text box to enter your text is on the right -->\n" + 
				"you ->hi\n" +
				"Bot ->Hello, Welcome to T22 Eats Customer Support! Are you an existing member?\n"+
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->yes\n" +
				"Bot ->Alright Welcome to the chat. To help you, choose an option frrom this existing menu.\n"+
				"\n"+
				"Bot ->1: missing food\n" + 
				"Bot ->2: late delivery\n" + 
				"Bot ->3: quality of food\n" + 
				"Bot ->4: delivery driver\n" + 
				"Bot ->5: the app isn't working\n" + 
				"Bot ->6: other\n" + 
				"Bot ->0: exit\n" +
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->4\n" +
				"Bot ->Enter the order number with the poor food quality: eg #d 45846\n" + 
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->#d\n" +
				"Bot ->Thank you! Could you please tell us what the issue was with the driver\n" +  
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->they were rude\n" +
				"Bot ->That's awful. We'll get that sorted by talkking to the driver.\n" + 
				"Bot ->We'll provide in app credits to make p for the experience. Our apologies\n" + 
				"Bot ->If you are unsatisfied, type real agent to talk to a real person and futther discuss this.\n" +
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n";
		assertEquals(expected,result);
		Thread.sleep(1000);
	}
}
