package com.gj;

import org.jivesoftware.smack.chat.Chat;

public class XmppTest {
    
    public static void main(String[] args) throws Exception {
        
        String username = "bhavna";
        String password = "bhavna";
        
        XMPPManager xmppManager = new XMPPManager("localhost", 5222);
        
        xmppManager.init();
      // xmppManager.performLogin(username, password);
        xmppManager.setStatus(true, "Hello everyone");
        
        String buddyJID = "ganesh";
        String buddyName = "ganesh";
        xmppManager.createEntry(buddyJID, buddyName);
        
        xmppManager.sendMessage("Hello mate", "ganesh@ind-pne3lw70125");
        
       /* Chat chat = chatManager.createChat(buddyJID, messageListener);
        chat.sendMessage(message);
        */
        boolean isRunning = true;
        
        while (isRunning) {
            Thread.sleep(50);
        }
        
        xmppManager.destroy();
        
    }

}