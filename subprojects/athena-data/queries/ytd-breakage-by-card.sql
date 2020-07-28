-- YTD Breakage on cards as of today
-- Description: Find out card types which have breakages on them as of today.

-- Approach
-- 1. Which cards have expired as of today in the last one year?
-- 2. What was the total sale value of those cards grouped by card type?
-- 3. How much was redeemed on those expired cards grouped by card type?
-- 4. What's the total breakage on those card types?

select C.gc_name, total_sales, total_redeemed, (total_sales - total_redeemed) as breakage
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
where TS.gc_type_code = B.gc_type_code
    and TR.gc_type_code = B.gc_type_code
    and B.gc_type_code = C.gc_type_code
order by breakage desc;


