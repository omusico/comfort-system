package comfort.exceptions;

/**
 * Непредвиденная ошибка.
 * Это исключение следует вызывать во всех случаях,
 * кроме {@link ProgrammerError ошибок программиста}
 * и {@link UserException ошибок пользователя}.
 * Не следует помещать такие исключения в секцию {@code throws},
 * так как они никогда не должны возникать.
 * Такие исключения могут быть при желании обработаны в секции {@code catch}.
 */
public class UnexpectedException extends RuntimeException {
	protected UnexpectedException() { super(); };
	protected UnexpectedException(Throwable e) { super(e); };
    protected UnexpectedException(String message) { super(message); };
	public static UnexpectedException exception(Exception e) {
		return new UnexpectedException(e);
	};
    public static UnexpectedException exception(String message, Object... args) {
        return new UnexpectedException(String.format(message, args));
    };
}