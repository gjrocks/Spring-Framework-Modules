package com.gj;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

public class TestXMPP {

	public static void main(String[] args) throws Exception{
		
	AbstractXMPPConnection conn1 = new XMPPTCPConnection("bhavna", "bhavna", "localhost");
	conn1.connect();
	System.out.println(conn1);
	
	/*
	 * 
	 * 
	 * XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
configBuilder.setUsernameAndPassword("username", "password");
configBuilder.setResource("SomeResource");
configBuilder.setServiceName("jabber.org");

AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());
// Connect to the server
connection.connect();
// Log into the server
connection.login();

...

// Disconnect from the server
connection.disconnect();

	 */
	
	ChatManager chatmanager = ChatManager.getInstanceFor(conn1);
	Chat newChat = chatmanager.createChat("ganesh@ind-pne3lw70125", new ChatMessageListener() {

		@Override
		public void processMessage(Chat arg0, Message message) {
			// TODO Auto-generated method stub
			
			System.out.println("Received message: " + message);
			
		}
		/*public void processMessage(Chat chat, ChatMessage message) {
			System.out.println("Received message: " + message);
		}*/
	});

	try {
		newChat.sendMessage("Howdy!");
	}
	catch (Exception e) {
		System.out.println("Error Delivering block");
	}

	System.out.println("done");
	}

}
