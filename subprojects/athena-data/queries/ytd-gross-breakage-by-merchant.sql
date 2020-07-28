-- YTD Gross breakage by merchant as of today
-- Description: Find out merchants with highest value of breakages across cards they sell.

select M.merchant_name, GMB.merchant_breakage
from (
         select MB.merchant_code, sum(MB.breakage) as merchant_breakage
         from (
                  select C.gc_merchant_code             as merchant_code,
                         total_sales,
                         total_redeemed,
                         (total_sales - total_redeemed) as breakage
                  from (select gc_type_code, sum(tx_value) as total_sales
                        from transaction
                        where tx_type = 'PURCHASE'
                        group by gc_type_code) TS,
                       (select gc_type_code, sum(tx_value) as total_redeemed
                        from transaction
                        where tx_type = 'REDEMPTION'
                        group by gc_type_code) TR,
                       (select * from card where gc_expiry_date < current_date and (CURRENT_DATE::date - gc_expiry_date) < 365) B,
                       card_type C
                  where B.gc_type_code = TS.gc_type_code
                    and B.gc_type_code = TR.gc_type_code
                    and B.gc_type_code = C.gc_type_code) MB
         group by MB.merchant_code) GMB,
     merchant M
where GMB.merchant_code = M.merchant_code
order by GMB.merchant_breakage desc;




