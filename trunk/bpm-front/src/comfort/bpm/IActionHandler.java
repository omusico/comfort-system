package comfort.bpm;

import java.util.Map;

/**
 * Обработчик действий. 
 */
public interface IActionHandler {
	/**
	 * Выполняет действие.
	 * @param action Имя действия, которое нужно выполнить.
	 * @param data Данные, переданные для выполнения действия.
	 * @return возвращает имя перехода. Имя перехода может быть пустой строкой.
	 * В этом случае, будет выбран переход по-умолчанию.
	 * После выполнения действия процесс пойдет по указанному переходу.
	 */
	String execute(String action, Map<String, Object> data);
}