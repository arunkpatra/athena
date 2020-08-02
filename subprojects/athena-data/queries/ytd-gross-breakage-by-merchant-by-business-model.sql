-- Get breakages for a merchant by business model
-- Description: For a given merchant, get breakages by business model, e.g Open Loop, Closed Loop etc.

-- Verified: OK

select business_model, sum(breakage) as merchant_breakage_by_business_model
        from (select  P.tx_value                        as purchase_value,
                   R.total_redeemed                  as redeemed_value,
                   (purchase_value - redeemed_value) as breakage,
                   P.gc_business_model               as business_model
            from (select gc_uuid, merchant_code, tx_value, card_type.gc_business_model
                  from transaction, card_type
                  where tx_type = 'PURCHASE'
                    and transaction.merchant_code = 'M-0001'
                     and transaction.gc_type_code = card_type.gc_type_code) P,  -- Purchase value on card instance
                 (select gc_uuid, sum(tx_value) as total_redeemed
                  from transaction
                  where tx_type = 'REDEMPTION'
                    and transaction.merchant_code = 'M-0001'
                  group by gc_uuid) R,                                  -- Redeemed value on card instance
                 (select gc_uuid
                  from card
                  where gc_expiry_date < current_date
                    and (CURRENT_DATE::date - gc_expiry_date) < 365) E, -- Expired card instances in this year (YTD)
                 card                                                   -- card instances
            where P.gc_uuid = E.gc_uuid
              and E.gc_uuid = card.gc_uuid
              and E.gc_uuid = R.gc_uuid) MB
      group by business_model;


