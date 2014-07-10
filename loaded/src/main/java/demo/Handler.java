package demo;

import org.springframework.stereotype.Component;

import reactor.event.Event;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

@Component
@Consumer
public class Handler {
	@Selector(value="t.t",reactor="@rootReactor")
    public void handleHotChange(Event<String> event) {
		System.out.println(event.getData());
    }
}
