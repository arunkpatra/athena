-- Get unredeemed value of cards for a customer

-- Here, customer ID = '76809bcc-0e1f-4b44-8119-8a795b103678'

select CT.gc_name, P.card_id, C.gc_expiry_date, purchase_value, redeemed_value, (purchase_value - redeemed_value) as unredeemed   from
    (select sum(transaction.tx_value) as purchase_value, card.gc_uuid as card_id from transaction, card
        where transaction.tx_type = 'PURCHASE' and
           transaction.gc_uuid = card.gc_uuid and
           transaction.customer_id = '76809bcc-0e1f-4b44-8119-8a795b103678' and
           card.gc_expiry_date > current_date
    group by card.gc_uuid) P,
    (select sum(transaction.tx_value) as redeemed_value, card.gc_uuid as card_id from transaction, card
    where transaction.tx_type = 'REDEMPTION' and
            transaction.gc_uuid = card.gc_uuid and
            transaction.customer_id = '76809bcc-0e1f-4b44-8119-8a795b103678' and
            card.gc_expiry_date > current_date
    group by card.gc_uuid) R, card C, card_type CT

where P.card_id = R.card_id and
    P.card_id = C.gc_uuid and
      C.gc_type_code = CT.gc_type_code

order by gc_expiry_date;