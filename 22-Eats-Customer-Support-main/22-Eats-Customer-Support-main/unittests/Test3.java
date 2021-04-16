package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bot.bot;

public class Test3 extends bot{
	@Test
	public void test_menuItem3() throws InterruptedException{
		bot b3 = new bot();
		String[] userinputs = {"hi", "yes", "3", "#c", "it was cold", "pizza"};
		for(String i:userinputs) {
			b3.chatbox.setText(i);
			b3.chatbox.postActionEvent();
			Thread.sleep(1000);
		}
		String result = b3.textarea.getText();
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
				"you ->3\n" +
				"Bot ->Enter the order number with the poor food quality: eg #c 45846\n" + 
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->#c\n" +
				"Bot ->Thank you! Could you please tell us how the food wasn't up to the standards\n" +  
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->it was cold\n" +
				"Bot ->We apologise! this must've ruined the experience. We can offer a partial compensation.\n" + 
				"Bot ->What items had the low quality\n" + 
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n" +
				"you ->pizza\n" +
				"Bot ->Understood. We will provide in app credits to make up for the items. Thank you for letting us know.\n" + 
				"Bot ->type menu to check the menu to address another issue. Thank you\n" +
				"you ->\n" +
				"Bot ->Please type something. We can't understand.\n";
		assertEquals(expected,result);
		Thread.sleep(1000);
	}
}
