package com.tc.websocket.tests.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import com.tc.websocket.tests.client.NettyTestClient;

public class NettyClientFactory {
	
	private static List<NettyTestClient> clients  = new ArrayList<NettyTestClient>();
	

	public List<NettyTestClient> buildClients(int maxPayload) throws URISyntaxException, InterruptedException{

		if(!clients.isEmpty()) return clients;

		System.out.println("building websocket clients...");

		TestConfig cfg = TestConfig.getInstance();

		String url = null;

		for(int i=0;i< cfg.getNumberOfClients() ;i++){

			String username = null;
			if(i < 10){
				username = "username00" + i;
			}else if (i < 100){
				username = "username0" + i;
			}else{
				username ="username" + i;
			}

			String url1 = cfg.getWebSocketUrl().replace("{uri}", "uri" + i) + "/" + username;
			String url2 = cfg.getWebSocketUrl2().replace("{uri}", "uri" + i) + "/" + username;

			NettyTestClient c = null;

			if ( (i % 2) == 0){
				url = url1;
			}else{
				url = url2;
			}

			c = new NettyTestClient( new URI(url));
			c.setMaxPayload(maxPayload);
			c.setUsername(username);
			c.setUuid(username);
			c.setCompress(cfg.isCompressionEnabled());
			c.connect();
			clients.add(c);

			Thread.sleep(cfg.getConnectionDelay());
		}
		
		
		//clean out the closed connections.
		boolean hasClosed= false;
		List<NettyTestClient> open = new ArrayList<NettyTestClient>();
		for(NettyTestClient client : clients){
			if(client.isOpen()){
				open.add(client);
			}else{
				hasClosed=true;
			}
		}
		
		if(hasClosed){
			clients.clear();
			clients.addAll(open);
		}
		
		
		return clients;
	}
	
	
	public void closeClients() throws InterruptedException{
		for(NettyTestClient client : clients){
			client.disconnect();
			Thread.sleep(500);
		}
	}

}
