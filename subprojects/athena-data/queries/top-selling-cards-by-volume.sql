-- top 10 most selling cards by volume this year

-- Verified: OK

select gc_name, total_sales
from (select sum(tx_value) as total_sales, gc_type_code
      from transaction
      where tx_type = 'PURCHASE' and (CURRENT_DATE::date - tx_date) < 365
      group by gc_type_code
      order by total_sales desc
      limit 10) V,
     card_type
where V.gc_type_code = card_type.gc_type_code
order by V.total_sales desc;
