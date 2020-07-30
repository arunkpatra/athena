-- Get YTD card sales grouped by card type
select gc_name, V.gc_type_code, total_sales, date_part('year', CURRENT_DATE) as year
from (select sum(tx_value) as total_sales, gc_type_code
      from transaction
      where tx_type = 'PURCHASE'
        and date_part('year', tx_date) = date_part('year', CURRENT_DATE)
      group by gc_type_code
      order by total_sales desc) V,
     card_type
where V.gc_type_code = card_type.gc_type_code
order by V.total_sales desc;