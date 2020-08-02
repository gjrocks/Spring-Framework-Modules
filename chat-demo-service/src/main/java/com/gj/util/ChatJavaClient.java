package com.gj.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class ChatJavaClient {

	public static void main(String[] args) {
	}
	
	public static MessageResponse getResponse(Map context,String input){
		//BufferedReader br = null;
		MessageResponse response = null;
		
		try {
			//br = new BufferedReader(new InputStreamReader(System.in));
			TimeZone timeZone = TimeZone.getDefault();
			/*System.out.println("Enter your Name: ");
			String userName = br.readLine();*/
			// Add userName to context to be used by Conversation.
			/*context.put("userName", userName);
			context.put("currentTime", Calendar.getInstance(timeZone).getTime().toString());
			context.put("timezone", timeZone.getID());*/
			if(context==null || context.size()==0){
				context=new HashMap<>();
				response = conversationAPI("", context);
				return response;
			}
			//printContext(response.getContext());
			//System.out.println("Watson Response: " + response.getText());
			//context=response.getContext();
			/*while (true) {
				*/
				//System.out.print("User Says : ");
				//String input = br.readLine();
			     printContext(context);
				response = conversationAPI(input, context);
				//System.out.println("Watson Response: " + response.getText());
				context = response.getContext();
				printContext(context);
				System.out.println("———–");
		//	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}

	private static void printContext(Map context) {
		if(context.containsKey("uname") && context.containsKey("lname"))
		System.out.println(" Context is uname:" +context.get("uname") + "::lname :" +context.get("lname"));
		else
			System.out.println("context empty");
	}

	public static MessageResponse conversationAPI(String input, Map context) {
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
		// Credentials of Workspace of Conversation
		service.setUsernameAndPassword("d3bbcda9-6db5-41f6-a33b-83447cf3671e", "t0TIlt5TDF3q");
		MessageRequest newMessage = new MessageRequest.Builder().inputText(input).context(context).build();
		// Workspace ID of Conversation current workspace
		String workspaceId = "3bbb3fab-0396-4f73-adb8-a7ba6b4e157e";
		MessageResponse response = service.message(workspaceId, newMessage).execute();
		System.out.println(response.getContext());
		return response;
	}

}
