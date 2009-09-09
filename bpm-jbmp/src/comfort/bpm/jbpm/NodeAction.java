package comfort.bpm.jbpm;

import comfort.bpm.IActionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import java.util.Map;


public class NodeAction implements ActionHandler {
	private static final Log log = LogFactory.getLog(NodeAction.class);
	
	public void execute(ExecutionContext executionContext) throws Exception {
		String action = executionContext.getNode().getName();
		log.debug("Process" + executionContext.getProcessDefinition().getName() + " has executed an action " + action);
		ContextInstance ci = executionContext.getContextInstance();
		IActionHandler h = StateMachine.getActionHandler(ci);
		
		Map<String, Object> map = StateMachine.getData(ci);
		String transition = h.execute(action, map);
		executionContext.setVariable("action", transition);
	}

}