package comfort.bpm.jbpm;

import comfort.bpm.ITransition;
import comfort.bpm.IUI;
import comfort.exceptions.ProgrammerError;
import comfort.exceptions.UnexpectedException;
import org.jbpm.context.exe.ContextInstance;

import java.util.Hashtable;
import java.util.Map;

class UIManager implements ITransition {
	private IUI ui;
	private ContextInstance contextInstance;
	public UIManager(IUI ui, ContextInstance contextInstance) {
		this.ui = ui;
		this.contextInstance = contextInstance;
	}
	
	public String show(String frame, String processName, String superProcessName) {
		ui.show(frame, processName, superProcessName, this);
		
		synchronized (mutex) {
			try {
				action = null;
				while(action == null)
					mutex.wait();
			} catch (InterruptedException e) {
				throw UnexpectedException.exception(e);
			}
		}
		StateMachine.setData(contextInstance, map);
		return action;
	}
	private final Object mutex = new Object();
	private String action;
	private Map<String, Object> map;
	
	public void execute(String name, Object... data) {
		if (name == null)
			throw ProgrammerError.nullArgument("name");

        if (data == null)
            data = new Object[0];

        if (data.length % 2 != 0)
			throw ProgrammerError.invalidArgument("data", "it must be a list of name-value pairs");
		
		synchronized (mutex) {
			action = name;
			map = new Hashtable<String, Object>(data.length / 2);
			for (int i = 0; i < data.length; i++) {
				Object o = data[i];
				if (!(o instanceof String))
					throw ProgrammerError.invalidArgument("data", "the first value in the pair must be a string");
				
				map.put((String) o, data[++i]);
			}
			mutex.notifyAll();
		}
	}

}