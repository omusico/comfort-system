package comfort.persistence.front;

import javax.ejb.Local;
import java.io.Serializable;

/**
 * Базовый тип всех сущностей
 */
public interface IEntity extends Serializable{
    /**
     * Возвращает уникальный идентификатор присвоенный
     * сущности.
     * @return  идентификатор
     */
    int getId();
}
