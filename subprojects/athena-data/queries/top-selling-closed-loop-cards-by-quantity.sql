-- top 10 most selling closed loop cards by quantity this year

-- Verified: OK
select gc_name, sale_qty, gc_business_model
from (select count(*) as sale_qty, TX.gc_type_code
      from transaction TX
      where tx_type = 'PURCHASE'
        and (CURRENT_DATE::date - tx_date) < 365
        and TX.gc_type_code in
            (select gc_type_code from card_type where gc_business_model = 'close-loop')
      group by TX.gc_type_code
      order by sale_qty desc
      limit 10) Q,
     card_type
where Q.gc_type_code = card_type.gc_type_code
order by Q.sale_qty desc;
