package comfort.config;

/**
 * Author: Michael Morozov
 * Date: 20.12.2007
 * Time: 0:22:32
 */
public enum ConfigCodes {
    MONEY("format.money"),
    FIXED("format.fixed"),

    GOODS_NAME("header.goods.name"),
    GOODS_PRICE("header.goods.price"),
    GOODS_QUANT("header.goods.quant"),
    GOODS_AMOUNT("header.goods.amount"),
    GOODS_NAME_WIDTH("goods.name.width"),
    GOODS_PRICE_WIDTH("goods.price.width"),
    GOODS_QUANT_WIDTH("goods.price.width"),
    GOODS_AMOUNT_WIDTH("goods.amount.width");

    private String code;
    private String value;

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    ConfigCodes(String code) {
        this.code = code;
        this.value = ConfigManager.getValue(this);
    }

    public String toString() {
        return getValue();
    }
}
