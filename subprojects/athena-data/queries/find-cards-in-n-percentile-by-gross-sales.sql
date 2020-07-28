-- Find gift cards in the 90 percentile in terms of YTD gross sales volumes.

-- Verified: OK
-- 1. For a given card type (e.g G-0007) what was the total YTD sale?
-- 2. Calculate percentile
select gc_name, total_sales
from (select gc_type_code, total_sales, ntile(10) over (order by total_sales desc) as percentile
      from (select gc_type_code, sum(tx_value) as total_sales
            from transaction
            where tx_type = 'PURCHASE' and (CURRENT_DATE::date - tx_date) < 365
            group by gc_type_code) S) V,
     card_type C
where V.gc_type_code = C.gc_type_code
  AND percentile = 1
order by total_sales desc;
