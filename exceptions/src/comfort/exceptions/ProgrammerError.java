package comfort.exceptions;

/**
 * Ошибка программиста.
 * Это исключение следует вызывать в случае, когда виновником ошибки является программист.
 * Не следует обрабатывать такие ошибки, так как они никогда не должны возникать.
 * Несмотря на это, {@code ProgrammerError} можно помещать в секцию {@code throws},
 * для того, чтобы пояснить в описании метода, в каком случае возникнет такая ошибка.
 */
public class ProgrammerError extends Error {
	protected ProgrammerError() { super(); };
	protected ProgrammerError(String message) { super(message); };
	protected ProgrammerError(String message, Exception cause) { super(message, cause); };
	
	public static ProgrammerError general(String message, Exception cause, Object... args) {
		return new ProgrammerError(String.format(message, args), cause);
	}
    public static ProgrammerError general(String message, Object... args) {
        return general(message, null, args);
    }
    public static ProgrammerError notImplemented(Class<?> cls, String method) {
        return general("The method '%s.%s' is not implemented", cls.getName(), method);
    }
    public static ProgrammerError invalidArgument(String argumentName, String description) {
		return general("The argument '%s' has invalid value: %s", argumentName, description);
	}
    public static ProgrammerError invalidArgument(String argumentName, Object argumentValue, String description) {
		return general("The argument '%s' has invalid value '%s': %s", argumentName, argumentValue, description);
	}
	public static ProgrammerError nullArgument(String argumentName) {
		return general("The argument '%s' must not be null", argumentName);
	}
	public static ProgrammerError emptyArgument(String argumentName) {
		return general("The argument '%s' must not be empty", argumentName);
	}
	public static ProgrammerError resourceNotFound(String resourceName) {
		return general("The resource '%s' is not found", resourceName);
	}
	public static ProgrammerError classNotFound(String className) {
		return general("The class '%s' is not found", className);
	}

    public static ProgrammerError notSupported(String featureName, String featureParams){
        return general("The %s not supported with params %s", featureName, featureParams);
    }
}