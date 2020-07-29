package org.athena.api.queries;

public class NativeQueries {

    public static final String TOP_SELLING_CARDS_QUANTITY =
            "select gc_name, sale_qty\n" +
                    "from (select count(*) as sale_qty, gc_type_code\n" +
                    "      from transaction\n" +
                    "      where tx_type = 'PURCHASE' and (CURRENT_DATE::date - tx_date) < 365\n" +
                    "      group by gc_type_code\n" +
                    "      order by sale_qty desc\n" +
                    "      limit 10) Q,\n" +
                    "     card_type\n" +
                    "where Q.gc_type_code = card_type.gc_type_code\n" +
                    "order by Q.sale_qty desc;";

    public static final String TOP_GROSSING_CARDS =
            "select gc_name, total_sales\n" +
                    "from (select sum(tx_value) as total_sales, gc_type_code\n" +
                    "      from transaction\n" +
                    "      where tx_type = 'PURCHASE' and (CURRENT_DATE::date - tx_date) < 365\n" +
                    "      group by gc_type_code\n" +
                    "      order by total_sales desc\n" +
                    "      limit 10) V,\n" +
                    "     card_type\n" +
                    "where V.gc_type_code = card_type.gc_type_code\n" +
                    "order by V.total_sales desc;";
}
