-- Top grossing merchants in last 6 months

-- Verified: OK
select merchant.merchant_name, gross_merchant_sale
from (select sum(tx_value) as gross_merchant_sale, merchant_code
      from transaction
      where tx_type = 'PURCHASE'
        and (CURRENT_DATE::date - tx_date) < 180
      group by merchant_code) MV,
     merchant
where MV.merchant_code = merchant.merchant_code
order by MV.gross_merchant_sale desc;
