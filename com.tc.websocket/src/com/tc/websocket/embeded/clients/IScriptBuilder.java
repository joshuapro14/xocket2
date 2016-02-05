/*
 * � Copyright Tek Counsel LLC 2016
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */


package com.tc.websocket.embeded.clients;

public interface IScriptBuilder extends Runnable {

	public abstract void addScript(String event, String script);

	@Override
	public abstract void run();

	public abstract IScriptClient getClient();

	public abstract String getWebsocketUrl();

	public abstract void setWebsocketUrl(String websocketUrl);

	public abstract String getSessionId();

	public abstract void setSessionId(String sessionId);

	public abstract String getUserId();

	public abstract void setUserId(String userId);
	
	public abstract void setRunAsCreds(String runAsUser, String runAsPassword);

}