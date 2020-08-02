/*
 * MIT License
 *
 * Copyright (c) 2020 Arun Patra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

    public static final String TOP_GROSSING_MERCHANTS =
            "select merchant.merchant_name, gross_merchant_sale\n" +
                    "from (select sum(tx_value) as gross_merchant_sale, merchant_code\n" +
                    "      from transaction\n" +
                    "      where tx_type = 'PURCHASE'\n" +
                    "        and (CURRENT_DATE::date - tx_date) < 180\n" +
                    "      group by merchant_code) MV,\n" +
                    "     merchant\n" +
                    "where MV.merchant_code = merchant.merchant_code\n" +
                    "order by MV.gross_merchant_sale desc;";

    public static final String BREAKAGE_BY_MERCHANT =
            "select merchant_name, AMB.merchant_breakage, CURRENT_DATE as as_of_date\n" +
                    "from (select merchant_code, sum(breakage) as merchant_breakage\n" +
                    "      from (select P.merchant_code                   as merchant_code,\n" +
                    "                   P.tx_value                        as purchase_value,\n" +
                    "                   R.total_redeemed                  as redeemed_value,\n" +
                    "                   (P.tx_value - R.total_redeemed) as breakage\n" +
                    "            from (select gc_uuid, merchant_code, tx_value\n" +
                    "                  from transaction\n" +
                    "                  where tx_type = 'PURCHASE') P,                        \n" +
                    "                 (select gc_uuid, sum(tx_value) as total_redeemed\n" +
                    "                  from transaction\n" +
                    "                  where tx_type = 'REDEMPTION'\n" +
                    "                  group by gc_uuid) R,                                  \n" +
                    "                 (select gc_uuid\n" +
                    "                  from card\n" +
                    "                  where gc_expiry_date < current_date\n" +
                    "                    and (CURRENT_DATE::date - gc_expiry_date) < 365) E, \n" +
                    "                 card                                                   \n" +
                    "            where P.gc_uuid = E.gc_uuid\n" +
                    "              and E.gc_uuid = card.gc_uuid\n" +
                    "              and E.gc_uuid = R.gc_uuid) MB\n" +
                    "      group by merchant_code) AMB,\n" +
                    "     merchant\n" +
                    "where AMB.merchant_code = merchant.merchant_code\n" +
                    "order by AMB.merchant_breakage desc;";

    public static final String BREAKAGE_BY_CARD =
            "select CC.gc_name, CC.gc_type_code card_type_code,\n" +
                    "       BB.total_purchase_value as ytd_purchase_value,\n" +
                    "       BB.total_breakage_value as ytd_breakage, (BB.total_breakage_value / BB.total_purchase_value)* 100 as breakage_rate,\n" +
                    "       CURRENT_DATE as as_of_date\n" +
                    "from (select ACB.card_type_code, sum(ACB.purchase_value) as total_purchase_value, sum(ACB.breakage) as total_breakage_value\n" +
                    "      from (select card.gc_uuid,\n" +
                    "                   card.gc_type_code                 as card_type_code,\n" +
                    "                   P.tx_value                        as purchase_value,\n" +
                    "                   R.total_redeemed                  as redeemed_value,\n" +
                    "                   (P.tx_value - R.total_redeemed) as breakage\n" +
                    "            from (select gc_uuid, tx_value from transaction where tx_type = 'PURCHASE') P, \n" +
                    "                 (select gc_uuid, sum(tx_value) as total_redeemed\n" +
                    "                  from transaction\n" +
                    "                  where tx_type = 'REDEMPTION'\n" +
                    "                  group by gc_uuid) R, \n" +
                    "                 (select gc_uuid\n" +
                    "                  from card\n" +
                    "                  where gc_expiry_date < current_date\n" +
                    "                    and (CURRENT_DATE::date - gc_expiry_date) < 365) E, \n" +
                    "                 card \n" +
                    "            where P.gc_uuid = E.gc_uuid\n" +
                    "              and E.gc_uuid = card.gc_uuid\n" +
                    "              and E.gc_uuid = R.gc_uuid) ACB, \n" +
                    "           card_type C \n" +
                    "      where C.gc_type_code = ACB.card_type_code\n" +
                    "      group by ACB.card_type_code) BB, \n" +
                    "     card_type CC \n" +
                    "where BB.card_type_code = CC.gc_type_code\n" +
                    "order by BB.total_breakage_value desc;";

    public static final String HISTORICAL_BREAKAGE_RATE_BY_CARD =
            "select * from historical_breakage_rate WHERE gc_type_code = ?;";

    public static final String SALES_THIS_YEAR_BY_CARD =
            "select gc_name, V.gc_type_code, total_sales, date_part('year', CURRENT_DATE) as year\n" +
                    "from (select sum(tx_value) as total_sales, gc_type_code\n" +
                    "      from transaction\n" +
                    "      where tx_type = 'PURCHASE' and date_part('year', tx_date) = date_part('year', CURRENT_DATE) and gc_type_code = ?\n" +
                    "      group by gc_type_code\n" +
                    "      order by total_sales desc) V,\n" +
                    "     card_type\n" +
                    "where V.gc_type_code = card_type.gc_type_code\n" +
                    "order by V.total_sales desc;";

    public static final String VALUE_ON_CARDS_BY_CUSTOMER =
            "select CT.gc_name, P.card_id, C.gc_expiry_date, purchase_value, redeemed_value, (purchase_value - redeemed_value) as unredeemed   from\n" +
                    "    (select sum(transaction.tx_value) as purchase_value, card.gc_uuid as card_id from transaction, card\n" +
                    "        where transaction.tx_type = 'PURCHASE' and\n" +
                    "           transaction.gc_uuid = card.gc_uuid and\n" +
                    "           (CURRENT_DATE::date - transaction.tx_date) < 365 and\n" +
                    "           transaction.customer_id = ?\n" +
                    "    group by card.gc_uuid) P,\n" +
                    "    (select sum(transaction.tx_value) as redeemed_value, card.gc_uuid as card_id from transaction, card\n" +
                    "    where transaction.tx_type = 'REDEMPTION' and\n" +
                    "            transaction.gc_uuid = card.gc_uuid and\n" +
                    "            transaction.customer_id = ?\n" +
                    "    group by card.gc_uuid) R, card C, card_type CT\n" +
                    "where P.card_id = R.card_id and\n" +
                    "    P.card_id = C.gc_uuid and\n" +
                    "      C.gc_type_code = CT.gc_type_code\n" +
                    "order by gc_expiry_date;";

    public static final String MERCHANT_BREAKAGE_BY_BUSINESS_MODEL =
            "select business_model, sum(breakage) as merchant_breakage_by_business_model\n" +
                    "        from (select  P.tx_value                        as purchase_value,\n" +
                    "                   R.total_redeemed                  as redeemed_value,\n" +
                    "                   (P.tx_value - R.total_redeemed) as breakage,\n" +
                    "                   P.gc_business_model               as business_model\n" +
                    "            from (select gc_uuid, merchant_code, tx_value, card_type.gc_business_model\n" +
                    "                  from transaction, card_type\n" +
                    "                  where tx_type = 'PURCHASE'\n" +
                    "                    and transaction.merchant_code = ?\n" +
                    "                     and transaction.gc_type_code = card_type.gc_type_code) P, \n" +
                    "                 (select gc_uuid, sum(tx_value) as total_redeemed\n" +
                    "                  from transaction\n" +
                    "                  where tx_type = 'REDEMPTION'\n" +
                    "                    and transaction.merchant_code = ?\n" +
                    "                  group by gc_uuid) R,                     \n" +
                    "                 (select gc_uuid\n" +
                    "                  from card\n" +
                    "                  where gc_expiry_date < current_date\n" +
                    "                    and (CURRENT_DATE::date - gc_expiry_date) < 365) E, \n" +
                    "                 card                                           \n" +
                    "            where P.gc_uuid = E.gc_uuid\n" +
                    "              and E.gc_uuid = card.gc_uuid\n" +
                    "              and E.gc_uuid = R.gc_uuid) MB\n" +
                    "      group by business_model;";

    public static final String MERCHANT_BREAKAGE_BY_CARD_CATEGORY =
            "select card_category, sum(breakage) as merchant_breakage_by_card_category\n" +
                    "        from (select  P.tx_value                        as purchase_value,\n" +
                    "                   R.total_redeemed                  as redeemed_value,\n" +
                    "                   (P.tx_value - R.total_redeemed) as breakage,\n" +
                    "                   P.gc_category               as card_category\n" +
                    "            from (select gc_uuid, merchant_code, tx_value, card_type.gc_category\n" +
                    "                  from transaction, card_type\n" +
                    "                  where tx_type = 'PURCHASE'\n" +
                    "                    and transaction.merchant_code = ?\n" +
                    "                     and transaction.gc_type_code = card_type.gc_type_code) P,  \n" +
                    "                 (select gc_uuid, sum(tx_value) as total_redeemed\n" +
                    "                  from transaction\n" +
                    "                  where tx_type = 'REDEMPTION'\n" +
                    "                    and transaction.merchant_code = ?\n" +
                    "                  group by gc_uuid) R,                          \n" +
                    "                 (select gc_uuid\n" +
                    "                  from card\n" +
                    "                  where gc_expiry_date < current_date\n" +
                    "                    and (CURRENT_DATE::date - gc_expiry_date) < 365) E, \n" +
                    "                 card                                                   \n" +
                    "            where P.gc_uuid = E.gc_uuid\n" +
                    "              and E.gc_uuid = card.gc_uuid\n" +
                    "              and E.gc_uuid = R.gc_uuid) MB\n" +
                    "      group by card_category;";

}
