package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bot.bot;

public class Test2 extends bot{
	@Test
	public void test_menuItem2() throws InterruptedException{
		bot b2 = new bot();
		String[] userinputs = {"hi", "yes", "2", "#b", "an hour", "talk"};
		for(String i:userinputs) {
			b2.chatbox.setText(i);
			b2.chatbox.postActionEvent();
			Thread.sleep(1000);
		}
		String result = b2.textarea.getText();
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
				"you ->2\n" +
				"Bot ->Enter the order number with the late delivery: eg #b 45846\n" +  
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->#b\n" +
				"Bot ->Thank you! Could you please tell us how late the delivery was\n" +  
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->an hour\n" +
				"Bot ->We apologise for the issue. would you like a rerfund or a conversation with a real agent?\n" + 
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->talk\n" +
				"Bot ->Understood. call 4511284 to get the help that I can't provide.\n" + 
				"Bot ->have a great day. type menu to see the menu again\n" +
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n";
		assertEquals(expected,result);
		Thread.sleep(1000);
	}
}
