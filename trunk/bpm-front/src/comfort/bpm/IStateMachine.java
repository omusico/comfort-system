package comfort.bpm;

/**
 * Машина состояний.
 */
public interface IStateMachine {
	/**
	 * Выполняет указанный процесс.
	 * @param processName Имя процесса.
	 * @param actionHandler Обработчик действий. 
	 * Когда машина перейдет в состояние выполнения действия,
	 * этот интерфейс будет использоваться для выполнения соответсвующего действия.
	 * @param ui UI.
	 * Когда машина перейдет в состояние ожидания ввода пользователя,
	 * этот интерфейс будет использоваться для отображения соответсвующего UI.
	 * @param processDefinitionResolver Полное имя класса, предоставляющего интерфейс {@link IProcessDefinitionResolver}.
	 * Указанный класс должен иметь публичный конструктор по-умолчанию.
	 * @param data Произвольные данные. Будут переданы в {@link IProcessDefinitionResolver#resolve}.
	 * @return Результат выполнения процесса.
	 */
	String run(String processName, IActionHandler actionHandler, IUI ui, String processDefinitionResolver, String data);
}