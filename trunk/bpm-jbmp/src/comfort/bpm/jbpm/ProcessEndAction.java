package comfort.bpm.jbpm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

public class ProcessEndAction implements ActionHandler {
	private static final Log log = LogFactory.getLog(ProcessEndAction.class);

	public void execute(ExecutionContext executionContext) throws Exception {
		String action = executionContext.getNode().getName();
		log.debug("Process " + executionContext.getProcessDefinition().getName() + " has ended with action=" + action);
		executionContext.setVariable("action", action);
	}

}