package fluxIO.proxy;

import fluxIO.client.gui.GUIHandler;

public class ClientProxy extends CommonProxy {
	public void initGuis() {
		new GUIHandler();
	}
}
