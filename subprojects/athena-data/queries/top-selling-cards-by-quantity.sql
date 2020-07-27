-- top 10 most selling cards by quantity

select gc_name, sale_qty
from (select count (*) as sale_qty, gc_type_code
      from transaction where tx_type = 'PURCHASE'
      group by gc_type_code
      order by sale_qty desc limit 10) Q, card_type
where Q.gc_type_code = card_type.gc_type_code
order by Q.sale_qty desc ;
