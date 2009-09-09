package comfort.bpm.jbpm;

import comfort.bpm.IStateMachine;

public class StateMachineFactory {
	public static IStateMachine createStateMachine() {
		return new StateMachine();
	}
}