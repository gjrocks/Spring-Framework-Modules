package com.gj;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class XMPPManager {
    
    private static final int packetReplyTimeout = 500; // millis
    
    private String server;
    private int port;
    
    private ConnectionConfiguration config;
    private AbstractXMPPConnection connection;

    private ChatManager chatManager;
    private ChatMessageListener messageListener;
   
    public XMPPManager(String server, int port) {
        this.server = server;
        this.port = port;
    }
    
    public void init() throws Exception {
        
        System.out.println(String.format("Initializing connection to server %1$s port %2$d", server, port));

       // SmackConfiguration.setPacketReplyTimeout(packetReplyTimeout);
        
        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
        configBuilder.setUsernameAndPassword("bhavna@ind-pne3lw70125", "bhavna");
        configBuilder.setResource("SomeResource");
        configBuilder.setServiceName("localhost");
        configBuilder.setHost("localhost");
        configBuilder.setSecurityMode(SecurityMode.disabled);
        
        SASLAuthentication.unBlacklistSASLMechanism("PLAIN");
        SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
        //configBuilder.set
        
      /*  config = new ConnectionConfiguration(server, port);
        config.setSASLAuthenticationEnabled(false);
        config.setSecurityMode(SecurityMode.disabled);
        
        connection = new XMPPConnection(config);*/
        
       // connection = new XMPPTCPConnection("bhavna", "bhavna", "localhost");
        
        connection = new XMPPTCPConnection(configBuilder.build());
        connection.connect();
        connection.login();
        System.out.println("Connected: " + connection.isConnected());
        System.out.println(connection.isAuthenticated());
        System.out.println(connection.getUser());
        
        chatManager = ChatManager.getInstanceFor(connection);
        messageListener = new MyMessageListener();
        
    }
    
    public void performLogin(String username, String password) throws Exception {
        if (connection!=null && connection.isConnected()) {
            connection.login(username, password);
        }
    }

    public void setStatus(boolean available, String status) throws Exception{
        
        Presence.Type type = available? Type.available: Type.unavailable;
        Presence presence = new Presence(type);
        
        presence.setStatus(status);
        connection.sendPacket(presence);
        
    }
    
    public void destroy() {
        if (connection!=null && connection.isConnected()) {
            connection.disconnect();
        }
    }
    
    public void sendMessage(String message, String buddyJID) throws Exception {
        System.out.println(String.format("Sending mesage '%1$s' to user %2$s", message, buddyJID));
        Chat chat = chatManager.createChat(buddyJID, messageListener);
        
        
     
        chatManager.addChatListener(
        	new ChatManagerListener() {
        		@Override
        		public void chatCreated(Chat chat, boolean createdLocally)
        		{
        			if (!createdLocally)
        				chat.addMessageListener(new MyMessageListener());;
        		}
        	});

        chat.sendMessage(message);
    }
    
    public void createEntry(String user, String name) throws Exception {
        System.out.println(String.format("Creating entry for buddy '%1$s' with name %2$s", user, name));
        Roster roster = Roster.getInstanceFor(connection);
        roster.createEntry(user, name, null);
    }
    
    class MyMessageListener implements ChatMessageListener {

        @Override
        public void processMessage(Chat chat, Message message) {
            String from = message.getFrom();
            String body = message.getBody();
            System.out.println(String.format("Received message '%1$s' from %2$s", body, from));
        }
        
    }
    
}
