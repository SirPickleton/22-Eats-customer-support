package unittests;

import static org.junit.Assert.assertEquals;

import bot.bot;

public class Test6 extends bot{
	public void test_menuItem6() throws InterruptedException{
		bot b6 = new bot();
		String[] userinputs = {"hi", "yes", "6", "real agent"};
		for(String i:userinputs) {
			b6.chatbox.setText(i);
			b6.chatbox.postActionEvent();
			Thread.sleep(1000);
		}
		String result = b6.textarea.getText();
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
				"you ->6\n" +
				"Bot ->Check up with a real agent by typing real agent to get stuff fixed that idk how to\n" +
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->real agent\n" +
				"Bot ->Understood. call 4511284 to get the help that I can't provide.\n" +  
				"Bot ->have a great day. type menu to see the menu again\n" +
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n";
		assertEquals(expected,result);
		Thread.sleep(1000);
	}
}
