package comfort.bpm.jbpm;

import comfort.bpm.IActionHandler;
import comfort.bpm.IStateMachine;
import comfort.bpm.IUI;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.graph.node.State;

import java.util.Hashtable;
import java.util.Map;

class StateMachine implements IStateMachine {

	public StateMachine() {
	}

	private static final String actionHandlerVariableName = "comfort.bpm.jbpm.StateMachine.ActionHandler";
	private static final String dataVariableName = "comfort.bpm.jbpm.StateMachine.data";

	private static ContextInstance getRootContextInstance(ContextInstance contextInstance) {
		ProcessInstance p = contextInstance.getProcessInstance();
		while (true) {
			Token t = p.getSuperProcessToken();
			if (t == null)
				break;
			
			p = t.getProcessInstance();
		}
		return p.getContextInstance();
	}
	private static Object getVariable(ContextInstance contextInstance, String name) {
		return getRootContextInstance(contextInstance).getVariable(name);
	}
	
	static IActionHandler getActionHandler(ContextInstance contextInstance) {
		return (IActionHandler)getVariable(contextInstance, actionHandlerVariableName);
	}

	@SuppressWarnings("unchecked")
	static Map<String, Object> getData(ContextInstance contextInstance) {
		Map<String, Object> map = (Map<String, Object>) getVariable(contextInstance, dataVariableName);
		if (map == null)
			map = new Hashtable<String, Object>(0);
		return map;
	}

	static void setData(ContextInstance contextInstance, Map<String, Object> data) {
		getRootContextInstance(contextInstance).setVariable(dataVariableName, data);
	}

	public String run(String processName, IActionHandler actionHandler, IUI ui, String processDefinitionResolver,
			String data) {
		ProcessDefinition processDefinition = ProcessDefinitionLoader.load(
				processName, "", processDefinitionResolver, data);
		ProcessInstance rootProcess = new ProcessInstance(processDefinition);
		
		rootProcess.getContextInstance().setVariable(actionHandlerVariableName, actionHandler);

		UIManager man = new UIManager(ui, rootProcess.getContextInstance());
				
		Token token = rootProcess.getRootToken();
        ProcessInstance process = rootProcess;
		do
		{
			if (process.hasEnded()) {
				Token superToken = process.getSuperProcessToken();
				if (superToken != null) {
					token = superToken;
					process = token.getProcessInstance();
					continue;
				}
				else
					break;
			}
			
			ProcessInstance subprocess = token.getSubProcessInstance();
			if (subprocess != null) {
				process = subprocess;
				token = subprocess.getRootToken();
			}
			
			Node n = token.getNode();
			
			if (n instanceof State) {
				for (;;) {
                    Helper.ProcessInformation pi = Helper.getProcessInformation(
                            process.getProcessDefinition().getStartState().getName());

                    String action = man.show(n.getName(), pi.processName, pi.superProcessName);
					try
					{
						token.signal(action);
					} catch (Exception e) {
                        e.printStackTrace();
						continue;
					}
					
					break;
				}
			}
			else
			{					
				token.signal();
			}
			
		
		} while (true);
		
		String action;
        action = (String) rootProcess.getContextInstance().getVariable("action");
        return action;
	}

}