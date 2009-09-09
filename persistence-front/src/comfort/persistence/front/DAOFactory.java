package comfort.persistence.front;

/**
 * Фабрика {@link DAO}
 */
public interface DAOFactory {
    DAO getDAO();
}
