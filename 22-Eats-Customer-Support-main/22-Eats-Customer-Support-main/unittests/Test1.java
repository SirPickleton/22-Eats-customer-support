package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bot.bot;

public class Test1 extends bot {
	@Test
	public void test_menuItem1() throws InterruptedException {
		bot b1 = new bot();
		String[] userinputs = {"hi", "yes", "1", "#a", "fries", "refund"};
		for(String i:userinputs) {
			b1.chatbox.setText(i);
			b1.chatbox.postActionEvent();
			Thread.sleep(1000);
		}
		String result = b1.textarea.getText();
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
				"you ->1\n" +
				"Bot ->Enter the order number with the missing food: eg #a 45846\n" +  
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->#a\n" +
				"Bot ->Thank you! Could you please tell us which items your order missed?\n" +  
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->fries\n" +
				"Bot ->We apologize for the inconvenience!\n" + 
				"Bot ->Would you like a refund or talk to a real agent?\n" + 
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->refund\n" +
				"Bot ->Understood. We will provide a refund via in app credits in an hour\n" + 
				"Bot ->I hope this solves the problem\n" + 
				"Bot ->have a great day. type menu to see the menu again\n" +
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n";
		assertEquals(expected,result);
		Thread.sleep(1000);
	}
}
