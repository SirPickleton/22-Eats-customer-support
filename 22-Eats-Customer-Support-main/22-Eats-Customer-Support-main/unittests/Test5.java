package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bot.bot;

public class Test5 extends bot{
	@Test
	public void test_menuItem5() throws InterruptedException{
		bot b5 = new bot();
		String[] userinputs = {"hi", "yes", "5", "unresponsive"};
		for(String i:userinputs) {
			b5.chatbox.setText(i);
			b5.chatbox.postActionEvent();
			Thread.sleep(1000);
		}
		String result = b5.textarea.getText();
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
				"you ->5\n" +
				"Bot ->Thank you for taking the time to provide feedback\n" +  
				"Bot ->What is wrong with the app?\n" + 
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->unresponsive\n" +
				"Bot ->WOah! THat's a pain. please try reinstalling the app\n" +
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n";
		assertEquals(expected,result);
		Thread.sleep(1000);
	}
}
