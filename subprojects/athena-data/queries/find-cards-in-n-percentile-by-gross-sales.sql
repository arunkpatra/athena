-- Find gift cards in the 90 percentile in terms of all time gross sales volumes.

select gc_name, total_sales
    from (select gc_type_code, total_sales, ntile(10) over(order by total_sales desc) as percentile
          from (select gc_type_code, sum(tx_value) as total_sales
                from transaction where tx_type = 'PURCHASE'
                group by gc_type_code) S) V, card_type C
          where V.gc_type_code = C.gc_type_code
          AND percentile = 1
order by total_sales desc;