package com.github.switcherapi.benchmark.switcher_online;

import static com.github.switcherapi.benchmark.switcher_online.Features.*;

import com.github.switcherapi.client.model.SwitcherBuilder;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.github.switcherapi.benchmark.Fail;
import com.github.switcherapi.client.ContextBuilder;

@State(Scope.Benchmark)
public class SwitcherOnlineSDKState {

	private SwitcherBuilder switcher;
	
	@Setup(Level.Trial)
    public void doSetup() {
		configure(ContextBuilder.builder()
				.contextLocation(Features.class.getName())
				.url("https://switcherapi.com/api")
				.apiKey("API_KEY")
				.domain("Playground")
				.component("benchmark"));
		
		initializeClient();
		switcher = getSwitcher(MY_ONLINE_SWITCHER);
    }
	
	public void run() {
		if (!switcher.isItOn())
			throw new Fail();
	}
	
	public static void main(String[] args) {
		SwitcherOnlineSDKState state = new SwitcherOnlineSDKState();
		state.doSetup();
		state.run();
	}
	
}
