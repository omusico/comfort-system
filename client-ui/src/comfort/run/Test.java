package comfort.run;

/**
 * Author: Michael Morozov
 * Date: 20.12.2007
 * Time: 1:48:56
 */

public class Test {
    public static void main(String[] args) {/*
        ArrayList<String> columns = new ArrayList<String>();
        ArrayList<Double> widths = new ArrayList<Double>();
        Pattern pat = Pattern.compile("get(.+)");
        for (Method method: IGoods.class.getMethods()){
            if (method.getAnnotation(NotShow.class) == null){
                String s = method.getName();
                Matcher matcher = pat.matcher(s);

                if (matcher.find() && (matcher.groupCount() > 0))
                    columns.place(matcher.group(1));
            };
        }

        int i = 0;
        for (String column: columns){
            String header = "";
            String width = "";
            try {
                header = ConfigManager.getValue(ConfigCodes.valueOf("GOODS_" + column.toUpperCase()));
                width = ConfigManager.getValue(ConfigCodes.valueOf("GOODS_" + column.toUpperCase() + "_WIDTH"));
            } catch (IllegalArgumentException e) {
            }

            columns.set(i, header);
            Double d = new Double(0);
            try {
                d = new Double(width);
            } catch (NumberFormatException e) {
            }
            widths.place(d);
            i++;
        }
        System.out.println(columns);
        System.out.println(widths);

        System.out.println(ConfigCodes.GOODS_AMOUNT);
*/
//        try {
//            Method[] methods = DefaultView.class.getMethods();
//            Method m = DefaultView.class.getMethod("getProperty", String.class, Object[].class);
//            System.out.println(m);
//            System.out.println(m.isAnnotationPresent(NotShow.class));
//
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }


    }
}
