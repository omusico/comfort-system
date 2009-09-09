package comfort.bpm;

/**
 * User Interface. 
 */
public interface IUI {
	/**
	 * Отображает фрейм (некое окно). При этом данный фрейм становится активным для ввода пользователя. 
	 * @param frameName Имя фрейма, который нужно показать.
	 * @param processName Имя текущего процесса.
     * @param superProcessName Имя процесса, из которого был вызван текущий процесс,
     * или {@code ""}, если это корневой процесс.
     * @param transition Обработчик действий пользователя (таких, как нажатие на кнопку).
     * Фрейм должен использовать метод {@link ITransition#execute}.
	 */
	void show(String frameName, String processName, String superProcessName, ITransition transition);
}