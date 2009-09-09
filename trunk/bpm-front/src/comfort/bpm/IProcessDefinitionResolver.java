package comfort.bpm;

import java.io.InputStream;

public interface IProcessDefinitionResolver {
	/**
	 * Возвращает поток файла определения процесса.
	 * @param processName Имя процесса.
	 * @param superProcessName Имя процесса, из которого был вызван данный процесс.
	 * @param data Данные, которые были переданы в {@link IStateMachine#run(String, IActionHandler, IUI, String, String)}.
     * @return поток файла определения процесса.
	 */
	InputStream resolve(String processName, String superProcessName, String data);
}